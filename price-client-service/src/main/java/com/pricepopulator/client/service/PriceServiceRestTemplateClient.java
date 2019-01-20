package com.pricepopulator.client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pricepopulator.model.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class PriceServiceRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.price.url}")
    private String url;

    public ResponseEntity getAllPrices(){
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.exchange(url,
                    HttpMethod.GET, null, String.class);
        }catch (HttpStatusCodeException e){
            responseEntity = new ResponseEntity(e.getResponseBodyAsString(), e.getStatusCode());
        }
            return responseEntity;
    }

    @HystrixCommand(fallbackMethod = "getDefaultPrice")
    public ResponseEntity getPriceById(Long id){
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.exchange(url + "/{id}",
                    HttpMethod.GET, null, String.class, id);
        }catch (HttpStatusCodeException e){
            responseEntity = new ResponseEntity(e.getResponseBodyAsString(), e.getStatusCode());
        }
            return responseEntity;
    }

    private ResponseEntity getDefaultPrice(Long id){
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setName("car");
        priceDTO.setPrice(20000);
        return ResponseEntity.ok(priceDTO);
    }

    public ResponseEntity createPrice(PriceDTO price){
        ResponseEntity responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity(price,httpHeaders);
        try{
            responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        }catch (HttpStatusCodeException e){
            responseEntity = new ResponseEntity(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return responseEntity;
    }

    public ResponseEntity updatePrice(PriceDTO price, Long id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity(price,httpHeaders);

        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.exchange(url + "/{id}",
                    HttpMethod.PUT, requestEntity, String.class, params);
        }catch (HttpStatusCodeException e){
            responseEntity = new ResponseEntity(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return responseEntity;
    }

    public ResponseEntity deletePrice(Long id){
        ResponseEntity responseEntity;
        try{
            responseEntity = restTemplate.exchange(url+"/{id}",
                HttpMethod.DELETE, null, String.class, id);
        }catch (HttpStatusCodeException e){
            responseEntity = new ResponseEntity(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return responseEntity;
    }

}
