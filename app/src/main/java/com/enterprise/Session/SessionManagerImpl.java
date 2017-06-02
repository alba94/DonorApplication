package com.enterprise.Session;

/**
 * Created by Donald on 1/9/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import javax.inject.Inject;

public class SessionManagerImpl implements SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();
    SharedPreferences pref;
    private Editor editor;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHiveLogin";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    @Inject
    Context _context;

    public SessionManagerImpl() {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setLogin(boolean isLoggedIn, String username, String access_token) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString("username", username);
        editor.putString("access_token", access_token);
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }


    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUsername() {
        return pref.getString("username", "null");
    }

    public String getToken() {
        return pref.getString("access_token", "null");
    }


}
