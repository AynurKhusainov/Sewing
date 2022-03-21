package com.example.myapplication.bottom_pages;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.About;
import com.example.myapplication.Favorite;
import com.example.myapplication.Profile;
import com.example.myapplication.R;

public class Menu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_menu, container, false);

        LinearLayout about=v.findViewById(R.id.about);
        LinearLayout profile=v.findViewById(R.id.profile);
        LinearLayout favorite=v.findViewById(R.id.favorite);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), About.class));
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Favorite.class));
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Profile.class));
            }
        });

        return v;
    }
}