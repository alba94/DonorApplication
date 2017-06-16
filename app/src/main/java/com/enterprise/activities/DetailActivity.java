package com.enterprise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enterprise.requests.DonorUpdateRequest;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.emri)
    EditText emri;

    @Bind(R.id.mbiemri)
    EditText mbiemri;

    @Bind(R.id.email)
    EditText email;

    @Bind(R.id.bloodType)
    EditText gjaku;

    @Bind(R.id.birthday)
    EditText ditelindja;

    @Bind(R.id.qyteti)
    EditText qyteti;

    @Bind(R.id.adresa)
    EditText adresa;

    @Bind(R.id.tel)
    EditText telefon;

    @Bind(R.id.personalNum)
    EditText personalNum;

    @Bind(R.id.dhurim)
    Button add;
    private final static String NameExtra = "NameExtra";
    private final static String BloodExtra = "BloodExtra";
    private final static String CityExtra = "CelExtra";
    private final static String BundleExtra = "BundleExtra";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        changeComponents(false);


        Bundle extra = getIntent().getBundleExtra(BundleExtra);
        emri.setText(extra.getString(NameExtra));
        gjaku.setText(extra.getString(BloodExtra));
        qyteti.setText(extra.getString(CityExtra));


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, NewDonation.class);
                startActivity(intent);
            }
        });


    }

    private void changeComponents(boolean value) {
        emri.setEnabled(value);
        mbiemri.setEnabled(value);
        email.setEnabled(value);
        gjaku.setEnabled(value);
        ditelindja.setEnabled(value);
        qyteti.setEnabled(value);
        adresa.setEnabled(value);
        telefon.setEnabled(value);
        personalNum.setEnabled(value);
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

            saveDonorData();


        }
        return super.onOptionsItemSelected(item);
    }

    public void saveDonorData() {
        DonorUpdateRequest donor = new DonorUpdateRequest();
        String emri1 = emri.getText().toString();
        donor.setName(emri1);
        String mbiemri1 = mbiemri.getText().toString();
        donor.setLastname(mbiemri1);
        String email1 = email.getText().toString();
        donor.setEmail(email1);
        String gjaku1 = gjaku.getText().toString();
        donor.setBllodtype(gjaku1);
        String adresa1 = adresa.getText().toString();
        donor.setAddress(adresa1);
        String telefon1 = telefon.getText().toString();
        donor.setPhonenumber(telefon1);
        String personalNum1 = personalNum.getText().toString();
        donor.setPersonalnumber(personalNum1);

       /* DonationService.editDonor(donor); */

    }


}
