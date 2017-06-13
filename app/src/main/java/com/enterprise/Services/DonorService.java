package com.enterprise.services;

import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Util;
import com.enterprise.requests.DonorCreateRequest;
import com.enterprise.requests.DonorSearchFilterRequest;
import com.enterprise.responses.Donor;
import com.enterprise.session.SessionManager;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by dlika on 6/5/2017.
 */


public class DonorService {


    SessionManager sessionManager;

    static RestTemplate  restTemplate= Util.getRestTemplate();

    public List<Donor> getAllDonors() {
        ResponseEntity<List> citiesResponse =restTemplate.exchange(ConfigValues.BASE_URL + "/donors/", HttpMethod.GET, null,List.class);
        return citiesResponse.getBody();
    }

    public List<Donor> search(DonorSearchFilterRequest request) {
        return null;
    }

    public static  Donor getDonorById(int id) {
        Donor donor=restTemplate.getForObject(ConfigValues.BASE_URL+"/donor/{id}", Donor.class,id);
        return donor;
    }

    public Donor createDonor(DonorCreateRequest request) {
        HttpEntity<DonorCreateRequest> requestEntity= new HttpEntity<DonorCreateRequest>(request, Util.getHeaders());
        Donor donor=restTemplate.postForObject(ConfigValues.BASE_URL+"/donors/", request, Donor.class);
        return donor;
    }



    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }


}
