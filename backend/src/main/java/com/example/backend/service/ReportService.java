package com.example.backend.service;

import com.example.backend.model.Location;
import com.example.backend.model.LoggedReport;
import com.example.backend.model.Severity;
import com.example.backend.model.Symptom;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ReportService {

    private static final Duration REPORT_LIFETIME = Duration.ofDays(5);

    // 100.0 rounds to 2 decimal places (about 1.1 km). Use 1000.0 for 3 (about 110 m).
    private final List<LoggedReport> reports = new CopyOnWriteArrayList<>();

    public LoggedReport createReport(String userId, List<Symptom> symptoms,
                                     Location location, Severity severity) {

        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (symptoms == null || symptoms.isEmpty()) {
            throw new IllegalArgumentException("At least one symptom is required");
        }
        if (severity == null) {
            throw new IllegalArgumentException("severity is required");
        }
        if (location == null
                || location.getLatitude() < -90 || location.getLatitude() > 90
                || location.getLongitude() < -180 || location.getLongitude() > 180) {
            throw new IllegalArgumentException("A valid location is required");
        }


        Instant loggedAt = Instant.now();
        Instant expiresAt = loggedAt.plus(REPORT_LIFETIME);

        Location approximate = new Location(
                round(location.getLatitude()),
                round(location.getLongitude()));

        LoggedReport report = new LoggedReport();
        report.setReportId(UUID.randomUUID().toString());
        report.setUserId(userId);
        report.setSymptoms(symptoms);
        report.setLocation(approximate);
        report.setSeverity(severity);
        report.setLoggedAt(loggedAt);
        report.setExpiresAt(expiresAt);

        reports.add(report);
        return report;
    }

    public List<LoggedReport> getActiveReports() {
        Instant now = Instant.now();
        return reports.stream()
                .filter(report -> report.getExpiresAt().isAfter(now))
                .toList();
    }

}