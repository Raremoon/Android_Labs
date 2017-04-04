package com.frankmeana.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
{
    protected static final String ACTIVITY_NAME = "LoginActivity";

    Button loginButton;
    EditText loginEmail;
    SharedPreferences.Editor editPrefs;
    SharedPreferences prefs;//= getPreferences(Context.MODE_PRIVATE);
    public static final String MyPreference = "MyPrefs" ;

    String emailId;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Log.i(ACTIVITY_NAME, "In onCreate()");

        loginButton = (Button) findViewById(R.id.loginButton);  //store a reference to the button in the Java Activity
        loginEmail = (EditText) findViewById(R.id.userName);
        prefs = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);

    }

    // on click for the login button
    public void loginButtonClicked(View view)
    {

        Log.i("Message", "Button clicked");

        editPrefs = prefs.edit();
        editPrefs.putString("email",loginEmail.getText().toString());
        editPrefs.commit();
        Intent intent = new Intent(LoginActivity.this, StartActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Log.i(ACTIVITY_NAME, "In onStart()");

        if(prefs != null && emailId != null) {
            emailId = prefs.getString("email", "Not defined");
        }
        else
        {
            emailId = prefs.getString("DefaultEmail", "email@domain.com");
        }
        loginEmail.setText(emailId);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");

        prefs=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editPrefs = prefs.edit();
        editPrefs.putString("email",loginEmail.getText().toString());
        editPrefs.commit();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");

        if(prefs!=null && emailId!=null)
        {
            emailId = prefs.getString("email", "Not defined");
        }
        else
        {
            emailId=prefs.getString("DefaultEmail", "email@domain.com");
        }
        loginEmail.setText(emailId);
    }
}
