package com.keenesys.midinclude.midinclude;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

public class FacebookProfileActivity extends AppCompatActivity {
    /** For know if the button is pressed */
    private static Boolean homeIsPressed = false;
    private static Boolean newsFeedIsPressed = false;
    private static Boolean moreIsPressed = false;

    private Button moreButton;
    private Button newsFeedButton;
    private Button homeButton;
    /* The activity's intent */
    private Intent intent;
    /* Context */
    private Context context;

    /** The settings for the delay */
    private Integer delay;

    /** The Layout */
    private RelativeLayout Rela;

    /** Color preference */
    private Boolean darkBackground;

    /** The user's preference */
    private SharedPreferences SP;

    private ProfilePictureView profileImage;

    /* The text colors and background state lists */
    private ColorStateList textColorHomeDefault;
    private ColorStateList textColorNewsFeedDefault;
    private ColorStateList textColorMoreDefault;
    private Integer textColorHomePressed;
    private Integer textColorNewsFeedPressed;
    private Integer textColorMorePressed;
    private Integer backgroundHomeDefault;
    private Integer backgroundNewsFeedDefault;
    private Integer backgroundMoreDefault;
    private Drawable icslMoreDefault;
    private Integer backgroundHomePressed;
    private Integer backgroundNewsFeedPressed;
    private Drawable icMorePressed;
    private Integer backgroundMorePressed;
    private Drawable icslHomeDefault;
    private Drawable icHomePressed;
    private Drawable icslNewsFeedDefault;
    private Drawable icNewsFeedPressed;

    private InCloude inCloude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inCloude = new InCloude();
        inCloude.makeClean(this);

        // Initialize the Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook_profile);
        Initialize();
        putSomeColors();
        // Put some color to the button
        setButtonColorToDefault();
    }

    private void Initialize(){
        Rela = (RelativeLayout) findViewById(R.id.layout_facebook_profile);
        // Declaring the context
        context = getApplicationContext();

        // The Preferences
        SP = PreferenceManager.getDefaultSharedPreferences(this);
        darkBackground = SP.getBoolean("backgroundDark", inCloude.getBackground());
        delay = SP.getInt("motorNeed",inCloude.getDelay());

        // The buttons
        moreButton = (Button) findViewById(R.id.more_button);
        homeButton = (Button) findViewById(R.id.buttonHome);
        newsFeedButton = (Button) findViewById(R.id.facebook_feed);

        // The user's profile
        Profile profile = Profile.getCurrentProfile();
        // The Facebook's Image
        profileImage = (ProfilePictureView) findViewById(R.id.profilePicture);
        profileImage.setProfileId(profile.getId());
    }

    private void putSomeColors(){
        if (darkBackground){
            Rela.setBackgroundColor(ContextCompat.getColor(context,R.color.AppYellow));

            backgroundHomeDefault = R.drawable.background_apppurple_appyellow;
            backgroundHomePressed = ContextCompat.getColor(context,R.color.AppYellow);
            textColorHomeDefault = ContextCompat.getColorStateList(context, R.color.AppYellow);
            textColorHomePressed = ContextCompat.getColor(context,R.color.AppPurple);
            icslHomeDefault = inCloude.getDarkMainDefaultDrawable(context);
            icHomePressed = inCloude.getDarkMainPressedDrawable(context);

            backgroundNewsFeedDefault = R.drawable.background_dark_green_appyellow;
            backgroundNewsFeedPressed = ContextCompat.getColor(context,R.color.AppYellow);
            textColorNewsFeedDefault = ContextCompat.getColorStateList(context, R.color.AppYellow);
            textColorNewsFeedPressed = ContextCompat.getColor(context,R.color.dark_green);
            icslNewsFeedDefault = ContextCompat.getDrawable(context,R.drawable.icsl_dark_facebook_timeline);
            icNewsFeedPressed = ContextCompat.getDrawable(context,R.drawable.ic_timeline_black_48dp_dark_green);

            backgroundMoreDefault = R.drawable.background_dark_orange_appyellow;
            backgroundMorePressed = ContextCompat.getColor(context,R.color.AppYellow);
            textColorMoreDefault = ContextCompat.getColorStateList(context, R.color.color_appyellow_dark_orange);
            textColorMorePressed = ContextCompat.getColor(context,R.color.dark_orange);
            icslMoreDefault = ContextCompat.getDrawable(context,R.drawable.icsl_dark_arrow_down);
            icMorePressed = ContextCompat.getDrawable(context,R.drawable.ic_expand_more_black_48dp_appyellow);
        }else {
            Rela.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_green));

            backgroundHomeDefault = R.drawable.background_light_blue_dark_green;
            backgroundHomePressed = ContextCompat.getColor(context,R.color.dark_green);
            textColorHomeDefault = ContextCompat.getColorStateList(context, R.color.color_dark_green_appyellow);
            textColorHomePressed = ContextCompat.getColor(context,R.color.light_blue);
            icslHomeDefault = inCloude.getLightMainDefaultDrawable(context);
            icHomePressed = inCloude.getLightMainPressedDrawable(context);

            backgroundNewsFeedDefault = R.drawable.background_light_green_dark_green;
            backgroundNewsFeedPressed = ContextCompat.getColor(context,R.color.dark_green);
            textColorNewsFeedDefault = ContextCompat.getColorStateList(context, R.color.color_apppurple_white);
            textColorNewsFeedPressed = ContextCompat.getColor(context,R.color.light_green);
            icslNewsFeedDefault = ContextCompat.getDrawable(context,R.drawable.icsl_light_facebook_timeline);
            icNewsFeedPressed = ContextCompat.getDrawable(context,R.drawable.ic_timeline_black_48dp_light_green);

            backgroundMoreDefault = R.drawable.background_light_orange_dark_green;
            backgroundMorePressed = ContextCompat.getColor(context,R.color.dark_green);
            textColorMoreDefault = ContextCompat.getColorStateList(context, R.color.color_appyellow_dark_orange);
            textColorMorePressed = ContextCompat.getColor(context,R.color.light_orange);
            icslMoreDefault = ContextCompat.getDrawable(context,R.drawable.icsl_light_arrow_down);
            icMorePressed = ContextCompat.getDrawable(context,R.drawable.ic_expand_more_black_48dp_light_orange);
        }
        icslNewsFeedDefault.setBounds(0,0,300,300);
        icNewsFeedPressed.setBounds(0,0,300,300);
        icslMoreDefault.setBounds(0,0,180,180);
        icMorePressed.setBounds(0,0,180,180);
    }
    /** This function will go to the Main Activity. */
    public void goToHomeFaceProfile(View view){
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

    public void goToMoreProfile(View view){
        moreIsPressed = true;
        setButtonColorToPressed();
        intent = new Intent(this, FacebookProfileMoreActivity.class);

        inCloude.vibrates(this);
        // Execute some code after n seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moreIsPressed = false;
                setButtonColorToDefault();
                startActivity(intent);
            }
        }, delay*1000);
    }

    public void goToNewsFeed(View view){
        newsFeedIsPressed = true;
        setButtonColorToPressed();
        intent = new Intent(this, FacebookMainActivity.class);

        inCloude.vibrates(this);
        // Execute some code after n seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newsFeedIsPressed = false;
                setButtonColorToDefault();
                startActivity(intent);
            }
        }, delay*1000);
    }

    /** Sets the colors to default */
    private void setButtonColorToDefault(){
        if (!homeIsPressed){
            homeButton.setBackgroundResource(backgroundHomeDefault);
            homeButton.setTextColor(textColorHomeDefault);
            homeButton.setCompoundDrawables(null,icslHomeDefault,null,null);
        }
        if (!newsFeedIsPressed){
            newsFeedButton.setBackgroundResource(backgroundNewsFeedDefault);
            newsFeedButton.setTextColor(textColorNewsFeedDefault);
            newsFeedButton.setCompoundDrawables(null,null,icslNewsFeedDefault,null);
        }
        if (!moreIsPressed){
            moreButton.setBackgroundResource(backgroundMoreDefault);
            moreButton.setTextColor(textColorMoreDefault);
            moreButton.setCompoundDrawables(null,null,null,icslMoreDefault);
        }
    }

    /** Sets the colors for when is pressed */
    private void setButtonColorToPressed(){
        if (homeIsPressed){
            homeButton.setBackgroundColor(backgroundHomePressed);
            homeButton.setTextColor(textColorHomePressed);
            homeButton.setCompoundDrawables(null,icHomePressed,null,null);
        }
        if (newsFeedIsPressed){
            newsFeedButton.setBackgroundColor(backgroundNewsFeedPressed);
            newsFeedButton.setTextColor(textColorNewsFeedPressed);
            newsFeedButton.setCompoundDrawables(null,null,icNewsFeedPressed,null);
        }
        if (moreIsPressed){
            moreButton.setBackgroundColor(backgroundMorePressed);
            moreButton.setTextColor(textColorMorePressed);
            moreButton.setCompoundDrawables(null,null,null,icMorePressed);
        }
    }
}
