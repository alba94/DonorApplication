package com.enterprise.Services;

import com.enterprise.Session.SessionManager;
import com.enterprise.Utils.AuthorizationUtil;
import com.enterprise.Utils.ConfigValues;
import com.enterprise.Utils.Constants;
import com.enterprise.requests.DonorCreateRequest;
import com.enterprise.requests.DonorSearchFilterRequest;
import com.enterprise.responses.Donor;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import static com.enterprise.Utils.AuthorizationUtil.getAuthorizationHeaderForAccessToken;

/**
 * Created by dlika on 6/5/2017.
 */

public class DonorServiceImpl implements DonorService {


    @Inject
    SessionManager sessionManager;


    @Override
    public List<Donor> getAllDonors() {
        return null;
    }

    @Override
    public List<Donor> search(DonorSearchFilterRequest request) {
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Donor getDonorById(int id) {

        String resourceURL = ConfigValues.BASE_URL + "/donors/" + id;
        HttpPost post = new HttpPost(resourceURL);
        post.addHeader(Constants.AUTHORIZATION,
                getAuthorizationHeaderForAccessToken(sessionManager.getToken()));
        post.addHeader("Content-type", "application/json");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        int code = -1;
        try {
            response = httpClient.execute(post);
            code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                return parseDonor(AuthorizationUtil.handleJsonResponse(response));
            }
        } catch (ClientProtocolException e) {

        } catch (IOException e) {
        } finally {
            try {
                response.getEntity().consumeContent();
            } catch (IOException e) {
            }
        }
        return null;
    }

    @Override
    public Donor createDonor(DonorCreateRequest request) {
        return null;
    }

    //THIS METHOD WILL PARSE REQUESTMAP TO DONOR
    private Donor parseDonor(Map<String, Object> requestMap) {
        return new Donor();
    }
}
