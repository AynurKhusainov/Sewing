package com.example.myapplication.Designer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Entrance.SignInScreen;

import java.util.ArrayList;

public class DesignerMainScreen extends AppCompatActivity {

    Button exit, my_orders;
    public static String PrefsSignUp = "prefs";
    SharedPreferences skip_log_in;
    ArrayList<ItemModel> arrayList;
    ItemAdapter modelAdapter,adapter;

    GridView mlistViewArticle;
    String header,desc,type;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.designer_main_screen);

        exit = findViewById(R.id.exit);
        my_orders = findViewById(R.id.my_orders);

        skip_log_in = getSharedPreferences(DesignerMainScreen.PrefsSignUp, 0);//push

        exit.setOnClickListener(view -> {
            SharedPreferences.Editor editor = skip_log_in.edit();
            editor.putString("rolee", "0").apply();
            startActivity(new Intent(DesignerMainScreen.this, SignInScreen.class));
            finish();
        });
        my_orders.setOnClickListener(view -> {
            startActivity(new Intent(DesignerMainScreen.this, MyOrders.class));
        });

        mlistViewArticle = findViewById(R.id.article_list);

        arrayList = new ArrayList<>();
        modelAdapter = new ItemAdapter(DesignerMainScreen.this, R.layout.item, arrayList);

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
                        " Гладить при температуре не выше 110 градусов\n" +
                        "\nСрок заказа 27.03.2022\n" +
                        "\nЦена: 2 000 руб.",
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
                "Гладить при температуре не выше 110 градусов.\n" +
                "\nСрок заказа 27.03.2022\n" +
                "\nЦена: 3 500 руб.", "Платье"));
        arrayList.add(new ItemModel(3, R.drawable.bruki, "990 Руб", "Брюки 3D сшиты из мягкой синтетической ткани," +
                " внутри тонкий приятный на ощупь слой. Принт наносится по всей поверхности. В сочетании с толстовкой на молнии можно" +
                " собрать спортивный костюм с собственным дизайном.\n" +
                "\nТип нанесения: сублимация на ткани\n" +
                "\nСостав: 100% полиэстер\n" +
                "\nДетали: манжеты по низу, эластичный пояс регулируется шнурком, по бокам два кармана без " +
                "застежек, внутренняя часть кармана из мелкой сетки\n" +
                "\nПравила ухода: стирать при температуре не выше 40 градусов, без отбеливателя. Гладить " +
                "при температуре не выше 60 градусов\n" +
                "\nСрок заказа 27.03.2022\n" +
                "\nЦена: 2 500 руб.", "Брюки"));
        arrayList.add(new ItemModel(4, R.drawable.pidjak, "990 Руб", "Состав: 100% полиэстер\n" +
                "\nТип нанесения: Сублимация на ткани\n" +
                "\nДетали: Костюм из тонкого трикотажа с мягким начёсом внутри. Толстовка : " +
                "прямой силуэт, рукава втачные одношовные, накладной карман-кенгуру, по низу изделия " +
                "и рукава притачные манжеты. Брюки: эластичный пояс на резинке регулируется шнурком, карманы " +
                "в боковых швах, эластичные манжеты на резинке по низу брюк.\n" +
                "\nСезон: Демисезон\n" +
                "\nПравила ухода: Стирать при температуре не выше 40 градусов. Гладить при температуре не " +
                "выше 110 градусов. Не использовать отбеливатель\n"+
                "\nСрок заказа 27.03.2022\n" +
                "\nЦена: 2 200 руб.", "Пиджак"));
        arrayList.add(new ItemModel(5, R.drawable.pidjak, "990 Руб", "Состав: 100% полиэстер\n" +
                "\nТип нанесения: Сублимация на ткани\n" +
                "\nДетали: Костюм из тонкого трикотажа с мягким начёсом внутри. Толстовка : " +
                "прямой силуэт, рукава втачные одношовные, накладной карман-кенгуру, по низу изделия " +
                "и рукава притачные манжеты. Брюки: эластичный пояс на резинке регулируется шнурком, карманы " +
                "в боковых швах, эластичные манжеты на резинке по низу брюк.\n" +
                "\nСезон: Демисезон\n" +
                "\nПравила ухода: Стирать при температуре не выше 40 градусов. Гладить при температуре не " +
                "выше 110 градусов. Не использовать отбеливатель\n"+
                "\nСрок заказа 27.03.2022\n" +
                "\nЦена: 2 200 руб.", "Пиджак"));
        arrayList.add(new ItemModel(6, R.drawable.pidjak, "990 Руб", "Состав: 100% полиэстер\n" +
                "\nТип нанесения: Сублимация на ткани\n" +
                "\nДетали: Костюм из тонкого трикотажа с мягким начёсом внутри. Толстовка : " +
                "прямой силуэт, рукава втачные одношовные, накладной карман-кенгуру, по низу изделия " +
                "и рукава притачные манжеты. Брюки: эластичный пояс на резинке регулируется шнурком, карманы " +
                "в боковых швах, эластичные манжеты на резинке по низу брюк.\n" +
                "\nСезон: Демисезон\n" +
                "\nПравила ухода: Стирать при температуре не выше 40 градусов. Гладить при температуре не " +
                "выше 110 градусов. Не использовать отбеливатель\n"+
                "\nСрок заказа 27.03.2022\n" +
                "\nЦена: 2 200 руб.", "Пиджак"));
        arrayList.add(new ItemModel(7, R.drawable.pidjak, "990 Руб", "Состав: 100% полиэстер\n" +
                "\nТип нанесения: Сублимация на ткани\n" +
                "\nДетали: Костюм из тонкого трикотажа с мягким начёсом внутри. Толстовка : " +
                "прямой силуэт, рукава втачные одношовные, накладной карман-кенгуру, по низу изделия " +
                "и рукава притачные манжеты. Брюки: эластичный пояс на резинке регулируется шнурком, карманы " +
                "в боковых швах, эластичные манжеты на резинке по низу брюк.\n" +
                "\nСезон: Демисезон\n" +
                "\nПравила ухода: Стирать при температуре не выше 40 градусов. Гладить при температуре не " +
                "выше 110 градусов. Не использовать отбеливатель\n"+
                "\nСрок заказа 27.03.2022\n" +
                "\nЦена: 2 200 руб.", "Пиджак"));


        mlistViewArticle.setAdapter(modelAdapter);

        mlistViewArticle.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int etem=i;

                new AlertDialog.Builder(DesignerMainScreen.this)
                        .setMessage("Вы хотите выполнить этот заказ?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SharedPreferences.Editor editor = skip_log_in.edit();
                                editor.putString("adding_in_mmy_orders", "1").apply();

                                arrayList.remove(etem);
                                modelAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Нет", null)
                        .show();

                return true;
            }
        });
        mlistViewArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                header = arrayList.get(i).getHeader();
                desc = arrayList.get(i).getDescription();
                image = arrayList.get(i).getImage();
                type = arrayList.get(i).getType();

                Intent intent = new Intent(DesignerMainScreen.this, ItemDetailActivity.class);

                intent.putExtra("Header", header);
                intent.putExtra("Description", desc);
                intent.putExtra("Image", image);
                intent.putExtra("Type", type);
                startActivity(intent);
            }
        });
    }
}