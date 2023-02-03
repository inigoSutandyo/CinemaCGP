package com.example.cinemacgp.db;

import com.example.cinemacgp.model.Cinema;
import com.example.cinemacgp.model.Movie;
import com.example.cinemacgp.model.Theater;

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

    public void populateCinemas() {
        /**
         * Seeder function to populate cinema data
         */
        cinemas.clear();
        addCinema(new Cinema("Cinema CGP Alpha", "Apple Street, 701",
                -6.193924061113853,
                106.78813220277623,
                new Theater(1, 120),
                new Theater(2, 223),
                new Theater(3, 150)));
        addCinema(new Cinema("Cinema CGP Beta", "Orange Street, West Avenue, 223",
                6.20175020412279,
                106.78223868546155,
                new Theater(1, 100),
                new Theater(2, 120),
                new Theater(3, 120),
                new Theater(4, 150)));
    }
}
