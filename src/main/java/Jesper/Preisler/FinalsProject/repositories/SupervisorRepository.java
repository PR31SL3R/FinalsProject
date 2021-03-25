package Jesper.Preisler.FinalsProject.repositories;

import Jesper.Preisler.FinalsProject.models.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Extending JPA Repository
@Repository
public interface SupervisorRepository extends JpaRepository <Supervisor, Long> {



}
