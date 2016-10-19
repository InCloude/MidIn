package com.keenesys.midinclude.midinclude;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;
import twitter4j.auth.AccessToken;

public class TwitterLoginActivity extends AppCompatActivity {
    // Constants
    /**
     * Register your here app https://dev.twitter.com/apps/new and get your
     * consumer key and secret
     */
    private static final String TWITTER_KEY = "WkMDMclXKomIaoVBwy30dglzJ";
    private static final String TWITTER_SECRET = "RFBj2wtXOTnQveR6ywLlaLyQkFYOxnPlrxwnjjcMwcXDn9AQmo";

    private Intent intent;

    private TwitterLoginButton loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        intent = new Intent(this, TwitterProfileActivity.class);

        setContentView(R.layout.activity_twitter_login);

        TwitterAuthToken accessToken = Twitter.getInstance().core.getSessionManager().getActiveSession().getAuthToken();
        if (accessToken.isExpired()) {
            loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
            loginButton.setCallback(new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    // The TwitterSession is also available through:
                    // Twitter.getInstance().core.getSessionManager().getActiveSession()
                    TwitterSession session = result.data;
                    // TODO: Remove toast and use the TwitterSession's userID
                    // with your app's user model
                    String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(TwitterException exception) {
                    Log.d("TwitterKit", "Login with Twitter failure", exception);
                }
            });
        }else startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}