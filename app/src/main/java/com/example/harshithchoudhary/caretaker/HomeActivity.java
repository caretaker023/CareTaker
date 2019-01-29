package com.example.harshithchoudhary.caretaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private EditText emailmob , password;
    private Button login,servicepro;
    private TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupUIView();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                startActivity(new Intent(HomeActivity.this,RegistrationActivity.class));
                finish();
            }
        });
    }

private void setupUIView() {
    emailmob = (EditText) findViewById(R.id.ETmail);
    password = (EditText) findViewById(R.id.ETpass);
    login = (Button) findViewById(R.id.BTlogin);
    servicepro = (Button) findViewById(R.id.BTservicepro);
    signup = (TextView) findViewById(R.id.TVsignup);
}
}