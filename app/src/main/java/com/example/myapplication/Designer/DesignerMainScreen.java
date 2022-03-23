package com.example.myapplication.Designer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.Entrance.SignInScreen;

public class DesignerMainScreen extends AppCompatActivity {

    Button exit;
    public static String PrefsSignUp = "prefs";
    SharedPreferences skip_log_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.designer_main_screen);

        exit = findViewById(R.id.exit);

        skip_log_in = getSharedPreferences(DesignerMainScreen.PrefsSignUp, 0);//push
        exit.setOnClickListener(view ->
        {
            SharedPreferences.Editor editor = skip_log_in.edit();
            editor.putString("rolee", "0").apply();
            startActivity(new Intent(DesignerMainScreen.this, SignInScreen.class));
            finish();
        });
    }
}