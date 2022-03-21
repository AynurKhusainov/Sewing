package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Designer.DesignerMainScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInScreen extends AppCompatActivity {

    TextView forgot;
    Button enter;

    FirebaseAuth mAuth;
    EditText mailET, passwordET;
    int role = 1;

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
            mAuth.signInWithEmailAndPassword(mailSTR, passwordSTR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if (role == 1) {
                            startActivity(new Intent(SignInScreen.this, MainScreen.class));
                        } else if (role == 2) {
                            startActivity(new Intent(SignInScreen.this, DesignerMainScreen.class));
                        }
                    } else {
                        Toast.makeText(SignInScreen.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}