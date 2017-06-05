package com.enterprise.Services;

/**
 * Created by dlika on 6/2/2017.
 */

public interface LoginService {

    boolean login(String username, String password);

    void logout();

    boolean isLogined();

    String getToken();

}
