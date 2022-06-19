package com.example.myapplication.User.bottom_pages.Create;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;


public class Create extends Fragment {

    Button create_male;
    Button btn2;
    Button btn3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_create, container, false);

        create_male=v.findViewById(R.id.create_male);
        btn2=v.findViewById(R.id.btn2);
        btn3=v.findViewById(R.id.btn3);

        Intent intent=new Intent(getActivity(),CreateCatalogScreen.class);

        create_male.setOnClickListener(view -> {
            intent.putExtra("type_tshirt","Мужская одежда");
            startActivity(intent);
        });
        btn2.setOnClickListener(view -> {
            intent.putExtra("type_tshirt","Женская одежда");
            startActivity(intent);
        });
        btn3.setOnClickListener(view -> {
            intent.putExtra("type_tshirt","Детская одежда");
            startActivity(intent);
        });

        return v;
    }
}