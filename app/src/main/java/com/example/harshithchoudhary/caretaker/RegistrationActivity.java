package com.example.harshithchoudhary.caretaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name,email,password,mobileno,age,gender;
    private Button signup;
    private TextView backtosignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //upload data to the database
                }
            }
        });
        backtosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
            }
        });

    }

    private void setupUIViews(){
        name=(EditText)findViewById(R.id.ETname);
        email=(EditText)findViewById(R.id.ETmail1);
        password=(EditText)findViewById(R.id.ETpass);
        mobileno=(EditText)findViewById(R.id.ETmobno);
        age=(EditText)findViewById(R.id.ETage);
        gender=(EditText)findViewById(R.id.ETgender);
        signup=(Button)findViewById(R.id.BTsignup);
        backtosignin=(TextView)findViewById(R.id.TVbacktosignin);
    }
    private Boolean validate(){
        Boolean result = false;

        String nameuser = name.getText().toString();
        String emailuser = email.getText().toString();
        String passworduser = password.getText().toString();
        String mobileuser = mobileno.getText().toString();
        String genderuser = gender.getText().toString();

        if(nameuser.isEmpty() && emailuser.isEmpty() && passworduser.isEmpty() && mobileuser.isEmpty() && genderuser.isEmpty()){
            Toast.makeText(this, "please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }
}
