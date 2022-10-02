package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import Repository.CarreraRepository;
import Repository.EstudianteRepository;
import Repository.Estudiante_CarreraRepository;
import dto.DTOReporte;
import entidades.Carrera;
import entidades.Estudiante;
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
            
           
            // cargar datos 
            // carreraRepository.insertCarrera(parserCarreras);
            // estudianteRepository.insertarEstudiante(parserEstudiante);
            //estudiante_carreraRepository.insertarEstudianteCarrera(parserEstudianteCarrera);
            
            //CONSULTA A
            // Estudiante est=new Estudiante((long)40, "Juan", "Rodriguez", "Las Flores", 29, "m", 1900);
            // estudianteRepository.saveEstudiante(est);
            //CONSULTA B
            //matricular estudiante a una carrera
            // Carrera carrera= carreraRepository.getCarreraById(2);
            // Estudiante estudiante=estudianteRepository.getEstudianteByLU(456);
            // estudiante_carreraRepository.matricularEstudiante(estudiante, carrera);

            //CONSULTA C
            //ordenar estudiantes por algun criterio de ordenamiento simple --> edad
            // for(Estudiante estudiante: estudianteRepository.getEstudiantesByCriterio("edad")){
            //     System.out.print(estudiante + "\n" );
            // }

            //ordenar estudiantes por algun criterio de ordenamiento simple -->genero
            // for(Estudiante estudiante: estudianteRepository.getEstudiantesByCriterio("genero")){
            //     System.out.print(estudiante + "\n" );
            // }

            //ordenar estudiantes por algun criterio de ordenamiento simple -->LU
            // for(Estudiante estudiante: estudianteRepository.getEstudiantesByCriterio("nroLibretaUniv")){
            //     System.out.print(estudiante + "\n" );
            // }

            //ordenar estudiantes por algun criterio de ordenamiento simple -->apellido
            // for(Estudiante estudiante: estudianteRepository.getEstudiantesByCriterio("apellido")){
            //     System.out.print(estudiante + "\n" );
            // }

            //ordenar estudiantes por algun criterio de ordenamiento simple -->nombre
            // for(Estudiante estudiante: estudianteRepository.getEstudiantesByCriterio("nombre")){
            //     System.out.print(estudiante + "\n" );
            // }

            //ordenar estudiantes por algun criterio de ordenamiento simple -->ciudad
            // for(Estudiante estudiante: estudianteRepository.getEstudiantesByCriterio("ciudadResidencia")){
            //     System.out.print(estudiante + "\n" );
            // }

            //CONSULTA D
            //obtener estudiante por libreta universitaria
            //System.out.println("estudiante libreta"+estudianteRepository.getEstudianteByLU(445));

            //CONSULTA E
            //obtener estudiantes por genero
            // for(Estudiante estudianteXGenero: estudianteRepository.getEstudiantesByGenero("f")){
            //          System.out.println(estudianteXGenero+"\n");
            // }

            //CONSULTA F
            //obtener carreras por estudiantes inscriptos, ordenadas por cantidad de inscriptos
            // for(Object carreraXEstudiantes: carreraRepository.getCarreraXEstudiantesInscriptos()){
            //     Object[] c=(Object[])carreraXEstudiantes;
            //     System.out.printf("%s %s\n" ,c[0],c[1]);
            // }
          
            //CONSULTA G
            //obtener estudiantes por carrera, filtrado por ciudad de residencia
            // Carrera c=carreraRepository.findCarrera((long)4);
            // System.out.println(c);
            //     for(Estudiante e: estudianteRepository.getEstudiantesByCarrera(c,"Mar del Plata")){
            //         System.out.println(e + "\n");
            //     }


            //REPORTE 

            List d = carreraRepository.getReporte();
            for(Object o : d) {
                Object[] y = (Object[])o;
                System.out.printf("%s %s %d %d\n", y[0], y[1],y[2],y[3]);
            }
      

       
				


           
      



    }
}