package com.enterprise.Utils;


import com.enterprise.responses.Token;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dlika on 6/2/2017.
 */

public class AuthorizationUtil {


    public static Token getAccessToken(String username, String password) {
        HttpPost post = new HttpPost(ConfigValues.BASE_URL + "/oauth/token");

        List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
        parametersBody.add(new BasicNameValuePair(Constants.GRANT_TYPE,
                "password"));
        parametersBody.add(new BasicNameValuePair(Constants.USERNAME,
                username));
        parametersBody.add(new BasicNameValuePair(Constants.PASSWORD,
                password));

        if (Util.isValid(ConfigValues.CLIENT_ID)) {
            parametersBody.add(new BasicNameValuePair(Constants.CLIENT_ID,
                    ConfigValues.CLIENT_ID));
        }
        if (Util.isValid(ConfigValues.CLIENT_SECRET)) {
            parametersBody.add(new BasicNameValuePair(
                    Constants.CLIENT_SECRET, ConfigValues.CLIENT_SECRET));
        }

        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        Token accessToken = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_8));

            response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400) {
                System.out
                        .println("Authorization server expects Basic authentication");
                // Add Basic Authorization header
                post.addHeader(
                        Constants.AUTHORIZATION,
                        Util.getBasicAuthorizationHeader(username,
                                password));
                System.out.println("Retry with login credentials");

                try {
                    response.getEntity().consumeContent();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                response = client.execute(post);
                code = response.getStatusLine().getStatusCode();
                if (code >= 400) {
                    System.out.println("Retry with client credentials");
                    post.removeHeaders(Constants.AUTHORIZATION);
                    post.addHeader(
                            Constants.AUTHORIZATION,
                            Util.getBasicAuthorizationHeader(
                                    ConfigValues.CLIENT_ID, ConfigValues.CLIENT_SECRET));

                    try {
                        response.getEntity().consumeContent();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    response = client.execute(post);
                    code = response.getStatusLine().getStatusCode();
                    if (code >= 400) {
                        throw new RuntimeException(
                                "Could not retrieve access token for user: "
                                        + username);
                    }
                }

            }
            Map<String, Object> map = Util.handleResponse(response);
            accessToken = new Token(new Long((Integer) map.get(Constants.EXPIRES_IN)), (String) map.get(Constants.TOKEN_TYPE),
                    (String) map.get(Constants.REFRESH_TOKEN), (String) map.get(Constants.ACCESS_TOKEN), (String) map.get(Constants.SCOPE), (String) map.get(Constants.JTI));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
    }


}
