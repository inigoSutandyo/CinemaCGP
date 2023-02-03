package com.example.cinemacgp.controller;

import com.example.cinemacgp.db.Database;
import com.example.cinemacgp.model.Booking;
import com.example.cinemacgp.model.Cinema;
import com.example.cinemacgp.model.Movie;
import com.example.cinemacgp.model.Theater;

public class CinemaController {
    private static Database database = Database.getInstance();
    public static Cinema getCinemaById(int id) {
        return database.findCinema(id);
    }

    public static void addBooking(String name, String email, Cinema cinema, Movie movie, Theater theater) {
        database.addBooking(new Booking(name,email,movie,cinema,theater));
    }
}
