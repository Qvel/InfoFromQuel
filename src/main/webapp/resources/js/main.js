
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
       get_user_by_email : ajax_url + "getUserByEmail",
       avatar_upload : ajax_url + "updateAvatar",
       user_update : ajax_url + "user/updateUser",
       topic_create: ajax_url + "user/createTopic",
       topic_get_all : ajax_url + "getAllTopics",
       topics_search : ajax_url + "findTopicByTitle"
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
        $("#user_email").attr("user_id",response.data);
    });

});

app.controller("avatarCtrl",function ($scope,$http,config,$rootScope) {
    console.log("controller");

    $scope.updateAvatarFront = function () {
        console.log("work");
        $rootScope.form = event.target.files[0];
        console.log($rootScope.form.name);
        var reader = new FileReader();

        reader.onload = function (e) {
            $("#user_logo")
                .attr('src', e.target.result)
        };

        reader.readAsDataURL($rootScope.form);
        $("#save_user_avatar").css("display","inline");
    };
    $scope.updateAvatarBack = function (){
        var data = new FormData();
        console.log($rootScope.form.name)
        data.append('id',$("#user_email").attr("user_id"));
        data.append('file',$rootScope.form);
        $http({
           url: config.avatar_upload,
           method: "POST",
           data:data,
           headers:{
               'Content-Type': undefined
           },
           transformRequest: angular.identity
       })
    }
});

app.directive('customOnChange', function() {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var onChangeFunc = scope.$eval(attrs.customOnChange);
            element.bind('change', onChangeFunc);
        }
    };
});

app.controller("updateUserCtrl",function ($scope,$http,config) {
    $scope.updateUser = function (email,name,password) {
        console.log(email,name,password);
        var data = {
            'id': $("#user_email").attr("user_id"),
            'name': name,
            'password': password,
            'email' : email
        };
        $http({
            url: config.user_update,
            method: "POST",
            data:data,
            headers:{
                'Content-Type': 'application/json'
            }
        }).then(function success(response){
            console.log(response);
        });
    }
});

app.controller("createPostCtrl",function ($scope,$http,config) {

   $scope.createPost = function (title,body) {
       console.log(title,body);
       var user = {
         'id' :  $("#user_email").attr("user_id")
       };
       var data = {
           'user' : user,
           'title' : title,
           'body' : body
       };

       $http({
          url: config.topic_create,
           method : "POST",
           data:data,
           headers :{
               'Content-Type': 'application/json'
           }
       }).then(function success(response) {
           console.log(response.data)
       });
   }
});

app.controller("findAllPostCtrl",function ($scope,$http,config) {
    $scope.getAllPosts = function(){
        alert("1");
        $http.get(config.topic_get_all).then(function (response) {
            $scope.topics = response.data;
            console.log(response.data);
        });
    }
});

app.controller("postSearchCtrl",function ($scope,$http,config) {
    $scope.getPostsByText = function (text) {
        var data = "title=" + text;
        $http({
            url: config.topics_search,
            method: "GET",
            data:data,
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).then(function success(response) {
            console.log(response.data);
        });
    };

});
