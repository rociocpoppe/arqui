package factory;

import Repository.CarreraRepository;
import Repository.EstudianteRepository;
import Repository.Estudiante_CarreraRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryRepositoryMySql extends FactoryRepository{

    private EntityManagerFactory emf;
    private EntityManager em;

    public FactoryRepositoryMySql() {
        emf = Persistence.createEntityManagerFactory("Entregable");
        em = emf.createEntityManager();
    }
    @Override
    public CarreraRepository getCarreraRepository() {
        return new CarreraRepository(em);
    }

    @Override
    public EstudianteRepository getEstudianteRepository() {
        return new EstudianteRepository(em);
    }

    @Override
    public Estudiante_CarreraRepository getEstudiante_CarreraRepository() {
        return new Estudiante_CarreraRepository(em);
    }
}
