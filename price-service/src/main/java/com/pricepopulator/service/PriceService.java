package com.pricepopulator.service;

import com.pricepopulator.model.Price;
import com.pricepopulator.model.PriceDTO;

import java.util.List;

public interface PriceService {

    List<Price> getAllPrices();

    Price getPrice(Long id);

    Price create(PriceDTO price);

    Price update(PriceDTO price, Long id);

    void delete(Long id);

}
