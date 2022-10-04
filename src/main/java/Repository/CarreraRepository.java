package Repository;


import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import Interfaces.*;
import entidades.Carrera;


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
            Long id=Long.parseLong(row.get("idCarrera"));
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

   

}