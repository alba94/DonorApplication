package com.enterprise.services;


import android.os.AsyncTask;
import android.util.Log;

import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Util;
import com.enterprise.responses.City;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by dlika on 6/5/2017.
 */


public class DataService {

    static RestTemplate restTemplate = Util.getRestTemplate();

    public static List<City> getAllCities() {
        try {
            return new AsyncTask<String, String, List<City>>() {
                @Override
                protected List<City> doInBackground(String... strings) {
                    ResponseEntity<List<City>> lista = restTemplate.exchange(ConfigValues.BASE_URL + "/cities/", HttpMethod.GET, null, new ParameterizedTypeReference<List<City>>() {
                    });
                    return lista.getBody();
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