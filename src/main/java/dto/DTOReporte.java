package dto;

import java.sql.Timestamp;
import java.util.Date;

import entidades.Carrera;

public class DTOReporte {

    private String c;
    private Timestamp fecha;
    private Integer inscriptos;
    private Integer graduados;

    public DTOReporte() {
    }

    public DTOReporte(String o, Timestamp fechaInscripcion, Integer inscriptos, Integer graduados) {
        this.c = o;
        this.fecha = fechaInscripcion;
        this.inscriptos=inscriptos;
        this.graduados=graduados;
    }
    public String getC() {
        return c;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public Integer getInscriptos() {
        return inscriptos;
    }

    public Integer getGraduados() {
        return graduados;
    }
   

    

    
}
