/*
    Esse e o MindInclude. O objetivo dele e promover a
    reabilitacao digital.
    Ele SEMPRE SERA GRATUITO e de CÓDIGO ABERTO.
    Agradecimentos a:
    = Ana Lucia Mancini - analuciamancini@gmail.com
    = Nathan de Mauro Leitao - nathan.leitao@gmail.com
    = Maria Dulce Colombo - mdcolombo@uol.com.br
    = Maria Julia Colombo - majucolombo@gmail.com
    = Thiago Iglesias - thiago.iglesias@keenesys.com
    = Vicente Colombo - vicentecolombo@uol.com.br
 */
package com.keenesys.midinclude.midinclude;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.cast.framework.Session;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

import static com.keenesys.midinclude.midinclude.R.color.AppPurple;
import static com.keenesys.midinclude.midinclude.R.color.AppYellow;
import static com.keenesys.midinclude.midinclude.R.color.white;
import static com.keenesys.midinclude.midinclude.R.drawable.background_brown_light_blue;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "WkMDMclXKomIaoVBwy30dglzJ";
    private static final String TWITTER_SECRET = "RFBj2wtXOTnQveR6ywLlaLyQkFYOxnPlrxwnjjcMwcXDn9AQmo";


    /* The buttons */
    private Button facebookButton;
    private Button twitterButton;
    private Button settingsButton;

    /* Variables to know if the buttons were pressed */
    private static Boolean facebookIsPressed = false;
    private static Boolean twitterIsPressed = false;
    private static Boolean settingsIsPressed = false;

    /** The intent */
    private Intent intent;

    /* The context */
    private Context context;

    /* The text colors and background state lists */
    private ColorStateList textColorFacebookDefault;
    private ColorStateList textColorTwitterDefault;
    private ColorStateList textColorSettingsDefault;
    private Integer textColorFacebookPressed;
    private Integer textColorTwitterPressed;
    private Integer textColorSettingsPressed;
    private Integer backgroundFacebookDefault;
    private Integer backgroundTwitterDefault;
    private Integer backgroundSettingsDefault;
    private Integer backgroundFacebookPressed;
    private Integer backgroundTwitterPressed;
    private Integer backgroundSettingsPressed;

    /* The settings for the delay */
    private Integer delay;

    // The Layout
    private RelativeLayout Rela;

    /* Color preference */
    private Boolean darkBackground;

    /* The user's preference */
    private SharedPreferences SP;

    private InCloude inCloude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        inCloude = new InCloude();
        inCloude.makeClean(this);
        setContentView(R.layout.activity_main);
        Initialize();
        putSomeColors();
        setButtonColorToDefault();
    }

    /**
     * Funcao que inicializa todas as variaveis
     *
     * Function that initialize all the variables
     */
    private void Initialize(){
        Rela =(RelativeLayout) findViewById(R.id.layout_main);
        // Declaring the context
        context = getApplicationContext();

        // The Preferences
        SP = PreferenceManager.getDefaultSharedPreferences(this);
        darkBackground = SP.getBoolean("backgroundDark", inCloude.getBackground());
        delay = SP.getInt("motorNeed",inCloude.getDelay());

        // Find the buttons
        facebookButton = (Button) findViewById(R.id.facebook);
        twitterButton = (Button) findViewById(R.id.twitter);
        settingsButton = (Button) findViewById(R.id.settings_button);
    }

    /**
     * Funcao para definir as cores dos botões de acordo com
     * as preferências do usuario
     *
     * Function for the user's preference colors
     */
    private void putSomeColors(){
        if (!darkBackground){
            Rela.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.light_blue));
            textColorFacebookPressed = ContextCompat.getColor(context, R.color.black);
            backgroundFacebookPressed = ContextCompat.getColor(context, R.color.light_blue);
            backgroundFacebookDefault = R.drawable.background_dark_green_light_blue;
            textColorFacebookDefault = ContextCompat.getColorStateList(context, R.color.color_light_blue_dark_green);

            textColorTwitterPressed = ContextCompat.getColor(context, R.color.brown);
            backgroundTwitterPressed = ContextCompat.getColor(context, R.color.light_blue);
            backgroundTwitterDefault = R.drawable.background_brown_light_blue;
            textColorTwitterDefault = ContextCompat.getColorStateList(context, R.color.color_light_blue_brown);

            textColorSettingsPressed = ContextCompat.getColor(context, R.color.white);
            backgroundSettingsPressed = ContextCompat.getColor(context, R.color.light_blue);
            backgroundSettingsDefault = R.drawable.background_white_light_blue;
            textColorSettingsDefault = ContextCompat.getColorStateList(context, R.color.color_light_blue_white);
        }else{
            Rela.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.AppPurple));
            textColorFacebookPressed = ContextCompat.getColor(context, R.color.AppYellow);
            backgroundFacebookPressed = ContextCompat.getColor(context, R.color.AppPurple);
            backgroundFacebookDefault = R.drawable.button_main;
            textColorFacebookDefault = ContextCompat.getColorStateList(context, R.color.color_text_button_main);

            textColorTwitterPressed = ContextCompat.getColor(context, R.color.light_green);
            backgroundTwitterPressed = ContextCompat.getColor(context, R.color.AppPurple);
            backgroundTwitterDefault = R.drawable.bacground_light_green_apppurple;
            textColorTwitterDefault = ContextCompat.getColorStateList(context, R.color.color_apppurple_light_green);

            textColorSettingsPressed = ContextCompat.getColor(context, R.color.white);
            backgroundSettingsPressed = ContextCompat.getColor(context, R.color.AppPurple);
            backgroundSettingsDefault = R.drawable.background_white_apppurple;
            textColorSettingsDefault = ContextCompat.getColorStateList(context, R.color.color_apppurple_white);
        }
    }


    /**
        Funcao chamada ao apertar o botao do Facebook

        This function is called when the Facebook's button
        is pushed
     */
    public void goToFacebook(View view) {
        facebookIsPressed = true;
        setButtonColorToPressed();

        intent = new Intent(this, FacebookActivity.class);

        inCloude.vibrates(this);
        // Execute some code after n seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                facebookIsPressed = false;
                setButtonColorToDefault();
            }
        }, delay*1000);
    }

    /**
        Funcao chamada ao apertar o botao das settings

        This function is called when the Setting's button
        is pushed
     */
    public void goToSettings(View view) {
        settingsIsPressed = true;
        setButtonColorToPressed();

        intent = new Intent(this, Settings2Activity.class);

        inCloude.vibrates(this);
        // Execute some code after n seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                settingsIsPressed = false;
                setButtonColorToDefault();
            }
        }, delay*1000);
    }

    /**
        Funcao chamada ao apertar o botao do Twitter

        This function is called when the Twitter's button
        is pushed
     */
    public void goToTwitter(View view) {
        twitterIsPressed = true;
        setButtonColorToPressed();
        intent = new Intent(this, TwitterLoginActivity.class);

        inCloude.vibrates(this);
        // Execute some code after n seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                twitterIsPressed = false;
                setButtonColorToDefault();
                startActivity(intent);
            }
        }, delay*1000);
    }

    private void setButtonColorToDefault() {
        if (!facebookIsPressed) {
            facebookButton.setTextColor(textColorFacebookDefault);
            facebookButton.setBackgroundResource(backgroundFacebookDefault);
        }
        if (!twitterIsPressed) {
            twitterButton.setTextColor(textColorTwitterDefault);
            twitterButton.setBackgroundResource(backgroundTwitterDefault);
        }
        if (true){
            settingsButton.setBackgroundResource(backgroundSettingsDefault);
            settingsButton.setTextColor(textColorSettingsDefault);
        }
    }

    private void setButtonColorToPressed(){
        if (facebookIsPressed) {
            facebookButton.setBackgroundColor(backgroundFacebookPressed);
            facebookButton.setTextColor(textColorFacebookPressed);
        }
        if (twitterIsPressed){
            twitterButton.setBackgroundColor(backgroundTwitterPressed);
            twitterButton.setTextColor(textColorTwitterPressed);
        }
        if (settingsIsPressed){
            settingsButton.setBackgroundColor(backgroundSettingsPressed);
            settingsButton.setTextColor(textColorSettingsPressed);
        }
    }
}