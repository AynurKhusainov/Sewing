package com.example.myapplication.User.bottom_pages.Create;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class CreateCatalogScreen extends AppCompatActivity {

    LinearLayout t_shirt;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_catalog);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));

        t_shirt=findViewById(R.id.t_shirt);
        back=findViewById(R.id.back);

        back.setOnClickListener(view -> {
            finish();
        });

        Intent intent=new Intent(CreateCatalogScreen.this,CreateScreen.class);

        t_shirt.setOnClickListener(view -> {
            Bundle extras = getIntent().getExtras();
            intent.putExtra("type_tshirt",extras.getString("type_tshirt"));
            startActivity(intent);
        });

    }
}