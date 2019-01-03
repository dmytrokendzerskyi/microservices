package com.pricepopulator.client.service;

import com.pricepopulator.model.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PriceServiceRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.price.url}")
    private String url;

    public ResponseEntity getAllPrices(){
        ResponseEntity responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, null, String.class);
        return responseEntity;
    }

    public ResponseEntity getPriceById(Long id){
        ResponseEntity responseEntity = restTemplate.exchange(url+"/{id}",
                HttpMethod.GET, null, String.class, id);
        return responseEntity;
    }

    public ResponseEntity createPrice(PriceDTO price){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity(price,httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,
                requestEntity, String.class);
        return responseEntity;
    }

    public ResponseEntity updatePrice(PriceDTO price, Long id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity(price,httpHeaders);

        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());

        ResponseEntity<String> responseEntity = restTemplate.exchange(url+"/{id}",
                HttpMethod.PUT, requestEntity, String.class, params);
        return responseEntity;
    }

    public ResponseEntity deletePrice(Long id){
        ResponseEntity responseEntity = restTemplate.exchange(url+"/{id}",
                HttpMethod.DELETE, null, String.class, id);
        return responseEntity;
    }

}
