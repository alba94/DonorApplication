package com.enterprise.services;

import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Constants;
import com.enterprise.requests.AreaNotifyRequest;
import com.enterprise.requests.DonationCreateRequest;
import com.enterprise.responses.Donation;
import com.enterprise.session.SessionManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.enterprise.Utils.AuthorizationUtil.getAuthorizationHeaderForAccessToken;

@Singleton
public class DonationService {


    SessionManager sessionManager;

    @Inject
    public DonationService(SessionManager sessionManager){
        this.sessionManager=sessionManager;
    }

    public Donation createDonation(DonationCreateRequest request) {
        return null;
    }

    public boolean notifyDonor(String qrCode) {

        String resourceURL = ConfigValues.BASE_URL + "/data/";
        HttpPost post = new HttpPost(resourceURL);
        post.addHeader(Constants.AUTHORIZATION,
                getAuthorizationHeaderForAccessToken(sessionManager.getToken()));
        JSONObject jsonBody = new JSONObject();
        post.addHeader("Content-type", "application/json");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        int code = -1;
        try {
            jsonBody.put("code", qrCode);
            StringEntity se = new StringEntity(jsonBody.toString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            response = httpClient.execute(post);
            code = response.getStatusLine().getStatusCode();
            if(code==200)
            {
                return true;
            }
        } catch (ClientProtocolException e) {

        } catch (IOException e) {
        }
        catch(JSONException e) {
        }
        finally {
            try {
                response.getEntity().consumeContent();
            } catch (IOException e) {
            }
        }
        return false;
    }

    public boolean notifyArea(AreaNotifyRequest request) {
        String resourceURL = ConfigValues.BASE_URL + "/areaNotify";
        HttpPost post = new HttpPost(resourceURL);
        post.addHeader(Constants.AUTHORIZATION,
                getAuthorizationHeaderForAccessToken(sessionManager.getToken()));
        JSONObject jsonBody = new JSONObject();
        post.addHeader("Content-type", "application/json");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        int code = -1;
        try {
            jsonBody.put("cityName",request.getCityName());
            jsonBody.put("bloodType",request.getBloodType());
            StringEntity se = new StringEntity(jsonBody.toString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            response = httpClient.execute(post);
            if(response!=null) {
                code = response.getStatusLine().getStatusCode();
                if (code == 200) {
                    return true;
                }
            }
        } catch (IOException |JSONException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean monthlyReport(String bloodType, int nrDonors, int month) {
        String resourceURL = ConfigValues.BASE_URL + "/monthlyreport/";

        HttpPost post = new HttpPost(resourceURL);
        post.addHeader(Constants.AUTHORIZATION,
                getAuthorizationHeaderForAccessToken(sessionManager.getToken()));
        JSONObject jsonBody = new JSONObject();
        post.addHeader("Content-type", "application/json");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        int code = -1;
        try {
            jsonBody.put("bloodType", bloodType);
            jsonBody.put("numberOfDonors", nrDonors);
            jsonBody.put("mounth", month);
            StringEntity se = new StringEntity(jsonBody.toString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            response = httpClient.execute(post);
            code = response.getStatusLine().getStatusCode();
            if(code==200)
            {
                return true;
            }
        } catch (ClientProtocolException e) {

        } catch (IOException e) {
        }
        catch(JSONException e) {
        }
        finally {
            try {
                response.getEntity().consumeContent();
            } catch (IOException e) {
            }
        }
        return false;
    }

    private Donation parseDonation(Map<String, Object> requestMap) {

        return new Donation();
    }
}

