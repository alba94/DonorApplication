package com.enterprise.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enterprise.requests.DonationCreateRequest;
import com.enterprise.responses.DonorDonation;
import com.enterprise.services.DonationService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewDonation extends AppCompatActivity {

    @Bind(R.id.lastDonation)
    EditText lastDonation;

    @Bind(R.id.actualDate)
    EditText actualDate;

    @Bind(R.id.id)
    EditText usrID;

    @Bind(R.id.bloodi)
    EditText bloodType;

    @Bind(R.id.ruaj)
    Button save;

    String grupGjaku;
    Integer usrId;
    DonorDonation d;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_donation);
        ButterKnife.bind(this);
        d = new DonorDonation();

        usrID.setText(d.getDonor().getIdblooddonor());
        bloodType.setText(d.getDonor().getBllodtype());

        Date lastDona = d.getLatestDonatedDate();
        String donationdata = dateFormat.format(lastDona);
        lastDonation.setText(donationdata);


        usrId = Integer.parseInt(usrID.getText().toString());
        grupGjaku = bloodType.getText().toString();

        Calendar cal = Calendar.getInstance();
        Date dt = cal.getTime();
        String actualdate = DateFormat.getDateInstance().format(dt);

        this.actualDate.setText(actualdate);
        this.actualDate.setEnabled(false);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shtoDhurim();

            }
        });

    }

    public void shtoDhurim() {
        DonationCreateRequest donation = new DonationCreateRequest();
        donation.setBloodtype(grupGjaku);
        donation.setDonorId(usrId);
        DonationService.createDonation(donation);

    }
}
