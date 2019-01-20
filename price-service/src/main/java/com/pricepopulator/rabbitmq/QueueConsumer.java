package com.pricepopulator.rabbitmq;

import com.google.gson.Gson;
import com.pricepopulator.model.PriceMessage;
import com.pricepopulator.repository.PriceRepositoryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    private PriceRepositoryResource priceRepository;

    @Autowired
    private Gson gson;

    public void receiveMessage(String message){
        PriceMessage priceMessage = gson.fromJson(message, PriceMessage.class);
        if(priceMessage.getMessageType().equals(PriceMessage.MessageType.CREATE)){
            priceRepository.saveAndFlush(priceMessage.getPrice());
        }
        if(priceMessage.getMessageType().equals(PriceMessage.MessageType.DELETE)){
            priceRepository.delete(priceMessage.getPrice().getId());
        }
    }

}
