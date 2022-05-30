package com.portfolio.crunchit.Abstract;

import android.graphics.Bitmap;

public class Item {
    public String itemName;
    public String itemCost;
    public String itemDescription;
    public Bitmap itemThumbs;

    public Item(String name, String cost, String desc, Bitmap thumbnail) {
        itemCost = cost;
        itemName = name;
        itemDescription = desc;
        itemThumbs = thumbnail.copy(thumbnail.getConfig(), true);
    }

    public Item(String name, String cost, Bitmap thumbnail) {
        itemCost = cost;
        itemName = name;
        itemThumbs = thumbnail.copy(thumbnail.getConfig(), true);
    }

    public Item(String name, String cost){
        itemCost = cost;
        itemName = name;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemCost() {
        return itemCost;
    }

    public String getItemDescription(){ return itemDescription;}

    public Bitmap getItemThumbnail(){return itemThumbs;}

}
