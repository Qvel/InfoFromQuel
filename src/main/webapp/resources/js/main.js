
var app = angular.module("indexPage",[]);

app.controller("ExCtrl",function ($scope) {
    $scope.name = "world";

    $scope.phones = [
        {"name":"serhii",
         "phone":"0934800415"},
        {"name":"vlad",
         "phone":"0312321312"},
        {"name":"max",
         "phone":"0933123211"}
    ];

});
