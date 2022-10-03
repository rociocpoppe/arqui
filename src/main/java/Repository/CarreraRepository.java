package Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Interfaces.*;
import dto.DTOReporte;
import entidades.Carrera;
import entidades.Estudiante;
import scala.unchecked;
import scala.annotation.compileTimeOnly;

public class CarreraRepository implements ICarrera{
    
    private EntityManager em;

    public CarreraRepository(){
        super();
    }

	public CarreraRepository(EntityManager em) {
        this.em = em;
	}

 
    @Override
    public Carrera getCarreraById(int id) {
        return findCarrera((long)id);
        
    }

    @Override
    public void insertCarrera(CSVParser carreras) {   
        for(CSVRecord row: carreras) {
			String nombre = row.get("nombre");
			System.out.println(row.get("nombre"));
            Long id=Long.parseLong(row.get("idCarrera"));
            System.out.println("id " +id);
			Carrera c = new Carrera(id,nombre);
			try {  
                em.getTransaction().begin();
				this.saveCarrera(c);
                em.getTransaction().commit();
          
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
     
    }

    @Override
    public void saveCarrera(Carrera c) {
        if (c.getIdCarrera() == null) {
			em.persist(c);
		} else {
			c = em.merge(c);
		}	
    }

    @Override
    public void deleteCarrera(Carrera c) {
        if (em.contains(c)) {
			em.remove(c);
		} else {
			em.merge(c);
		}
    }

    @Override
    public Carrera findCarrera(Long id) { 
        return em.find(Carrera.class, id);
    }

    public List<Carrera> getCarreraXEstudiantesInscriptos(){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT c.nombre,count(e) as contador FROM Carrera c "
        + "JOIN c.estudiantes e "
        + "WHERE c.estudiantes IS NOT EMPTY "
        + "GROUP BY c.nombre "
        + "ORDER BY contador DESC");
        @SuppressWarnings("unchecked")
        List<Carrera> carreras=q.getResultList();
        em.getTransaction().commit(); 
		return carreras;
    }

   @SuppressWarnings("unchecked")
	public List<Object> getReporte() {
        em.getTransaction().begin();
		Query query = em.createNativeQuery("select nombre,YEAR(anio), sum(inscriptos) as inscriptos,"
                                    +  " sum(graduados) as graduados from"
                                    +  " (SELECT c.nombre, fechaInscripcion as anio, count(estudianteId) as inscriptos,"
                                    +  " '0' as graduados from Carrera c inner join  Estudiante_Carrera ec "
                                    +  " on carreraId= c.idCarrera group by carreraId,anio union"
                                    +  " SELECT c.nombre, fechaGraduacion as anio,  '0' as inscriptos, count(estudianteId) as graduados"
                                    + " from Carrera c inner join  Estudiante_Carrera ec on carreraId= c.idCarrera "
                                    + " where fechaGraduacion is not null group by carreraId,anio order by nombre,anio) a group by nombre, anio");
                              
        //List<DTOReporte> reports = query.stream().map(o -> new DTOReporte((String)o[0], (Timestamp)o[1], (Integer)o[2], (Integer)o[3])).collect(Collectors.toList());
        List<Object> resultado=new ArrayList<>(query.getResultList()); 
        em.getTransaction().commit();
        return query.getResultList(); 
	}

}