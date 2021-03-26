// Create a new intern
function createStudent() {


    let student = {};
    let supervisor = {};
    let supervisorID = 0;

    $('#btnAddStudent').click(function() {
        student.studentFirstName = $('#addStudentFirstName').val();
        student.studentLastName = $('#addStudentLastName').val();
        student.studentEmail = $('#studentEmail').val();
        student.studentComments = $('#studentComment').val();
        const supervisorNameAndId = $("#populateDropdownCreateStudent option:selected").text().split(" ")
        supervisorID = supervisorNameAndId[1]
        console.log(supervisorID);
        $.ajax({
            url: "/getSupervisorById/" + supervisorID,
            method: "GET",
            contentType: 'application/JSON',
            success: function(data) {
                console.log(data);
                supervisor.id = data.supervisorId
               supervisor.supervisorFirstName =  data.supervisorFirstName
                supervisor.supervisorLastName = data.supervisorLastName
                supervisor.supervisorEmail = data.supervisorEmail
              //student.supervisor = supervisor

            },
            error: function(error) {
                alert("A error occured: " + error);
            }
        })

        $.ajax({
            url: "http://localhost:8080/createStudent",
            method: "POST",
            contentType: 'application/JSON',
            data: JSON.stringify(student),
            success: function() {
                getAllStudents();
                cancelAddStudentForm();
            },
            error: function(error) {
                alert("A error occured: " + error);
            }
        })
    })
}

function createSupervisor() {

    let supervisor = {};

    $('#btnAddSupervisor').click(function() {
        supervisor.supervisorFirstName = $('#addSupervisorFirstName').val();
        supervisor.supervisorLastName = $('#addSupervisorLastName').val();
        supervisor.supervisorEmail = $('#supervisorEmail').val();

        $.ajax({
            url: "http://localhost:8080/createSupervisor",
            method: "POST",
            contentType: 'application/JSON',
            data: JSON.stringify(supervisor),
            success: function() {
                populateDropdownCreateStudent();
                cancelSupervisorForm();
            },
            error: function(error) {
                alert("A error occured: " + error);
            }
        })
    })
}






// Get all students
function getAllStudents() {
    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/getAllStudents'
    }).done(function(data) {
        $('#studentTable').empty();
        $.each(data, function(i, students) {
            $('#studentTable')
                .append($("<tr id='reset'>"))
                .append($("<td>"))
                .append($("<input name='id' class='form-control' type='text' id='studentId' readonly>").val(students.studentId))
                .append($("</td>"))
                .append($("<td>"))
                .append($("<input name='firstname' class='form-control' type='text' id='studentFirstName'>").val(students.studentFirstName))
                .append($("</td>"))
                .append($("<td>"))
                .append($("<input name='lastname' class='form-control' type='text' id='studentLastName' >").val(students.studentLastName))
                .append($("</td>"))
                .append($("<td>"))
                .append($("<input name='email' class='form-control' type='text'  id='studentEmail'>").val(students.studentEmail))
                .append($("</td>"))
                .append($("<td>"))
                .append($("<button type='button' class='btn btn-info btn-block' id='studentInfo'  style='padding: 5px'onclick=\"myFunction(" + students.studentId + ")\"> Info</button>"))
                .append($("</td>"))
                .append($("<td>"))
                .append($("<button type='button' class='btn btn-primary btn-block' id='updateStudent' style='padding: 5px' onclick=\"updateStudent2(" + students.studentId + ")\">Update</button>"))
                .append($("</td>"))
                .append($("<td>"))
                .append($("<button type='button' class='btn btn-danger btn-block' style='padding: 5px 'onclick=\"deleteStudent(" + students.studentId + ")\">Delete</button>"))
                .append($("</td>"));
        });
    })
}

function deleteStudent(id){

    $.ajax({
        url: "http://localhost:8080/deleteStudent/" + id,
        method: "DELETE",
        contentType: 'application/JSON',
        success: function() {
            getAllStudents();
        },
        error: function(error) {
            alert("A error occured: " + error);
        }
    })

}



// Update Students
function updateStudent2(id) {
    let student = {}
    let tempVar = 0;


            console.log(id);
            $(".form-control").each(function (index) {
                if (Number($(this).val()) === id && tempVar === 0) {
                    tempVar=1;
                    student.studentFirstName = ($(`.form-control:eq(${index + 1})`).val());
                    student.studentLastName = ($(`.form-control:eq(${index + 2})`).val());
                    student.studentEmail = ($(`.form-control:eq(${index + 3})`).val());
                    student.supervisorId = ($(`.form-control:eq(${index + 4})`).val());
                    $.ajax({
                        url: 'http://localhost:8080/updateStudent/' + id,
                        method: 'PUT',
                        dataType: 'json',
                        contentType: 'application/JSON',
                        data: JSON.stringify(student),
                        success: function () {
                            getAllStudents();
                        },
                        error: function (error) {
                            alert(error)
                        }

                    })
                }
            })



        }
        function myFunction(id) {

                $.ajax({
                    url: "http://localhost:8080/getStudentById/" + id,
                    method: "GET",
                    contentType: 'application/JSON',
                    success: function(data) {
                      if (data.studentComments !== ""){ prompt("Student Id: "+ data.studentId + "\nStudent name: "
                            + data.studentFirstName + " " + data.studentLastName + "\n" + "Student email is: " + data.studentEmail + "\n" + "The comments for this student are: " + data.studentComments)
                            }else prompt("Student Id: "+ data.studentId + "\nStudent name: "
                          + data.studentFirstName + " " + data.studentLastName + "\n" + "Student email is: " + data.studentEmail + "\n" + "no comments availabe for the student")

                    },
                    error: function(error) {
                        alert("A error occured: " + error);
                    }
                })




}

// * populate drowndown in create students
function populateDropdownCreateStudent() {
    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/getAllSupervisors'
    }).done(function(data) {
        $('#populateDropdownCreateStudent').empty();
        $.each(data, function(key, supervisor) {
            $('#populateDropdownCreateStudent')
                .append($("<option></option>")
                    .attr("value", key)
                    .text(supervisor.supervisorFirstName + " " + supervisor.supervisorId));
        });
    })
}

function cancelAddStudentForm(){
    $('#addStudentFirstName').val('');
    $('#addStudentLastName').val('');
     $('#addStudentLastName').val('');
    $('#studentEmail').val('');
    $('#studentComment').val('');

}

function cancelSupervisorForm(){
    $('#addSupervisorFirstName').val('');
    $('#addSupervisorLastName').val('');
    $('#supervisorEmail').val('');


}



$(document).ready(function (){
    getAllStudents();
    populateDropdownCreateStudent();
    createStudent();
    createSupervisor();



})





