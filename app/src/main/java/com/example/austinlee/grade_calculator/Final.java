package com.example.austinlee.grade_calculator;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Final extends AppCompatActivity implements View.OnClickListener{

    private Double fGrade;
    private TextView finGrade;
    private Button savedb;
    private Button curve;
    private EditText className;
    private String cname;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    DatabaseReference fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        user = mAuth.getCurrentUser();
        savedb = (Button)findViewById(R.id.savedb);
        curve = (Button)findViewById(R.id.curve);

        fGrade = getIntent().getDoubleExtra("final", 0.00);
        finGrade = (TextView)findViewById(R.id.finalGrade);
        finGrade.setText(String.format("%.2f", fGrade) + "%");

        className = (EditText)findViewById(R.id.className);
        className.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
    }

    private void addGrade(){
        cname = className.getText().toString().trim();
        if(TextUtils.isEmpty(cname)){
            Toast.makeText(this,"Please enter a name for the class.", Toast.LENGTH_SHORT).show();
            return;
        }
        fin = FirebaseDatabase.getInstance().getReference(cname);
        Grade grade = new Grade(fGrade);
        fin.child(user.getUid()).setValue(grade);
    }

    @Override
    public void onClick(View view){
        if(view == savedb){
            addGrade();
        }
        if(view == curve){
            Intent next = new Intent(getApplicationContext(), Curve.class);
            next.putExtra("finalgrade", fGrade);
            next.putExtra("className", cname);
            startActivity(next);
        }
    }
}
