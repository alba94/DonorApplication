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
import android.widget.Toast;

import com.enterprise.database.DBHelper;
import com.enterprise.requests.DonorUpdateRequest;
import com.enterprise.responses.DonorDonation;
import com.enterprise.services.DonorService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    DonorDonation donor;

    boolean action = true;

    private final static String NumriPersonalExtra = "NumriPersonalExtra";
    private final static String BundleExtra = "BundleExtra";
    DBHelper db;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initComponents();
        db = new DBHelper(getApplicationContext());
        changeComponents(false);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasDonated()) {
                    Toast.makeText(getApplicationContext(), "Nuk lejohet dhurimi!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DetailActivity.this, NewDonation.class);
                    startActivity(intent);
                }
            }
        });


    }

    public boolean hasDonated() {
        Date date = this.donor.getLatestDonatedDate();
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -6);
            Date beforesix = cal.getTime();
            return date.after(beforesix);
        }
        return false;
    }


    public void initComponents() {
        Bundle extra = getIntent().getBundleExtra(BundleExtra);
        donor = DonorService.getDonorByPersonalNumber(extra.getString(NumriPersonalExtra));
        if (donor != null && donor.getDonor() != null) {
            emri.setText(donor.getDonor().getName());
            mbiemri.setText(donor.getDonor().getLastname());
            email.setText(donor.getDonor().getEmail());
            gjaku.setText(donor.getDonor().getBllodtype());
            adresa.setText(donor.getDonor().getAddress());
            telefon.setText(donor.getDonor().getPhonenumber());

        }
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
            if (action) {
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
                action = false;
            } else {

                saveDonorData();
                action = true;
            }


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
        String adresa1 = adresa.getText().toString();
        donor.setAddress(adresa1);
        String telefon1 = telefon.getText().toString();
        donor.setPhonenumber(telefon1);
        DonorService.updateDonor(this.donor.getDonor().getIdblooddonor(), donor);

    }


}
