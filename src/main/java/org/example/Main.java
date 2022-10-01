package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import Repository.CarreraRepository;
import Repository.EstudianteRepository;
import Repository.Estudiante_CarreraRepository;
import entidades.Carrera;
import factory.FactoryRepository;
import factory.FactoryRepositoryMySql;

public class Main {
    private static EstudianteRepository estudianteRepository;
    private static CarreraRepository carreraRepository;
    private static Estudiante_CarreraRepository estudiante_carreraRepository;
    public static void main(String[] args) throws FileNotFoundException, IOException {

            FactoryRepository factory= FactoryRepository.getFactory(FactoryRepository.MYSQL_DB);

            estudianteRepository=factory.getEstudianteRepository();
            carreraRepository=factory.getCarreraRepository();
            estudiante_carreraRepository=factory.getEstudiante_CarreraRepository();

            CSVParser parserCarreras = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/carreras.csv"));
            CSVParser parserEstudiante= CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/Estudiante.csv"));
            CSVParser parserEstudianteCarrera=CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/estudiantes_carreras.csv"));
            //carreraRepository.insertCarrera(parserCarreras);
            //estudianteRepository.insertarEstudiante(parserEstudiante);
            //System.out.println(carreraRepository.findCarrera((long) 1));
            //Carrera c=carreraRepository.findCarrera((long) 1);
            
            //carreraRepository.deleteCarrera(c);

            // for(Carrera carrera:carreraRepository.getCarreras()){
            //     System.out.println(carrera.toString());
            // }

            // for(Carrera c:carreraRepository.getCarreraByEstudiante()){
            //     System.out.println(c);
            // }
            // estudiante_carreraRepository.insertarEstudianteCarrera(parserEstudianteCarrera);
            
            //System.out.println(estudianteRepository.getEstudianteByLU(445));

            estudiante_carreraRepository.getCarrerasByInscriptos();
    }
}