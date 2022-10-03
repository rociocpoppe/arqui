package dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import entidades.Carrera;

public class DTOReporte implements Serializable{

    private String c;
    private Timestamp fecha;
    private int inscriptos;
    private int graduados;

    public DTOReporte() {
    }

    public DTOReporte(String o, Timestamp fechaInscripcion, int inscriptos, int graduados) {
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

    public int getInscriptos() {
        return inscriptos;
    }

    public int getGraduados() {
        return graduados;
    }

    
    public void setC(String c) {
        this.c = c;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public void setInscriptos(int inscriptos) {
        this.inscriptos = inscriptos;
    }

    public void setGraduados(int graduados) {
        this.graduados = graduados;
    }

    @Override
    public String toString() {
        return "DTOReporte [Carrera=" + c + ", fecha=" + fecha + ", inscriptos=" + inscriptos + ", graduados=" + graduados
                + "]";
    }
   

    

    
}
