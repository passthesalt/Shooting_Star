package com.example.austinlee.grade_calculator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Reg extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private EditText password;
    private Button reg;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            //user already logged in
            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));
        }

        progressDialog = new ProgressDialog(this);

        email = (EditText)findViewById(R.id.enterEmailText);
        email.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);

        password = (EditText)findViewById(R.id.enterPwText);
        password.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);

        reg = (Button)findViewById(R.id.Register);
    }

    private void registerUser(){
        String emAddr = email.getText().toString().trim();
        String pw = password.getText().toString().trim();

        if(TextUtils.isEmpty(emAddr)){
            Toast.makeText(this,"Please enter an email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pw)){
            Toast.makeText(this,"Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Signing up... You will be signed in upon successful registering.");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(emAddr,pw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //user is successfully registered
                    finish();
                    startActivity(new Intent(getApplicationContext(), Calc.class));
                }
                else{
                    //user failed to register
                    Toast.makeText(Reg.this,"Failed to sign up",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view){
        if(view == reg){
            registerUser();
        }
    }
}
