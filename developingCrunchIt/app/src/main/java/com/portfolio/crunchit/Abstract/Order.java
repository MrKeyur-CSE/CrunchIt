package com.portfolio.crunchit.Abstract;

import java.util.List;

public class Order {
    public String orderId;
    public String placedBy;
    public String status;
    public List<String> itemsInOrder;

    public Order(){}

    public Order(String id, String who, List<String> items, String state){
        orderId = id;
        placedBy = who;
        status = state;
        itemsInOrder = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(String placedBy) {
        this.placedBy = placedBy;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public List<String> getItemsInOrder() {
        return itemsInOrder;
    }

    public void setItemsInOrder(List<String> itemsInOrder) {
        this.itemsInOrder = itemsInOrder;
    }

}
