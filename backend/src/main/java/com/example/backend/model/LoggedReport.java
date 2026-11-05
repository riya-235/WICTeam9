package com.example.backend.model;
import java.time.Instant;

public class LoggedReport {

    private String reportId;
    private String userId;

    private List<Symptom> symptoms;

    private Location location;

    private Severity severity;

    private Instant loggedAt;
    private Instant expiresAt;

}