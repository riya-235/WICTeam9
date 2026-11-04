
-- UCSD Sickness Tracking Database
-- PostgreSQL Schema

-- 1. Campus Locations
CREATE TABLE IF NOT EXISTS locations (
    location_id BIGSERIAL PRIMARY KEY,
    location_name VARCHAR(100) NOT NULL UNIQUE,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 2. Anonymous Sickness Reports
CREATE TABLE IF NOT EXISTS sickness_reports (
    report_id BIGSERIAL PRIMARY KEY,

    location_id BIGINT NOT NULL
        REFERENCES locations(location_id),

    source VARCHAR(20) NOT NULL
        CHECK (source IN ('SELF', 'SURROUNDINGS')),

    severity VARCHAR(20) NOT NULL
        CHECK (severity IN ('MILD', 'MODERATE', 'SEVERE')),

    created_at TIMESTAMPTZ NOT NULL
        DEFAULT CURRENT_TIMESTAMP
);

-- 3. Indexes
CREATE INDEX IF NOT EXISTS idx_reports_location
ON sickness_reports(location_id);

CREATE INDEX IF NOT EXISTS idx_reports_created_at
ON sickness_reports(created_at);

-- 4. Heatmap View (Last 7 Days)
CREATE OR REPLACE VIEW sickness_heatmap AS
SELECT
    l.location_id,
    l.location_name,
    l.latitude,
    l.longitude,

    COUNT(r.report_id) AS total_reports,

    COUNT(r.report_id) FILTER (
        WHERE r.source = 'SELF'
    ) AS self_reports,

    COUNT(r.report_id) FILTER (
        WHERE r.source = 'SURROUNDINGS'
    ) AS surroundings_reports,

    COUNT(r.report_id) FILTER (
        WHERE r.severity = 'MILD'
    ) AS mild_reports,

    COUNT(r.report_id) FILTER (
        WHERE r.severity = 'MODERATE'
    ) AS moderate_reports,

    COUNT(r.report_id) FILTER (
        WHERE r.severity = 'SEVERE'
    ) AS severe_reports

FROM locations l
LEFT JOIN sickness_reports r
    ON l.location_id = r.location_id
    AND r.created_at >= NOW() - INTERVAL '7 days'

GROUP BY
    l.location_id,
    l.location_name,
    l.latitude,
    l.longitude;
