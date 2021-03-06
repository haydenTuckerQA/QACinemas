var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $http, $window) {

        $scope.data =  {
            dateAndTime: "",
            tickets:0,
            dTickets: 0
        };

        $scope.bookTicket = function(){
            var reqData = {
                dateTime: $scope.data.dateAndTime,
                movie_id: localStorage.getItem("movie_id"),
                seats: $scope.data.tickets+$scope.data.dTickets,
                price: (($scope.data.tickets*6)+($scope.data.dTickets*8)).toFixed(2)
            };
            $http({
                method: "POST",
                url: "http://localhost:8080/QACinemas/api/pay",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: reqData
            }).then(function (res) {
                    $scope.payment = res.data;
                    $window.location.href = res.data.links[1].href;
                    console.log(res.data);

                }, function (err) {
                    console.log(err);
                }
            )
        };

    $scope.srch;
    $scope.refresh = function(){

        var ID = localStorage.getItem("store_id");
        var specialURL = "http://www.omdbapi.com/?i=" + ID + "&apikey=cbf6483d";
        var movieID = "http://localhost:8080/QACinemas/api/ticket/json/" + localStorage.getItem("movie_id");

            $http({
                method : "GET",
                url : specialURL
            }).then(function mySuccess(response) {
                $scope.myOMDB = response.data;
            }, function myError(response) {
                $scope.myOMDB = response.statusText;
            });
             $http({
                    method : "GET",
                    url : movieID
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
	