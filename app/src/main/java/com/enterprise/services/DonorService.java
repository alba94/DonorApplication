package com.enterprise.services;

import android.os.AsyncTask;
import android.util.Log;

import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Util;
import com.enterprise.requests.DonorCreateRequest;
import com.enterprise.requests.DonorUpdateRequest;
import com.enterprise.responses.DonationsPerMonthResponse;
import com.enterprise.responses.Donor;
import com.enterprise.responses.DonorDonation;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
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
                    ResponseEntity<List<Donor>> citiesResponse = restTemplate.exchange(ConfigValues.BASE_URL + "/donors/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Donor>>() {
                    });
                    return citiesResponse.getBody();
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.d("Error loading", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("Error loading", e.getMessage());
        }
        return Collections.emptyList();
    }

       public static Donor getDonorById(final int id) {
        try {
            return new AsyncTask<Boolean, Boolean, Donor>() {
                @Override
                protected Donor doInBackground(Boolean... booleen) {
                    Donor donor = restTemplate.getForObject(ConfigValues.BASE_URL + "/donors/{id}", Donor.class, id);
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

    public static DonorDonation getDonorByPersonalNumber(final String personalNumber) {
        try {
            return new AsyncTask<Boolean, Boolean, DonorDonation>() {
                @Override
                protected DonorDonation doInBackground(Boolean... booleen) {
                    DonorDonation donor = restTemplate.getForObject(ConfigValues.BASE_URL + "/donorDonation/{personalNumber}", DonorDonation.class, personalNumber);
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

    public static Donor updateDonor(final int id, final DonorUpdateRequest request)
    {
        try {
            return new AsyncTask<Boolean, Boolean, Donor>() {
                @Override
                protected Donor doInBackground(Boolean... booleen) {
                    HttpEntity<DonorUpdateRequest> requestEntity = new HttpEntity<DonorUpdateRequest>(request, Util.getHeaders());
                    Donor donor = restTemplate.postForObject(ConfigValues.BASE_URL + "/donors/"+id, request, Donor.class);
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

    public static boolean deleteDonor(final int donorId){
        try {
            return new AsyncTask<Boolean, Boolean, Boolean>() {
                @Override
                protected Boolean doInBackground(Boolean... booleen) {
                    restTemplate.delete(ConfigValues.BASE_URL + "/donors/{donorId}",donorId);
                    return true;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.d("Error loading", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("Error loading", e.getMessage());
        }
        return false;

    }


    public static List<DonationsPerMonthResponse> getStatistics(){

        try {
            return new AsyncTask<Boolean, Boolean, List<DonationsPerMonthResponse>>() {
                @Override
                protected List<DonationsPerMonthResponse> doInBackground(Boolean... booleen) {
                    ResponseEntity<List<DonationsPerMonthResponse>> report = restTemplate.exchange(ConfigValues.BASE_URL + "/monthlyreport/", HttpMethod.GET, null, new ParameterizedTypeReference<List<DonationsPerMonthResponse>>() {
                    });
                    return report.getBody();
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.d("Error loading", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("Error loading", e.getMessage());
        }
        return Collections.emptyList();

    }

}
