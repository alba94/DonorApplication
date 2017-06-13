package com.enterprise.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

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

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        actualDate.setText(currentDateTimeString);
        actualDate.setEnabled(false);

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.MONTH, 6);
        Date nextYear = cal.getTime();
        
    }
}
