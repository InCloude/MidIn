package com.keenesys.midinclude.midinclude;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class FacebookActivity extends AppCompatActivity {
    private TextView callbackFacebook;
    private CallbackManager callbackManager;
    private Intent intent;

    // The Layout
    private RelativeLayout Rela;

    /* Color preference */
    private Boolean darkBackground;

    /* The user's preference */
    private SharedPreferences SP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoginButton facebookLoginButton;

        super.onCreate(savedInstanceState);

        InCloude inc = new InCloude();
        inc.makeClean(this);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook);
        Initialize();
        putSomeColors();

        // If the access token is available already assign it.
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {
            facebookLoginButton = (LoginButton) findViewById(R.id.login_button);
            facebookLoginButton.setReadPermissions("user_posts");
            facebookLoginButton.setReadPermissions("user_friends");

            facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    startActivity(intent);
                    callbackFacebook.setText(
                            "User ID: "
                                    + loginResult.getAccessToken().getUserId()
                                    + "\n" +
                                    "Auth Token: "
                                    + loginResult.getAccessToken().getToken()
                    );
                }

                @Override
                public void onCancel() {
                    callbackFacebook.setText("Login attempt canceled.");
                }

                @Override
                public void onError(FacebookException e) {
                    callbackFacebook.setText("Login attempt failed.");
                }
            });
        }else startActivity(intent);
    }
    private void Initialize(){
        // The layout
        Rela =(RelativeLayout) findViewById(R.id.facebook_login_rela);

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_facebook);
        callbackFacebook = (TextView)findViewById(R.id.callback_login);

        intent = new Intent(this, FacebookProfileActivity.class);

        // The Preferences
        SP = PreferenceManager.getDefaultSharedPreferences(this);
        darkBackground = SP.getBoolean("backgroundDark", true);
    }
    private void putSomeColors(){
        /*if (darkBackground)**/ Rela.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.AppYellow));
        //else Rela.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.dark_green));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

