package com.pricepopulator.repository;

import com.pricepopulator.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepositoryResource extends JpaRepository<Price, Long> {
}
