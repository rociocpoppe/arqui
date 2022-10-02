package Repository;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IEstudiante;
import entidades.Carrera;
import entidades.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
                // em.getTransaction().begin();
                saveEstudiante(e);
                // em.getTransaction().commit();
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
        em.getTransaction().begin();
        if (est.getNroDni()== null) {
            em.persist(est);
        } else {
            est = em.merge(est);
        }
        em.getTransaction().commit();
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
    public List<Estudiante> getEstudiantesByGenero(String genero){
        String q="SELECT e FROM Estudiante e WHERE e.genero=:genero";
        Query query=em.createQuery(q);
        query.setParameter("genero", genero);
        @SuppressWarnings("unchecked")
        List<Estudiante> estudiantesXGenero=query.getResultList();
        return estudiantesXGenero;
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
       String jpql = "SELECT e FROM Estudiante e WHERE e.nroLibretaUniv = :lu";
        try {
            TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
            query.setParameter("lu", nroLibretaUniv);
            estudiante = query.getSingleResult();
        } catch (Exception e) {
            estudiante = null;
            System.out.println(e);
        }
       return estudiante;
    }


    public List <Estudiante> getEstudiantesByCarrera(Carrera c, String ciudad){
        em.getTransaction().begin();
        String q="SELECT e FROM Estudiante e"
                +" JOIN e.carreras c"
                +" WHERE e.ciudadResidencia=:ciudad AND carreraId=:carreraId"
                +" ORDER BY e.ciudadResidencia";
        Query query=em.createQuery(q);
        query.setParameter("ciudad", ciudad);
        query.setParameter("carreraId", c.getIdCarrera());
        @SuppressWarnings("unchecked")
        List<Estudiante> estudiantesXCarrera=query.getResultList();
        em.getTransaction().commit(); 
		return estudiantesXCarrera;

    }

    public List<Estudiante> getEstudiantesByCriterio(String dato) {
        em.getTransaction().begin();
        String jpql = "SELECT e FROM Estudiante e ORDER BY e."+dato;
        Query query=em.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<Estudiante> estudiantesOrderCriterio=query.getResultList();
        em.getTransaction().commit();
        return estudiantesOrderCriterio;
    }
}