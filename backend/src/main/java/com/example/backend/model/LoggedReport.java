package com.example.backend.model;
import java.time.Instant;
import java.util.List;

public class LoggedReport {

    private String reportId;
    private String userId;

    private List<Symptom> symptoms;

    private Location location;

    private Severity severity;

    private Instant loggedAt;
    private Instant expiresAt;
    public LoggedReport() {
    }

    // Constructor that sets all fields
    public LoggedReport(String reportId, String userId, List<Symptom> symptoms,
                        Location location, Severity severity,
                        Instant loggedAt, Instant expiresAt) {
        this.reportId = reportId;
        this.userId = userId;
        this.symptoms = symptoms;
        this.location = location;
        this.severity = severity;
        this.loggedAt = loggedAt;
        this.expiresAt = expiresAt;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Instant getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(Instant loggedAt) {
        this.loggedAt = loggedAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

}