package com.example.cinemacgp.db;

import com.example.cinemacgp.model.Cinema;
import com.example.cinemacgp.model.Movie;

import java.util.ArrayList;

public class Database {
    /**
     * SINGLETON class to save data in runtime (for booking)
     */

    private static volatile Database instance = null;
    private static Object obj = new Object();

    private ArrayList<Movie> movies;
    private ArrayList<Cinema> cinemas;

    private Database() {
        movies = new ArrayList<>();
        cinemas = new ArrayList<>();
    }

    /**
     * Thread safe singleton
     * @return
     */
    public static Database getInstance() {
        Database result = instance;
        if (result == null) {
            synchronized (obj) {
                result = instance;
                if (result == null) {
                    instance = result = new Database();
                }
            }
        }
        return result;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public boolean isMoviesEmpty() {
        return movies.size() < 1;
    }

    public boolean isCinemasEmpty() { return cinemas.size() < 1; }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }
}
