package Jesper.Preisler.FinalsProject.controllers;


import Jesper.Preisler.FinalsProject.models.Student;
import Jesper.Preisler.FinalsProject.services.StudentServiceImplementation;
import Jesper.Preisler.FinalsProject.services.SupervisorServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestApiController {


    final SupervisorServiceImplementation supervisorServiceImplementation;

    final StudentServiceImplementation studentServiceImplementation;

    @Autowired
    public RestApiController(SupervisorServiceImplementation supervisorServiceImplementation, StudentServiceImplementation studentServiceImplementation) {
        this.supervisorServiceImplementation = supervisorServiceImplementation;
        this.studentServiceImplementation = studentServiceImplementation;
    }

    @GetMapping("/getAllStudents")
    public List<Student>  getAllstudents(){
        return studentServiceImplementation.findAll();
    }


    @GetMapping("/getStudentById/{studentId}")
    public Optional<Student> getStudentById(@PathVariable Long studentId){
        return studentServiceImplementation.findById(studentId);
    }

    // *
    @PostMapping("/createStudent")
    public Student createStudent(@RequestBody Student student){
        return studentServiceImplementation.save(student);
    }

    // *
    @PutMapping("updateStudent/{studentId}")
    public Student updateStudent(@RequestBody Student newStudent, @PathVariable Long studentId){
        return studentServiceImplementation.findById(studentId).map(student -> {
            student.setStudentFirstName(newStudent.getStudentFirstName());
            student.setStudentLastName(newStudent.getStudentLastName());
            st
        })
    }

    @DeleteMapping("deleteStudent/{studentId}")
    public ResponseEntity deleteStudentById(@PathVariable Long studentId){
        studentServiceImplementation.deleteById(studentId);

        return ResponseEntity.ok("Student Deleted");
    }






}
