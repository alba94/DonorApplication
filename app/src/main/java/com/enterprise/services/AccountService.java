package com.enterprise.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Util;
import com.enterprise.requests.LoginRequest;
import com.enterprise.requests.UserUpdateRequest;
import com.enterprise.responses.SessionObject;
import com.enterprise.responses.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

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

    public boolean login(final LoginRequest request) {
        SessionObject token = restTemplate.postForObject(ConfigValues.BASE_URL + "/login", request, SessionObject.class);
        return setLoggined(token);
    }

    public static User updateUser(final int id,final UserUpdateRequest request)
    {
        try {
            return new AsyncTask<Boolean, Boolean, User>() {
                @Override
                protected User doInBackground(Boolean... booleen) {
                    HttpEntity<UserUpdateRequest> requestEntity = new HttpEntity<UserUpdateRequest>(request, Util.getHeaders());
                    User user = restTemplate.postForObject(ConfigValues.BASE_URL + "/users/"+id, request, User.class);
                    return user;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            Log.d("Error loading", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("Error loading", e.getMessage());
        }
        return null;
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
        String jsonInString = pref.getString("session", null);
        SessionObject sessionObject = null;
        if (jsonInString != null) {
            try {
                sessionObject = mapper.readValue(jsonInString, SessionObject.class);
                Log.i("ok parsing", sessionObject.toString());
            } catch (IOException e) {
                Log.i("Error parsing", "");
            }
        }
        return sessionObject;
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }


}