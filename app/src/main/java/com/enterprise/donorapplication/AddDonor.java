package com.enterprise.donorapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    EditText gjaku;

    @Bind(R.id.birthday)
    EditText ditelindja;

    @Bind(R.id.txt_qyteti)
    EditText qyteti;

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
                t_ditelindja = ditelindja.getText().toString();
                t_qyteti = qyteti.getText().toString();
                t_adresa = adresa.getText().toString();
                t_telefon = telefon.getText().toString();
                t_personal = personalNum.getText().toString();

                newDonor();
            }
        });

    }

    public void newDonor() {
        /*Donor d1 = new Donor();
        d1.setBirthday(t_ditelindja);
        d1.setAddress(t_adresa);
        d1.setBllodtype(t_gjaku);
        d1.setEmail(t_email);
        d1.setPhonenumber(t_telefon);
        d1.setLastname(t_mbiemri);
        d1.setName(t_emri);
        d1.setPersonalnumber(t_personal);
        d1.setCity(t_qyteti);

        DonorCreateRequest(d1); */
    }
}
