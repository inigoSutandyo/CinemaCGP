package com.example.cinemacgp.db;

import com.example.cinemacgp.model.Booking;
import com.example.cinemacgp.model.Cinema;
import com.example.cinemacgp.model.Movie;
import com.example.cinemacgp.model.Theater;

import java.util.ArrayList;
import java.util.Vector;

public class Database {
    /**
     * SINGLETON class to save data in runtime (for booking)
     */

    private static volatile Database instance = null;
    private static Object obj = new Object();

    private Vector<Movie> movies;
    private ArrayList<Cinema> cinemas;
    private ArrayList<Booking> bookings;

    private Database() {
        bookings = new ArrayList<>();
        movies = new Vector<>();
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

    public Vector<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public boolean isMoviesEmpty() {
        return movies.size() < 1;
    }

    public boolean isBookingsEmpty() {
        return bookings.size() < 1;
    }

    public boolean isCinemasEmpty() { return cinemas.size() < 1; }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Cinema findCinema(int id) {
        for (Cinema cinema:
             cinemas) {
            if (cinema.getId() == id) {
                return cinema;
            }
        }
        return null;
    }

    public void populateCinemas() {
        /**
         * Seeder function to populate cinema data
         */
        cinemas.clear();
        addCinema(new Cinema(1, "Cinema CGP Alpha", "Apple Street, 701",
                -6.193924061113853,
                106.78813220277623,
                new Theater(1, 120),
                new Theater(2, 223),
                new Theater(3, 150)));
        addCinema(new Cinema(2, "Cinema CGP Beta", "Orange Street, West Avenue, 223",
                -6.20175020412279,
                106.78223868546155,
                new Theater(1, 100),
                new Theater(2, 120),
                new Theater(3, 120),
                new Theater(4, 150)));
    }
}
