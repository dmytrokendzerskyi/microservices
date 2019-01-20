package com.pricepopulator.rabbitmq;

import com.google.gson.Gson;
import com.pricepopulator.model.Price;
import com.pricepopulator.model.PriceMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {

    @Value("${fanout.exchange}")
    private String fanoutExchange;
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson;
    @Autowired
    public QueueProducer(RabbitTemplate rabbitTemplate, Gson gson) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.gson = gson;
    }
    public void produce(PriceMessage priceMessage) {
        rabbitTemplate.setExchange(fanoutExchange);
        rabbitTemplate.convertAndSend(gson.toJson(priceMessage));
    }

}
