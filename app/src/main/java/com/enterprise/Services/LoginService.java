package com.enterprise.services;

import com.enterprise.Utils.AuthorizationUtil;
import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Constants;
import com.enterprise.responses.Token;
import com.enterprise.session.SessionManager;

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

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by albal on 11/02/2017.
 */

@Singleton
public class LoginService {

    SessionManager sessionManager;

    @Inject
    public LoginService(SessionManager sessionManager){
        this.sessionManager=sessionManager;
    }

    public boolean login(String username, String password)
    {

//        sessionManager.setLogin(true,username,"tests");
//        return true;

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

        return false;
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
