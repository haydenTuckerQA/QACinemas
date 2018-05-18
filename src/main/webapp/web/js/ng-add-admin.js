var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	$scope.username = null;
	$scope.password = null;
	$scope.addAdmin = function (username, password) {
		var data = {
			username: username,
			password: password,
			role: ""
		};
		//Call the services
		$http.post('http://localhost:8080/QACinemas/api/user/json', JSON.stringify(data)).then(function (response) {
			if (response.data)
				$scope.msg = "Admin Added to the database!";
		}, function (response) {
			$scope.msg = "Output Message :  Service not Exists";
		});
	};
});