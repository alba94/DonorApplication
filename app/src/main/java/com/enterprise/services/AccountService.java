package com.enterprise.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Constants;
import com.enterprise.Utils.Util;
import com.enterprise.responses.SessionObject;
import com.enterprise.responses.Token;
import com.enterprise.responses.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by albal on 11/02/2017.
 */


public class AccountService {

    private static SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    static RestTemplate restTemplate = Util.getRestTemplate();


    public AccountService(Context context) {
        _context = context;
        pref = _context.getSharedPreferences(ConfigValues.PREF_NAME, ConfigValues.PRIVATE_MODE);
        editor = pref.edit();
        restTemplate = Util.getRestTemplate();
    }

    public boolean login(final String username, final String password) {
        try {
            return new AsyncTask<Boolean, Boolean, Boolean>() {

                @Override
                protected Boolean doInBackground(Boolean... booleen) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setAuthorization(new HttpBasicAuthentication(ConfigValues.CLIENT_ID, ConfigValues.CLIENT_SECRET));
                    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ConfigValues.BASE_URL + "/oauth/token")
                            .queryParam(Constants.GRANT_TYPE, Constants.PASSWORD)
                            .queryParam(Constants.USERNAME, username)
                            .queryParam(Constants.PASSWORD, password);
                    HttpEntity<Token> entity = new HttpEntity<>(headers);
                    ResponseEntity<Token> token = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity, Token.class);
                    User user = restTemplate.getForObject(ConfigValues.BASE_URL + "/users/{username}", User.class, username);
                    return setLoggined(new SessionObject(token.getBody(), user));
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.i("Error loading", e.getMessage());
        } catch (ExecutionException e) {
            Log.i("Error loading", e.getMessage());
        }
        return false;
    }


    private boolean setLoggined(SessionObject sessionObject) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(sessionObject);
            editor.putString("session", jsonInString);
            editor.commit();
            Log.i("Useri ne log=", jsonInString);
            Log.d("AccountService", "User login session modified!");
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Log.i("AccountService", "User login error!");
        return false;
    }


    public static SessionObject getSessionObject() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = pref.getString("session", "null");
        SessionObject sessionObject = null;
        try {
            sessionObject = mapper.readValue(jsonInString, SessionObject.class);
            Log.i("ok parsing", sessionObject.toString());
        } catch (IOException e) {
            Log.i("Error parsing", "");
        }
        return sessionObject;
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }


}
