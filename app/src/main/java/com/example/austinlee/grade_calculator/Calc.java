package com.example.austinlee.grade_calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Calc extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private Button addModule;
    private Button calculate;
    public static ArrayList<Module> modules = new ArrayList<>();
    private ArrayList<Module> saved = new ArrayList<>();
    private ArrayList<Module> inter = new ArrayList<>();
    private Double grade = 0.00;

    public static ArrayAdapter<Module> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        addModule = (Button)findViewById(R.id.addModule);
        calculate = (Button)findViewById(R.id.calculate);

        ListView listView = (ListView)findViewById(R.id.moduleList);
        arrayAdapter = new ArrayAdapter<Module>(this, android.R.layout.simple_list_item_1, modules);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(itemClickListener);
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), Edit.class);
            intent.putExtra("mod", position);
            startActivity(intent);
        }
    };

    public void Sum(){
        Module first = modules.get(0);
        for(int i = 1; i < modules.size(); i++){
            if(first.classification.equals(modules.get(i).classification)){
                inter.add(modules.get(i));
                modules.remove(i);
                i--;
            }
        }
        inter.add(first);
        modules.remove(0);

        Double weight = first.classWeight;
        Double modScore  = 0.00;
        Double modTotScore = 0.00;
        for(int j = 0 ; j < inter.size(); j++){
            modScore+=inter.get(j).getScore();
            modTotScore+=inter.get(j).getTotalScore();
        }

        modScore = (modScore/modTotScore) * weight;
        grade+=modScore;

        for(int k = 0; k< inter.size(); k++){
            saved.add(inter.get(k));
        }
        inter.clear();

        Calculate();
    }

    public void Calculate(){
        //Calculate final grade
        if(modules.isEmpty()){
            for(int i = 0; i<saved.size(); i++){
                modules.add(saved.get(i));
            }
            saved.clear();
            Intent intent = new Intent(getApplicationContext(), Final.class);
            intent.putExtra("final", grade);
            grade = 0.00;
            startActivity(intent);
        }
        else{
            Sum();
        }
    }

    @Override
    public void onClick(View view){
        if(view == addModule){
            startActivity(new Intent(getApplicationContext(), Add.class));
        }
        if(view == calculate){
            Calculate();
        }
    }
}
