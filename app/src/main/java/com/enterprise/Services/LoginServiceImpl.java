package com.enterprise.Services;

import com.enterprise.Session.SessionManager;

import javax.inject.Inject;

/**
 * Created by albal on 11/02/2017.
 */

public class LoginServiceImpl implements LoginService {

    @Inject
    SessionManager sessionManager;


    public boolean login(String username, String password)
    {


        sessionManager.setLogin(true,username,"tests");
        return true;

/*
        HttpPost post = new HttpPost(ConfigValues.BASE_URL + "/oauth/token");
        List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
        parametersBody.add(new BasicNameValuePair(Constants.GRANT_TYPE,
                "password"));
        parametersBody.add(new BasicNameValuePair(Constants.USERNAME,
                username));
        parametersBody.add(new BasicNameValuePair(Constants.PASSWORD,
                password));

        if (AuthorizationUtil.isValid(ConfigValues.CLIENT_ID)) {
            parametersBody.add(new BasicNameValuePair(Constants.CLIENT_ID,
                    ConfigValues.CLIENT_ID));
        }
        if (AuthorizationUtil.isValid(ConfigValues.CLIENT_SECRET)) {
            parametersBody.add(new BasicNameValuePair(
                    Constants.CLIENT_SECRET, ConfigValues.CLIENT_SECRET));
        }

        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        Token accessToken = null;
        try {
            post.addHeader(
                    Constants.AUTHORIZATION,
                    AuthorizationUtil.getBasicAuthorizationHeader(ConfigValues.CLIENT_ID, ConfigValues.CLIENT_SECRET));
            post.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_8));
            response = client.execute(post);
            System.out.println(response.toString());
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                Map map = AuthorizationUtil.handleJsonResponse(response);
                sessionManager.setLogin(true, username, (String) map.get(Constants.ACCESS_TOKEN));
                return true;
            }

        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        return false;*/
    }

    public void logout()
    {
       sessionManager.logout();
    }

    public boolean isLogined()
    {
        return sessionManager.isLoggedIn();
    }

    public String getToken()
    {
        return sessionManager.getToken();
    }


}
