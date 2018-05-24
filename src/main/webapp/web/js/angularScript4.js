 var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $http) {
	
$scope.pass= function ( pass_id,pass_title,pass_genre,pass_IMDBID,pass_imgURL)
{
	
	window.localStorage.setItem("store_id",pass_id);
	window.localStorage.setItem("store_Tl",pass_title);
	window.localStorage.setItem("store_GE",pass_genre);
	window.localStorage.setItem("store_IMID",pass_IMDBID);
	window.localStorage.setItem("store_imU",pass_imgURL);
	
}
$scope.deletedata = function (id_del) {
var url = "http://localhost:8080/QACinemas/api/movie/json/" + id_del.toString();
$http.delete(url).then(function (response) {
if (response.data)
$scope.msg = "Movie Deleted";
$scope.refresh();
}, function (response) {
$scope.msg = "Output Message : Service not Exists";
});
};
$scope.local = function(id) {
	window.localStorage.setItem("idShowing", id);
};
$scope.refresh =  function(){
       $http({
            method : "GET",
            url : "http://localhost:8080/QACinemas/api/movie/json/"
        }).then(function mySuccess(response) {
            $scope.myWelcome = response.data;
        }, function myError(response) {
            $scope.myWelcome = response.statusText;
        });
		};
    });