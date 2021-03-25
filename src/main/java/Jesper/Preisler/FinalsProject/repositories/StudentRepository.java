package Jesper.Preisler.FinalsProject.repositories;


import Jesper.Preisler.FinalsProject.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Extending JPA Repository
@Repository
public interface StudentRepository extends JpaRepository < Student, Long > {



}
