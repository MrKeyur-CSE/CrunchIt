package com.portfolio.crunchit;

public class Item {
    public String itemName;
    public String itemCost;

    public Item(String name, String cost) {
        itemCost = cost;
        itemName = name;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemCost() {
        return itemCost;
    }

}
