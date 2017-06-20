package com.enterprise.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.enterprise.services.AccountService;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by dlika on 6/5/2017.
 */

public class Util {


    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    public static MultiValueMap getHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", Util.getAuthorizationHeaderForAccessToken(AccountService.getSessionObject().getToken().getAccess_token()));
        headers.add("Content-Type", "application/json");
        return headers;
    }

    public static MultiValueMap getContentHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        return headers;
    }

    public static boolean checkForNet(final Context context) {
        try {
            return new AsyncTask<String, String, Boolean>() {
                ProgressDialog nDialog;

                protected void onPreExecute() {
                    super.onPreExecute();
                    nDialog = new ProgressDialog(context);
                    nDialog.setTitle("Checking Network");
                    nDialog.setMessage("Loading..");
                    nDialog.setIndeterminate(false);
                    nDialog.setCancelable(true);
                    nDialog.show();
                }

                protected Boolean doInBackground(String... args) {

                    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo netInfo = cm.getActiveNetworkInfo();
                    if (netInfo != null && netInfo.isConnected()) {
                        try {
                            URL url = new URL("http://www.google.com");
                            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                            urlc.setConnectTimeout(3000);
                            urlc.connect();
                            if (urlc.getResponseCode() == 200) {
                                return true;
                            }
                        } catch (MalformedURLException e1) {

                        } catch (IOException e) {

                        }
                    }
                    return false;
                }


            }.execute().get();
        } catch (InterruptedException e) {
            Log.d("Error", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("Error", e.getMessage());
        }
        return false;
    }


    public static String getAuthorizationHeaderForAccessToken(String accessToken) {
        return Constants.BEARER + " " + accessToken;
    }

}
