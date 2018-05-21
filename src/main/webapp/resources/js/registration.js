var ajax_location = "http://localhost:8080/InfoQuel";
$(document).ready(function() {

    //getUsers();
    
    $(".Check").click(
      function () {
          console.log("Start check");
          var logo = $(".logo").prop('files');
          console.log(logo);
      }  
    );
    
    $(".RegistrationButton").click(
        function(event){


            event.preventDefault()
            registration();

           /*if(password === retrypassword){
              // registration(logo);
           }else{
               console.log("passwords aren't the same");
           }*/

        }
    );

});



function registration(){

    var form = $("#fileUploadForm")[0];
    var data = new FormData(form);

    $.ajax({
        url: ajax_location + "/createUser",
        type: "POST",
        enctype: 'multipart/form-data',
        //data:{'login':login,'email':email,'password':password,'file':logo},
        data:data,
        /*contentType:'multipart/form-data;boundary=45',
        enctype:'multipart/form-data',*/
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
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
    type: "GET",
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


