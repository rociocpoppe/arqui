package Interfaces;

import java.util.List;

import org.apache.commons.csv.CSVParser;

import entidades.Carrera;

public interface ICarrera{

    public Carrera getCarreraById(int id);
    public void insertCarrera(CSVParser c);
    public void saveCarrera(Carrera c);
    public void deleteCarrera(Carrera c);
    public Carrera findCarrera(Long id);
    public List<Carrera> getCarreraXEstudiantesInscriptos();
 

}