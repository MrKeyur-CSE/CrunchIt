package com.example.crunchit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationScreen extends AppCompatActivity {

    public Button registerNewUserBtn;
    public EditText userFullNameEdtTxt;
    public EditText emailEdtTxt;
    public EditText phNumberEdtTxt;
    public EditText pwordEdtTxt;
    public EditText pwordConfrmEdtTxt;
    public TextView loginLabel;



    private FirebaseAuth mAuth;
    String email = "", password = "", rePassword = "", fullName = "", phNumber = "", emailAddress = "";
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        userFullNameEdtTxt = (EditText) findViewById(R.id.fullName);
        emailEdtTxt = (EditText) findViewById(R.id.emailAddress);
        phNumberEdtTxt = (EditText) findViewById(R.id.phoneNumber);
        pwordEdtTxt = (EditText) findViewById(R.id.password);
        pwordConfrmEdtTxt = (EditText) findViewById(R.id.rePassword);

        loginLabel = (TextView) findViewById(R.id.notRegisteredLogin);

        loginLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Test Toast", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), LoginScreen.class));

            }
        });

        registerNewUserBtn = (Button) findViewById(R.id.registerBtn);

        registerNewUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fullName = userFullNameEdtTxt.getText().toString().trim();
                email = emailEdtTxt.getText().toString().trim();
                phNumber = phNumberEdtTxt.getText().toString().trim();
                password = pwordEdtTxt.getText().toString().trim();
                rePassword = pwordConfrmEdtTxt.getText().toString().trim();

                createAccount();

            }
        });
    }


    private void createAccount() {
        // [START create_user_with_email]
        if (email.isEmpty() || password.isEmpty() || fullName.isEmpty() || rePassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fields are empty...", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEdtTxt.setError("Please enter a valid email.");
                return;
            }
        }

        if(password.equals(rePassword)){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                User newUser = new User();
                                myRef.child("Users").child(user.getUid()).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(), "Sign up successful.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration Failed...", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else {
            Toast.makeText(getApplicationContext(), "Passwords don't match...", Toast.LENGTH_LONG).show();
        }

        // [END create_user_with_email]
    }


}