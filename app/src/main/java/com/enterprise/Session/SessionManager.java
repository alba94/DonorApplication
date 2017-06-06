package com.enterprise.session;

/**
 * Created by Donald on 1/9/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();

    private SharedPreferences sharedPreferences;
    private Editor editor;
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    @Inject
    public SessionManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences("mobilepref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
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
        return sharedPreferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUsername() {
        return sharedPreferences.getString("username", "null");
    }

    public String getToken() {
        return sharedPreferences.getString("access_token", "null");
    }


}
