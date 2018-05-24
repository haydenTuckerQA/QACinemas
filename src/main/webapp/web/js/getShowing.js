 var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $http) {
	

$scope.deletedata = function (id_del) {
var url = "http://localhost:8080/QACinemas/api/ticket/json/" + id_del.toString();
$http.delete(url).then(function (response) {
if (response.data)
$scope.msg = "Showing Deleted";
$scope.refresh();
}, function (response) {
$scope.msg = "Output Message : Service not Exists";
});
};

$scope.refresh =  function(){
		var showURL = "http://localhost:8080/QACinemas/api/ticket/json/" + window.localStorage.getItem("idShowing");
       $http({
            method : "GET",
            url : showURL
        }).then(function mySuccess(response) {
            $scope.myWelcome = response.data;
        }, function myError(response) {
            $scope.myWelcome = response.statusText;
        });
		};
    });