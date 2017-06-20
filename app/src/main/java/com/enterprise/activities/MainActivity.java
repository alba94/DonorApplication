package com.enterprise.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.enterprise.services.AccountService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.input_username)
    EditText _usernameText;

    @Bind(R.id.input_password)
    EditText _passwordText;

    @Bind(R.id.btn_login)
    Button _loginButton;

    @Bind(R.id.forgot_password)
    TextView forgot_pass;

    AccountService accountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        accountService = new AccountService(getApplicationContext());


        if (AccountService.getSessionObject() != null) {
            Intent intent = new Intent(MainActivity.this, LoginedActivity.class);
            startActivity(intent);
            finish();
        }

        _loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (validate()) {

                    new NetCheck().execute();
                }
            }
        });

    }

    private class NetCheck extends AsyncTask<String, String, Boolean> {
        private ProgressDialog nDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            nDialog = new ProgressDialog(MainActivity.this);
            nDialog.setTitle("Checking Network");
            nDialog.setMessage("Loading..");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... args) {

            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(3000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        return true;
                    }
                } catch (MalformedURLException e1) {

                } catch (IOException e) {

                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean th) {

            if (th) {
                nDialog.dismiss();
                nDialog = new ProgressDialog(MainActivity.this);
                nDialog.setTitle("Contacting Servers");
                nDialog.setMessage("Logging in ...");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
                String username = _usernameText.getText().toString();
                String password = _passwordText.getText().toString();
                if (accountService.login(username, password)) {
                    nDialog.setMessage("Loading User Space");
                    nDialog.setTitle("Getting Data");
                    Intent upanel = new Intent(getApplicationContext(), LoginedActivity.class);
                    upanel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nDialog.dismiss();
                    startActivity(upanel);
                    finish();
                } else {
                    nDialog.dismiss();
                    Toast.makeText(getApplication(), "Incorrect Username/Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                nDialog.dismiss();
                Toast.makeText(getApplication(), "Error in Network Connection", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private class ProcessLogin extends AsyncTask<String, String, Boolean> {

        private ProgressDialog pDialog;

        String username, password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            username = _usernameText.getText().toString();
            password = _passwordText.getText().toString();

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Logging in ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... args) {

            return accountService.login(username, password);
        }

        @Override
        protected void onPostExecute(Boolean response) {
            if (response) {
                pDialog.setMessage("Loading User Space");
                pDialog.setTitle("Getting Data");
                Intent upanel = new Intent(getApplicationContext(), LoginedActivity.class);
                upanel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pDialog.dismiss();
                startActivity(upanel);

                finish();

            } else {
                pDialog.dismiss();
                Toast.makeText(getApplication(), "Incorrect Username/Password", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public boolean validate() {
        boolean valid = true;

        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (username.isEmpty()) {
            _usernameText.setError("Enter a valid username");
            valid = false;
        } else {
            _usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError("Enter a valid password");
            valid = false;
        } else {
            _usernameText.setError(null);
        }

        return valid;
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
