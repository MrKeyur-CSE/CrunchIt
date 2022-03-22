package com.portfolio.crunchit;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;

    String email = "", password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();
        final EditText mEmail = findViewById(R.id.email);
        final EditText mPass = findViewById(R.id.pass);
        final Button mLoginBtn = findViewById(R.id.loginBtn);
        final TextView mNotRegister = findViewById(R.id.notRegister);

        mNotRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationScreen.class));
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//               email = mEmail.getText().toString().trim();
//                password = mPass.getText().toString().trim();
//                signUp();

            }
        });
    }


//    public void signUp() {
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(getApplicationContext(), "You're in...",
//                                    Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(LoginScreen.this, MainActivity.class));
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(getApplicationContext(), "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
}