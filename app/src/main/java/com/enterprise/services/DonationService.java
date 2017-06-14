package com.enterprise.services;

import android.os.AsyncTask;
import android.util.Log;

import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Util;
import com.enterprise.requests.AreaNotifyRequest;
import com.enterprise.requests.DonationCreateRequest;
import com.enterprise.responses.Donation;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class DonationService {

    static RestTemplate restTemplate = Util.getRestTemplate();

    public static Donation createDonation(final DonationCreateRequest request) {
        try {
            return new AsyncTask<Boolean, Boolean, Donation>() {
                @Override
                protected Donation doInBackground(Boolean... booleen) {
                    HttpEntity<DonationCreateRequest> requestEntity = new HttpEntity<DonationCreateRequest>(request, Util.getHeaders());
                    Donation donation = restTemplate.postForObject(ConfigValues.BASE_URL + "/donations/", request, Donation.class);
                    return donation;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.d("Error loading", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("Error loading", e.getMessage());
        }
        return null;
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

