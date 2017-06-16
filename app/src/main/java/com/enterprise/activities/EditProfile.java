package com.enterprise.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enterprise.requests.UserUpdateRequest;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.enterprise.activities.R.id.username;

public class EditProfile extends AppCompatActivity {

    @Bind(R.id.name)
    EditText nameEditText;

    @Bind(R.id.lastname)
    EditText lastnameEditText;

    @Bind(username)
    EditText usernameEditText;

    @Bind(R.id.password)
    EditText passwordEditText;

    @Bind(R.id.email)
    EditText emailEditText;

    @Bind(R.id.button)
    Button ruaj_tedhenat;

    String emri, mbiemri, username1, fjalekalim, emaili;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        emri = nameEditText.getText().toString();
        mbiemri = lastnameEditText.getText().toString();
        username1 = usernameEditText.getText().toString();
        fjalekalim = passwordEditText.getText().toString();
        emaili = emailEditText.getText().toString();


        ruaj_tedhenat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editProfile();
            }
        });

    }

    public void editProfile() {

        UserUpdateRequest user = new UserUpdateRequest();
        user.setName(emri);
        user.setSurname(mbiemri);
        user.setUsername(username1);
        user.setPassword(fjalekalim);
        user.setEmail(emaili);


    }

}
