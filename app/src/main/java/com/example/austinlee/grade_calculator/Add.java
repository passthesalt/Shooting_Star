package com.example.austinlee.grade_calculator;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity implements View.OnClickListener {

    private EditText nameTF;
    private EditText scoreTF;
    private EditText totalScoreTF;
    private EditText classificationTF;
    private EditText weightTF;
    private FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameTF = (EditText)findViewById(R.id.nameTF);
        nameTF.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        scoreTF = (EditText)findViewById(R.id.scoreTf);
        scoreTF.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        totalScoreTF = (EditText)findViewById(R.id.totalScoreTF);
        totalScoreTF.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        classificationTF = (EditText)findViewById(R.id.classificationTF);
        classificationTF.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        weightTF = (EditText)findViewById(R.id.weightTF);
        weightTF.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        add = (FloatingActionButton)findViewById(R.id.add);

    }

    private void AddModule(){
        String name = nameTF.getText().toString().trim();
        String sscore = scoreTF.getText().toString().trim();
        String stotal = totalScoreTF.getText().toString().trim();
        String classification = classificationTF.getText().toString().trim();
        String sweight = weightTF.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please enter a name for the assignment.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(sscore)){
            Toast.makeText(this,"Please enter a score.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(stotal)){
            Toast.makeText(this,"Please enter a total score.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(classification)){
            Toast.makeText(this,"Please enter a classification.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(sweight)){
            Toast.makeText(this,"Please enter a weight for the specified classification.", Toast.LENGTH_SHORT).show();
            return;
        }

        Double score = Double.parseDouble(sscore);
        Double total = Double.parseDouble(stotal);
        Double weight = Double.parseDouble(sweight);

        Module module = new Module(name, score, total, classification, weight);
        Calc.modules.add(module);

        finish();
    }

    @Override
    public void onClick(View view){
        if(view == add){
            AddModule();
        }
    }
}
