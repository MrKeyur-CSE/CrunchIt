package com.portfolio.crunchit.Abstract;

import android.graphics.Bitmap;

public class Item {
    public String itemName;
    public String itemCost;
    public Bitmap itemThumbs;

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

    public Bitmap getItemThumbnail(){return itemThumbs;}

}
