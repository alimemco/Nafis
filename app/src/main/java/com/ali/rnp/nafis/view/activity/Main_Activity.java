package com.ali.rnp.nafis.view.activity;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.fragment.FragmentForm;
import com.ali.rnp.nafis.view.fragment.FragmentHome;
import com.ali.rnp.nafis.view.fragment.FragmentUser;

public class Main_Activity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentHome fragmentHome;
    private FragmentUser fragmentUser;
    private FragmentForm fragmentForm;
    android.support.v4.app.FragmentManager fragmentManager;

    private ImageView shopBtn;
    private ImageView searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupToolbar();
        setupFragments();
        setupBottomNavigation();


    }


    private void setupFragments() {
        fragmentHome = new FragmentHome();
        fragmentUser = new FragmentUser();
        fragmentForm = new FragmentForm();

        fragmentManager = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction HomeTransaction = fragmentManager.beginTransaction();
        HomeTransaction.add(R.id.mainFragmentContainer, fragmentHome);
        HomeTransaction.commit();
    }

    private void setupBottomNavigation() {
        bottomNavigationView = findViewById(R.id.mainActivity_BottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.bottom_navigation_home:
                        android.support.v4.app.FragmentTransaction HomeTransaction = fragmentManager.beginTransaction();
                        HomeTransaction.replace(R.id.mainFragmentContainer, fragmentHome);
                        HomeTransaction.commit();
                        return true;

                    case R.id.bottom_navigation_form:
                        android.support.v4.app.FragmentTransaction FormTransaction = fragmentManager.beginTransaction();
                        FormTransaction.replace(R.id.mainFragmentContainer, fragmentForm);
                        FormTransaction.commit();
                        return true;

                    case R.id.bottom_navigation_userManagement:
                        android.support.v4.app.FragmentTransaction UserTransaction = fragmentManager.beginTransaction();
                        UserTransaction.replace(R.id.mainFragmentContainer, fragmentUser);
                        UserTransaction.commit();
                        return true;
                }
                return false;
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        shopBtn = findViewById(R.id.activity_main_toolbar_shop_xml);
        searchBtn = findViewById(R.id.activity_main_toolbar_search_xml);

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main_Activity.this, "Shop", Toast.LENGTH_SHORT).show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main_Activity.this, "Search", Toast.LENGTH_SHORT).show();
            }
        });


        DrawerLayout drawerLayout = findViewById(R.id.main_drawer_layout);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.White));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.mainActivity_BottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_navigation_home);
    }
}
