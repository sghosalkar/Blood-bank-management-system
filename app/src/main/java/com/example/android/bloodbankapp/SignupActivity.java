package com.example.android.bloodbankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = (EditText) findViewById(R.id.user);
                EditText pass = (EditText) findViewById(R.id.pass);
                EditText passw = (EditText) findViewById(R.id.passw);
                EditText phone = (EditText) findViewById(R.id.et_dphone);
                EditText email = (EditText) findViewById(R.id.et_email);

                if (pass.getText().toString().equals(passw)){
                    Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i);
                } else
                    Toast.makeText(getApplicationContext(), "Signup Failed", Toast.LENGTH_LONG).show();

            }
        });
    }
    }



