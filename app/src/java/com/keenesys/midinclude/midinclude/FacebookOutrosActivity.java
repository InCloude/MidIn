package com.keenesys.midinclude.midinclude;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class FacebookOutrosActivity extends AppCompatActivity {

    /**
     * For know if the button is pressed
     */
    private static Boolean homeIsPressed = false;
    private static Boolean notificantionsIsPressed = false;
    private static Boolean eventsIsPressed = false;
    private static Boolean birthdaysIsPressed = false;

    private Button notificationsButton;
    private Button eventsButton;
    private Button birthdaysButton;
    private Button homeButton;
    /** The activity's intent */
    private Intent intent;
    /** Context */
    private Context context;

    /**
     * The settings for the delay
     */
    private Integer delay;

    /**
     * The Layout
     */
    private RelativeLayout Rela;

    /**
     * Color preference
     */
    private Boolean darkBackground;

    /**
     * The user's preference
     */
    private SharedPreferences SP;

    /** The text colors and background state lists */
    private ColorStateList textColorHomeDefault;
    private ColorStateList textColorNotificationsDefault;
    private ColorStateList textColorEventsDefault;
    private ColorStateList textColorBirthdaysDefault;
    private Integer textColorHomePressed;
    private Integer textColorNotificationsPressed;
    private Integer textColorEventsPressed;
    private Integer textColorBirthdaysPressed;
    private Integer backgroundHomeDefault;
    private Integer backgroundNotificationsdDefault;
    private Integer backgroundEventsDefault;
    private Integer backgroundBirthdaysDefault;
    private Integer backgroundHomePressed;
    private Integer backgroundNotificationsPressed;
    private Integer backgroundEventsPressed;
    private Integer backgroundBirthdaysPressed;

    private InCloude inCloude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inCloude = new InCloude();
        inCloude.makeClean(this);
        setContentView(R.layout.activity_facebook_outros);
        Initialize();
        putSomeColors();
        setButtonColorToDefault();
    }

    private void Initialize() {
        Rela = (RelativeLayout) findViewById(R.id.layout_facebook_outros);
        // Declaring the context
        context = getApplicationContext();

        // The Preferences
        SP = PreferenceManager.getDefaultSharedPreferences(this);
        darkBackground = SP.getBoolean("backgroundDark", inCloude.getBackground());
        delay = SP.getInt("motorNeed", inCloude.getDelay());

        homeButton = (Button) findViewById(R.id.home_button);
        notificationsButton = (Button) findViewById(R.id.notifications);
        eventsButton = (Button) findViewById(R.id.events);
        birthdaysButton = (Button) findViewById(R.id.birthdays);
    }

    private void putSomeColors() {
        if (darkBackground) {
            Rela.setBackgroundColor(ContextCompat.getColor(context, R.color.light_grey));

            backgroundHomeDefault = R.drawable.background_appurple_light_grey;
            backgroundHomePressed = ContextCompat.getColor(context, R.color.light_grey);
            textColorHomeDefault = ContextCompat.getColorStateList(context, R.color.AppYellow);
            textColorHomePressed = ContextCompat.getColor(context, R.color.AppPurple);

            backgroundNotificationsdDefault = R.drawable.background_dark_blue_light_grey;
            backgroundNotificationsPressed = ContextCompat.getColor(context, R.color.light_grey);
            textColorNotificationsDefault = ContextCompat.getColorStateList(context, R.color.color_light_grey_dark_blue);
            textColorNotificationsPressed = ContextCompat.getColor(context, R.color.dark_blue);

            backgroundEventsDefault = R.drawable.background_black_light_grey;
            backgroundEventsPressed = ContextCompat.getColor(context, R.color.light_grey);
            textColorEventsDefault = ContextCompat.getColorStateList(context, R.color.color_light_grey_black);
            textColorEventsPressed = ContextCompat.getColor(context, R.color.black);

            backgroundBirthdaysDefault = R.drawable.background_dark_pink_light_grey;
            backgroundBirthdaysPressed = ContextCompat.getColor(context, R.color.light_grey);
            textColorBirthdaysDefault = ContextCompat.getColorStateList(context, R.color.color_light_grey_dark_pink);
            textColorBirthdaysPressed = ContextCompat.getColor(context, R.color.dark_pink);
        } else {
            Rela.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_grey));

            backgroundHomeDefault = R.drawable.background_light_blue_dark_grey;
            backgroundHomePressed = ContextCompat.getColor(context, R.color.dark_grey);
            textColorHomeDefault = ContextCompat.getColorStateList(context, R.color.color_dark_grey_light_blue);
            textColorHomePressed = ContextCompat.getColor(context, R.color.light_blue);

            backgroundNotificationsdDefault = R.drawable.background_light_orange_dark_grey;
            backgroundNotificationsPressed = ContextCompat.getColor(context, R.color.dark_grey);
            textColorNotificationsDefault = ContextCompat.getColorStateList(context, R.color.color_dark_grey_light_orange);
            textColorNotificationsPressed = ContextCompat.getColor(context, R.color.light_orange);

            backgroundEventsDefault = R.drawable.background_white_dark_grey;
            backgroundEventsPressed = ContextCompat.getColor(context, R.color.dark_grey);
            textColorEventsDefault = ContextCompat.getColorStateList(context, R.color.color_dark_grey_white);
            textColorEventsPressed = ContextCompat.getColor(context, R.color.white);

            backgroundBirthdaysDefault = R.drawable.background_light_pink_dark_grey;
            backgroundBirthdaysPressed = ContextCompat.getColor(context, R.color.dark_grey);
            textColorBirthdaysDefault = ContextCompat.getColorStateList(context, R.color.color_dark_grey_light_pink);
            textColorBirthdaysPressed = ContextCompat.getColor(context, R.color.light_pink);
        }
    }
    /** This function will go to the Main Activity. */
    public void goToHomeFaceOutros(View view){
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

    /** Sets the colors to default */
    private void setButtonColorToDefault(){
        if (!homeIsPressed){
            homeButton.setBackgroundResource(backgroundHomeDefault);
            homeButton.setTextColor(textColorHomeDefault);
        }
        if (!notificantionsIsPressed){
            notificationsButton.setBackgroundResource(backgroundNotificationsdDefault);
            notificationsButton.setTextColor(textColorNotificationsDefault);
        }
        if (!eventsIsPressed){
            eventsButton.setBackgroundResource(backgroundEventsDefault);
            eventsButton.setTextColor(textColorEventsDefault);
        }
        if (!birthdaysIsPressed){
            birthdaysButton.setBackgroundResource(backgroundBirthdaysDefault);
            birthdaysButton.setTextColor(textColorBirthdaysDefault);
        }
    }

    /** Sets the colors for when is pressed */
    private void setButtonColorToPressed(){
        if (homeIsPressed){
            homeButton.setBackgroundColor(backgroundHomePressed);
            homeButton.setTextColor(textColorHomePressed);
        }
        if (notificantionsIsPressed){
            notificationsButton.setBackgroundColor(backgroundNotificationsPressed);
            notificationsButton.setTextColor(textColorNotificationsPressed);
        }
        if (eventsIsPressed){
            eventsButton.setBackgroundColor(backgroundEventsPressed);
            eventsButton.setTextColor(textColorEventsPressed);
        }
        if (birthdaysIsPressed){
            birthdaysButton.setBackgroundColor(backgroundBirthdaysPressed);
            birthdaysButton.setTextColor(textColorBirthdaysPressed);
        }
    }
}