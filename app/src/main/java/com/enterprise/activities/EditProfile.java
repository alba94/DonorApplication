package com.enterprise.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditProfile extends AppCompatActivity {

    @Bind(R.id.name)
    EditText nameEditText;

    @Bind(R.id.lastname)
    EditText lastnameEditText;

    @Bind(R.id.username)
    EditText usernameEditText;

    @Bind(R.id.password)
    EditText passwordEditText;

    @Bind(R.id.email)
    EditText emailEditText;

    @Bind(R.id.button)
    Button ruaj_tedhenat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ruaj_tedhenat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
