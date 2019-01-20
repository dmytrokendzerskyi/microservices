package com.pricepopulator.service;

import com.pricepopulator.rabbitmq.QueueProducer;
import com.pricepopulator.exception.Error;
import com.pricepopulator.exception.PriceNotFoundException;
import com.pricepopulator.exception.PriceNotFoundWarning;
import com.pricepopulator.model.Price;
import com.pricepopulator.model.PriceDTO;
import com.pricepopulator.model.PriceMessage;
import com.pricepopulator.repository.PriceRepositoryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    private PriceRepositoryResource priceRepository;
    @Autowired
    private QueueProducer queueProducer;

    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Price getPrice(Long id) {
        return Optional.ofNullable(priceRepository.findOne(id)).orElseThrow(() ->
                new PriceNotFoundException(Error.ErrorMessage.NOT_FOUND_ERROR_MESSAGE.concat(id.toString())));
    }

    @Override
    public Price create(PriceDTO price) {
        Price createdPrice = priceRepository.saveAndFlush(new Price(price.getPrice(), price.getName()));
        queueProducer.produce(new PriceMessage(PriceMessage.MessageType.CREATE, createdPrice));
        return createdPrice;
    }

    @Override
    public Price update(PriceDTO price, Long id) {
        Price updatedPrice = Optional.ofNullable(priceRepository.findOne(id)).orElseThrow(() ->
                new PriceNotFoundException(Error.ErrorMessage.NOT_FOUND_ERROR_MESSAGE.concat(id.toString())));
        updatedPrice.setName(price.getName());
        updatedPrice.setPrice(price.getPrice());
        priceRepository.saveAndFlush(updatedPrice);
        queueProducer.produce(new PriceMessage(PriceMessage.MessageType.CREATE, updatedPrice));
        return updatedPrice;
    }

    @Override
    public void delete(Long id) {
        Price price = Optional.ofNullable(priceRepository.findOne(id)).orElseThrow(() ->
                new PriceNotFoundWarning(Error.ErrorMessage.NOT_FOUND_ERROR_MESSAGE.concat(id.toString())));
        priceRepository.delete(price);
        queueProducer.produce(new PriceMessage(PriceMessage.MessageType.DELETE, price));
    }

}
