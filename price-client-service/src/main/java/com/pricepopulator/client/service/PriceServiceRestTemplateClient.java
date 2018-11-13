package com.pricepopulator.client.service;

import com.pricepopulator.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PriceServiceRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.price.url}")
    private String url;

    public List<Price> getAllPrices(){
        List<Price> priceList = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Price>>() {
                }).getBody();
        return priceList;
    }

    public Price getPriceById(Long id){
        Price price = restTemplate.exchange(url+"/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<Price>() {
                }, id).getBody();
        return price;
    }
}
