package com.thushan.ecommerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.thushan.ecommerce.R;

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail,inputPassword;
    private FirebaseAuth auth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth= FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null) {
            startActivity(new Intent(LoginActivity.this, OnBoardingActivity.class));
            finish();
        }
        inputEmail=findViewById(R.id.email);
        inputPassword=findViewById(R.id.password);
        progressBar=findViewById(R.id.progressBar);

    }

    public void goToSignUp(View view) {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    public void goToLogin(View view) {

        progressBar.setVisibility(View.VISIBLE);

        String userEmail = inputEmail.getText().toString();
        String userPassword = inputPassword.getText().toString();

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Enter Email Address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.length() < 8) {
            Toast.makeText(this, "Password too short, enter minimum 8 character!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);

                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,OnBoardingActivity.class));
                        }else{
                               Toast.makeText(LoginActivity.this,"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                           }

                    }
                });
    }
}