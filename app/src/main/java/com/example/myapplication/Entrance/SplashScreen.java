package com.example.myapplication.Entrance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.myapplication.Designer.DesignerMainScreen;
import com.example.myapplication.R;
import com.example.myapplication.User.MainScreen;
import com.example.myapplication.Entrance.SliderPages.SliderScreen;
import com.example.myapplication.User.bottom_pages.Menu.Profile;

public class SplashScreen extends AppCompatActivity {

    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.zeltiy));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.splash_screen);


        h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences(SignUpScreen.PrefsSignUp, 0);
                SharedPreferences sharedPreferences_in = getSharedPreferences(SignInScreen.PrefsSignIn, 0);
                SharedPreferences sharedPreferences_out = getSharedPreferences(Profile.PrefsSignUp, 0);
                SharedPreferences sharedPreferences_out2 = getSharedPreferences(DesignerMainScreen.PrefsSignUp, 0);

                boolean hasLoggedIn_in = sharedPreferences_in.getBoolean("hasLoggedIn", false);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);

                String role_in = sharedPreferences.getString("rolee", "null");
                String role_up = sharedPreferences_in.getString("rolee", "null");
                String role_out = sharedPreferences_out.getString("rolee", "null");
                String role_out2 = sharedPreferences_out.getString("rolee", "null");

                if ((hasLoggedIn || hasLoggedIn_in)&&(role_in.equals("1")||role_up.equals("1"))) {
                    startActivity(new Intent(SplashScreen.this, MainScreen.class));
                    finish();
                } else if ((hasLoggedIn || hasLoggedIn_in)&&(role_in.equals("2")||role_up.equals("2"))) {
                    startActivity(new Intent(SplashScreen.this, DesignerMainScreen.class));
                    finish();
                } else if (role_out.equals("0")||role_out2.equals("0")){
                    startActivity(new Intent(SplashScreen.this, SignInScreen.class));
                    finish();
                } else {
                    Intent mainIntent = new Intent(SplashScreen.this, SliderScreen.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, 1000);
    }
}