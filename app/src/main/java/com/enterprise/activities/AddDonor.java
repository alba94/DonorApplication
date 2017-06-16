package com.enterprise.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
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

                if (isTextBad(t_emri, t_mbiemri, t_telefon, t_email, t_adresa, t_personal)) {
                    Toast.makeText(getApplicationContext(), "K", Toast.LENGTH_SHORT).show();

                } else {

                    newDonor();
                    Toast.makeText(getApplicationContext(), "Dhuruesi u krijua me sukses!", Toast.LENGTH_SHORT).show();
                }
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

    public boolean isTextBad(String name, String lasname, String phone, String email, String adresa, String personalNum) {
        boolean flag = false;
        if (name == null || name.length() == 0 || name.length() < 2) {
            flag = true;
            Toast.makeText(this, "Emri smund te jete bosh",
                    Toast.LENGTH_LONG).show();
        }
        if (lasname == null || lasname.length() == 0 || lasname.length() < 2) {
            flag = true;
            Toast.makeText(this, "Mbiemri smund te jete bosh",
                    Toast.LENGTH_LONG).show();
        }
        if (adresa == null || adresa.length() == 0 || adresa.length() < 2) {
            flag = true;
            Toast.makeText(this, "Adresa smund te jete bosh",
                    Toast.LENGTH_LONG).show();
        }
        if (personalNum == null || personalNum.length() == 0 || personalNum.length() < 2) {

            Toast.makeText(this, "Numri personal smund te jete bosh",
                    Toast.LENGTH_LONG).show();
        }

        if (phone == null || phone.length() == 0 || phone.length() < 10) {
            flag = true;
            Toast.makeText(this, "Tel smund te jete bosh",
                    Toast.LENGTH_LONG).show();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            return Patterns.EMAIL_ADDRESS.matcher(email).matches();

        }

        return flag;
    }
}
