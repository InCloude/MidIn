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
import android.widget.ProgressBar;

public class Settings2Activity extends AppCompatActivity {

    private ProgressBar progressBar;
    private SharedPreferences prefs;
    private Intent intent;
    private InCloude inCloude;

    private Button mororNeed;
    private Button wMotorNeed;

    private ColorStateList textColorMotorNeedDefault;
    private ColorStateList textColorWMotorNeedDefault;
    private Integer textColorMottorNeedPressed;
    private Integer textColorWMottorNeedPressed;
    private Integer backgroundMottorNeedDefault;
    private Integer backgroundWMottorNeedDefault;
    private Integer backgroundMottorNeedPressed;
    private Integer backgroundWMottorNeedPressed;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inCloude = new InCloude();
        inCloude.makeClean(this);
        setContentView(R.layout.activity_settings2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(50);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        intent = new Intent(this, SettingsActivity.class);
        Initialize();
        putSomeColors();
    }

    private void Initialize() {
        context = getApplicationContext();
        mororNeed = (Button) findViewById(R.id.motor_need);
        wMotorNeed = (Button) findViewById(R.id.wmotor_need);
    }

    private void putSomeColors() {
        textColorMotorNeedDefault = ContextCompat.getColorStateList(context,R.color.color_white_light_blue);
    }

    public void setMotorNeed(View view) {
        prefs.edit().putInt("motorNeed", inCloude.getDelay()).commit();
        inCloude.vibrates(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, inCloude.getDelay() * 1000);
    }

    public void unsetMotorNeed(View view) {
        prefs.edit().putInt("motorNeed", 0).commit();
        inCloude.vibrates(this);
        startActivity(intent);
    }
}