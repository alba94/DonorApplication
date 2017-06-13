package com.enterprise.donorapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    EditText emri;
    EditText mbiemri;
    EditText email;
    EditText gjaku;
    EditText ditelindja;
    EditText qyteti;
    EditText adresa;
    EditText telefon;
    EditText personalNum;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        emri = (EditText) findViewById(R.id.emri);
        emri.setEnabled(false);
        mbiemri = (EditText) findViewById(R.id.mbiemri);
        mbiemri.setEnabled(false);
        email = (EditText) findViewById(R.id.email);
        email.setEnabled(false);
        gjaku = (EditText) findViewById(R.id.bloodType);
        gjaku.setEnabled(false);
        ditelindja = (EditText) findViewById(R.id.birthday);
        ditelindja.setEnabled(false);
        qyteti = (EditText) findViewById(R.id.qyteti);
        qyteti.setEnabled(false);
        adresa = (EditText) findViewById(R.id.adresa);
        adresa.setEnabled(false);
        telefon = (EditText) findViewById(R.id.tel);
        telefon.setEnabled(false);
        personalNum = (EditText) findViewById(R.id.personalNum);
        personalNum.setEnabled(false);


        add = (Button) findViewById(R.id.dhurim);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, NewDonation.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.edit) {
            item.setIcon(R.drawable.ic_check_black_24dp);
            emri.setEnabled(true);
            mbiemri.setEnabled(true);
            email.setEnabled(true);
            gjaku.setEnabled(false);
            ditelindja.setEnabled(false);
            qyteti.setEnabled(true);
            adresa.setEnabled(true);
            telefon.setEnabled(true);
            personalNum.setEnabled(false);


        }
        return super.onOptionsItemSelected(item);
    }

}
