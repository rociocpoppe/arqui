package entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table (name="Carrera")
public class Carrera {

    @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idCarrera;

    @Column
    private String nombre;

    private int duracion;

    @OneToMany (fetch= FetchType.LAZY, mappedBy = "carrera")
    private Set<Estudiante_Carrera> estudiantes;

    public Carrera(Long id,String nombre) {
        this.idCarrera=id;
        this.nombre = nombre;
    }

    public Carrera() {

    }


    public Long getIdCarrera() {
        return idCarrera;
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Carrera [estudiantes=" + estudiantes + ", idCarrera=" + idCarrera + ", nombre=" + nombre + "]";
    }


    // public ArrayList<Estudiante_Carrera> getEstudiantes() {
    //      return new ArrayList<Estudiante_Carrera>(estudiantes);
    // }

    //  public void addEstudiante(Estudiante estudiante) {
    //     Estudiante_Carrera e= new Estudiante_Carrera(estudiante, this);
    //     this.estudiantes.add(e);
    //     estudiante.addCarrera(e);
    // }


    
    
    
}
