package com.portfolio.crunchit.business;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.portfolio.crunchit.Abstract.Item;
import com.portfolio.crunchit.Abstract.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Business {


    public static FirebaseDatabase database;
    public static DatabaseReference ordersRef;
    public static DatabaseReference usersRef;
    public static DatabaseReference cartRef;
    public static FirebaseAuth mAuth;

    public static StatusMessages placeOrder(Order order){
        final StatusMessages[] status = {StatusMessages.FAILED};
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference();
        ordersRef = database.getReference();

        ordersRef.child("Orders").child(order.getOrderId()).setValue(order);
        usersRef.child("Users").child(order.placedBy).child("Orders").child(order.orderId).setValue(order.status);

        return status[0];
    }

    public static String generateOrderId(){
        database = FirebaseDatabase.getInstance();
        ordersRef = database.getReference();
        final long[] numberOfChildren = {0};
        ordersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                numberOfChildren[0] = snapshot.exists() && snapshot.hasChildren()?snapshot.getChildrenCount() + 1:0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String currentDate = getDateTime();
        return "ORDER" + currentDate + String.format("%09d", numberOfChildren[0]);
    };

    public static void addToCart(Item item){};

    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}

