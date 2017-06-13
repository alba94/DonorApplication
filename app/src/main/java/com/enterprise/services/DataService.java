package com.enterprise.services;


import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Util;
import com.enterprise.responses.City;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * Created by dlika on 6/5/2017.
 */


public class DataService {

    RestTemplate restTemplate = Util.getRestTemplate();

    public List<City> getAllCities() {
        ResponseEntity<List> citiesResponse = restTemplate.exchange(ConfigValues.BASE_URL + "/cities/", HttpMethod.GET, null, List.class);
        return citiesResponse.getBody();
    }
}