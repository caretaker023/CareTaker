package com.example.harshithchoudhary.caretaker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class HomeActivity extends AppCompatActivity {

    private EditText emailmob , password;
    private Button login,servicepro;
    private TextView signup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        emailmob = (EditText) findViewById(R.id.ETmail);
        password = (EditText) findViewById(R.id.ETpass);
        login = (Button) findViewById(R.id.BTlogin);
        servicepro = (Button) findViewById(R.id.BTservicepro);
        signup = (TextView) findViewById(R.id.TVsignup);


        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(HomeActivity.this, SigninActivity.class ));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(emailmob.getText().toString(),password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,RegistrationActivity.class));
                finish();
            }
        });
    }

    private void validate(String userName,String Password){

        progressDialog.setMessage("SIT Tight Until VERIFIED!");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
         //           progressDialog.dismiss();
                    Toast.makeText(HomeActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HomeActivity.this, SigninActivity.class ));
                 }else
                    Toast.makeText(HomeActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}