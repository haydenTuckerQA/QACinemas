var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
		document.getElementById("placeH1").placeholder = localStorage.getItem("store_Tl");
		document.getElementById("placeH2").placeholder = localStorage.getItem("store_GE");
		document.getElementById("placeH3").placeholder = localStorage.getItem("store_IMID");
		document.getElementById("placeH4").placeholder = localStorage.getItem("store_imU");

$scope.updatedata = function ( updateTitle, updateGenre, updateIMDBID,updateURL) {
var data = {
id: localStorage.getItem("store_id"),
title : updateTitle,
genre : updateGenre,
IMDBID : updateIMDBID,
imgURL : updateURL
};

$http.put('http://localhost:8080/QACinemas/api/movie/json', 
JSON.stringify(data)).then(function (response) {
if (response.data)
$scope.msg = "Movie Updated!";
}, function (response) {
$scope.msg = "Output Message : Service not Exists";

});
};
    });