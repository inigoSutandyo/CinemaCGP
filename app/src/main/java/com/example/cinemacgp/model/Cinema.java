package com.example.cinemacgp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Cinema {
    private int id;
    private String name;
    private String address;
    private Double latitude, longitude;
    private ArrayList<Theater> theaters;

    public Cinema(int id, String name, String address, Double latitude, Double longitude, Theater... theaters) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.theaters = new ArrayList<>();
        this.theaters.addAll(Arrays.asList(theaters));
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<Theater> getTheaters() {
        return theaters;
    }

    public void setTheaters(ArrayList<Theater> theaters) {
        this.theaters = theaters;
    }
}
