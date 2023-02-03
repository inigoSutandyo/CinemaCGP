package com.example.cinemacgp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.cinemacgp.R;
import com.example.cinemacgp.controller.MovieController;
import com.example.cinemacgp.fragment.CinemaFragment;
import com.example.cinemacgp.fragment.MovieFragment;
import com.example.cinemacgp.interfaces.IFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements IFragment {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNavigation();
        selectItem();
        replaceFragment(new MovieFragment());
//        MovieController.fetchTop(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initNavigation() {
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void selectItem() {
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.close();
            if (item.getItemId() == R.id.movies_nav && !isMovieFragment()) {
                replaceFragment(new MovieFragment());
            } else if (item.getItemId() == R.id.cinemas_nav && !isCinemaFragment()) {
                replaceFragment(new CinemaFragment());
            }
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isMovieFragment() {
        boolean flag = getSupportFragmentManager().findFragmentByTag("FRAGMENT") instanceof MovieFragment;
        return flag;
    }

    private boolean isCinemaFragment() {
        boolean flag = getSupportFragmentManager().findFragmentByTag("FRAGMENT") instanceof CinemaFragment;
        return flag;
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
        );
        fragmentTransaction.replace(R.id.frame_layout, fragment, "FRAGMENT");
        fragmentTransaction.commit();
    }
}