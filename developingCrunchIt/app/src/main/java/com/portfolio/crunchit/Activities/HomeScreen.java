package com.portfolio.crunchit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.portfolio.crunchit.Adapters.InventoryAdapter;
import com.portfolio.crunchit.Abstract.Item;
import com.portfolio.crunchit.R;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    ArrayList<Item> listOfItems;
    InventoryAdapter itemSelectorAdapter;
    RecyclerView.LayoutManager itemSelectorLayoutManager;
    public RecyclerView inventoryRecyclerView;
    FirebaseDatabase database;
    DataSnapshot listSnapshotReturn;
    DatabaseReference databaseRefRoot;
    DatabaseReference databaseRefInventory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        database = FirebaseDatabase.getInstance();
        databaseRefRoot = database.getReference("Items");
        databaseRefInventory = databaseRefRoot;

        inventoryRecyclerView = findViewById(R.id.ListOfProducts);


        listOfItems = new ArrayList<>();
        inventoryRecyclerView.setHasFixedSize(true);
        itemSelectorLayoutManager = new LinearLayoutManager(this);
        itemSelectorAdapter = new InventoryAdapter(listOfItems);
        inventoryRecyclerView.setLayoutManager(itemSelectorLayoutManager);
        inventoryRecyclerView.setAdapter(itemSelectorAdapter);

        ValueEventListener inventoryListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOfItems.clear();
                listSnapshotReturn = snapshot;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String key = ds.getKey();
                    Log.e(" -------------------- ", key);
                        Item temp = new Item(ds.child("itemName").getValue().toString(),
                                ds.child("itemCost").getValue().toString());
                        listOfItems.add(temp);
                }
                itemSelectorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Constants.GENERIC_ERROR_MSG", Toast.LENGTH_SHORT).show();

            }

        };
        databaseRefInventory.addValueEventListener(inventoryListener);
        itemSelectorAdapter.notifyDataSetChanged();

    }
}