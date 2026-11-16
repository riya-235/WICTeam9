import * as maplibregl from 'https://unpkg.com/maplibre-gl@6.16.0/dist/maplibre-gl.mjs';

const map = new maplibregl.Map({
    container: 'map',
    style: 'https://tiles.openfreemap.org/styles/liberty',
    center: [-117.2340, 32.8801],
    zoom: 14
});

map.addControl(new maplibregl.NavigationControl());


// FICTIONAL SAMPLE FLU REPORTS
// Report counts are made up for testing purposes.
// Coordinates are approximate campus locations.

const sampleReports = [
    {
        location: "Revelle College",
        coordinates: [-117.2410, 32.8758],
        reports: 4
    },
    {
        location: "John Muir College",
        coordinates: [-117.2430, 32.8800],
        reports: 7
    },
    {
        location: "Thurgood Marshall College",
        coordinates: [-117.2350, 32.8815],
        reports: 3
    },
    {
        location: "Earl Warren College",
        coordinates: [-117.2340, 32.8840],
        reports: 6
    },
    {
        location: "Eleanor Roosevelt College",
        coordinates: [-117.2420, 32.8835],
        reports: 2
    },
    {
        location: "Sixth College",
        coordinates: [-117.2370, 32.8785],
        reports: 9
    },
    {
        location: "Seventh College",
        coordinates: [-117.2310, 32.8785],
        reports: 5
    },
    {
        location: "Eighth College",
        coordinates: [-117.2290, 32.8815],
        reports: 3
    },
    {
        location: "Geisel Library",
        coordinates: [-117.2374, 32.8812],
        reports: 12
    },
    {
        location: "Price Center",
        coordinates: [-117.2340, 32.8792],
        reports: 5
    }
];

// Create a red dot for each location
sampleReports.forEach((report) => {

    // More reports = larger dot
    const dotSize = 16 + report.reports * 1.5;

    const dot = document.createElement('div');

    dot.style.width = `${dotSize}px`;
    dot.style.height = `${dotSize}px`;
    dot.style.borderRadius = '50%';

    // All dots use the same medium red color
    dot.style.backgroundColor = '#D95C5C';

    // Make dots stand out against the map
    dot.style.border = '2px solid white';
    dot.style.boxShadow = '0 1px 5px rgba(0, 0, 0, 0.35)';
    dot.style.cursor = 'pointer';

    // Add a popup showing the sample report count
    const popup = new maplibregl.Popup({
        offset: 12
    }).setText(
        `${report.location}: ${report.reports} sample flu reports`
    );

    // Place the dot on the map
    new maplibregl.Marker({
        element: dot,
        anchor: 'center'
    })
        .setLngLat(report.coordinates)
        .setPopup(popup)
        .addTo(map);
});