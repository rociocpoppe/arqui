package entidades;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table (name="Estudiante_Carrera")
public class Estudiante_Carrera{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn (name="estudianteId")
    private Estudiante estudiante;

    @ManyToOne (cascade= CascadeType.MERGE)
    @JoinColumn (name="carreraId")
    private Carrera carrera;
    
    @Column
	private Timestamp fechaInscripcion;
	@Column
	private Timestamp fechaGraduacion;

    private int antiguedad;

    public Estudiante_Carrera() {
		super();
	}

    
    public Estudiante_Carrera(Estudiante estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
    }


    public Estudiante_Carrera(Estudiante estudiante, Carrera carrera, Timestamp inscripcion, Timestamp graduacion) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.fechaInscripcion=inscripcion;
        this.fechaGraduacion=graduacion;
    }


    public Long getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Timestamp getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Timestamp fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Timestamp getFechaGraduacion() {
        return fechaGraduacion;
    }

    public void setFechaGraduacion(Timestamp fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public String toString() {
        return "Estudiante_Carrera{" +
                "id=" + id +
                ", estudiante=" + estudiante +
                ", carrera=" + carrera +
                ", fechaInscripcion=" + fechaInscripcion +
                ", fechaGraduacion=" + fechaGraduacion +
                ", antiguedad=" + antiguedad +
                '}';
    }
}