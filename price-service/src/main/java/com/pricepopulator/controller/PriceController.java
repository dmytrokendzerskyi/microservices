package com.pricepopulator.controller;

import com.pricepopulator.model.Price;
import com.pricepopulator.model.PriceDTO;
import com.pricepopulator.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceController {

    private static final String PRICES_REL = "_prices";
    private static final String PRICE_REL = "_price";

    @Autowired
    private PriceService priceService;

    @GetMapping
    public Resources<List<Resource<Price>>> getAllPrices(){
        List allPrices = priceService.getAllPrices();
        List<Resource<Price>> resources = (List<Resource<Price>>) allPrices.stream().map(p -> new Resource<Price>((Price) p))
                        .peek(r -> ((Resource<Price>) r).add(linkTo(methodOn(this.getClass()).getPrice(((Resource<Price>) r)
                        .getContent().getId())).withRel(PRICE_REL))).collect(Collectors.toList());
        return new Resources(resources);
    }

    @GetMapping("/{id}")
    public Resource<Price> getPrice(@PathVariable("id") Long id){
        Price price = priceService.getPrice(id);
        Resource<Price> priceResource = new Resource<Price>(price);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllPrices());
        priceResource.add(linkBuilder.withRel(PRICES_REL));
        return priceResource;
    }

    @PostMapping
    public Resource<Price> createPrice(@RequestBody PriceDTO price){
        Price createdPrice = priceService.create(price);
        Resource<Price> priceResource = new Resource<Price>(createdPrice);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllPrices());
        ControllerLinkBuilder selfLinkBuilder = linkTo(methodOn(this.getClass()).getPrice(createdPrice.getId()));
        priceResource.add(linkBuilder.withRel(PRICES_REL));
        priceResource.add(selfLinkBuilder.withSelfRel());
        return priceResource;
    }

    @PutMapping("/{id}")
    public Resource<Price> updatePrice(@PathVariable("id") Long id, @RequestBody PriceDTO price){
        Price updatedPrice = priceService.update(price, id);
        Resource<Price> priceResource = new Resource<Price>(updatedPrice);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllPrices());
        ControllerLinkBuilder selfLinkBuilder = linkTo(methodOn(this.getClass()).getPrice(id));
        priceResource.add(linkBuilder.withRel(PRICES_REL));
        priceResource.add(selfLinkBuilder.withSelfRel());
        return priceResource;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePrice(@PathVariable("id") Long id){
        priceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
