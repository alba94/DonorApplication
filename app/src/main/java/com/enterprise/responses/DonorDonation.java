package com.enterprise.responses;

import java.util.Date;

public class DonorDonation {

    private Donor donor;

    private Date latestDonatedDate;

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Date getLatestDonatedDate() {
        return latestDonatedDate;
    }

    public void setLatestDonatedDate(Date latestDonatedDate) {
        this.latestDonatedDate = latestDonatedDate;
    }


}