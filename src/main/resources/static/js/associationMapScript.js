var marker;
var mymap;
function initMap() {
    mymap = L.map('map').setView(new L.LatLng(47.754,2.219), 6);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap contributors</a>'
    }).addTo(mymap);
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
        let nameText = document.createElement("span");
        var img = new Image();
        img.src = element.imageLink;
        img.className = "imgAssoList";

        nameText.innerText = element.name;
        nameText.className = "textAssoList";
        name.setAttribute("href","/association/"+element.id);
        name.appendChild(img);
        name.appendChild(nameText);
        div.appendChild(name);
        list.appendChild(div);
    });
}

function getAssos(gameId){
    let address = "/api/assoList/";
    if(gameId != null){
       address = "/api/assoList/"+gameId;
    }
    let request = new XMLHttpRequest();
    request.addEventListener("readystatechange", () =>{
        if (request.readyState === XMLHttpRequest.DONE && request.status === 200) {
            let assos = JSON.parse(request.responseText);
            generateMarkers(assos);
            generateList(assos);
        }
    });
    request.open("GET",address);
    request.send();
}

function initPage(){
    initMap();
    initAsso(); //MUST BE DECLARED IN HTML
}

window.addEventListener("load",initPage);