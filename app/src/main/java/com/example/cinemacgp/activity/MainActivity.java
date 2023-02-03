package com.example.cinemacgp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.cinemacgp.R;
import com.example.cinemacgp.db.Database;
import com.example.cinemacgp.fragment.CinemaFragment;
import com.example.cinemacgp.fragment.HomeFragment;
import com.example.cinemacgp.interfaces.IFragment;
import com.example.cinemacgp.model.Cinema;
import com.example.cinemacgp.model.Theater;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements IFragment {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Database.getInstance();
        database.populateCinemas();
        initNavigation();
        selectItem();
        replaceFragment(new HomeFragment());

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
            if (item.getItemId() == R.id.home_nav && !isMovieFragment()) {
                replaceFragment(new HomeFragment());
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
        boolean flag = getSupportFragmentManager().findFragmentByTag("FRAGMENT") instanceof HomeFragment;
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