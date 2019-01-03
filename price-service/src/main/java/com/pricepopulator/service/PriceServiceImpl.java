package com.pricepopulator.service;

import com.pricepopulator.config.QueueProducer;
import com.pricepopulator.model.Price;
import com.pricepopulator.model.PriceDTO;
import com.pricepopulator.model.PriceMessage;
import com.pricepopulator.repository.PriceRepositoryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return priceRepository.findOne(id);
    }

    @Override
    public Price create(PriceDTO price) {
        Price createdPrice = priceRepository.saveAndFlush(new Price(price.getPrice(), price.getName()));
        queueProducer.produce(new PriceMessage(PriceMessage.MessageType.CREATE, createdPrice));
        return createdPrice;
    }

    @Override
    public Price update(PriceDTO price, Long id) {
        Price updatedPrice = priceRepository.findOne(id);
        updatedPrice.setName(price.getName());
        updatedPrice.setPrice(price.getPrice());
        priceRepository.saveAndFlush(updatedPrice);
        queueProducer.produce(new PriceMessage(PriceMessage.MessageType.CREATE, updatedPrice));
        return updatedPrice;
    }

    @Override
    public void delete(Long id) {
        priceRepository.delete(id);
        queueProducer.produce(new PriceMessage(PriceMessage.MessageType.DELETE, new Price(id)));
    }

}
