var ajax_location = "http://localhost:8080/InfoQuel";
$(document).ready(function() {


    console.log("Hi my js");

    getUsers();

});




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