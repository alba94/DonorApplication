package com.enterprise.Utils;

import com.enterprise.responses.City;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by dlika on 6/8/2017.
 */

public class RestClient {

    private static RestTemplate restTemplate = Util.getRestTemplate();


    public static List<City> getAllCities() {
        ResponseEntity<List> citiesResponse = restTemplate.exchange(ConfigValues.BASE_URL + "/cities/", HttpMethod.GET, null, List.class);
        return citiesResponse.getBody();
    }


}
