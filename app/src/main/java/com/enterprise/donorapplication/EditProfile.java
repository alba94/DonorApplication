package com.enterprise.donorapplication;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity {

    EditText nameEditText;
    EditText lastnameEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText emailEditText;
    Button ruaj_tedhenat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameEditText = (EditText) findViewById(R.id.name);
        lastnameEditText = (EditText) findViewById(R.id.lastname);
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        emailEditText = (EditText) findViewById(R.id.email);

        ruaj_tedhenat = (Button) findViewById(R.id.button);
        ruaj_tedhenat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
