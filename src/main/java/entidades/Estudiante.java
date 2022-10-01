package entidades;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="Estudiante")
public class Estudiante{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long nroDni;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudadResidencia;
    @Column
    private int nroLibretaUniv;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
    private Set <Estudiante_Carrera> carreras;
    

    
    public Estudiante(Long nroDni, String nombre, String apellido,  String ciudadResidencia,int edad, String genero,
            int nroLibretaUniv) {
        this.nroDni = nroDni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudadResidencia = ciudadResidencia;
        this.nroLibretaUniv = nroLibretaUniv;
    }

    public Estudiante(Long nroDni, String nombre, String apellido, int nroLibretaUniv) {
        this.nroDni = nroDni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroLibretaUniv = nroLibretaUniv;
    }


    
    public Estudiante(Long nroDni) {
        this.nroDni = nroDni;
    }

    public Estudiante() {

    }

    public Long getNroDni() {
        return nroDni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public int getNroLibretaUniv() {
        return nroLibretaUniv;
    }

    public ArrayList<Estudiante_Carrera> getCarreras() {
        return new ArrayList<>(carreras);
    }

    public void addCarrera(Estudiante_Carrera carrera) {
        this.carreras.add(carrera);
    }

    @Override
    public String toString() {
        return "Estudiante [apellido=" + apellido + ", carreras=" + carreras + ", ciudadResidencia=" + ciudadResidencia
                + ", edad=" + edad + ", genero=" + genero + ", nombre=" + nombre + ", nroDni=" + nroDni
                + ", nroLibretaUniv=" + nroLibretaUniv + "]";
    }


    

}