var marker;
var mymap;
function initMap() {
    mymap = L.map('map').setView(new L.LatLng(47.754,2.219), 6);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap contributors</a>'
    }).addTo(mymap);
    getAssos();
}

function generateMarkers(assoList){
    assoList.forEach((element) => {
        let latLng = new L.LatLng(element["latitude"], element["longitude"]);
        new L.marker(latLng).addTo(mymap)
    });

}

function generateList(assoList){
    let list = document.getElementById("list");
    assoList.forEach((element) => {
        let div = document.createElement("div");
        let name = document.createElement("a");
        name.setAttribute("href","/association/"+element.id);
        name.innerText = element.name;
        div.appendChild(name);
        list.appendChild(div);
    });
}

function getAssos(){
    let request = new XMLHttpRequest();
    request.addEventListener("readystatechange", () =>{
        if (request.readyState === XMLHttpRequest.DONE && request.status === 200) {
            let assos = JSON.parse(request.responseText)
            generateMarkers(assos);
            generateList(assos);
        }
    });
    request.open("GET","/api/assoList");
    request.send();
}

window.addEventListener("load",initMap);