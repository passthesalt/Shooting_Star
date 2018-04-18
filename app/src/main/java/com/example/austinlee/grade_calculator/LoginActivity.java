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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email;
    private EditText password;
    private Button signIn;
    private Button signUp;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            //user already logged in
            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));
        }

        progressDialog = new ProgressDialog(this);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        email.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
        password.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);

        signIn = (Button)findViewById(R.id.signIn);
        signUp = (Button)findViewById(R.id.signUp);

    }

    private void Login(){
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

        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(emAddr, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //finish current activity and start next
                            finish();
                            startActivity(new Intent(getApplicationContext(), Profile.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Failed to sign in.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view){
        if(view == signIn){
            Login();
        }
        if(view == signUp){
            Intent next = new Intent(LoginActivity.this, Reg.class);
            startActivityForResult(next, 0);
        }
    }


}
