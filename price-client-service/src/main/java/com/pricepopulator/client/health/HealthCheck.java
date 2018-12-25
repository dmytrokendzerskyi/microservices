package com.pricepopulator.client.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HealthCheck implements HealthIndicator {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.price.liveness}")
    private String url;

    @Override
    public Health health() {
        System.out.println("HEALTH CHECKING !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        int errorCode = check();
        if (errorCode != 200) {
            return Health.down()
                    .withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

    public int check() {
        try {
            return restTemplate.exchange(url, HttpMethod.GET,
                    null, String.class).getStatusCodeValue();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
