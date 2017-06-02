package com.enterprise.ServerAccess;

/**
 * Created by dlika on 6/2/2017.
 */

public interface LoginService {

    boolean Login(String username, String password);

    void Logout();

    boolean isLogined();

    String getToken();

}
