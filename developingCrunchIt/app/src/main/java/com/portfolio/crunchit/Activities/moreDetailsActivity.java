package com.portfolio.crunchit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.portfolio.crunchit.R;

public class moreDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DataSnapshot listSnapshotReturn;
    DatabaseReference databaseRefRoot;
    DatabaseReference databaseRefInventory;

    FirebaseStorage storageForThumbs;
    StorageReference storageReference;
    StorageReference thumbs;

    ImageView itemImage;
    TextView itemNameTV;
    TextView itemPriceTV;
    TextView itemDescriptionTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        Intent intent = getIntent();

        itemImage = findViewById(R.id.image);
        itemNameTV = findViewById(R.id.itemName);
        itemPriceTV = findViewById(R.id.itemPrice);
        itemDescriptionTV = findViewById(R.id.itemDescription);


        String itemId = intent.getStringExtra("CurrentItem").toString();
        
        database = FirebaseDatabase.getInstance();
        
        databaseRefRoot = database.getReference("Items/"+itemId);
        databaseRefInventory = databaseRefRoot;

        databaseRefInventory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String itemId = snapshot.getKey().toString();
                Log.e("------------------->", itemId);
                String itemName = snapshot.child("itemName").getValue().toString();
                String itemCost = snapshot.child("itemCost").getValue().toString();
                String itemDescription = snapshot.child("itemDescription").getValue() != null?snapshot.child("itemDescription").getValue().toString():"No description found";

                itemImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.loginimg));
                itemNameTV.setText(itemName);
                itemPriceTV.setText(itemCost + " CAD");
                itemDescriptionTV.setText(itemDescription);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}