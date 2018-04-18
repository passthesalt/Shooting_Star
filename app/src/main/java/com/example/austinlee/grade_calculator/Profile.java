package com.example.austinlee.grade_calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private Button logout;
    private Button cont;
    private TextView prof;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        user = mAuth.getCurrentUser();
        prof = (TextView)findViewById(R.id.loggedin);
        prof.setText(user.getEmail());
        logout = (Button)findViewById(R.id.logout);
        cont = (Button)findViewById(R.id.cont);
    }

    @Override
    public void onClick(View view){
        if(view == logout){
            mAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        if(view == cont){
            startActivity(new Intent(getApplicationContext(), Calc.class));
        }
    }
}
