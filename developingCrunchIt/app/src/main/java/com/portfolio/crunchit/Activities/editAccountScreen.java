package com.portfolio.crunchit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.portfolio.crunchit.R;
import com.portfolio.crunchit.Abstract.User;

public class editAccountScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser currentUser;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_screen);

        TextView fullName, emailId, phoneNo;

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        fullName = findViewById(R.id.fullname);
        emailId = findViewById(R.id.email2);
        phoneNo = findViewById(R.id.phoneno);

        currentUser = auth.getCurrentUser();

        myRef.child("Users").child(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    User temp = task.getResult().getValue(User.class);
                    fullName.setText(temp.fullName);
                    emailId.setText(temp.email);
                    phoneNo.setText(temp.number);
                }
            }
        });
    }
    public void update(View view) {

    }
}