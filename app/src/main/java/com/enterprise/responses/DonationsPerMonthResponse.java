package com.enterprise.responses;

/**
 * Created by dlika on 6/2/2017.
 */

public class DonationsPerMonthResponse {

    private long numberOfDonors;

    private Integer mounth;

    public DonationsPerMonthResponse(long numberOfDonors, Integer mounth) {
        super();
        this.numberOfDonors = numberOfDonors;
        this.mounth = mounth;
    }


    public long getNumberOfDonors() {
        return numberOfDonors;
    }

    public void setNumberOfDonors(long numberOfDonors) {
        this.numberOfDonors = numberOfDonors;
    }

    public Integer getMounth() {
        return mounth;
    }

    public void setMounth(Integer mounth) {
        this.mounth = mounth;
    }


    @Override
    public String toString() {
        return "DonationsPerMonthResponse [numberOfDonors=" + numberOfDonors + ", mounth=" + mounth + "]";
    }
}
