package com.portfolio.crunchit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.portfolio.crunchit.Abstract.User;
import com.portfolio.crunchit.R;

public class settingScreen extends AppCompatActivity {
    private Button signOutBtn;
    private  Button editProfileBtn;

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser currentUser;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        signOutBtn = findViewById(R.id.signOutBtn);
        editProfileBtn = findViewById(R.id.editProfileBtn);


        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoEditprofile();

            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Signout();

            }
        });




        TextView fullName, emailId, phoneNo;

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        fullName = findViewById(R.id.fullname);
        emailId = findViewById(R.id.email2);
        phoneNo = findViewById(R.id.phoneno);

        currentUser = auth.getCurrentUser();
        phoneNo.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

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
    public void gotoEditprofile(){
        Intent intent= new Intent(this, editAccountScreen.class);
        startActivity(intent);
    }

    public void Signout(){
        Intent intent= new Intent(this,LoginScreen.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Sucessfully LogOut...", Toast.LENGTH_SHORT).show();
        finish();
    }
}
