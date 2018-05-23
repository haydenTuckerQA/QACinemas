var map;
var infowindow;
var markers = [];

function initMap() {
  var directionsService = new google.maps.DirectionsService;
  var directionsDisplay = new google.maps.DirectionsRenderer;
  var myLatLng = {lat: 53.474053, lng: -2.286321};

  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 15,
    center: myLatLng
  });

  var marker = new google.maps.Marker({
    position: myLatLng,
    map: map,
    title: 'QA Cinema!'
  });
  markers.push(marker);

  directionsDisplay.setMap(map);

  var onInputLocation = function() {
    var dest = document.getElementById('start_input').value;

    if (dest != "") {
      directionsDisplay.setMap(map);
      calculateAndDisplayRoute(directionsService, directionsDisplay, dest);
      deleteAllMarkers();
    } else {
      window.alert("You didn't enter anything!");
    }
  };

  document.getElementById('start_input').addEventListener('change', onInputLocation);
  document.getElementById('route_button').addEventListener('click', onInputLocation);

  var onClickMyLocation = function() {
    if ("geolocation" in navigator){
      navigator.geolocation.getCurrentPosition(function(position){ 
        var currentLatitude = position.coords.latitude;
        var currentLongitude = position.coords.longitude;
        var dest =  currentLatitude + " " + currentLongitude;
        directionsDisplay.setMap(map);
        calculateAndDisplayRoute(directionsService, directionsDisplay, dest);
        deleteAllMarkers();
      });
    }
  }

  document.getElementById('my_location_button').addEventListener('click', onClickMyLocation);

  var onClickPlaces = function() {
    var radius = document.getElementById('radius').value;
    var atm = document.getElementById('atm');
    var bar = document.getElementById('bar');
    var restaurant = document.getElementById('restaurant');
    var types = [];

    if (atm.checked) {
      types.push(['atm']);
    }

    if (bar.checked) {
      types.push(['bar']);
    }

    if (restaurant.checked) {
      types.push(['restaurant']);
    }

    if (types.length > 0) {
      directionsDisplay.setMap(null);
      map.setCenter(myLatLng);
      map.setZoom(15);
      deleteAllMarkers();
      types.forEach(function(type) {
        infowindow = new google.maps.InfoWindow();
        var service = new google.maps.places.PlacesService(map);
        service.nearbySearch({
          location: {lat: 53.474053, lng: -2.286321},
          radius: radius,
          types: type
        }, callback);
      });
    } else {
      window.alert("You didn't select any places to show!");
    }
  }

  document.getElementById('places_button').addEventListener('click', onClickPlaces);
}

function calculateAndDisplayRoute(directionsService, directionsDisplay, dest) {
  directionsService.route({
    origin: dest,
    destination: '53.474053 -2.286321',
    travelMode: 'DRIVING'
  }, function(response, status) {
    if (status === 'OK') {
      directionsDisplay.setDirections(response);
    } else {
      window.alert('Directions request failed due to ' + status);
    }
  });
}

function callback(results, status) {
  if (status === google.maps.places.PlacesServiceStatus.OK) {
    for (var i = 0; i < results.length; i++) {
      createMarker(results[i]);
    }
  }
}

function createMarker(place) {
  var placeLoc = place.geometry.location;
  var marker = new google.maps.Marker({
    map: map,
    position: place.geometry.location
  });
  markers.push(marker);

  google.maps.event.addListener(marker, 'click', function() {
    infowindow.setContent('<div><img src=' + place.icon + '> ' + 
                '<strong>' + place.name + '</strong><br>' +
                place.vicinity + '<br>' +
                'Rating: ' + place.rating + '/5<br></div>');
    infowindow.open(map, this);
  });
}

function deleteAllMarkers() {
  for (let i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
  }
  markers = [];
};