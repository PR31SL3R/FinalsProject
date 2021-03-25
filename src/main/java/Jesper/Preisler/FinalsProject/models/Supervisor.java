package Jesper.Preisler.FinalsProject.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Supervisor {

   @Id
   // *
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long supervisorId;

   @Column
   String supervisorFirstName;

   @Column
   String supervisorLastName;

   @OneToMany
   private Set < Student > students;



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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
