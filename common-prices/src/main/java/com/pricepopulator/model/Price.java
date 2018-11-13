package com.pricepopulator.model;

public class Price {

    private Long id;
    private Integer price;
    private String name;

    public Price(Long id, Integer price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public Price() {
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
