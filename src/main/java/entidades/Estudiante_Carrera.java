package entidades;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table (name="EstudianteCarrera")
public class Estudiante_Carrera{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn (name="fk_estudiante")
    private Estudiante estudiante;

    @ManyToOne (cascade= CascadeType.MERGE)
    @JoinColumn (name="fk_carrera")
    private Carrera carrera;
    
    @Column
	private Date fechaInscripcion;
	@Column
	private Date fechaGraduacion;

    private int antiguedad;

    public Estudiante_Carrera() {
		super();
	}


    
    public Estudiante_Carrera(Estudiante estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
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

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Date getFechaGraduacion() {
        return fechaGraduacion;
    }

    public void setFechaGraduacion(Date fechaGraduacion) {
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