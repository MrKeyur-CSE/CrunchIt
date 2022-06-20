package com.portfolio.pickup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public FirebaseDatabase database;
    public DatabaseReference ordersRef;
    public DatabaseReference usersRef;

    RecyclerView orderView;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Order> listOfItems;
    DataSnapshot listSnapshotReturn;

    OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderView = findViewById(R.id.ListOfProducts);

        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference();
        ordersRef = database.getReference("Orders");

        listOfItems = new ArrayList<Order>();
        orderView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        orderView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(listOfItems);
        orderView.setAdapter(adapter);

        ValueEventListener inventoryListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOfItems.clear();
                listSnapshotReturn = snapshot;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String orderId = ds.getKey().toString();
                    String placedBy = ds.child("placedBy").getValue().toString();
                    String status = ds.child("status").getValue().toString();
                    String key = ds.getKey();
                    Order temp = new Order(orderId, placedBy, status);
                    listOfItems.add(temp);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };

        ordersRef.addValueEventListener(inventoryListener);

        adapter.setOnItemClickListener(new OrderAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onButtonOneClick(int position) {
                ordersRef.child(listOfItems.get(position).getOrderId()).child("status").setValue("COMPLETE");
                usersRef.child("Users").child(listOfItems.get(position).getPlacedBy()).child("Orders").child(listOfItems.get(position).orderId).setValue("COMPLETE");

            }

            @Override
            public void onButtonTwoClick(int position) {

            }

            @Override
            public void onButtonThreeClick(int position) {
                ordersRef.child(listOfItems.get(position).getOrderId()).child("status").setValue("CANCELLED");
                usersRef.child("Users").child(listOfItems.get(position).getPlacedBy()).child("Orders").child(listOfItems.get(position).orderId).setValue("CANCELLED");

            }
        });
    }
}