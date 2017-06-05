package com.enterprise.Services;

import com.enterprise.requests.DonationCreateRequest;
import com.enterprise.responses.Donation;

/**
 * Created by dlika on 6/2/2017.
 */

public interface DonationService {


    Donation createDonation(DonationCreateRequest request);

    boolean notifyDonor(String qrCode);

    boolean notifyArea(String city, String bloodType);

    boolean monthlyReport(String bloodType, int nrDonors, int month);

}
