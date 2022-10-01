package factory;

import Repository.CarreraRepository;
import Repository.EstudianteRepository;
import Repository.Estudiante_CarreraRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryRepositoryDerby extends FactoryRepository{

    private EntityManagerFactory emf;
    private EntityManager em;

    public FactoryRepositoryDerby() {
        emf = Persistence.createEntityManagerFactory("Entregable2Derby");
        em = emf.createEntityManager();
    }

    @Override
    public CarreraRepository getCarreraRepository() {
        return null;
    }

    @Override
    public EstudianteRepository getEstudianteRepository() {
        return null;
    }

    @Override
    public Estudiante_CarreraRepository getEstudiante_CarreraRepository() {
        return null;
    }
}
