package com.enterprise.Utils;


import android.util.Base64;

import com.enterprise.responses.Token;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        if (isValid(ConfigValues.CLIENT_ID)) {
            parametersBody.add(new BasicNameValuePair(Constants.CLIENT_ID,
                    ConfigValues.CLIENT_ID));
        }
        if (isValid(ConfigValues.CLIENT_SECRET)) {
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
                        getBasicAuthorizationHeader(username,
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
                            getBasicAuthorizationHeader(
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
            Map<String, Object> map = handleResponse(response);
            accessToken = new Token(new Long((Integer) map.get(Constants.EXPIRES_IN)), (String) map.get(Constants.TOKEN_TYPE),
                    (String) map.get(Constants.REFRESH_TOKEN), (String) map.get(Constants.ACCESS_TOKEN), (String) map.get(Constants.SCOPE), (String) map.get(Constants.JTI));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
    }


    public static Map handleResponse(HttpResponse response) {
        String contentType = Constants.JSON_CONTENT;
        if (response.getEntity().getContentType() != null) {
            contentType = response.getEntity().getContentType().getValue();
            System.out.println(response.getEntity().getContentType().getValue());

        }
        if (contentType.contains(Constants.JSON_CONTENT)) {
            return handleJsonResponse(response);
        } else if (contentType.contains(Constants.URL_ENCODED_CONTENT)) {
            return handleURLEncodedResponse(response);
        } else {
            // Unsupported Content type
            throw new RuntimeException(
                    "Cannot handle "
                            + contentType
                            + " content type. Supported content types include JSON, XML and URLEncoded");
        }

    }

    public static Map handleJsonResponse(HttpResponse response) {
        JSONObject oauthLoginResponse = null;
        String contentType = response.getEntity().getContentType().getValue();
        try {
            oauthLoginResponse = new JSONObject(EntityUtils.toString(response.getEntity()));
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        System.out.println();
        System.out.println("********** JSON Response Received **********");

        Map<String, Object> outMap = new HashMap<String, Object>();
        Iterator<String> keysIterator = oauthLoginResponse.keys();
        while (keysIterator.hasNext()) {
            String keyStr = keysIterator.next();
            Object value = null;
            try {
                value = oauthLoginResponse.get(keyStr);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            outMap.put(keyStr, value);
            System.out.println(String.format("  %s = %s", keyStr, value));
        }

        return outMap;
    }

    public static Map handleURLEncodedResponse(HttpResponse response) {
        Map<String, Charset> map = Charset.availableCharsets();
        Map<String, String> oauthResponse = new HashMap<String, String>();
        Set<Map.Entry<String, Charset>> set = map.entrySet();
        Charset charset = null;
        HttpEntity entity = response.getEntity();

        System.out.println();
        System.out.println("********** URL Encoded Response Received **********");

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(String.format("  %s = %s", entry.getKey(),
                    entry.getValue()));
            if (entry.getKey().equalsIgnoreCase(HTTP.UTF_8)) {
                charset = entry.getValue();
            }
        }

        try {
            List<NameValuePair> list = URLEncodedUtils.parse(entity);
            for (NameValuePair pair : list) {
                System.out.println(String.format("  %s = %s", pair.getName(),
                        pair.getValue()));
                oauthResponse.put(pair.getName(), pair.getValue());
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("Could not parse URLEncoded Response");
        }

        return oauthResponse;
    }


    public static String getAuthorizationHeaderForAccessToken(String accessToken) {
        return Constants.BEARER + " " + accessToken;
    }

    public static String getBasicAuthorizationHeader(String username,
                                                     String password) {
        return Constants.BASIC + " "
                + encodeCredentials(username, password);
    }

    public static String encodeCredentials(String username, String password) {
        String cred = username + ":" + password;
        String encodedValue = null;
        byte[] encodedBytes = Base64.encode(cred.getBytes(), Base64.NO_WRAP);
        encodedValue = new String(encodedBytes);
        System.out.println("encodedBytes " + new String(encodedBytes));

        byte[] decodedBytes = Base64.decode(encodedBytes, Base64.NO_WRAP);
        System.out.println("decodedBytes " + new String(decodedBytes));

        return encodedValue;

    }

    public static boolean isValid(String str) {
        return (str != null && str.trim().length() > 0);
    }

}
