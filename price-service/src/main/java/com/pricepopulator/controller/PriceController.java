package com.pricepopulator.controller;

import com.pricepopulator.model.Price;
import com.pricepopulator.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public ResponseEntity<List<Price>> getAllPrices(){
        return ResponseEntity.ok(priceService.getAllPrices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Price> getPrice(@PathVariable("id") Long id){
        return ResponseEntity.ok(priceService.getPrice(id));
    }

    @PostMapping
    public ResponseEntity<Price> createPrice(@RequestBody Price price){
        return ResponseEntity.ok(priceService.create(price));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Price> updatePrice(@PathVariable("id") Long id, @RequestBody Price price){
        return ResponseEntity.ok(priceService.update(price, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePrice(@PathVariable("id") Long id){
        priceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
