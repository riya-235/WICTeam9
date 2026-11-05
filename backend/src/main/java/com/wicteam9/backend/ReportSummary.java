
package com.wicteam9.backend;

public class ReportSummary {

    private Long locationId;
    private String locationName;
    private Long totalReports;

    public ReportSummary(
            Long locationId,
            String locationName,
            Long totalReports) {

        this.locationId = locationId;
        this.locationName = locationName;
        this.totalReports = totalReports;
    }

    public Long getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public Long getTotalReports() {
        return totalReports;
    }
}
