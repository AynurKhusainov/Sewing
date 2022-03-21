package com.example.myapplication.SliderPages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class SliderScreen extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentManager fm;
    SliderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.zeltiy));
        setContentView(R.layout.slider_screen);

        tabLayout = findViewById(R.id.tab_lay);
        pager2 = findViewById(R.id.pager);

        fm = getSupportFragmentManager();
        adapter = new SliderAdapter(fm, getLifecycle());

        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.slider_ball));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.slider_ball));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.slider_ball));

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

    }
}
