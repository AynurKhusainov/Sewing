package com.example.myapplication.Designer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MyOrders extends AppCompatActivity {

    ImageButton my_orders;

    ArrayList<ItemModel> arrayList;
    ItemAdapter modelAdapter;

    GridView mlistViewArticle;
    String header,desc,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_my_orders);

        my_orders=findViewById(R.id.back_my_orders);

        my_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mlistViewArticle = findViewById(R.id.grwid_orders);

        arrayList = new ArrayList<>();
        modelAdapter = new ItemAdapter(MyOrders.this, R.layout.item, arrayList);

        arrayList.add(new ItemModel(1, R.drawable.futbolka, "990 Руб",
                "Футболка 3D — это удобная футболка прямого кроя, выполненная из синтетической ткани, " +
                        "приятной на ощупь. Свойства ткани позволяют нанести яркий принт по всей поверхности, " +
                        "сохраняя при этом цену за изделие доступной при персонализированном производстве ( все " +
                        "товары на Vsemayki создаются под заказ). Футболка быстро сохнет, не мнется и сохраняет форму. " +
                        "Можно заказать со своим дизайном.\n" +
                        "\nДетали: прямой крой, круглый вырез горловины, длина до линии бедер \n" +
                        "\nТип нанесения: сублимация на ткани\n" +
                        "\nСостав: 100% полиэстер\n" +
                        "\nПараметры модели на фото: рост 170 см, обхваты 80-60-93 см, размер футболки S\n" +
                        "\nПравила ухода: стирать при температуре не выше 40 градусов, без отбеливателя." +
                        " Гладить при температуре не выше 110 градусов",
                "Футболка"));
        arrayList.add(new ItemModel(2, R.drawable.platie, "990 Руб", "Платье на широких бретелях со слегка " +
                "прилегающим силуэтом из комфортной синтетической ткани. Принт наносится по всей поверхности и сохраняет свою " +
                "яркость даже спустя сотни стирок. Можно заказать со своим дизайном.\n" +
                "\nРост модели на фото: 172 см\n" +
                "\nТип нанесения: сублимация на ткани\n" +
                "\nДетали: полуприлегающий силуэт, широкие бретели, круглый вырез горловины, удлиненный подол сзади\n" +
                "\nПараметры модели на фото: 90-63-82 см\n" +
                "\nСостав: 100% полиэстер\n" +
                "\nРазмер модели на фото: S\n" +
                "\nПравила ухода: стирать при температуре не выше 40 градусов без отбеливателя. " +
                "Гладить при температуре не выше 110 градусов.", "Платье"));

        mlistViewArticle.setAdapter(modelAdapter);
    }
}