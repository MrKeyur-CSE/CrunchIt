package com.portfolio.crunchit.Abstract;

import java.util.List;

public class Order {
    public String orderId;
    public String placedBy;
    public List<Item> itemsInOrder;

    public Order(){

    }

    public Order(String id, String who, List<Item> items){
        setOrderId(id);
        setPlacedBy(who);
        setItemsInOrder(items);

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

    public List<Item> getItemsInOrder() {
        return itemsInOrder;
    }

    public void setItemsInOrder(List<Item> itemsInOrder) {
        this.itemsInOrder = itemsInOrder;
    }

}
