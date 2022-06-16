package com.portfolio.crunchitWarehouse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Catagory extends AppCompatActivity {

    FloatingActionButton addCatagory;
    AlertDialog dialog;
    LinearLayout layout;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);

        backBtn = (ImageView) findViewById(R.id.backBtn);
        addCatagory = findViewById(R.id.addCatagory);
        layout = findViewById(R.id.cardCatagory);
        
        buildDialog();

        addCatagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Catagory.this, "add Button Clicked.", Toast.LENGTH_LONG).show();
                dialog.show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Catagory.this, MainActivity.class));
            }
        });
    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.activity_addcatagory, null);

        EditText name = view.findViewById(R.id.nameCatagory);

        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addCard(name.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        dialog = builder.create();
    }

    private void addCard(String name) {
        View view = getLayoutInflater().inflate(R.layout.activity_catgorycard, null);
        TextView catagoryName = view.findViewById(R.id.catagoryName);
        Button deletebtn = view.findViewById(R.id.deleteBtn);

        catagoryName.setText(name);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.removeView(view);
            }
        });

        layout.addView(view);
    }
}