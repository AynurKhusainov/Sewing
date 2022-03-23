package com.example.myapplication.User.bottom_pages.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.Entrance.SignInScreen;

public class Profile extends AppCompatActivity {

    public static String PrefsSignUp = "prefs";
    SharedPreferences skip_log_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_profile);

        ImageView back=findViewById(R.id.back);
        ImageView out=findViewById(R.id.out);
        Button save=findViewById(R.id.save_profile);

        skip_log_in = getSharedPreferences(Profile.PrefsSignUp, 0);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = skip_log_in.edit();
                editor.putString("rolee", "0").apply();
                startActivity(new Intent(Profile.this, SignInScreen.class));
                finishAffinity();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}