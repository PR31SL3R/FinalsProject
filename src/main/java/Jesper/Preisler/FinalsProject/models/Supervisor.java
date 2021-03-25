package Jesper.Preisler.FinalsProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Supervisor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long supervisorId;

   @Column
   String supervisorFirstName;

   @Column
   String supervisorLastName;

   String supervisorEmail;
/*
    @OneToMany(
            mappedBy = "studentId",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonBackReference
    private Set < Student > students;

 */


   public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorFirstName() {
        return supervisorFirstName;
    }

    public void setSupervisorFirstName(String supervisorFirstName) {
        this.supervisorFirstName = supervisorFirstName;
    }

    public String getSupervisorLastName() {
        return supervisorLastName;
    }

    public void setSupervisorLastName(String supervisorLastName) {
        this.supervisorLastName = supervisorLastName;
    }
/*
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

 */


}
