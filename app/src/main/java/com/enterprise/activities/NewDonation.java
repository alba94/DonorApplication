package com.enterprise.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.enterprise.responses.Donation;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewDonation extends AppCompatActivity {

    @Bind(R.id.actualdate)
    EditText actualDate;

    @Bind(R.id.nextdate)
    EditText nextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_donation);
        ButterKnife.bind(this);

        Donation d = new Donation();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        Date dt = cal.getTime();

        String actualDate = DateFormat.getDateInstance().format(dt);


        this.actualDate.setText(actualDate);
        this.actualDate.setEnabled(false);

        String lastDonationDate = DateFormat.getDateInstance().format(new Date());


        if (actualDate.compareTo(lastDonationDate) > 0) {
            nextDate.setEnabled(true);

        } else {
            nextDate.setEnabled(false);
            this.nextDate.setText(DateFormat.getDateInstance().format(new Date()));
        }


    }
}
