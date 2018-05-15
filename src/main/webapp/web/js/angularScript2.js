var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $http) {

$scope.refresh = function(){
	
	var ID = localStorage.getItem("store_id");
	var specialURL = "http://www.omdbapi.com/?i=tt" + ID + "&apikey=cbf6483d";

		$http({
            method : "GET",
            url : specialURL
        }).then(function mySuccess(response) {
            $scope.myOMDB = response.data;
        }, function myError(response) {
            $scope.myOMDB = response.statusText;
        });
};


    });
	