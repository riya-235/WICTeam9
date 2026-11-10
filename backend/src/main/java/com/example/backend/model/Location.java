package com.example.backend.model;

public class Location {

    private double latitude;
    private double longitude;

    // No-argument constructor
    public Location() {
    }

    // Constructor that sets both values
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}