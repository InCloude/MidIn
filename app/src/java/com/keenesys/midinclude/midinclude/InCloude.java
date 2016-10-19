package com.keenesys.midinclude.midinclude;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by pedro on 29/08/16.
 */
public class InCloude {

    /** O tempo dos botões
     *  The settings for the delay */
    private Integer delay;

    /** As preferências de cores
     *  Color preference */
    private Boolean darkBackground;

    /** As preferências do usuário
     * The user's preference */
    private SharedPreferences SP;

    /**
     * Inicialização das variáveis.
     *
     * The variables initialization.
     * @param activity
     */
    private void init(Activity activity){
        // The Preferences
        SP = PreferenceManager.getDefaultSharedPreferences(activity);
        darkBackground = SP.getBoolean("backgroundDark", getBackground());
        delay = SP.getInt("motorNeed",getDelay());
    }
    /**
     * Remove tudo da tela.
     *
     * Removes all the things from above the screen.
     * @param isso
     */
    public void makeClean(Activity isso){
        //Remove title bar
        isso.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        isso.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
    }
    /**
     * Faz o celular vibrar.
     *
     * Vibrates the celphone.
     * @param activity
     */
    public void vibrates(Activity activity){
        init(activity);
        Vibrator v =  (Vibrator) activity.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (delay>0)v.vibrate(delay*1000);
        else v.vibrate(500);
    }
    /**
     * Retorna o tempo, em segundos, que o botão demorará para
     * ser acionado.
     *
     * Return the time, in seconds, thats the button's action
     * will delay to  start.
     * @return t (s)
     */
    public int getDelay(){ return 3; }

    /**
     * Retorna se o fundo será escuro.
     *
     * Retuns if The app has a dark background.
     *
     * @return True for Dark Background, false for Light
     * Background
     */
    public boolean getBackground(){ return true; }

    /**
     *
     * @param activity
     * @return The app's Main Activity
     */
    public Intent getMainActivity(Activity activity){return new Intent(activity,MainActivity.class);}

    /**
     * Retorna o ícone escuro primário do botão que
     * volta para a Atividade principal do app.
     *
     * Returns the main activity button's default dark
     * icon.
     * @param activity
     * @return The main activity button's default dark
     * icon.
     */
    public Drawable getDarkMainDefaultDrawable(Context context){
        Drawable drawable = ContextCompat.getDrawable(context,R.drawable.icsl_dark_home);
        drawable.setBounds(0,-10,240,240);
        return drawable;
    }

    /**
     * Retorna o ícone escuro secundário do botão que
     * volta para a Atividade principal do app.
     *
     * Returns the main activity button's pressed dark
     * icon.
     * @param activity
     * @return The main activity button's pressed dark
     * icon.
     */
    public Drawable getDarkMainPressedDrawable(Context context){
        Drawable drawable = ContextCompat.getDrawable(context,R.drawable.ic_home_black_48dp_apppurple);
        drawable.setBounds(0,-50,240,240);
        return drawable;
    }

    /**
     * Retorna o ícone claro primário do botão que
     * volta para a Atividade principal do app.
     *
     * Returns the main activity button's default light
     * icon.
     * @param activity
     * @return The main activity button's default light
     * icon.
     */
    public Drawable getLightMainDefaultDrawable(Context context){
        Drawable drawable = ContextCompat.getDrawable(context,R.drawable.icsl_light_home);
        drawable.setBounds(0,-50,240,240);
        return drawable;
    }

    /**
     * Retorna o ícone claro secundário do botão que
     * volta para a Atividade principal do app.
     *
     * Returns the main activity button's pressed light
     * icon.
     * @param activity
     * @return The main activity button's pressed light
     * icon.
     */
    public Drawable getLightMainPressedDrawable(Context context){
        Drawable drawable = ContextCompat.getDrawable(context,R.drawable.ic_home_black_48dp_light_blue);
        drawable.setBounds(0,-50,240,240);
        return drawable;
    }
}
