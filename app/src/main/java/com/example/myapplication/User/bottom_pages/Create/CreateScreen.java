package com.example.myapplication.User.bottom_pages.Create;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.example.myapplication.R;

public class CreateScreen extends AppCompatActivity {

    ImageButton btn;
    ImageView imageView, palette, back,image_view_fon;
    Button next;

    ImageView col1,col2,col3,col4,col5;

    Uri uri;
    PorterDuffColorFilter greyFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_screen);

        next = findViewById(R.id.next);

        col1 = findViewById(R.id.col1);
        col2 = findViewById(R.id.col2);
        col3 = findViewById(R.id.col3);
        col4 = findViewById(R.id.col4);
        col5 = findViewById(R.id.col5);

        Intent intent = new Intent(this, OrderingScreen.class);

        intent.putExtra("color","3");
        col1.setOnClickListener(view -> {
            intent.putExtra("color","1");
            greyFilter= new PorterDuffColorFilter(getResources().getColor(R.color.black2), PorterDuff.Mode.SRC_ATOP);
            image_view_fon.setColorFilter(greyFilter);
        });
        col2.setOnClickListener(view -> {
            intent.putExtra("color","2");
            greyFilter= new PorterDuffColorFilter(getResources().getColor(R.color.my12), PorterDuff.Mode.SRC_ATOP);
            image_view_fon.setColorFilter(greyFilter);
        });
        col3.setOnClickListener(view -> {
            intent.putExtra("color","3");
            greyFilter= new PorterDuffColorFilter(getResources().getColor(R.color.white2), PorterDuff.Mode.SRC_ATOP);
            image_view_fon.setColorFilter(greyFilter);
        });
        col4.setOnClickListener(view -> {
            intent.putExtra("color","4");
            greyFilter= new PorterDuffColorFilter(getResources().getColor(R.color.zeltiy2), PorterDuff.Mode.SRC_ATOP);
            image_view_fon.setColorFilter(greyFilter);
        });
        col5.setOnClickListener(view -> {
            intent.putExtra("color","5");
            greyFilter= new PorterDuffColorFilter(getResources().getColor(R.color.purple_2002), PorterDuff.Mode.SRC_ATOP);
            image_view_fon.setColorFilter(greyFilter);
        });

        next.setOnClickListener(view -> {
            if (uri!=null) {

                Bundle extras = getIntent().getExtras();
                intent.putExtra("type_tshirt",extras.getString("type_tshirt"));
                intent.putExtra("uri", uri.toString());
                startActivity(intent);
            }else {
                Toast.makeText(this, "Создайте дизайн", Toast.LENGTH_SHORT).show();
            }
        });

        back = findViewById(R.id.back);
        btn = findViewById(R.id.btn);
        imageView = findViewById(R.id.image_view);
        image_view_fon=findViewById(R.id.image_view_fon);

        image_view_fon.setColorFilter(greyFilter);

        back.setOnClickListener(view -> finish());
        btn.setOnClickListener(view -> {
            checkPermission();
        });
    }


    private void checkPermission() {

        int permission = ActivityCompat.checkSelfPermission(CreateScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            pickImage();
        } else {
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CreateScreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            } else {
                pickImage();
            }
        }
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            switch (requestCode) {
                case 100:
                    Intent intent = new Intent(this, DsPhotoEditorActivity.class);
                    intent.setData(uri);
                    intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "images");
                    intent.putExtra(DsPhotoEditorConstants.DS_TOOL_BAR_BACKGROUND_COLOR, Color.parseColor("#F6E366"));
                    intent.putExtra(DsPhotoEditorConstants.DS_MAIN_BACKGROUND_COLOR, Color.parseColor("#ffffff"));
                    intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, new int[]{DsPhotoEditorActivity.TOOL_WARMTH,
                            DsPhotoEditorActivity.TOOL_PIXELATE});
                    startActivityForResult(intent, 101);
                    break;
                case 101:
                    imageView.setImageURI(uri);
                    Toast.makeText(this, "Фотография сохранена", Toast.LENGTH_SHORT).show();

            }
        }
    }
}