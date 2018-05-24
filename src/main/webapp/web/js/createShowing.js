var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $http) {
	
$scope.day = null;
$scope.hour = null;
$scope.type = null;
$scope.screen = null;
$scope.seats = null;
$scope.seatsD = null;
$scope.postdata = function (day, hour, type, screen,seats,seatsD) {
var data = {
movieID : window.localStorage.getItem("idShowing"),
dayShowing: day,
hourShowing: hour,
typeShowing: type,
screening : screen,
seats : seats,
disabledSeats : seatsD
};
//Call the services
$http.post('http://localhost:8080/QACinemas/api/ticket/json', JSON.stringify(data)).then(function (response) {
if (response.data)
$scope.msg = "Showing Added to the database!";
}, function (response) {
$scope.msg = "Output Message :  Service not Exists";
});
};
    });