package com.pricepopulator.client.controller;

import com.pricepopulator.client.service.PriceServiceRestTemplateClient;
import com.pricepopulator.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/price-client")
public class PriceController {

    @Autowired
    private PriceServiceRestTemplateClient priceServiceRestTemplateClient;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Price>> getAllPrices(){
        return ResponseEntity.status(HttpStatus.OK).body(priceServiceRestTemplateClient.getAllPrices());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Price> getPriceById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(priceServiceRestTemplateClient.getPriceById(id));
    }

}
