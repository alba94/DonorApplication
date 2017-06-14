package com.enterprise.services;

import android.os.AsyncTask;
import android.util.Log;

import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Util;
import com.enterprise.requests.DonorCreateRequest;
import com.enterprise.requests.DonorSearchFilterRequest;
import com.enterprise.responses.Donor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by dlika on 6/5/2017.
 */


public class DonorService {

    static RestTemplate restTemplate = Util.getRestTemplate();

    public static List<Donor> getAllDonors() {
        try {
            return new AsyncTask<Boolean, Boolean, List<Donor>>() {
                @Override
                protected List<Donor> doInBackground(Boolean... booleen) {
                    ResponseEntity<List> citiesResponse = restTemplate.exchange(ConfigValues.BASE_URL + "/donors/", HttpMethod.GET, null, List.class);
                    return citiesResponse.getBody();
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.d("Error loading", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("Error loading", e.getMessage());
        }
        return null;
    }

    public List<Donor> search(DonorSearchFilterRequest request) {
        return null;
    }

    public static Donor getDonorById(final int id) {
        try {
            return new AsyncTask<Boolean, Boolean, Donor>() {
                @Override
                protected Donor doInBackground(Boolean... booleen) {
                    Donor donor = restTemplate.getForObject(ConfigValues.BASE_URL + "/donor/{id}", Donor.class, id);
                    return donor;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.d("Error loading", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("Error loading", e.getMessage());
        }
        return null;
    }

    public static Donor createDonor(final DonorCreateRequest request) {
        try {
            return new AsyncTask<Boolean, Boolean, Donor>() {
                @Override
                protected Donor doInBackground(Boolean... booleen) {
                    HttpEntity<DonorCreateRequest> requestEntity = new HttpEntity<DonorCreateRequest>(request, Util.getHeaders());
                    Donor donor = restTemplate.postForObject(ConfigValues.BASE_URL + "/donors/", request, Donor.class);
                    return donor;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.d("Error loading", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("Error loading", e.getMessage());
        }
        return null;
    }

}
