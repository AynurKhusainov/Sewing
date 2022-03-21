package com.example.myapplication.SliderPages;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.myapplication.R;
import com.example.myapplication.SignInScreen;
import com.example.myapplication.SignUpScreen;

public class ThirdSlide extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_third_slide, container, false);

        Button sign_in=v.findViewById(R.id.sign_in);
        Button sign_up=v.findViewById(R.id.sign_up);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SignInScreen.class));
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SignUpScreen.class));
            }
        });

        return v;
    }
}