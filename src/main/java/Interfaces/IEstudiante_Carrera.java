package Interfaces;

import java.util.List;
import org.apache.commons.csv.CSVParser;
import entidades.Carrera;
import entidades.Estudiante;
import entidades.Estudiante_Carrera;

public interface IEstudiante_Carrera {


    public void insertarEstudianteCarrera(CSVParser c);
    public void saveEstudianteCarrera(Estudiante_Carrera e);
    public void matricularEstudiante(Estudiante e, Carrera c);
    public List<Object[]> getReporte();
}