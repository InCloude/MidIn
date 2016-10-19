package com.keenesys.midinclude.midinclude;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import java.io.IOException;
import java.net.URL;

public class TwitterProfileActivity extends AppCompatActivity {
    /**
     * Register your here app https://dev.twitter.com/apps/new and get your
     * consumer key and secret
     */
    private static final String TWITTER_KEY = "WkMDMclXKomIaoVBwy30dglzJ";
    private static final String TWITTER_SECRET = "RFBj2wtXOTnQveR6ywLlaLyQkFYOxnPlrxwnjjcMwcXDn9AQmo";

    /** For know if the buttons are pressed */
    private static Boolean homeIsPressed = false;
    private static Boolean timeLineIsPressed = false;

    /** The Layout */
    private RelativeLayout Rela;

    /** The settings for the delay */
    private Integer delay;

    /** Color preference */
    private Boolean darkBackground;

    /** The user's preference */
    private SharedPreferences SP;

    private Integer textColor;

    private Context context;

    /** The Infos */
    private TextView userName;
    private TextView userId;
    private ImageView profilePicture;

    /** The Buttons and Colors */
    private Button homeButton;
    private Button timeLineButton;

    private Integer backgroundHomeDefault;
    private Integer backgroundHomePressed;
    private Integer backgroundTimeLineDefault;
    private Integer backgroundTimeLinePressed;

    /** The icons */
    private Drawable icslHomeDefault;
    private Drawable icHomePressed;
    private Drawable icslTimeLineDefalt;
    private Drawable icTimeLinePressed;

    private Intent intent;

    private InCloude inCloude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inCloude = new InCloude();
        inCloude.makeClean(this);
        setContentView(R.layout.activity_twitter_profile);
        Initialize();
        putSomeColors();
        setButtonColorToDefault();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
    private void Initialize(){
        Rela = (RelativeLayout) findViewById(R.id.layout_twitter_profile);

        // Declaring the context
        context = getApplicationContext();

        // The Preferences
        SP = PreferenceManager.getDefaultSharedPreferences(this);
        darkBackground = SP.getBoolean("backgroundDark", inCloude.getBackground());
        delay = SP.getInt("motorNeed",inCloude.getDelay());

        homeButton = (Button) findViewById(R.id.home_twitter_button);
        timeLineButton = (Button) findViewById(R.id.timeline_twitter_button);

        TwitterSession session = Twitter.getInstance().core.getSessionManager().getActiveSession();

        userId = (TextView) findViewById(R.id.twitter_user_id);
        userId.setText("@" +session.getUserName());
        userName = (TextView) findViewById(R.id.twitter_user_name);
        profilePicture = (ImageView) findViewById(R.id.imageView);
        profile();
    }
    private void profile(){

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        twitterApiClient.getAccountService().verifyCredentials(false, false, new Callback<User>() {
            @Override
            public void success(Result<User> userResult) {
                userName.setText(userResult.data.name);
                String photoUrlBiggerSize   = userResult.data.profileImageUrl.replace("_normal", "_bigger");
                try {
                    URL url = new URL(photoUrlBiggerSize);
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    profilePicture.setImageBitmap(bmp);
                }catch (IOException e){}
            }

            @Override
            public void failure(TwitterException exc) {}
        });
    }
    private void putSomeColors(){
        if (darkBackground){
            Rela.setBackgroundColor(ContextCompat.getColor(context,R.color.light_green));

            backgroundHomeDefault = R.drawable.background_apppurple_light_green;
            backgroundHomePressed = ContextCompat.getColor(context,R.color.light_green);
            icslHomeDefault = inCloude.getDarkMainDefaultDrawable(context);
            icHomePressed = inCloude.getDarkMainPressedDrawable(context);

            backgroundTimeLineDefault = R.drawable.background_white_light_green;
            backgroundTimeLinePressed = ContextCompat.getColor(context,R.color.light_green);

            textColor = ContextCompat.getColor(context,R.color.black);
        }else {
            Rela.setBackgroundColor(ContextCompat.getColor(context,R.color.brown));

            backgroundHomeDefault = R.drawable.background_light_bliue_brown;
            backgroundHomePressed = ContextCompat.getColor(context,R.color.brown);
            icslHomeDefault = inCloude.getLightMainDefaultDrawable(context);
            icHomePressed = inCloude.getLightMainPressedDrawable(context);

            backgroundTimeLineDefault = R.drawable.background_white_brown;
            backgroundTimeLinePressed = ContextCompat.getColor(context,R.color.brown);

            textColor = ContextCompat.getColor(context,R.color.white);
        }
        userName.setTextColor(textColor);
    }
    /** This function will go to the Main Activity. */
    public void goToHomeTwitterProfile(View view){
        homeIsPressed = true;
        setButtonColorToPressed();
        intent = inCloude.getMainActivity(this);

        inCloude.vibrates(this);
        // Execute some code after n seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                homeIsPressed = false;
                setButtonColorToDefault();
                startActivity(intent);
            }
        }, delay*1000);
    }

    public void goToTwitterTimeLine(View view){
        Intent intent = new Intent(this,TwitterHomeActivity.class);
        startActivity(intent);
    }

    private void setButtonColorToDefault() {
        if (!homeIsPressed) {
            homeButton.setBackgroundResource(backgroundHomeDefault);
            homeButton.setCompoundDrawables(null,icslHomeDefault,null,null);
        }
        if (!timeLineIsPressed) {
            timeLineButton.setBackgroundResource(backgroundTimeLineDefault);

        }
    }

    /** Sets the colors for when is pressed */
    private void setButtonColorToPressed() {
        if (homeIsPressed) {
            homeButton.setBackgroundColor(backgroundHomePressed);
            homeButton.setCompoundDrawables(null,icHomePressed,null,null);
        }
        if (timeLineIsPressed) {
            timeLineButton.setBackgroundColor(backgroundTimeLinePressed);

        }
    }
}
