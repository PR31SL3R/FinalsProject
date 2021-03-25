package Jesper.Preisler.FinalsProject.services;

import Jesper.Preisler.FinalsProject.models.Supervisor;
import Jesper.Preisler.FinalsProject.repositories.SupervisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SupervisorServiceImplementation implements SupervisorService{

    private final SupervisorRepository supervisorRepository;

    public SupervisorServiceImplementation(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public List<Supervisor> findAll() {
        return supervisorRepository.findAll();
    }

    @Override
    public Supervisor save(Supervisor object) {
        return supervisorRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        supervisorRepository.deleteById(aLong);

    }

    @Override
    public Optional<Supervisor> findById(Long aLong) {
        return supervisorRepository.findById(aLong);
    }
}
