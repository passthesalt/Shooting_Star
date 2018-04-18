package com.example.austinlee.grade_calculator;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit extends AppCompatActivity implements View.OnClickListener{

    private EditText enameTF;
    private EditText escoreTF;
    private EditText etotalScoreTF;
    private EditText eclassificationTF;
    private EditText eweightTF;
    private FloatingActionButton save;
    private FloatingActionButton delete;

    private int modNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        modNum = getIntent().getIntExtra("mod", -1);

        enameTF = (EditText)findViewById(R.id.enameTF);
        enameTF.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        enameTF.setText(Calc.modules.get(modNum).getName());
        escoreTF = (EditText)findViewById(R.id.escoreTf);
        escoreTF.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        escoreTF.setText(Calc.modules.get(modNum).getScore().toString());
        etotalScoreTF = (EditText)findViewById(R.id.etotalScoreTF);
        etotalScoreTF.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        etotalScoreTF.setText(Calc.modules.get(modNum).getTotalScore().toString());
        eclassificationTF = (EditText)findViewById(R.id.eclassificationTF);
        eclassificationTF.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        eclassificationTF.setText(Calc.modules.get(modNum).getClassification());
        eweightTF = (EditText)findViewById(R.id.eweightTF);
        eweightTF.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        eweightTF.setText(Calc.modules.get(modNum).getClassWeight().toString());
        save = (FloatingActionButton)findViewById(R.id.add);
        delete = (FloatingActionButton)findViewById(R.id.delete);
    }

    public void updateData(ArrayList<Module> upList) {

        Calc.arrayAdapter.clear();

        if (upList != null){

            for (Module module : upList) {
                Calc.arrayAdapter.insert(module, Calc.arrayAdapter.getCount());
            }
        }
        Calc.arrayAdapter.notifyDataSetChanged();

    }

    private void EditModule(){
        String name = enameTF.getText().toString().trim();
        String sscore = escoreTF.getText().toString().trim();
        String stotal = etotalScoreTF.getText().toString().trim();
        String classification = eclassificationTF.getText().toString().trim();
        String sweight = eweightTF.getText().toString().trim();

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

        Calc.modules.get(modNum).setName(name);
        Calc.modules.get(modNum).setScore(score);
        Calc.modules.get(modNum).setTotalScore(total);
        Calc.modules.get(modNum).setClassification(classification);
        Calc.modules.get(modNum).setClassWeight(weight);

        //updateData(Calc.modules);

        finish();
    }

    public void DeleteModule(){
        Calc.modules.remove(modNum);
        finish();
    }

    @Override
    public void onClick(View view){
        if(view == save){
            Toast.makeText(this,"This feature has yet to be implemented. Please try again next time!", Toast.LENGTH_LONG).show();
        }
        if(view == delete){
            DeleteModule();
        }
    }
}
