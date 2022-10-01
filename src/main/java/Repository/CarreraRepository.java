package Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Interfaces.*;
import entidades.Carrera;
import entidades.Estudiante;
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
    public void matricularEstudiante(Estudiante e) {
           //?   
    }

    @Override
    public List<Carrera> getCarreras() {
        em.getTransaction().begin();
        String jpql= ("SELECT c FROM carrera c");
        Query query=em.createQuery(jpql);
        return query.getResultList();
        
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
        System.out.println("delete " + c);
        if (em.contains(c)) {
            System.out.println("delete " + c);
			em.remove(c);
            System.out.println("delete " + c);
		} else {
			em.merge(c);
		}
    }

    @Override
    public Carrera findCarrera(Long id) { 
        return em.find(Carrera.class, id);
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


    public List<Carrera> getCarreraByEstudiante(){
        List<Carrera>carreras=new ArrayList<Carrera>();
        em.getTransaction().begin();
        String query="SELECT c.nombre,count(ec) as contador FROM Carrera c  "
        + "JOIN c.estudiantes ec "
        + "WHERE c.estudiantes IS NOT EMPTY "
        + "GROUP BY c.nombre "
        + "ORDER BY contador DESC";
        try{
            carreras = em.createQuery(query).getResultList();
            em.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }

        return carreras;
    }
    
}