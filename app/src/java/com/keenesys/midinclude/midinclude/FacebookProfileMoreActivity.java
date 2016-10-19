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
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

public class FacebookProfileMoreActivity extends AppCompatActivity {
    /** For know if the buttons are pressed */
    private static Boolean homeIsPressed = false;
    private static Boolean backIsPressed = false;

    /** The Layout */
    private RelativeLayout Rela;

    /** The settings for the delay */
    private Integer delay;

    /** Color preference */
    private Boolean darkBackground;

    /** The user's preference */
    private SharedPreferences SP;

    /** The infos */
    private TextView firstName;
    private TextView lastName;
    private TextView middleName;

    private Integer textColor;

    private Context context;

    /** The Buttons and Colors */
    private Button homeButton;
    private Button backButton;

    private ColorStateList textColorHomeDefault;
    private Integer textColorHomePressed;
    private Integer backgroundHomeDefault;
    private Integer backgroundHomePressed;
    private ColorStateList textColorBackDefault;
    private Integer textColorBackPressed;
    private Integer backgroundBackDefault;
    private Integer backgroundBackPressed;
    private Drawable icslHomeDefault;
    private Drawable icHomePressed;
    private Drawable icslBackDefault;
    private Drawable icBackPressed;

    private Intent intent;

    private InCloude inCloude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inCloude = new InCloude();
        inCloude.makeClean(this);

        // Initialize the Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook_profile_more);
        Initialize();
        putSomeColors();
        setButtonColorToDefault();
    }

    private void Initialize(){
        Rela = (RelativeLayout) findViewById(R.id.facebook_profile_more);

        // The Preferences
        SP = PreferenceManager.getDefaultSharedPreferences(this);
        darkBackground = SP.getBoolean("backgroundDark", true);
        delay = SP.getInt("motorNeed",3);

        Profile profile = Profile.getCurrentProfile();

        // The profile's texts
        firstName = (TextView) findViewById(R.id.facebook_profile_first_name);
        middleName = (TextView) findViewById(R.id.facebook_profile_middle_name);
        lastName = (TextView) findViewById(R.id.facebook_profile_last_name);

        firstName.setText(profile.getFirstName());
        middleName.setText(profile.getMiddleName());
        lastName.setText(profile.getLastName());

        context = getApplicationContext();

        backButton = (Button) findViewById(R.id.back_button);
        homeButton = (Button) findViewById(R.id.buttonHome);
    }

    private void putSomeColors(){
        if (darkBackground){
            Rela.setBackgroundColor(ContextCompat.getColor(context,R.color.dark_orange));
            textColor = ContextCompat.getColor(context,R.color.white);

            backgroundHomeDefault = R.drawable.background_apppurple_dark_orange;
            backgroundHomePressed = ContextCompat.getColor(context,R.color.dark_orange);
            textColorHomeDefault = ContextCompat.getColorStateList(context, R.color.color_dark_pink_apppurple);
            textColorHomePressed = ContextCompat.getColor(context,R.color.AppPurple);
            icslHomeDefault = inCloude.getDarkMainDefaultDrawable(context);
            icHomePressed = inCloude.getDarkMainPressedDrawable(context);

            backgroundBackDefault = R.drawable.background_appyellow_dark_orange;
            backgroundBackPressed = ContextCompat.getColor(context,R.color.dark_orange);
            textColorBackDefault = ContextCompat.getColorStateList(context, R.color.color_dark_pink_appyellow);
            textColorBackPressed = ContextCompat.getColor(context,R.color.AppYellow);
        }else {
            Rela.setBackgroundColor(ContextCompat.getColor(context,R.color.light_orange));
            textColor = ContextCompat.getColor(context,R.color.black);

            backgroundHomeDefault = R.drawable.background_light_blue_light_orange;
            backgroundHomePressed = ContextCompat.getColor(context,R.color.light_orange);
            textColorHomeDefault = ContextCompat.getColorStateList(context, R.color.color_light_orange_light_blue);
            textColorHomePressed = ContextCompat.getColor(context,R.color.light_blue);
            icslHomeDefault = inCloude.getLightMainDefaultDrawable(context);
            icHomePressed = inCloude.getLightMainPressedDrawable(context);

            backgroundBackDefault = R.drawable.background_dark_green_light_orarange;
            backgroundBackPressed = ContextCompat.getColor(context,R.color.light_orange);
            textColorBackDefault = ContextCompat.getColorStateList(context, R.color.color_light_orange_dark_green);
            textColorBackPressed = ContextCompat.getColor(context,R.color.dark_green);
            }
        firstName.setTextColor(textColor);
        middleName.setTextColor(textColor);
        lastName.setTextColor(textColor);
    }
    /** This function will go to the Main Activity. */
    public void goToHomeFaceProfileMore(View view){
        homeIsPressed = true;
        setButtonColorToPressed();
        intent = new Intent(this, MainActivity.class);

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

    public void goBack(View view){
        backIsPressed = true;
        setButtonColorToPressed();

        intent = new Intent(this, FacebookProfileActivity.class);

        inCloude.vibrates(this);
        // Execute some code after n seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                backIsPressed = false;
                setButtonColorToDefault();
            }
        }, delay*1000);
    }

    private void setButtonColorToDefault() {
        if (!homeIsPressed) {
            homeButton.setBackgroundResource(backgroundHomeDefault);
            homeButton.setTextColor(textColorHomeDefault);
            homeButton.setCompoundDrawables(null,icslHomeDefault,null,null);
        }
        if (!backIsPressed) {
            backButton.setBackgroundResource(backgroundBackDefault);
            backButton.setTextColor(textColorBackDefault);
        }
    }

    /** Sets the colors for when is pressed */
    private void setButtonColorToPressed() {
        if (homeIsPressed) {
            homeButton.setBackgroundColor(backgroundHomePressed);
            homeButton.setTextColor(textColorHomePressed);
            homeButton.setCompoundDrawables(null,icHomePressed,null,null);
        }
        if (backIsPressed) {
            backButton.setBackgroundColor(backgroundBackPressed);
            backButton.setTextColor(textColorBackPressed);
        }
    }
}
