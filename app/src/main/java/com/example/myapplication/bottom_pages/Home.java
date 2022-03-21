package com.example.myapplication.bottom_pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.bottom_pages.HomeSlides.SliderHomeAdapter;
import com.google.android.material.tabs.TabLayout;

public class Home extends Fragment {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentManager fm;
    SliderHomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);


        tabLayout = v.findViewById(R.id.tab_layH);
        pager2 = v.findViewById(R.id.pagerH);

        fm = getFragmentManager();
        adapter = new SliderHomeAdapter(fm, getLifecycle());

        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ball_h));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ball_h));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ball_h));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ball_h));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        return v;
    }
}