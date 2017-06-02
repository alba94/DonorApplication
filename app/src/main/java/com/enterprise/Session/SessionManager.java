package com.enterprise.Session;

/**
 * Created by Donald on 1/9/2017.
 */

public interface SessionManager {

    void setLogin(boolean isLoggedIn, String username, String access_token);

    void logout();

    boolean isLoggedIn();

    String getUsername();

    String getToken();


}
