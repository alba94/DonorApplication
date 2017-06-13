package com.enterprise.requests;

/**
 * Created by dlika on 6/5/2017.
 */

public class DonationCreateRequest {
    private String bloodtype;
    private int donorId;

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }
}
