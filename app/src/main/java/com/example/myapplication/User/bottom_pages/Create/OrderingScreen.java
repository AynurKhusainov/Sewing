package com.example.myapplication.User.bottom_pages.Create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.User.MainScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class OrderingScreen extends AppCompatActivity {

    Spinner spinSize, spinRaw;
    String spinner, spinner2;

    ImageButton minus, plus;
    TextView counter;
    TextView price_ord;
    public TextView type;
    ImageView back;
    ImageView order_img,image_view_fon;
    int i_price = 1;
    StorageReference storageReference;

    Button order;
    EditText description;
    String myFormat2 = "yyyy-MM-dd HH:mm:SS";
    String myFormat = "MM.dd.yyyy";
    SimpleDateFormat dateFormat2, dateFormat;
    final Calendar myCalendar = Calendar.getInstance();
    Date date_now;
    Uri myUri;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    int price = 990;
    String path;
    Boolean tm;
    Intent intenta;
    PorterDuffColorFilter greyFilter;
    String colorStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordering_screen);
        dateFormat2 = new SimpleDateFormat(myFormat2, Locale.getDefault());
        dateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        date_now = myCalendar.getTime();

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        order = findViewById(R.id.order);
        type = findViewById(R.id.type);
        order_img = findViewById(R.id.order_img);
        description = findViewById(R.id.description);
        price_ord = findViewById(R.id.price_ord);
        back = findViewById(R.id.back);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        image_view_fon = findViewById(R.id.image_view_fon);
        counter = findViewById(R.id.counter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myUri = Uri.parse(extras.getString("uri"));
            order_img.setImageURI(myUri);

        }
        type.setText(extras.getString("type_tshirt"));

        if (extras.getString("color").equals("1")){
            colorStr="#80000000";
            greyFilter= new PorterDuffColorFilter(getResources().getColor(R.color.black2), PorterDuff.Mode.SRC_ATOP);
            image_view_fon.setColorFilter(greyFilter);
        }else if (extras.getString("color").equals("2")){
            colorStr="#80FF5C45";
            greyFilter= new PorterDuffColorFilter(getResources().getColor(R.color.my12), PorterDuff.Mode.SRC_ATOP);
            image_view_fon.setColorFilter(greyFilter);
        }else if (extras.getString("color").equals("3")){
            colorStr="#80FFFFFF";
            greyFilter= new PorterDuffColorFilter(getResources().getColor(R.color.white2), PorterDuff.Mode.SRC_ATOP);
            image_view_fon.setColorFilter(greyFilter);
        }else if (extras.getString("color").equals("4")){
            colorStr="#80F6E366";
            greyFilter= new PorterDuffColorFilter(getResources().getColor(R.color.zeltiy2), PorterDuff.Mode.SRC_ATOP);
            image_view_fon.setColorFilter(greyFilter);
        }else if (extras.getString("color").equals("5")){
            colorStr="#80BB86FC";
            greyFilter= new PorterDuffColorFilter(getResources().getColor(R.color.purple_2002), PorterDuff.Mode.SRC_ATOP);
            image_view_fon.setColorFilter(greyFilter);
        }
        back.setOnClickListener(view -> {
            finish();
        });
        plus.setOnClickListener(view -> {
            i_price++;
            price_ord.setText(String.valueOf(price * i_price) + " руб");
            counter.setText(String.valueOf(i_price));
        });
        minus.setOnClickListener(view -> {
            if (i_price > 1) {
                i_price--;
            } else i_price = 1;
            price_ord.setText(String.valueOf(price * i_price) + " руб");
            counter.setText(String.valueOf(i_price));
        });

        spinSize = (Spinner) findViewById(R.id.spinSize);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.size));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSize.setAdapter(adapter);

        spinSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner = spinSize.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinRaw = (Spinner) findViewById(R.id.spinRaw);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.raw));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRaw.setAdapter(adapter2);

        spinRaw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner2 = spinRaw.getSelectedItem().toString();
                if (spinner2.equals("Бамбук")) {
                    price = 700;
                } else if (spinner2.equals("Шерсть")) {
                    price = 990;
                } else if (spinner2.equals("Шелк")) {
                    price = 900;
                } else if (spinner2.equals("Хлопок")) {
                    price = 850;
                } else if (spinner2.equals("Лен")) {
                    price = 790;
                } else if (spinner2.equals("Вискоза")) {
                    price = 790;
                } else if (spinner2.equals("Ацетат")) {
                    price = 850;
                } else if (spinner2.equals("Акрил")) {
                    price = 900;
                } else if (spinner2.equals("Полиэстер")) {
                    price = 900;
                } else if (spinner2.equals("Пенька")) {
                    price = 790;
                }
                price_ord.setText(price * i_price + " руб");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        order.setOnClickListener(view -> {

            Map<String, Object> userInfo = new HashMap<>();
            FirebaseUser user = mAuth.getCurrentUser();
            storageReference = FirebaseStorage.getInstance().getReference().child("images");
            StorageReference mountainsRef = storageReference.child("mountains.jpg");

            StorageReference mountainImagesRef = storageReference.child("" + dateFormat2.format(date_now) + " " + type.getText().toString());

            tm = false;
            mountainImagesRef.putFile(myUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mountainImagesRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (tm == true)
                                myCalendar.add(Calendar.DAY_OF_MONTH, -14);

                            DocumentReference df = fStore.collection("orders").document("" + dateFormat2.format(date_now) + " " + type.getText().toString());

                            userInfo.put("description", "Размер одежды: " + spinner + "; Количество: " + String.valueOf(i_price) + "; Материал для шитья: " + spinner2 + "; Примечание от заказчика: " + description.getText().toString());
                            userInfo.put("price", price * i_price);

                            userInfo.put("img_print", task.getResult().toString());
                            userInfo.put("img_color", colorStr);
                            userInfo.put("order_time", "" + dateFormat2.format(date_now));
                            myCalendar.add(Calendar.DAY_OF_MONTH, 14);
                            date_now = myCalendar.getTime();
                            userInfo.put("order_term", dateFormat.format(date_now));
                            userInfo.put("user_id", user.getUid());
                            userInfo.put("type", type.getText().toString());
                            userInfo.put("status", "cart");
                            df.set(userInfo);
                            Toast.makeText(OrderingScreen.this, "Заказ добавлен в корзину", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(OrderingScreen.this, MainScreen.class));
                            tm=true;

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(OrderingScreen.this, "error" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            mountainsRef.getName().equals(mountainImagesRef.getName());
            mountainsRef.getPath().equals(mountainImagesRef.getPath());


        });
    }
}