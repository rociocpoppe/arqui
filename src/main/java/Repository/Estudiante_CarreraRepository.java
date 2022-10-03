package Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Interfaces.IEstudiante_Carrera;
import entidades.Carrera;
import entidades.Estudiante;
import entidades.Estudiante_Carrera;

public class Estudiante_CarreraRepository implements IEstudiante_Carrera{

    private EntityManager em;

    public Estudiante_CarreraRepository() {
    }

    public Estudiante_CarreraRepository(EntityManager em) {
        super();
        this.em=em;
    }
    
    @Override
    public void saveEstudianteCarrera(Estudiante_Carrera e) {
        if (e.getId() == null) {
			em.persist(e);
		} else {
			e = em.merge(e);
		}	
    }

    @Override
    public void insertarEstudianteCarrera(CSVParser estudiantesCarreras) {   
        for(CSVRecord row: estudiantesCarreras) {
            try {  
                em.getTransaction().begin();
                Long nroDni = Long.parseLong(row.get("estudiante"));
                Estudiante e=em.find(Estudiante.class,nroDni);
                Long idCarrera= Long.parseLong(row.get("carrera"));
                Carrera c= em.find(Carrera.class, idCarrera);
                String dato=row.get("fechaInscripcion");
                System.out.println(dato);
                Timestamp inscripcion = Timestamp.valueOf(row.get("fechaInscripcion"));
                Timestamp graduacion = null;
                if(!row.get("fechaGraduacion").equals("NULL")) {
                    graduacion = Timestamp.valueOf(row.get("fechaGraduacion"));
                }
                
                Estudiante_Carrera ec = new Estudiante_Carrera(e,c, inscripcion, graduacion);
                this.saveEstudianteCarrera(ec);
                em.getTransaction().commit();
        
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }	
    }   


    @Override
    public List<Carrera> getCarrerasByInscriptos() {
        List<Carrera> result;
        String jpql = "SELECT COUNT(ec.estudiante)"
                + "FROM Carrera c JOIN c.estudiantes ec "
                + "WHERE ec.carrera.idCarrera=c.idCarrera AND C.estudiantes IS NOT EMPTY"
                +  "GROUP BY ec.carrera.idCarrera";


        em.getTransaction().begin();
        try {
            TypedQuery<Carrera> query = em.createQuery(jpql, Carrera.class);
            result = query.getResultList() ;
        } catch (Exception e) {
            System.out.println(e);
            result = null;
        }
        em.getTransaction().commit();

        return result;
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante, Carrera carrera){
        // estudiante.addCarrera(carrera);
        // carrera.addEstudiante(estudiante);
        em.getTransaction().begin();
        String q=" INSERT INTO Estudiante_Carrera (antiguedad, fechaGraduacion, fechaInscripcion, carreraId, estudianteId) VALUES (?,?,?,?,?)";
        Query query= em.createNativeQuery(q);
        query.setParameter(1,0);
        query.setParameter(2,null);
        query.setParameter(3,null);
        query.setParameter(5,estudiante.getNroDni());
        query.setParameter(4,carrera.getIdCarrera());
        query.executeUpdate();
        em.getTransaction().commit(); 
    }
    

}