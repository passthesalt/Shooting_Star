package com.example.austinlee.grade_calculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.setStatusBarColor(Color.GRAY);

        Button enter = (Button)findViewById(R.id.Enter);
        enter.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent next = new Intent(MainActivity.this, LoginActivity.class);
                        startActivityForResult(next, 0);
                    }
                }
        );
    }
}
