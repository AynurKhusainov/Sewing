package com.example.myapplication.User.bottom_pages.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Entrance.SignInScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    public static String PrefsSignUp = "prefs";
    SharedPreferences skip_log_in;

    EditText NameEdt;
    EditText mail;
    EditText numberEdt;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    DocumentReference df;
    AuthCredential credential;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_profile);

        NameEdt = findViewById(R.id.NameEdt);
        mail = findViewById(R.id.mail);
        numberEdt = findViewById(R.id.numberEdt);

        ImageView back = findViewById(R.id.back);
        ImageView out = findViewById(R.id.out);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        df = fStore.collection("users").document(user.getUid());


        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        NameEdt.setText(document.getString("name"));
                        mail.setText(document.getString("mail"));
                        numberEdt.setText(document.getString("password"));
                    } else {
                    }
                } else {
                    Toast.makeText(Profile.this, "error2", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
    }
}