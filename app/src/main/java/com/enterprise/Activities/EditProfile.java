package com.enterprise.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.enterprise.session.SessionManager;

import org.robospring.RoboSpring;
import org.springframework.beans.factory.annotation.Autowired;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EditProfile extends AppCompatActivity {

    @Bind(R.id.username)
    EditText nameEditText;

    @Bind(R.id.phone)
    EditText phoneEditText;

    @Bind(R.id.email)
    EditText emailEditText;

    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Autowired
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        RoboSpring.autowire(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}

