package com.example.myapplication.Entrance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Designer.DesignerMainScreen;
import com.example.myapplication.R;
import com.example.myapplication.User.MainScreen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInScreen extends AppCompatActivity {

    TextView forgot;
    Button enter;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    EditText mailET, passwordET;
    public DatabaseReference rootDatabaseRef;

    public static String PrefsSignIn = "prefs";
    SharedPreferences skip_log_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.sign_in_screen);

        enter = findViewById(R.id.login);
        forgot = findViewById(R.id.sUp_txt);
        mailET = findViewById(R.id.mailEdt);
        passwordET = findViewById(R.id.passwordEdt);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        rootDatabaseRef= FirebaseDatabase.getInstance().getReference().child("mail");

        skip_log_in = getSharedPreferences(SignInScreen.PrefsSignIn, 0);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInScreen.this, SignUpScreen.class));
            }
        });

    }

    private void LoginUser() {
        String mailSTR = mailET.getText().toString();
        String passwordSTR = passwordET.getText().toString();

        if (TextUtils.isEmpty(mailSTR)) {
            mailET.setError("Email не должен быть пустым");
            mailET.requestFocus();
        } else if (TextUtils.isEmpty(passwordSTR)) {
            passwordET.setError("Пароль не должен быть пустым");
            passwordET.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(mailSTR, passwordSTR).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    checkUserAccessLevel(authResult.getUser().getUid());
                    Toast.makeText(SignInScreen.this, "successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignInScreen.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void checkUserAccessLevel(String uid) {

        DocumentReference df=fStore.collection("users").document(uid);

        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                SharedPreferences.Editor skip_editor = skip_log_in.edit();
                skip_editor.putBoolean("hasLoggedIn", true).apply();
                if (documentSnapshot.get("role").equals("designer"))
                {
                    SharedPreferences.Editor editor = skip_log_in.edit();
                    editor.putString("rolee", "2").apply();
                    startActivity(new Intent(SignInScreen.this, DesignerMainScreen.class));
                    finish();
                }else if (documentSnapshot.getString("role").equals("user"))
                {
                    SharedPreferences.Editor editor = skip_log_in.edit();
                    editor.putString("rolee", "1").apply();
                    startActivity(new Intent(SignInScreen.this, MainScreen.class));
                    finish();
                }
            }
        });

    }
}