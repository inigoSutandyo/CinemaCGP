package com.example.cinemacgp.parser;

import android.util.Log;

import com.example.cinemacgp.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieParser {

    public static ArrayList<Movie> parseList(String string) {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(string);
            JSONArray data = obj.getJSONArray("data");
            Log.d("SCROLL", "length: " + data.length());
            int len = data.length();
            for (int i = 0; i < 25; i++) {
                JSONObject json = data.getJSONObject(i);
                int year = -1;
                int id = json.getInt("mal_id");
                try {
                    year = json.getInt("year");
                } catch (Exception e) {
                    year = -1;
                }

                String title = json.getString("title");
                String image =  json.getJSONObject("images")
                        .getJSONObject("jpg")
                        .getString("large_image_url");
                String synopsis = json.getString("synopsis");
                String type = json.getString("type");
                String rating = json.getString("rating");
                Double score = json.getDouble("score");
//
                movies.add(new Movie(id, year, score, synopsis, title, type, rating, image));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("SCROLL", "total: " + movies.size());
        return movies;
    }
}
