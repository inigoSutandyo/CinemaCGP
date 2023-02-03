package com.example.cinemacgp.controller;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinemacgp.model.Movie;
import com.example.cinemacgp.parser.MovieParser;
import com.example.cinemacgp.util.UrlFetcher;
import java.util.ArrayList;

public class MovieController {

    public static void fetchTop(Activity activity) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url =  UrlFetcher.getStringUrl("/top/anime");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            ArrayList<Movie> movies = MovieParser.parseList(response);

        }, error -> {
            String err = error.getMessage();
            Log.d("API", err);
        });
        queue.add(stringRequest);
    }
}
