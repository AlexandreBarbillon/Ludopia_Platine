var marker;
var mymap;
function initMap() {
    mymap = L.map('map').setView(new L.LatLng(47.754,2.219), 6);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap contributors</a>'
    }).addTo(mymap);
    new L.esri.Geocoding.Geosearch({useMapBounds:false}).addTo(mymap);
    mymap.addEventListener("click", addMarker);
}
function addMarker(e) {
    // Add marker to map at click location; add popup window
    if (marker !== undefined) {
        mymap.removeLayer(marker);
    }
    marker = new L.marker(e.latlng).addTo(mymap);
    var longitude = document.getElementById("longitude");
    var latitude = document.getElementById("latitude");
    latitude.value = e.latlng.lat;
    longitude.value = e.latlng.lng;
}

window.addEventListener("load",initMap);