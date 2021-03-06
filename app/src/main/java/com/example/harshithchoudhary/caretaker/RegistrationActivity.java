package com.example.harshithchoudhary.caretaker;

import android.content.Intent;
import android.provider.Settings;
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

import java.net.NoRouteToHostException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name,email,password,mobileno,age,gender;
    private Button signup;
    private TextView backtosignin;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

       // android.provider.Settings.System.putInt(this.getContentResolver(),Settings.System.TEXT_SHOW_PASSWORD,0);

        firebaseAuth = firebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //upload data to the database

                    String emailuser = email.getText().toString().trim();
                    String passuser = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(emailuser,passuser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                               // Toast.makeText(RegistrationActivity.this, "Registration Successful",Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(RegistrationActivity.this,HomeActivity.class));
                                sendEmailVerification();
                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                            }
                    });
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
        password=(EditText)findViewById(R.id.ETpass1);
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

        if(nameuser.isEmpty() || emailuser.isEmpty() || passworduser.isEmpty() || mobileuser.isEmpty() || genderuser.isEmpty()){
            Toast.makeText(this, "please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   Toast.makeText(RegistrationActivity.this,"Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(RegistrationActivity.this,HomeActivity.class));
                    }else{
                        Toast.makeText(RegistrationActivity.this,"Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
