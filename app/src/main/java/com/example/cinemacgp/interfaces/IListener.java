package com.example.cinemacgp.interfaces;

import com.example.cinemacgp.model.Movie;

import java.util.ArrayList;

public interface IListener {
    void onSuccess(Movie... movies);
}
