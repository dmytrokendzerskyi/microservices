package com.pricepopulator.client.controller;

import com.pricepopulator.client.service.PriceServiceRestTemplateClient;
import com.pricepopulator.model.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/price-client")
public class PriceController {

    @Autowired
    private PriceServiceRestTemplateClient priceServiceRestTemplateClient;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceDTO>> getAllPrices(){
        return priceServiceRestTemplateClient.getAllPrices();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDTO> getPriceById(@PathVariable("id") Long id){
        return priceServiceRestTemplateClient.getPriceById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPrice(@Valid @RequestBody PriceDTO price){
        return priceServiceRestTemplateClient.createPrice(price);
    }

    @PutMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePrice(@Valid @RequestBody PriceDTO price, @PathVariable("id") Long id){
        return priceServiceRestTemplateClient.updatePrice(price, id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePrice(@PathVariable("id") Long id){
        return priceServiceRestTemplateClient.deletePrice(id);
    }

}
