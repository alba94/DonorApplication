package com.enterprise.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        String emri = nameEditText.getText().toString();
        String mbiemri = lastnameEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String fjalekalim = passwordEditText.getText().toString();
        String email = emailEditText.getText().toString();


        ruaj_tedhenat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();

            }
        });

    }

    public void editProfile() {

        /*  User user = new User();
        user.setName(emri);
        user.setLasName(mbiemri);
        user.setUsername(username);
        user.setPassword(fjalekalimi);
        user.setEmail(email);


        *
        *
        *
        *
        * */
    }

    public boolean isTextBad(String name, String lasname, String username, String password, String email) {
        boolean flag = false;
        if (name == null || name.length() == 0 || name.length() > 50) {
            flag = true;
            Toast.makeText(this, "The contact name can't be blank or exceed 50 characters",
                    Toast.LENGTH_LONG).show();
        }
        if (!email.matches("^([a-z]|[A-Z]|\\d*)*@[a-z]*(\\.com)") || email == null ||
                email.length() == 0) {
            flag = true;
            Toast.makeText(this, "Email address can't be blank and needs to be valid",
                    Toast.LENGTH_LONG).show();
        }

        return flag;
    }
}
