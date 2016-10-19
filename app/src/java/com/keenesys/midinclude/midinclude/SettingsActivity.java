package com.keenesys.midinclude.midinclude;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private SharedPreferences prefs;

    /** For know if the buttons are pressed */
    private static Boolean darkIsPressed = false;
    private static Boolean lightIsPressed = false;

    /** The Layout */
    private RelativeLayout Rela;

    /** The settings for the delay */
    private Integer delay;

    /** The user's preference */
    private SharedPreferences SP;

    private Integer textColor;

    private Context context;

    /** The Buttons and Colors */
    private Button darkButton;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(100);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void setBackgroundLight(View view) {
        prefs.edit().putBoolean("backgroundDark", false).commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void setBackgroundDark(View view) {
        prefs.edit().putBoolean("backgroundDark", true).commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
