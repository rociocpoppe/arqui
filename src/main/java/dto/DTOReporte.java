package dto;

import java.util.Date;

import entidades.Carrera;

public class DTOReporte {

    private Carrera c;
    private Date fechaInscripcion;
    private Date fechaGraduacion;

    public DTOReporte() {
    }

    public DTOReporte(Carrera c, Date fechaInscripcion, Date fechaGraduacion) {
        this.c = c;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaGraduacion = fechaGraduacion;
    }
    public Carrera getC() {
        return c;
    }
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }
    public Date getFechaGraduacion() {
        return fechaGraduacion;
    }
    

    
}
