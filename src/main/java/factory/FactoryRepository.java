package factory;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import Repository.CarreraRepository;
import Repository.EstudianteRepository;
import Repository.Estudiante_CarreraRepository;

public abstract class FactoryRepository {

    public static final String MYSQL_DB = "mysql";
    public static final String DERBY_DB = "derby";

    public abstract CarreraRepository getCarreraRepository();
    public abstract EstudianteRepository getEstudianteRepository();
    public abstract Estudiante_CarreraRepository getEstudiante_CarreraRepository();

    //Patron Singleton mysql
    private static FactoryRepository db_mysql =null;

    public static FactoryRepository getInstanceMysql() {
        if(db_mysql== null) {
            db_mysql = new FactoryRepositoryMySql();
            return db_mysql;
        }
        else return db_mysql;
    }

    //Patron Singleton derby
    private static FactoryRepository db_derby = null;

    public static FactoryRepository getInstanceDerby() {
        if(db_derby== null) {
            db_derby = new FactoryRepositoryDerby();
            return  db_derby;
        }
        else return db_derby;
    }


    public static FactoryRepository getFactory(String db) {
        switch (db) {
            case MYSQL_DB:
                return getInstanceMysql();
            case DERBY_DB:
                return getInstanceDerby();
            default:
                return null;
        }
    }

}

