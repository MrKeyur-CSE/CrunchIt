package com.portfolio.crunchit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.portfolio.crunchit.Abstract.Item;
import com.portfolio.crunchit.R;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    public BottomNavigationView accountScreenNavBar;
    public Button checkoutButton;
    public List<Item> itemsInCart;

    FirebaseDatabase database;
    DatabaseReference databaseRefRoot;
    DatabaseReference databaseRefInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database = FirebaseDatabase.getInstance();
        databaseRefRoot = database.getReference("Orders");
        databaseRefInventory = databaseRefRoot;

        checkoutButton = (Button) findViewById(R.id.checkoutButton);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrder();
            }
        });

        accountScreenNavBar = findViewById(R.id.cartScreenNavbar);
        accountScreenNavBar.setSelectedItemId(R.id.accountNavBar);
        accountScreenNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.homeNavBar: startActivity(new Intent(getApplicationContext(), HomeScreen.class)); break;
                    case R.id.searchNavBar:startActivity(new Intent(getApplicationContext(), SearchScreen.class)); break;
                    case R.id.cartNavBar:break;
                    case R.id.accountNavBar: startActivity(new Intent(getApplicationContext(), accountScreen.class)); break;
                }
                return false;
            }
        });

    }

    private void placeOrder() {


    }
}