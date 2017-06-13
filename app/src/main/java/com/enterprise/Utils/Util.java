package com.enterprise.Utils;

import android.util.Base64;

import com.enterprise.session.SessionManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dlika on 6/5/2017.
 */

public class Util {


    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    public static MultiValueMap getLoginHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", Util.getBasicAuthorizationHeader(Constants.CLIENT_ID, Constants.CLIENT_SECRET));
        headers.add("Content-Type", "application/json");
        return headers;
    }

    public static MultiValueMap getHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", Util.getAuthorizationHeaderForAccessToken(SessionManager.getToken()));
        headers.add("Content-Type", "application/json");
        return headers;
    }
//    private  class NetCheck extends AsyncTask<String, String, Boolean> {
//        private ProgressDialog nDialog;
//        private Context context;
//
//        public NetCheck(Context context)
//        {
//            this.context=context;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            nDialog = new ProgressDialog(MainActivity.this);
//            nDialog.setTitle("Checking Network");
//            nDialog.setMessage("Loading..");
//            nDialog.setIndeterminate(false);
//            nDialog.setCancelable(true);
//            nDialog.show();
//        }
//
//        @Override
//        protected Boolean doInBackground(String... args) {
//
//            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo netInfo = cm.getActiveNetworkInfo();
//
//            if (netInfo != null && netInfo.isConnected()) {
//                try {
//                    URL url = new URL("http://www.google.com");
//                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
//                    urlc.setConnectTimeout(3000);
//                    urlc.connect();
//                    if (urlc.getResponseCode() == 200) {
//                        return true;
//                    }
//                } catch (MalformedURLException e1) {
//
//                } catch (IOException e) {
//
//                }
//            }
//            return false;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean th) {
//
//            if (th) {
//                nDialog.dismiss();
//            } else {
//                nDialog.dismiss();
//                Toast.makeText(context,"Error in Network Connection", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//
//    public boolean checkConnection(Context context)
//    {
//        try {
//            return new NetCheck(context).execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


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

    private static Map handleJsonResponse(HttpResponse response) {
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

    private static Map handleURLEncodedResponse(HttpResponse response) {
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

    private static String encodeCredentials(String username, String password) {
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
