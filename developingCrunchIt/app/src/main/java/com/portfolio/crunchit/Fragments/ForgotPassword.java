package com.portfolio.crunchit.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.portfolio.crunchit.R;


public class ForgotPassword extends DialogFragment {

    Handler mHandler = new Handler();

    public EditText emailAddress;
    public Button ConfirmButton;
    public Button cancelButton;

    FirebaseAuth mAuth;

    ForgotPassword instance;

    public ForgotPassword() {
        mAuth =  FirebaseAuth.getInstance();
        instance = this;

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailAddress = view.findViewById(R.id.forgotpassEmail);
        ConfirmButton = view.findViewById(R.id.forgotPassConfirm);
        cancelButton = view.findViewById(R.id.forgotPassCancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.dismiss();
            }
        });

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString =  emailAddress.getText().toString().trim();
                Log.e("------------------>", emailString);
                if (emailString.isEmpty()){
                    emailAddress.setError("Enter valid email");
                    return;
                }
                mAuth.sendPasswordResetEmail(emailString).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Log.e("------------------>", "Successss");
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    instance.dismiss();
                                    Toast.makeText(getContext(), "An email has been sent to the above address", Toast.LENGTH_SHORT).show();
                                }
                            }, 1000);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                instance.dismiss();
                                Toast.makeText(getContext(), "Unable to find your email. Please check credentials.", Toast.LENGTH_SHORT).show();
                            }
                        }, 1000);

                    }
                });
            }
        });
    }
}