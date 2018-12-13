package com.pricepopulator.repository;

import com.pricepopulator.model.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "price" , path = "prices")
public interface  PriceRepositoryResourse extends CrudRepository<Price, Long>{
}
