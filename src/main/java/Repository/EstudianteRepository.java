package Repository;

import java.util.ArrayList;

import Interfaces.IEstudiante;
import entidades.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class EstudianteRepository implements IEstudiante{

    private EntityManager em;

    public EstudianteRepository(){
        super();
    }

    public EstudianteRepository(EntityManager em) {
        super();
        this.em=em;
    }

    @Override
    public boolean insertarEstudiante(CSVParser estudiantes) {
        boolean inserted = false;
        for (CSVRecord row : estudiantes) {
            Long dni = Long.parseLong(row.get("nroDni"));
            String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            String ciudadResidencia = row.get("ciudadResidencia");
            int edad = Integer.parseInt(row.get("edad"));
            String genero = row.get("genero");
            int nroLibreta = Integer.parseInt((row.get("nroLibretaUniv")));
            Estudiante e = new Estudiante(dni, nombre, apellido,ciudadResidencia,edad, genero, nroLibreta);
            try {
                em.getTransaction().begin();
                saveEstudiante(e);
                em.getTransaction().commit();
                inserted = true;
            } catch (Exception ex) {
                em.getTransaction().rollback();
                System.out.println("exception  = " + ex);
                inserted = false;
            }

        }
        return inserted;
    }


    @Override
    public void saveEstudiante(Estudiante est) {
        if (est.getNroDni()== null) {
            em.persist(est);
        } else {
            est = em.merge(est);
        }
    }

    @Override
    public void deleteEstudiante(Estudiante e) {
        if (em.contains(e)) {
            em.remove(e);
        } else {
            em.merge(e);
        }
    }

    // @Override
    // public ArrayList<Estudiante> getEstudiantesByCriterio(String dato) {
    //     return null;
    // }

    @Override
    public Estudiante getEstudianteByLU(String lu) {
        return null;
    }

    @Override
    public ArrayList<Estudiante> getEstudiantesByGenero(String dato) {
        return null;
    }
    

    @Override
    public Estudiante findEstudiante(Long nroDni) {
            return em.find(Estudiante.class, nroDni);
    }

    // @Override
    // public ArrayList<Estudiante> getEstudiantesByCriterio(String dato) {
    //     return new Estudiante();
    // }

    @Override
    public Estudiante getEstudianteByLU(int nroLibretaUniv) {
       Estudiante estudiante;
       String jpql = "SELECT e "
               +"FROM Estudiante e WHERE e.nroLibretaUniv = ?1";

        try {
            TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
            query.setParameter(1, nroLibretaUniv);
            estudiante = query.getSingleResult();
        } catch (Exception e) {
            estudiante = null;
            System.out.println(e);
        }
       return estudiante;
    }
}