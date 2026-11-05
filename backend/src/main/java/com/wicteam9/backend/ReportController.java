
package com.wicteam9.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @PostMapping
    public ResponseEntity<?> submitReport(
            @RequestBody ReportRequest report) {

        // Make sure all required fields are present
        if (report.getLocationId() == null ||
            report.getLocationId() <= 0 ||
            report.getSource() == null ||
            report.getSeverity() == null) {

            return ResponseEntity.badRequest()
                    .body(Map.of(
                        "error", "Missing or invalid fields"
                    ));
        }

        // Validate source
        if (!report.getSource().equals("SELF") &&
            !report.getSource().equals("SURROUNDINGS")) {

            return ResponseEntity.badRequest()
                    .body(Map.of(
                        "error", "Invalid source"
                    ));
        }

        // Validate severity
        if (!report.getSeverity().equals("MILD") &&
            !report.getSeverity().equals("MODERATE") &&
            !report.getSeverity().equals("SEVERE")) {

            return ResponseEntity.badRequest()
                    .body(Map.of(
                        "error", "Invalid severity"
                    ));
        }

        // TODO: Connect to teammate's PostgreSQL database
        // and save the report here.

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of(
                    "message",
                    "Report validated, but database saving is not implemented"
                ));
    }
}
