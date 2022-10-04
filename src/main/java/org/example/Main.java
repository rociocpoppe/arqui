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
import entidades.Carrera;
import entidades.Estudiante;
import factory.FactoryRepository;


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
            // estudiante_carreraRepository.insertarEstudianteCarrera(parserEstudianteCarrera);
            
            //CONSULTA A
            System.out.println(" ");
            System.out.println("************CONSULTA A*******************************************");
            // Estudiante est=new Estudiante((long)40, "Juan", "Rodriguez", "Las Flores", 29, "m", 1900);
            // estudianteRepository.saveEstudiante(est);

            //CONSULTA B
            //matricular estudiante a una carrera
            System.out.println(" ");
            System.out.println("************CONSULTA B*******************************************");
            // Carrera carrera= carreraRepository.getCarreraById(2);
            // Estudiante estudiante=estudianteRepository.getEstudianteByLU(456);
            // estudiante_carreraRepository.matricularEstudiante(estudiante, carrera);

            //CONSULTA C
            System.out.println(" ");
            System.out.println("************CONSULTA C*******************************************");
            //ordenar estudiantes por algun criterio de ordenamiento simple -->apellido
            for(Estudiante estudiante: estudianteRepository.getEstudiantesByCriterio("apellido")){
                System.out.print(estudiante + "\n" );
            }

            //ordenar estudiantes por algun criterio de ordenamiento simple --> edad
            // for(Estudiante e: estudianteRepository.getEstudiantesByCriterio("edad")){
            //     System.out.print(e + "\n" );
            // }
            
            //ordenar estudiantes por algun criterio de ordenamiento simple -->genero
            // for(Estudiante estudiante: estudianteRepository.getEstudiantesByCriterio("genero")){
            //     System.out.print(estudiante + "\n" );
            // }

            //ordenar estudiantes por algun criterio de ordenamiento simple -->LU
            // for(Estudiante estudiante: estudianteRepository.getEstudiantesByCriterio("nroLibretaUniv")){
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
            System.out.println(" ");
            System.out.println("************CONSULTA D*******************************************");
            //obtener estudiante por libreta universitaria
            System.out.println("estudiante libreta"+estudianteRepository.getEstudianteByLU(445));

            //CONSULTA E
            System.out.println(" ");
            System.out.println("************CONSULTA E*******************************************");
            //obtener estudiantes por genero
            for(Estudiante estudianteXGenero: estudianteRepository.getEstudiantesByGenero("f")){
                     System.out.println(estudianteXGenero+"\n");
            }

            //CONSULTA F
            System.out.println(" ");
            System.out.println("************CONSULTA F*******************************************");
            //obtener carreras por estudiantes inscriptos, ordenadas por cantidad de inscriptos
            for(Object carreraXEstudiantes: carreraRepository.getCarreraXEstudiantesInscriptos()){
                Object[] c=(Object[])carreraXEstudiantes;
                System.out.printf("%s %s\n" ,c[0],c[1]);
            }
          
            //CONSULTA G
            System.out.println(" ");
            System.out.println("************CONSULTA G*******************************************");
            //obtener estudiantes por carrera, filtrado por ciudad de residencia
            Carrera ca=carreraRepository.findCarrera((long)4);
            for(Estudiante e: estudianteRepository.getEstudiantesByCarrera(ca,"Mar del Plata")){
                System.out.println(e + "\n");
            }


            //REPORTE 
            System.out.println(" ");
            System.out.println("************REPORTE *******************************************");
            List<Object[]> carrerasReporte=carreraRepository.getReporte();
            for(Object carr: carrerasReporte){
                Object[] y=(Object[])carr;
                String c=(String) y[0];
                c.toUpperCase();
                Integer anio=(int) y[1];
                double r1=(double)y[2];
                Integer inscriptos=(int)r1;
                double r2= (double)y[3];
                int graduados=(int)r2;
                System.out.println(c + " año " + anio + " inscriptos "+  inscriptos + " graduados " + graduados);
            }
          
    }
}