package com.example.myapplication.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.User.bottom_pages.Cart;
import com.example.myapplication.User.bottom_pages.Catalog;
import com.example.myapplication.User.bottom_pages.Create.Create;
import com.example.myapplication.User.bottom_pages.Home.Home;
import com.example.myapplication.User.bottom_pages.Menu.Menu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreen extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public BottomNavigationView bottomNav;
    Fragment selectedFragment;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.grey));
        setContentView(R.layout.main_screen);

        bottomNav = findViewById(R.id.bot_nav);
        bottomNav.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Catalog()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectedFragment = null;

        id = 0;

        switch (item.getItemId()) {

            case R.id.catalog:
                id = 0;
                bottomNav.getMenu().getItem(0).setChecked(true);
                selectedFragment = new Catalog();
                break;
            case R.id.create:
                id = 1;
                bottomNav.getMenu().getItem(1).setChecked(true);
                selectedFragment = new Create();
                break;
            case R.id.cart:
                id = 2;
                bottomNav.getMenu().getItem(2).setChecked(true);
                selectedFragment = new Cart();
                break;
            case R.id.menu:
                id = 3;
                bottomNav.getMenu().getItem(3).setChecked(true);
                selectedFragment = new Menu();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).addToBackStack(null).commit();

        return true;
    }

    @Override
    public void onBackPressed() {
        if (bottomNav.getSelectedItemId() == R.id.catalog) {
            this.finishAffinity();
        } else {
            bottomNav.setSelectedItemId(R.id.catalog);
        }
    }

}