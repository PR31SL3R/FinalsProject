package Jesper.Preisler.FinalsProject.controllers;


import Jesper.Preisler.FinalsProject.models.Student;
import Jesper.Preisler.FinalsProject.models.Supervisor;
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
    // Students Get All Students
    @GetMapping("/getAllStudents")
    public List<Student>  getAllstudents(){
        return studentServiceImplementation.findAll();
    }

    // Student Get student by id
    @GetMapping("/getStudentById/{studentId}")
    public Optional<Student> getStudentById(@PathVariable Long studentId){
        return studentServiceImplementation.findById(studentId);
    }

    // * Student create Student
    @PostMapping("/createStudent")
    public Student createStudent(@RequestBody Student student){
        student.setStudentId(null);
        return studentServiceImplementation.save(student);
    }

    // * Student Update Student
    @PutMapping("/updateStudent/{studentId}")
    Student updateStudent(@RequestBody Student newStudent, @PathVariable Long studentId) {
        return studentServiceImplementation.findById(studentId)
                .map(student -> {
                    student.setStudentFirstName(newStudent.getStudentFirstName());
                    student.setStudentLastName(newStudent.getStudentLastName());
                    student.setStudentEmail(newStudent.getStudentEmail());
                    student.setStudentComments(newStudent.getStudentComments());
                    student.setSupervisor(newStudent.getSupervisor());
                    return studentServiceImplementation.save(student);
                })
                .orElseGet(() -> studentServiceImplementation.save(newStudent));
    }


    @DeleteMapping("deleteStudent/{studentId}")
    public ResponseEntity deleteStudentById(@PathVariable Long studentId){
        studentServiceImplementation.deleteById(studentId);

        return ResponseEntity.ok("Student Deleted");
    }


    // Supervisor Get All Students
    @GetMapping("/getAllSupervisors")
    public List<Supervisor>getAllSupervisors(){
        return supervisorServiceImplementation.findAll();
    }

    // Supervisor Get student by id
    @GetMapping("/getSupervisorById/{supervisorId}")
    public Optional<Supervisor> getSupervisorById(@PathVariable Long supervisorId){
        return supervisorServiceImplementation.findById(supervisorId);
    }

    // * Supervisor create Student
    @PostMapping("/createSupervisor")
    public Supervisor createSupervisor(@RequestBody Supervisor supervisor){
        supervisor.setSupervisorId(null);

        return supervisorServiceImplementation.save(supervisor);
    }

    // * Supervisor Update Student
    @PutMapping("/updateSupervisor/{supervisorId}")
    Supervisor updateSupervisor(@RequestBody Supervisor newSupervisor, @PathVariable Long supervisorId) {
        return supervisorServiceImplementation.findById(supervisorId)
                .map(supervisor -> {
                    System.out.println("supervisor");
                    supervisor.setSupervisorId(supervisor.getSupervisorId());
                    supervisor.setSupervisorFirstName(newSupervisor.getSupervisorFirstName());
                    supervisor.setSupervisorLastName(newSupervisor.getSupervisorLastName());
                    supervisor.setSupervisorEmail(newSupervisor.getSupervisorEmail());
                    return supervisorServiceImplementation.save(supervisor);
                })
                .orElseGet(() -> supervisorServiceImplementation.save(newSupervisor));
    }

    // Supervisor delete supervisor
    @DeleteMapping("deleteSupervisor/{supervisorId}")
    public ResponseEntity deleteSupervisorById(@PathVariable Long supervisorId){
        supervisorServiceImplementation.deleteById(supervisorId);
        return ResponseEntity.ok("supervisor Deleted");
    }






}
