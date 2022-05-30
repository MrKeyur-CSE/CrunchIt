package com.portfolio.crunchit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    TextView itemName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        itemName = findViewById(R.id.itemName);

        Intent intent = getIntent();

        itemName.setText(intent.getStringExtra("CurrentItem").toString());
    }
}