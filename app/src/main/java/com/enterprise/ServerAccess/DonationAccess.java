package com.enterprise.ServerAccess;

import com.enterprise.OAuth.OAuthConstants;

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

import static com.enterprise.OAuth.OAuthUtils.getAuthorizationHeaderForAccessToken;

public class DonationAccess{

    public static boolean notifyDonor(String qrCode,String tokenfromSession)  {

        String resourceURL = "https://mobileservices.herokuapp.com/mobileservices/data/";
        HttpPost post = new HttpPost(resourceURL);
        post.addHeader(OAuthConstants.AUTHORIZATION,
                getAuthorizationHeaderForAccessToken(tokenfromSession));
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

    public static boolean notifyArea(String city,String bloodType,String tokenfromSession)  {
        String resourceURL = "https://mobileservices.herokuapp.com/mobileservices/areaNotify";
        HttpPost post = new HttpPost(resourceURL);
        post.addHeader(OAuthConstants.AUTHORIZATION,
                getAuthorizationHeaderForAccessToken(tokenfromSession));
        JSONObject jsonBody = new JSONObject();
        post.addHeader("Content-type", "application/json");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        int code = -1;
        try {
            jsonBody.put("cityName",city);
            jsonBody.put("bloodType",bloodType);
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


    public static boolean monthlyReport(String bloodType,int nrDonors,int month,String tokenfromSession)  {

        String resourceURL = "https://mobileservices.herokuapp.com/mobileservices/monthlyreport/";
        HttpPost post = new HttpPost(resourceURL);
        post.addHeader(OAuthConstants.AUTHORIZATION,
                getAuthorizationHeaderForAccessToken(tokenfromSession));
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


}
