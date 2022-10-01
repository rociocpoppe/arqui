package Interfaces;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;

import entidades.Carrera;
import entidades.Estudiante;
import entidades.Estudiante_Carrera;

public interface IEstudiante_Carrera {


    public void insertarEstudianteCarrera(CSVParser c);
    public void saveEstudianteCarrera(Estudiante_Carrera e);
    //ordenado por cantidad de inscriptos
    public List<Carrera> getCarrerasByInscriptos ();

    //recuperar estudiantes de una determinada carrera, filtrado por ciudad de residencia
    public ArrayList<Estudiante> getEstudiantesByCarrera (Carrera c, String ciudad);
}