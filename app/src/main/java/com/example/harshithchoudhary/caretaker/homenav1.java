package com.example.harshithchoudhary.caretaker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class homenav1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homenav1);

        BottomNavigationView navigationView = findViewById(R.id.navigation);

        final HomeFragment homeFragment = new HomeFragment();
        final BookingFragment bookingFragment = new BookingFragment();
        final ProfileFragment profileFragment = new ProfileFragment();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id==R.id.navigation_home){
                    setFragment(homeFragment);
                }else if (id==R.id.navigation_booking){
                    setFragment(bookingFragment);
                }else if (id==R.id.navigation_profile){
                    setFragment(profileFragment);
                }
                return false;
            }
        });
        navigationView.setSelectedItemId(R.id.navigation_home);
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace (R.id.frame,fragment);
        fragmentTransaction.commit();
    }

}
