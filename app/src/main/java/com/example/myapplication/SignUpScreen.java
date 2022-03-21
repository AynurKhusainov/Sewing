package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DB.DAOEmployee;
import com.example.myapplication.DB.Employee;
import com.example.myapplication.Designer.DesignerMainScreen;
import com.example.myapplication.bottom_pages.Cart;
import com.example.myapplication.bottom_pages.Catalog;
import com.example.myapplication.bottom_pages.Create;
import com.example.myapplication.bottom_pages.Home;
import com.example.myapplication.bottom_pages.Menu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpScreen extends AppCompatActivity {
    ImageView customer;
    ImageView designer;
    TextView tv_sign_in, txt1_id, txt2_id;
    Button enter;
    Boolean custom = false, design = false;

    int role = 0;


    FirebaseAuth mAuth;
    EditText mailET, passwordET;
    DAOEmployee dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.sign_up_screen);

        txt1_id = findViewById(R.id.txt1_id);
        txt2_id = findViewById(R.id.txt2_id);
        customer = findViewById(R.id.customer);
        designer = findViewById(R.id.fashion_designer);
        tv_sign_in = findViewById(R.id.sIn_txt);
        enter = findViewById(R.id.login);

        dao=new DAOEmployee();
        mAuth = FirebaseAuth.getInstance();
        mailET = findViewById(R.id.mailEdt);
        passwordET = findViewById(R.id.passwordEdt);

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role = 1;
                custom = !custom;
                design = false;
                txt1_id.setText("Привет, Пользователь");
                txt2_id.setText("Пожалуйста, заполните форму ниже, чтобы начать");

                if (custom == true) {
                    customer.setBackgroundResource(R.drawable.users_s_up_back);
                    designer.setBackgroundResource(R.drawable.users_s_up_back_def);
                }
            }
        });
        designer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role = 2;
                design = !design;
                custom = false;
                txt1_id.setText("Привет, Дизайнер");
                txt2_id.setText("Пожалуйста, заполните форму ниже, чтобы начать");

                if (design == true) {
                    designer.setBackgroundResource(R.drawable.users_s_up_back);
                    customer.setBackgroundResource(R.drawable.users_s_up_back_def);
                }
            }
        });


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
            }
        });
    }

    private void createUser() {
        String mailSTR = mailET.getText().toString();
        String passwordSTR = passwordET.getText().toString();

        {
            if (role == 0) {
                Toast.makeText(SignUpScreen.this, "Выберите кто вы", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(mailSTR, passwordSTR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (role==1){
                                startActivity(new Intent(SignUpScreen.this, MainScreen.class));
                                Employee emp=new Employee(role);
                                dao.add(emp).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(SignUpScreen.this, "Успешно создан!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SignUpScreen.this, "Ошибка создания", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else if (role==2){
                                startActivity(new Intent(SignUpScreen.this, DesignerMainScreen.class));
                                Employee emp=new Employee(role);
                                dao.add(emp).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(SignUpScreen.this, "Успешно создан!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SignUpScreen.this, "Ошибка создания", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            Toast.makeText(SignUpScreen.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        }
    }
}