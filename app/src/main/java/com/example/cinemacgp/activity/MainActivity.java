package com.example.cinemacgp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cinemacgp.R;
import com.example.cinemacgp.controller.MovieController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MovieController.fetchTop(this);
    }
}