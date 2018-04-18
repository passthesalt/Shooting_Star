package com.example.austinlee.grade_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Curve extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference ref;
    private ArrayList<Double> allGrades = new ArrayList<>();
    private TextView rank;
    private TextView size;

    Double fGrade = 0.00;
    String cname = "";
    int standing = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curve);

        fGrade = getIntent().getDoubleExtra("finalgrade", 0.00);
        cname = getIntent().getStringExtra("className");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference(cname);

        rank = (TextView)findViewById(R.id.rank);
        size = (TextView)findViewById(R.id.size);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Grade grade = postSnapshot.getValue(Grade.class);
                    allGrades.add(grade.getGrade());
                }
                Collections.sort(allGrades);
                Collections.reverse(allGrades);
                for(int i = 0; i< allGrades.size(); i++){
                    if(fGrade.toString().contains(allGrades.get(i).toString())){
                        standing = i +1;
                        break;
                    }
                }
                rank.setText(""+ standing);
                size.setText("" + allGrades.size());

                Toast.makeText(Curve.this,"Your class standing is: "+ standing + "/" + allGrades.size(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }


}
