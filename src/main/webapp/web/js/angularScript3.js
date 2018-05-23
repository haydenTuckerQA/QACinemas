var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $http) {
	
$scope.title = null;
$scope.genre = null;
$scope.IMDBID = null;
$scope.imgURL = null;
$scope.postdata = function (title, genre, IMDBID, imgURL) {
var data = {
title: title,
genre: genre,
IMDBID: IMDBID,
imgURL : imgURL
};
//Call the services
$http.post('http://localhost:8080/QACinemas/api/movie/json', JSON.stringify(data)).then(function (response) {
if (response.data)
$scope.msg = "Movie Added to the database!";
}, function (response) {
$scope.msg = "Output Message :  Service not Exists";
});
};
    });