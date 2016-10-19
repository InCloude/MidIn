package com.keenesys.midinclude.midinclude;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InCloude inCloude = new InCloude();
        Intent intent = inCloude.getMainActivity(this);
        startActivity(intent);
        finish();
    }
}
