var ajax_location = "http://localhost:8080/InfoQuel";
$(document).ready(function() {

    /* Registration form */
    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

    getUsers();

    getEvents();

});


function registration(login,email,password){
    $.ajax({
        url: ajax_location + "/createUser",
        type: "POST",
        dataType: "json",
        success: registrationSuccess,
        error: registrationError
    });
}

function getEvents(){
    $.ajax({
        url: ajax_location + "/facebook",
        type: "GET",
        dataType: "json",
        contentType: 'application/json;charset=utf-8',
        mimeType: 'application/json',
        success: parseEvents,
        error: function (data) {
            console.log(data);
        }
    });
}

function getUsers(){


$.ajax({
    url: ajax_location + "/getUsers",
    type: "GET",
    dataType: "json",
    contentType: 'application/json;charset=utf-8',
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


function parseEvents (data) {
    Events = data;
}