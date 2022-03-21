package com.portfolio.crunchit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser currentUser;
    FirebaseAuth auth;

    private Button Accountsrnbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        TextView uName = findViewById(R.id.userName);
        TextView email = findViewById(R.id.email);
        TextView phone = findViewById(R.id.phNumber);

        currentUser = auth.getCurrentUser();

        myRef.child("Users").child(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    User temp = task.getResult().getValue(User.class);
                    uName.setText("Name  : " + temp.fullName);
                    email.setText("Mail  : " +temp.email);
                    phone.setText("Phone : " +temp.number);
                }
            }
        });

        Accountsrnbutton = findViewById(R.id.accountsrnBtn);
        Accountsrnbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openAccountScreen();
           }
       });
    }
    public void openAccountScreen(){
        Intent intent= new Intent(this,accountScreen.class);
        startActivity(intent);


    }
}