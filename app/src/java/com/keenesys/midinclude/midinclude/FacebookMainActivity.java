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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FacebookMainActivity extends AppCompatActivity {
    /* For knowing if there's a button pressed */
    private static Boolean homeIsPressed = false;
    private static Boolean outrosIsPressed = false;
    /* The Context */
    private Context context;
    /* Buttons */
    private Button outrosButton;
    private Button homeButton;
    /* The adapter to the ListView */
    private ArrayAdapter adapter;
    private JSONArray feed;
    private JSONObject newsFeed;
    private ArrayList<String> wall;
    /* The activity's intent */
    private Intent intent;
    /** The settings for the delay */
    private Integer delay;

    /** The Layout */
    private RelativeLayout Rela;

    /** Color preference */
    private Boolean darkBackground;

    /** The user's preference */
    private SharedPreferences SP;

    private ListView listView;

    private InCloude inCloude;

    /** The Colors */
    private ColorStateList textColorHomeDefault;
    private ColorStateList textColorOutrosDefault;
    private Integer textColorHomePressed;
    private Integer textColorOutrosPressed;
    private Integer backgroundHomeDefault;
    private Integer backgroundOutrosDefault;
    private Integer backgroundHomePressed;
    private Integer backgroundOutrosPressed;
    private Drawable icslHomeDefault;
    private Drawable icHomePressed;
    private Drawable icslOutrosDefault;
    private Drawable icOutrosPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inCloude = new InCloude();
        inCloude.makeClean(this);
        setContentView(R.layout.activity_facebook_main);
        Initialize();
        putSomeColors();
        setButtonColorToDefault();
        FacebookSdk.sdkInitialize(getApplicationContext());

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        /* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/feed",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        wall = new ArrayList<String>();
                        try {
                            newsFeed = response.getJSONObject();
                            feed = newsFeed.getJSONArray("data");
                            int length = feed.length();
                            for (int i = 0; i < length; i++) wall.add(feed.getJSONObject(i).getString("story"));
                            }catch(JSONException e){
                                e.printStackTrace();
                            }
                        }

                }
        ).executeAsync();
        wall = new ArrayList<String>();
        wall.add("pedro");
        wall.add("henrique");
        wall.add("de");
        wall.add("oliveira");
        wall.add("colombo");
        adapter = new ArrayAdapter<String>(this, R.layout.facebook_news_feed, wall);
        listView.setAdapter(adapter);

    }

    private void Initialize(){
        Rela = (RelativeLayout) findViewById(R.id.layout_facebook_news_feed);
        // Call to the context
        context = getApplicationContext();
        listView = (ListView) findViewById(R.id.mobile_list);
        // Picking the buttons
        outrosButton = (Button) findViewById(R.id.facebook_outros);
        homeButton = (Button) findViewById(R.id.home);

        // The Preferences
        SP = PreferenceManager.getDefaultSharedPreferences(this);
        darkBackground = SP.getBoolean("backgroundDark", inCloude.getBackground());
        delay = SP.getInt("motorNeed",inCloude.getDelay());
    }

    private void putSomeColors(){
        if (darkBackground){
            Rela.setBackgroundColor(ContextCompat.getColor(context,R.color.dark_green));

            backgroundHomeDefault = R.drawable.background_apppurple_dark_green;
            backgroundHomePressed = ContextCompat.getColor(context,R.color.dark_green);
            textColorHomeDefault = ContextCompat.getColorStateList(context,R.color.color_dark_green_appyellow);
            textColorHomePressed = ContextCompat.getColor(context,R.color.AppYellow);
            icslHomeDefault = ContextCompat.getDrawable(context,R.drawable.icsl_dark_home);
            icHomePressed = ContextCompat.getDrawable(context,R.drawable.ic_home_black_48dp_apppurple);

            backgroundOutrosDefault = R.drawable.background_light_grey_dark_green;
            backgroundOutrosPressed = ContextCompat.getColor(context,R.color.dark_green);
            textColorOutrosDefault = ContextCompat.getColorStateList(context,R.color.color_dark_green_light_grey);
            textColorOutrosPressed = ContextCompat.getColor(context,R.color.light_grey);
            icslOutrosDefault = ContextCompat.getDrawable(context,R.drawable.icsl_dark_outros);
            icOutrosPressed = ContextCompat.getDrawable(context,R.drawable.ic_add_black_48dp_lightgrey);
        }else {
            Rela.setBackgroundColor(ContextCompat.getColor(context,R.color.light_green));

            backgroundHomeDefault = R.drawable.background_light_blue_light_green;
            backgroundHomePressed = ContextCompat.getColor(context,R.color.light_green);
            textColorHomeDefault = ContextCompat.getColorStateList(context,R.color.color_light_green_light_blue);
            textColorHomePressed = ContextCompat.getColor(context,R.color.light_blue);
            icslHomeDefault = ContextCompat.getDrawable(context,R.drawable.icsl_light_home);
            icHomePressed = ContextCompat.getDrawable(context,R.drawable.ic_home_black_48dp_dark_green);

            backgroundOutrosDefault = R.drawable.background_dark_grey_light_green;
            backgroundOutrosPressed = ContextCompat.getColor(context,R.color.light_green);
            textColorOutrosDefault = ContextCompat.getColorStateList(context,R.color.color_light_green_dark_grey);
            textColorOutrosPressed = ContextCompat.getColor(context,R.color.dark_grey);
            icslOutrosDefault = ContextCompat.getDrawable(context,R.drawable.icsl_light_outros);
            icOutrosPressed = ContextCompat.getDrawable(context,R.drawable.ic_add_black_48dpp_dark_grey);
        }
        icslHomeDefault.setBounds(0,-50,240,240);
        icHomePressed.setBounds(0,-50,240,240);
        icslOutrosDefault.setBounds(0,0,300,300);
        icOutrosPressed.setBounds(0,0,300,300);
    }

    public void goToOutros(View view){
        outrosIsPressed = true;
        setButtonColorToPressed();
        intent = new Intent(this, FacebookOutrosActivity.class);

        inCloude.vibrates(this);
        // Execute some code after n seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                outrosIsPressed = false;
                setButtonColorToDefault();
                startActivity(intent);
            }
        }, delay*1000);
    }
    /** This function will go to the Main Activity. */
    public void goToHomeFaceMain(View view){
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
            homeButton.setCompoundDrawables(null,icslHomeDefault,null,null);
        }
        if (!outrosIsPressed){
            outrosButton.setBackgroundResource(backgroundOutrosDefault);
            outrosButton.setTextColor(textColorOutrosDefault);
            homeButton.setCompoundDrawables(null,icslOutrosDefault,null,null);
        }
    }

    /** Sets the colors for when is pressed */
    private void setButtonColorToPressed(){
        if (homeIsPressed){
            homeButton.setBackgroundColor(backgroundHomePressed);
            homeButton.setTextColor(textColorHomePressed);
            homeButton.setCompoundDrawables(null,icHomePressed,null,null);
        }
        if (outrosIsPressed){
            outrosButton.setBackgroundColor(backgroundOutrosPressed);
            outrosButton.setTextColor(textColorOutrosPressed);
            homeButton.setCompoundDrawables(null,icOutrosPressed,null,null);
        }
    }
}
