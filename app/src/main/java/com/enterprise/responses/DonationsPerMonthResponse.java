package com.enterprise.responses;

import java.util.Date;

/**
 * Created by dlika on 6/2/2017.
 */

public class DonationsPerMonthResponse {

    private long donationsNumber;

    private Date dateMonth;

    public long getDonationsNumber() {
        return donationsNumber;
    }

    public void setDonationsNumber(long donationsNumber) {
        this.donationsNumber = donationsNumber;
    }

    public Date getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(Date dateMonth) {
        this.dateMonth = dateMonth;
    }
}