package com.example.harshithchoudhary.caretaker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.PasswordAuthentication;

public class HomeActivity extends AppCompatActivity {

    private EditText emailmob, passwordmob;
    private Button login, servicepro;
    private TextView signup,forgotpass;
    private FirebaseAuth firebaseAuth;
    //private FirebaseUser FirebaseUser;
    private ProgressDialog progressDialog;
    private CheckBox check;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        emailmob = (EditText) findViewById(R.id.ETmail);
        passwordmob = (EditText) findViewById(R.id.ETpass);
        login = (Button) findViewById(R.id.BTlogin);
        servicepro = (Button) findViewById(R.id.BTservicepro);
        signup = (TextView) findViewById(R.id.TVsignup);
        check = (CheckBox) findViewById(R.id.CBcheck);
        forgotpass=(TextView)findViewById(R.id.TVforgotpassword);
        show_hide_pass();

      //  passwordmob.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            finish();
            startActivity(new Intent(HomeActivity.this, homenav1.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(emailmob.getText().toString(), passwordmob.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,ResetPassword.class));
            }
        });
    }

    private void validate(String emailmob, String password) {

        progressDialog.setMessage("Signing in!!");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(emailmob, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                   // Toast.makeText(HomeActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                   // startActivity(new Intent(HomeActivity.this, homenav1.class));
                     checkEmailVerification();
                } else {
                    Toast.makeText(HomeActivity.this, "Invalid Email/Password Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void show_hide_pass(){
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b){
                    // hide password
                    passwordmob.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }else{
                    // show password
                    passwordmob.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    } // end show_hide_pass

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag){
            finish();
            startActivity(new Intent(HomeActivity.this,homenav1.class));
        }else{
                Toast.makeText(this,"vetify your email",Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
            }
        }

    }


