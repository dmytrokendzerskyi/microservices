package com.pricepopulator.controller;

import com.pricepopulator.model.Price;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private List<Price> prices;

    @PostConstruct
    private void setUpPrices(){
        prices = new ArrayList<>();
        prices.add(new Price(Long.valueOf(1),2000,"engine"));
        prices.add(new Price(Long.valueOf(2),300,"panel"));
        prices.add(new Price(Long.valueOf(3),100,"wheel"));
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Price>> getAllPrices(){
        return ResponseEntity.ok(prices);
    }

    @GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Price> getPriceById(@PathVariable("id") final Long id){
        Price price = prices.stream().filter(pr -> pr.getId().equals(id)).findFirst().get();
        return ResponseEntity.ok(price);
    }

}
