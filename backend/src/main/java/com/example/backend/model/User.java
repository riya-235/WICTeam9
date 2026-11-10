package com.example.backend.model;

public class User {

    private String userId;
    private String passwordHash;

    // No-argument constructor
    public User() {
    }

    // Constructor that sets all fields
    public User(String userId, String passwordHash) {
        this.userId = userId;
        this.passwordHash = passwordHash;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}