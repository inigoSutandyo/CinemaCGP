package com.example.cinemacgp.model;

public class Booking {
    private String name, email;
    private Movie movie;
    private Cinema cinema;
    private Theater theater;

    public Booking(String name, String email, Movie movie, Cinema cinema, Theater theater) {
        this.name = name;
        this.email = email;
        this.movie = movie;
        this.cinema = cinema;
        this.theater = theater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
