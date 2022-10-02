package Interfaces;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;

import entidades.Estudiante;

public interface IEstudiante{

    public boolean insertarEstudiante(CSVParser estudiantes);

    public void saveEstudiante(Estudiante e);
 
    public Estudiante findEstudiante(Long id);
    public void deleteEstudiante(Estudiante e);
    //el get se podria hacer en uno solo
    // public ArrayList<Estudiante> getEstudiantesByCriterio(String dato);

    public List<Estudiante> getEstudiantesByGenero(String dato);
    public Estudiante getEstudianteByLU(int nroLibretaUniv);
}