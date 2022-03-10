package com.training.covidpersonaltracker;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.training.covidpersonaltracker.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        drawerLayout = binding.drawerLayout;
        //making toolbar as action bar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Drawer Toggle (Humburger icom)
        drawerToggle = new  ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.open,R.string.close);
        drawerToggle.syncState();
        binding.drawerLayout.addDrawerListener(drawerToggle);

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

        binding.sideNavigationView.setNavigationItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.nav_side_menu_home:
                    callFragment(new HomeFragment());
                    binding.bottomNavigationView.setSelectedItemId(R.id.nav_menu_home);
                    drawerLayout.close();
                    break;
                case R.id.nav_side_menu_nearby:
                    callFragment(new NearbyFragment());
                    binding.bottomNavigationView.setSelectedItemId(R.id.nav_menu_nearby);
                    drawerLayout.close();
                    break;
                case R.id.nav_side_menu_profile:
                    callFragment(new ProfileFragment());
                    binding.bottomNavigationView.setSelectedItemId(R.id.nav_menu_profile);
                    drawerLayout.close();
                    break;
                case R.id.nav_side_menu_settings:
                    Toast.makeText(this, "Settings tab is selected", Toast.LENGTH_SHORT).show();
                    drawerLayout.close();
                    break;
                case R.id.nav_side_menu_logout:
                    Toast.makeText(this, "Logout tab is selected", Toast.LENGTH_SHORT).show();
                    drawerLayout.close();
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