package com.training.covidpersonaltracker;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.training.covidpersonaltracker.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //making toolbar as action bar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Drawer Toggle (Humburger icom)
//        drawerToggle = new  ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.open,R.string.close);
//        drawerToggle.syncState();
//        binding.drawerLayout.addDrawerListener(drawerToggle);

        callFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(v -> {
            switch(v.getItemId()) {
                case R.id.nav_menu_home:
                    callFragment(new HomeFragment());
                    break;
                case R.id.nav_menu_nearby:
                    callFragment(new NearbyFragment());
                    break;
                case R.id.nav_menu_profile:
                    callFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }

    private void callFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }
}