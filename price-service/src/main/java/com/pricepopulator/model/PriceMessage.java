package com.pricepopulator.model;

public class PriceMessage {

    private MessageType messageType;
    private Price price;

    public PriceMessage(MessageType messageType, Price price) {
        this.messageType = messageType;
        this.price = price;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public enum MessageType{
        CREATE,DELETE
    }
}
