package com.thushan.ecommerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thushan.ecommerce.R;
import com.thushan.ecommerce.fragments.HomeFragment;
import com.thushan.ecommerce.fragments.SellerFragment;

public class HomeActivity extends AppCompatActivity {

//    FirebaseAuth auth;
//    FirebaseUser user;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    MenuItem logOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav);
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        loadFragment(new HomeFragment());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.buyer:
                        loadFragment(new HomeFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.seller:
                        loadFragment(new SellerFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.share:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.location:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });


//        auth=FirebaseAuth.getInstance();
//        user=auth.getCurrentUser();
//        if(user==null){
//            Intent intent=new Intent(HomeActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }else{
//            Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
//        }

    }

//    public void goToLogOut(View view) {
//        FirebaseAuth.getInstance().signOut();
//        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//
//    public void goToStart(View view) {
//
//        Intent intent=new Intent(HomeActivity.this, OnBoardingActivity.class);
//        startActivity(intent);
//        finish();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_manu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.menu_logout) {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
         else if (id==R.id.menu_my_cart) {

            Intent intent=new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void loadFragment(Fragment fragment){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}