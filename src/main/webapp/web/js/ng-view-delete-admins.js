var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
    $scope.deletedata = function (usr_del) {
        var url = "http://localhost:8080/QACinemas/api/user/json/" + usr_del.toString();
        $http.delete(url).then(function (response) {
            if (response.data)
            $scope.msg = "Deleted Account!";
            $scope.refresh();
        }, function (response) {
            $scope.msg = "Output Message : Service not Exists";
        });
    };

    $scope.refresh =  function(){
           $http({
                method : "GET",
                url : "http://localhost:8080/QACinemas/api/user/json/"
            }).then(function mySuccess(response) {
                $scope.myWelcome = response.data;
            }, function myError(response) {
                $scope.myWelcome = response.statusText;
            });
		};
});