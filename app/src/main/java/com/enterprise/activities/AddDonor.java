package com.enterprise.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.enterprise.database.DBHelper;
import com.enterprise.requests.DonorCreateRequest;
import com.enterprise.services.DonorService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddDonor extends AppCompatActivity {

    @Bind(R.id.txt_emri)
    EditText emri;

    @Bind(R.id.txt_mbiemri)
    EditText mbiemri;

    @Bind(R.id.txt_email)
    EditText email;

    @Bind(R.id.txt_bloodType)
    AutoCompleteTextView gjaku;

    @Bind(R.id.txt_birthday)
    EditText ditelindja;

    @Bind(R.id.txt_qyteti)
    AutoCompleteTextView qyteti;

    @Bind(R.id.txt_adresa)
    EditText adresa;

    @Bind(R.id.txt_tel)
    EditText telefon;

    @Bind(R.id.txt_personalNum)
    EditText personalNum;

    @Bind(R.id.save)
    Button save;

    String t_emri,t_ditelindja, t_qyteti, t_mbiemri, t_gjaku, t_adresa, t_telefon, t_personal, t_email;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    DBHelper db;
    List<String> cities;
    List<String> bloody;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);
        ButterKnife.bind(this);

        db = new DBHelper(getApplicationContext());
        cities = db.getCity();
        bloody = db.getBloodType();

        ArrayAdapter<String> array = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, cities);
        qyteti.setThreshold(1);
        qyteti.setAdapter(array);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDonor();

            }
        });

    }

    public void newDonor() {

        DonorCreateRequest donor = new DonorCreateRequest();
        donor.setName(t_emri);
        donor.setLastname(t_mbiemri);
        donor.setBllodtype(t_gjaku);
        donor.setEmail(t_email);
        donor.setAddress(t_adresa);
        donor.setPhonenumber(t_telefon);
        donor.setPersonalnumber(t_personal);
        Date date = null;
        try {
            date = dateFormat.parse(t_ditelindja);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        donor.setBirthday(date);
        int cityId = db.getCityId(t_qyteti);
        donor.setCityId(cityId);
        DonorService.createDonor(donor);
    }

    public void addDonor() {
        t_emri = emri.getText().toString();
        t_mbiemri = mbiemri.getText().toString();
        t_gjaku = gjaku.getText().toString();
        t_email = email.getText().toString();
        t_adresa = adresa.getText().toString();
        t_telefon = telefon.getText().toString();
        t_personal = personalNum.getText().toString();
        t_ditelindja = ditelindja.getText().toString();
        t_qyteti = qyteti.getText().toString();


        if (t_emri.equals("") && t_mbiemri.equals("") && t_email.equals("") && t_gjaku.equals("") && t_adresa.equals("") && t_telefon.equals("") && t_personal.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.error_empty_field), Toast.LENGTH_LONG).show();

        } else if (t_emri.equals("")) {
            emri.setError(getString(R.string.error_empty_field));
            emri.hasFocus();
        } else if (t_mbiemri.equals("")) {
            mbiemri.setError(getString(R.string.error_empty_field));
            mbiemri.hasFocus();

        } else if (!t_email.matches(emailPattern) || t_email.equals("")) {
            email.setError(getString(R.string.invalid_email));
            email.hasFocus();

        } else if (t_gjaku.equals("")) {
            gjaku.setError(getString(R.string.error_empty_field));
            gjaku.hasFocus();
        } else if (t_adresa.equals("")) {
            adresa.setError(getString(R.string.error_empty_field));
            adresa.hasFocus();
        } else if ((t_telefon.length() < 6 && t_telefon.length() > 14) || t_telefon.equals("")) {
            telefon.setError(getString(R.string.invalid_phone));
            telefon.hasFocus();
        } else if (t_personal.equals("")) {
            personalNum.setError(getString(R.string.error_empty_field));
            personalNum.hasFocus();
        } else {
            newDonor();
            Toast.makeText(getApplicationContext(), "Dhuruesi u krijua me sukses!", Toast.LENGTH_SHORT).show();

        }


    }

}
