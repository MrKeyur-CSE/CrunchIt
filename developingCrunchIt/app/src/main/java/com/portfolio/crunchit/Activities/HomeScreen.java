package com.portfolio.crunchit.Activities;

import static java.lang.System.out;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.portfolio.crunchit.Abstract.Order;
import com.portfolio.crunchit.Adapters.GridLayoutAdapter;
import com.portfolio.crunchit.Adapters.InventoryAdapter;
import com.portfolio.crunchit.Abstract.Item;
import com.portfolio.crunchit.R;
import com.portfolio.crunchit.business.Business;
import com.portfolio.crunchit.business.StatusMessages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeScreen extends AppCompatActivity {

    ArrayList<Item> listOfItems;
    ArrayList<Item> listOfCategories;
    InventoryAdapter itemSelectorAdapter;
    RecyclerView.LayoutManager itemSelectorLayoutManager;

    GridLayoutAdapter buttonGridAdapter;
    public RecyclerView inventoryRecyclerView;
    public BottomNavigationView homeScreenNavBar;
    public GridView catButtonGrid;

    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DataSnapshot listSnapshotReturn;
    DatabaseReference databaseRefRoot;
    DatabaseReference databaseRefInventory;

    FirebaseStorage storageForThumbs;
    StorageReference storageReference;
    StorageReference thumbs;
    final Bitmap[] finalImage = new Bitmap[1];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseRefRoot = database.getReference("Items");
        databaseRefInventory = databaseRefRoot;

        inventoryRecyclerView = findViewById(R.id.ListOfProducts);

        catButtonGrid = findViewById(R.id.ButtonGrid);

        homeScreenNavBar = findViewById(R.id.homeScreenNavbar);

        storageForThumbs = FirebaseStorage.getInstance();
        storageReference = storageForThumbs.getReference();

        listOfItems = new ArrayList<Item>();
        listOfCategories = new ArrayList<Item>();


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
                    String itemId = ds.getKey().toString();
                    String itemName = ds.child("itemName").getValue().toString();
                    String itemCost = ds.child("itemCost").getValue().toString();
                    String itemDescription = ds.child("itemDescription").getValue() != null?ds.child("itemDescription").getValue().toString():"No description found";
                    String key = ds.getKey();
                    //String imgUrl = "images/" + itemName +"/"+ ds.child("thumbUrl").getValue().toString();//
                    String imgUrl = "images/Muruku/image.png";
                    Bitmap referenceThumb = downloadThumbnail(imgUrl);
                    referenceThumb.copy(downloadThumbnail(imgUrl).getConfig(), true);
                    Log.e(" Item---------------- ", key);
                    Log.e(" Url----------------- ", imgUrl);
                    Item temp = new Item(itemId, itemName, itemCost, itemDescription, downloadThumbnail(imgUrl));
                    listOfItems.add(temp);
                }
                itemSelectorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Constants.GENERIC_ERROR_MSG", Toast.LENGTH_SHORT).show();

            }

        };

        itemSelectorAdapter.setOnItemClickListener(new InventoryAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent moreInfoIntent = new Intent(getApplicationContext(), moreDetailsActivity.class);
                moreInfoIntent.putExtra("CurrentItem", listOfItems.get(position).itemId);
                startActivity(moreInfoIntent);
            }

            @Override
            public void onButtonTwoClick(int position) {
                Toast.makeText(getApplicationContext(), "add to cart has been clicked", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onButtonThreeClick(int position) {
                Toast.makeText(getApplicationContext(), "Instabuy has been clicked", Toast.LENGTH_SHORT).show();
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(listOfItems.get(position).itemId);
                Order toBePlaced = new Order(Business.generateOrderId(), currentUser.getUid(), temp, "PLACED");
               // Log.e("Order to be placed ----->",toBePlaced.orderId.toString());
                if(Business.placeOrder(toBePlaced) == StatusMessages.SUCCESS){
                    Toast.makeText(getApplicationContext(), "Order has been placed", Toast.LENGTH_SHORT).show();
                }
            }
        });



        databaseRefInventory.addValueEventListener(inventoryListener);
        itemSelectorAdapter.notifyDataSetChanged();


        databaseRefRoot = database.getReference("Categories");
        databaseRefInventory = databaseRefRoot;


        ValueEventListener gridButton = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOfCategories.clear();
                listSnapshotReturn = snapshot;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String key = ds.getKey();
                    Log.e(" Cat ----------------- ", key);
                    Item temp = new Item(ds.child("catName").getValue().toString(),
                            "cost");

                    Log.e(" temp ---------------- ", temp.itemName);
                    listOfCategories.add(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error loading buttons", Toast.LENGTH_SHORT).show();

            }

        };


        databaseRefInventory.addValueEventListener(gridButton);

        buttonGridAdapter = new GridLayoutAdapter(this,listOfCategories);
        catButtonGrid.setAdapter(buttonGridAdapter);
        homeScreenNavBar.setSelectedItemId(R.id.homeNavBar);
        homeScreenNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.homeNavBar: break;
                    case R.id.searchNavBar:startActivity(new Intent(getApplicationContext(), SearchScreen.class)); break;
                    case R.id.cartNavBar:startActivity(new Intent(getApplicationContext(), CartActivity.class)); break;
                    case R.id.accountNavBar: startActivity(new Intent(getApplicationContext(), accountScreen.class)); break;

                }
                return false;
            }
        });
    }



    Bitmap downloadThumbnail(String url){

        thumbs = storageReference.child(url);
        Log.e("------------->", thumbs.toString());
        final long MAX_BYTES = 512 * 512;

        thumbs.getBytes(MAX_BYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                finalImage[0] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //finalImage[0] = BitmapFactory.decodeResource(getResources(), R.drawable.loginimg);
                Toast.makeText(getApplicationContext(), "Firebase image could not be downloaded", Toast.LENGTH_SHORT).show();
            }
        });

        if (finalImage[0] == null)
                finalImage[0] = BitmapFactory.decodeResource(getResources(), R.drawable.loginimg);

        return finalImage[0];
    }

}