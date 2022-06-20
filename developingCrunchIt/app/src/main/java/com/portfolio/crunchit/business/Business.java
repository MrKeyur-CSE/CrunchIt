package com.portfolio.crunchit.business;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.portfolio.crunchit.Abstract.Item;
import com.portfolio.crunchit.Abstract.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Business {

    public static void placeOrder(Order order){}

    public static String generateOrderId(){
        FirebaseDatabase database;
        DatabaseReference databaseRefRoot;


        database = FirebaseDatabase.getInstance();
        databaseRefRoot = database.getReference("Orders");

        final long[] numberOfChildren = {0};
        databaseRefRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                numberOfChildren[0] = snapshot.getChildrenCount() + 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DateFormat currentDate = new SimpleDateFormat("ddmmyyyyhhmmss");
        return "ORDER" + currentDate.toString() + numberOfChildren;
    };

    public static void addToCart(Item item){};
}
