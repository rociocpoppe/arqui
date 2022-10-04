package Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Interfaces.IEstudiante_Carrera;
import dto.DTOReporte;
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
    public void matricularEstudiante(Estudiante estudiante, Carrera carrera){
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
    
    @SuppressWarnings("unchecked")
	public List<Object[]> getReporte() {
        em.getTransaction().begin();
        String q="select nombre,YEAR(anio), sum(inscriptos) as inscriptos,"
        +  " sum(graduados) as graduados from"
        +  " (SELECT c.nombre, fechaInscripcion as anio, count(estudianteId) as inscriptos,"
        +  " '0' as graduados from Carrera c inner join  Estudiante_Carrera ec "
        +  " on carreraId= c.idCarrera group by carreraId,anio union"
        +  " SELECT c.nombre, fechaGraduacion as anio,  '0' as inscriptos, count(estudianteId) as graduados"
        + " from Carrera c inner join  Estudiante_Carrera ec on carreraId= c.idCarrera "
        + " where fechaGraduacion is not null group by carreraId,anio order by nombre,anio) a group by nombre, anio";
		Query query = em.createNativeQuery(q);
        List <Object[]> auxReport = query.getResultList();
        auxReport.stream().map(r-> new DTOReporte((String) r[0],(Integer) r[1],(double) r[2],(double) r[3])).collect(Collectors.toList());
        em.getTransaction().commit();
        return auxReport; 
	}


}