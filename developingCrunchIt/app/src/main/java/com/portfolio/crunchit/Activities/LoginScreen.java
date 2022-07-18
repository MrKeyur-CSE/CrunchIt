package com.portfolio.crunchit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.portfolio.crunchit.Fragments.ForgotPassword;
import com.portfolio.crunchit.Processes.NotificationManager;
import com.portfolio.crunchit.R;

public class LoginScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;

    TextView forgotPassword;

    String email = "", password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        if(mAuth.getCurrentUser() != null)
            startActivity(new Intent(LoginScreen.this, HomeScreen.class));

        final EditText mEmail = findViewById(R.id.email);
        final EditText mPass = findViewById(R.id.password);
        final Button mLoginBtn = findViewById(R.id.loginBtn);
        final TextView mNotRegister = findViewById(R.id.notRegister);
        forgotPassword = findViewById(R.id.forgotpass);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPassword temp = new ForgotPassword();
                temp.show(getSupportFragmentManager(), "Forgot Password?");
            }
        });

        mNotRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationScreen.class));
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString().trim();
                password = mPass.getText().toString().trim();
                signUp();

            }
        });
    }


    public void signUp() {
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Fields are empty...", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            DatabaseReference FCMTokenRef = db.getReference().child(mAuth.getCurrentUser().getUid()).child("fcmtokens");
                            final String[] FCMToken = {"null"};
                            FCMTokenRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    FCMToken[0] = snapshot.getValue(String.class);
                                    if(FCMToken[0] == "null"){
                                        FCMTokenRef.setValue(NotificationManager.getToken(getApplicationContext())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(getApplicationContext(), "You're in...",
                                                        Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(LoginScreen.this, HomeScreen.class));

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}