package com.enterprise.ServerAccess;

/**
 * Created by dlika on 6/2/2017.
 */

public interface DonationService {

    boolean notifyDonor(String qrCode, String tokenfromSession);

    boolean notifyArea(String city, String bloodType, String tokenfromSession);

    boolean monthlyReport(String bloodType, int nrDonors, int month, String tokenfromSession);

}
