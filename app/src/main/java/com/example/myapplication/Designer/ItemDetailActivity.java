package com.example.myapplication.Designer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class ItemDetailActivity extends AppCompatActivity {

    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_item_detail);


        TextView txt_HeaderArticleDetail = this.findViewById(R.id.txtHeaderArticleDetail);
        TextView txt_DesArticleDetail = this.findViewById(R.id.txtDesArticleDetail);
        TextView txtPrice = this.findViewById(R.id.txtPrice);


        back = findViewById(R.id.btnBackArticleDetail);

        back.setOnClickListener(view -> {
            finish();
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            txt_HeaderArticleDetail.setText(extras.getString("Type"));
            txtPrice.setText(extras.getString("Header"));
            txt_DesArticleDetail.setText(extras.getString("Description"));

        }
    }
}