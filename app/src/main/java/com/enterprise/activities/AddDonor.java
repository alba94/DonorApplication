package com.enterprise.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.enterprise.requests.DonorCreateRequest;
import com.enterprise.services.DonorService;

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

    String t_ditelindja, t_emri, t_mbiemri, t_gjaku, t_qyteti, t_adresa, t_telefon, t_personal, t_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);
        ButterKnife.bind(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t_emri = emri.getText().toString();
                t_mbiemri = mbiemri.getText().toString();
                t_gjaku = gjaku.getText().toString();
                t_email = email.getText().toString();
                t_adresa = adresa.getText().toString();
                t_telefon = telefon.getText().toString();
                t_personal = personalNum.getText().toString();



                newDonor();
                Toast.makeText(getApplicationContext(), "Dhuruesi u krijua me sukses!", Toast.LENGTH_SHORT).show();

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
        DonorService.createDonor(donor);
    }

}
