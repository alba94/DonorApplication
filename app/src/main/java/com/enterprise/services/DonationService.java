package com.enterprise.services;

import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Util;
import com.enterprise.requests.AreaNotifyRequest;
import com.enterprise.requests.DonationCreateRequest;
import com.enterprise.responses.Donation;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class DonationService {

    RestTemplate restTemplate = Util.getRestTemplate();

    public Donation createDonation(DonationCreateRequest request) {
        HttpEntity<DonationCreateRequest> requestEntity = new HttpEntity<DonationCreateRequest>(request, Util.getHeaders());
        Donation donation = restTemplate.postForObject(ConfigValues.BASE_URL + "/donations/", request, Donation.class);
        return donation;
    }

    public boolean notifyDonor(String qrCode) {
        return true;
    }

    public boolean notifyArea(AreaNotifyRequest request) {

        return false;
    }

    public boolean monthlyReport(String bloodType, int nrDonors, int month) {

        return false;
    }


    public List<Donation> getDonationsByDonorId(int id) {
        return null;
    }

}

