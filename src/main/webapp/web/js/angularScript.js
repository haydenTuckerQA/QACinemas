var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $http) {


        $scope.refresh =  function(){
           $http({
                method : "GET",
                url : "http://localhost:8080/QACinemas/api/movie/json"
            }).then(function mySuccess(response) {
                $scope.myWelcome = response.data;
            }, function myError(response) {
                $scope.myWelcome = response.statusText;
           });
            };
        $scope.local = function(ID)
        {
            window.localStorage.setItem("store_id",ID);
        };

    });
	