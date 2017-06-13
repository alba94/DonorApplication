package com.enterprise.donorapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewDonation extends AppCompatActivity {

    EditText actualDate,nextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_donation);

        actualDate =(EditText)findViewById(R.id.actualdate);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        actualDate.setText(currentDateTimeString);
        actualDate.setEnabled(false);

        nextDate = (EditText)findViewById(R.id.nextdate);
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.MONTH, 6);
        Date nextYear = cal.getTime();
        
    }
}
