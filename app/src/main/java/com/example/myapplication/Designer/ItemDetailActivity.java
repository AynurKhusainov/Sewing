package com.example.myapplication.Designer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

public class ItemDetailActivity extends AppCompatActivity {

    ImageButton back;
    ImageView imageViewArticleDetail;
    ImageView image_view_fon;
    PorterDuffColorFilter greyFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_item_detail);


        TextView txt_HeaderArticleDetail = this.findViewById(R.id.txtHeaderArticleDetail);
        TextView txt_DesArticleDetail = this.findViewById(R.id.txtDesArticleDetail);
        TextView price = this.findViewById(R.id.price);
        TextView status = this.findViewById(R.id.status);


        back = findViewById(R.id.btnBackArticleDetail);
        imageViewArticleDetail = findViewById(R.id.imageViewArticleDetail);
        image_view_fon = findViewById(R.id.image_view_fon);

        back.setOnClickListener(view -> {
            finish();
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            txt_HeaderArticleDetail.setText(extras.getString("Type"));
            price.setText(extras.getInt("price") + " руб.");
            txt_DesArticleDetail.setText(extras.getString("Description"));
            if (extras.getString("status").equals("completed")) {
                status.setText("Выполнен");
            } else if (extras.getString("status").equals("adopted")) {
                status.setText("Выполняется");
            } else if (extras.getString("status").equals("cart")) {
                status.setText("В корзине");
            } else if (extras.getString("status").equals("consideration"))
                status.setText("На рассмотрении");


            String img = extras.getString("img_frontS");
            String color = extras.getString("img_colorS");
            Picasso.get().load(img).into(imageViewArticleDetail);

            greyFilter= new PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_ATOP);

            image_view_fon.setColorFilter(greyFilter);

        }
    }
}