package com.enterprise.donorapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDonor extends AppCompatActivity {

    EditText emri;
    EditText mbiemri;
    EditText email;
    EditText gjaku;
    EditText ditelindja;
    EditText qyteti;
    EditText adresa;
    EditText telefon;
    EditText personalNum;
    Button save;
    String t_ditelindja, t_emri, t_mbiemri, t_gjaku, t_qyteti, t_adresa, t_telefon, t_personal, t_email;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);

        emri = (EditText) findViewById(R.id.txt_emri);
        mbiemri = (EditText) findViewById(R.id.txt_mbiemri);
        email = (EditText) findViewById(R.id.txt_email);
        gjaku = (EditText) findViewById(R.id.txt_bloodType);
        ditelindja = (EditText) findViewById(R.id.txt_birthday);
        qyteti = (EditText) findViewById(R.id.txt_qyteti);
        adresa = (EditText) findViewById(R.id.txt_adresa);
        telefon = (EditText) findViewById(R.id.txt_tel);
        personalNum = (EditText) findViewById(R.id.txt_personalNum);

        save = (Button) findViewById(R.id.save);

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
