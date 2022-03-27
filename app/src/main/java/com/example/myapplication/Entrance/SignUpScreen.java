package com.example.myapplication.Entrance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Designer.DesignerMainScreen;
import com.example.myapplication.R;
import com.example.myapplication.User.MainScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpScreen extends AppCompatActivity {
    TextView tv_sign_in, txt1_id, txt2_id;
    Button enter;
    Boolean custom = false, design = false;
    String mailSTR, passwordSTR, nameSTR, role="user";

    public static String PrefsSignUp = "prefs";
    SharedPreferences skip_log_in;

    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    EditText mailET, passwordET, nameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.sign_up_screen);

        tv_sign_in = findViewById(R.id.sIn_txt);
        enter = findViewById(R.id.login);


        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        mailET = findViewById(R.id.mailEdt);
        passwordET = findViewById(R.id.passwordEdt);
        nameET = findViewById(R.id.NameEdt);

        skip_log_in = getSharedPreferences(SignUpScreen.PrefsSignUp, 0);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();

            }
        });
        tv_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpScreen.this, SignInScreen.class));
                finish();
            }
        });
    }

    private void createUser() {
        mailSTR = mailET.getText().toString();
        passwordSTR = passwordET.getText().toString();
        nameSTR = nameET.getText().toString();
        {
            mAuth.createUserWithEmailAndPassword(mailSTR, passwordSTR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user=mAuth.getCurrentUser();
                        DocumentReference df=fstore.collection("users").document(user.getUid());
                        Map<String,Object> userInfo=new HashMap<>();
                        userInfo.put("name",nameSTR);
                        userInfo.put("mail",mailSTR);
                        userInfo.put("password",passwordSTR);
                        userInfo.put("role",role);

                        df.set(userInfo);

                        SharedPreferences.Editor editor = skip_log_in.edit();
                        editor.putString("rolee", "1").apply();
                        startActivity(new Intent(SignUpScreen.this, MainScreen.class));
                        finish();

                        SharedPreferences.Editor skip_editor = skip_log_in.edit();
                        skip_editor.putBoolean("hasLoggedIn", true).apply();

                    } else {
                        Toast.makeText(SignUpScreen.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
}