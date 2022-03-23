package com.example.myapplication.User.bottom_pages.Home.HomeSlides;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class SliderHomeAdapter extends FragmentStateAdapter {

    public SliderHomeAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new SecondSlideHome();
            case 2:
                return new ThirdSlideHome();
            case 3:
                return new FourthSlideHome();
        }
        return new FirstSlideHome();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
