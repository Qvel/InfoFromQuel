var ajax_location = "http://localhost:8080/InfoQuel";
$(document).ready(function() {

    getUsers();

    $(".RegistrationButton").click(
        function(){
           var login = $(".UserName").val();
           var email = $(".UserEmail").val();
           var password = $(".UserPassword").val();
           var retrypassword = $(".RetryPassword").val();

           console.log("login = " + login + "; email = " + email + "; password = " + password);

           if(password === retrypassword){
               registration(login,email,password);
           }else{
               console.log("passwords aren't the same");
           }

        }
    );

});



function registration(login,email,password){
    $.ajax({
        url: ajax_location + "/createUser",
        type: "POST",
        data:{'login':login,'email':email,'password':password},
        dataType: "json",
        success: registrationSuccess,
        error: registrationError
    });
}

function registrationSuccess(data) {
    console.log(data);
    console.log("Ty for registration");
}

function registrationError(data){
    console.log("some errors with registration");
    console.log(data);
}

function getUsers(){


$.ajax({
    url: ajax_location + "/getUsers",
    type: "POST",
    dataType: "json",
    contentType: 'application/json',
    mimeType: 'application/json',
    success: parseUsers,
    error: function (data) {
        console.log(data);
    }
});


}

function parseUsers(data) {
    Users  = data;
    /*
    for(var index_student in data)
    {
        studentIndex = data[index_student];
        $("#TableStudents").append('<tr class="win getModalStudent" candidate_id="'+ studentIndex.id +'">' +
            '<td>'+studentIndex.user.name+'</td>' +
            '<td>'+studentIndex.user.surname+'</td>' +
            '<td>'+studentIndex.user.patronymic+'</td>' +
            '<td>'+studentIndex.user.email+'</td>' +
            '</tr>');
    }
    */
}


