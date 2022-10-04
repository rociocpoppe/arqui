package dto;

import java.io.Serializable;

public class DTOReporte implements Serializable{

    private String c;
    private Integer fecha;
    private double inscriptos;
    private double graduados;

    public DTOReporte() {
    }

    public DTOReporte(String o, Integer fechaInscripcion, double inscriptos, double graduados) {
        this.c = o;
        this.fecha = fechaInscripcion;
        this.inscriptos=inscriptos;
        this.graduados=graduados;
    }
    public String getC() {
        return c;
    }

    public Integer getFecha() {
        return fecha;
    }

    public double getInscriptos() {
        return inscriptos;
    }

    public double getGraduados() {
        return graduados;
    }

    
    public void setC(String c) {
        this.c = c;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }

    public void setInscriptos(double inscriptos) {
        this.inscriptos = inscriptos;
    }

    public void setGraduados(double graduados) {
        this.graduados = graduados;
    }

    @Override
    public String toString() {
        return "DTOReporte [Carrera=" + c + ", fecha=" + fecha + ", inscriptos=" + inscriptos + ", graduados=" + graduados
                + "]";
    }
 
}
