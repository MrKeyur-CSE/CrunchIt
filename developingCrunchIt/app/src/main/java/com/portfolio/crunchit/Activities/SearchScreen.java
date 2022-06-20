package com.portfolio.crunchit.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.portfolio.crunchit.Abstract.Item;
import com.portfolio.crunchit.Adapters.GridLayoutAdapter;
import com.portfolio.crunchit.Adapters.InventoryAdapter;
import com.portfolio.crunchit.R;

import java.util.ArrayList;

public class SearchScreen extends AppCompatActivity {

    ArrayList<Item> listOfItems;
    ArrayList<Item> listOfCategories;
    InventoryAdapter itemSelectorAdapter;
    RecyclerView.LayoutManager itemSelectorLayoutManager;

    GridLayoutAdapter buttonGridAdapter;
    public RecyclerView inventoryRecyclerView;
    public BottomNavigationView homeScreenNavBar;
    public GridView catButtonGrid;
    public EditText searchBar;


    FirebaseDatabase database;
    public DataSnapshot listSnapshotReturn;
    DatabaseReference databaseRefRoot;
    DatabaseReference databaseRefInventory;

    FirebaseStorage storageForThumbs;
    StorageReference storageReference;
    StorageReference thumbs;
    final Bitmap[] finalImage = new Bitmap[1];

    String searchString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        database = FirebaseDatabase.getInstance();
        databaseRefRoot = database.getReference("Items");
        databaseRefInventory = databaseRefRoot;

        inventoryRecyclerView = findViewById(R.id.ListOfProducts);

        catButtonGrid = findViewById(R.id.ButtonGrid);

        homeScreenNavBar = findViewById(R.id.homeScreenNavbar);

        searchBar = findViewById(R.id.searchBar);

        storageForThumbs = FirebaseStorage.getInstance();
        storageReference = storageForThumbs.getReference();

        listOfItems = new ArrayList<Item>();
        listOfCategories = new ArrayList<Item>();

        databaseRefRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listSnapshotReturn = snapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        inventoryRecyclerView.setHasFixedSize(true);
        itemSelectorLayoutManager = new LinearLayoutManager(this);
        itemSelectorAdapter = new InventoryAdapter(listOfItems);
        inventoryRecyclerView.setLayoutManager(itemSelectorLayoutManager);
        inventoryRecyclerView.setAdapter(itemSelectorAdapter);


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                listOfItems.clear();
                searchString = ("" + charSequence).trim().toLowerCase();
                if (searchString == null) return;
                String imgUrl = "images/Muruku/image.png";
                for (DataSnapshot ds : listSnapshotReturn.getChildren()) {

                    Item temp = new Item(ds.child("itemName").getValue().toString(),
                            ds.child("itemCost").getValue().toString(), downloadThumbnail(imgUrl));
                    if (searchString.isEmpty())
                        listOfItems.add(temp);
                    else {
                        if (temp.itemName.toLowerCase().contains(searchString)) {
                            listOfItems.add(temp);
                        }
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                itemSelectorAdapter.notifyDataSetChanged();


            }
        });



        itemSelectorAdapter.setOnItemClickListener(new InventoryAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(SearchScreen.this, "Clicked on me -- " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onButtonTwoClick(int position) {

            }

            @Override
            public void onButtonThreeClick(int position) {

            }
        });
    }


    Bitmap downloadThumbnail(String url) {

        thumbs = storageReference.child(url);
        Log.e("------------->", thumbs.toString());
        final long MAX_BYTES = 512 * 512;

        thumbs.getBytes(MAX_BYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                finalImage[0] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                Toast.makeText(getApplicationContext(), "Images downloaded", Toast.LENGTH_SHORT).show();
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