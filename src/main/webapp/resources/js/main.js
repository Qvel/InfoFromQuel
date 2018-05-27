
var app = angular.module("indexPage",[]);

/*
For @RequestParam Url  :
For all another @RequestBody
var data = 'name=' + name + '&email=' + email + '&password=' + password;
app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
}]);*/

app.factory("config",function(){
    var ajax_url = "http://localhost:8080/InfoQuel/";
   return {
       get_users_url : ajax_url + "getUsers",
       registration_url : ajax_url + "registration",
       get_user_by_email : ajax_url + "getUserByEmail"
   }
});


app.controller("userCtrl",function ($scope,$http,config) {
    $http.get(config.get_users_url).then(function (response) {
        $scope.users = response.data;
        console.log(response.data);
    });
});

app.controller("registrationCtrl",function ($scope,$http,config) {

    $scope.registration = function (email,name,password){
        console.log(email,name,password);
        var data = {
            'name': name,
            'password': password,
            'email' : email
        };
        $http({
            url: config.registration_url,
            method: "POST",
            data:data,
            headers:{
                'Content-Type': 'application/json'
            }
        }).then(function success(response){
            console.log(response);
        });
    };

});

app.controller("userCtrl",function ($scope,$http,config) {

    var email = $("#user_email").attr("user_email");
    console.log(email);
    var data = "email=" + email;
    $http({
        url: config.get_user_by_email,
        method: "POST",
        data:data,
        headers:{
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    }).then(function success(response){
        console.log(response.data);
        $("#user_email").attr("href","/InfoQuel/user/"+response.data);
    });


});

