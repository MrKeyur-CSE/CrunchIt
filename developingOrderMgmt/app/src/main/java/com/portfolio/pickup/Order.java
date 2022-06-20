package com.portfolio.pickup;

import java.util.List;

public class Order {
    public String orderId;
    public String placedBy;
    public String status;

    public Order(){}

    public Order(String id, String who, String state){
        orderId = id;
        placedBy = who;
        status = state;

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



}
