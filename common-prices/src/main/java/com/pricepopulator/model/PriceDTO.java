package com.pricepopulator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PriceDTO {

    @JsonIgnore
    private Long id;
    @Min(10)
    @Max(1000000000)
    private Integer price;
    @NotNull
    private String name;

    public PriceDTO(Long id, Integer price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public PriceDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
