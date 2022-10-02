package entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Estudiante_CarreraId implements Serializable{

    private static final long serial = 1L;

	private int id_estudiante;
	private int id_carrera;
	
	public Estudiante_CarreraId() {
		super();
	}

	public Estudiante_CarreraId(int id_estudiante, int id_carrera) {
		super();
		this.id_estudiante = id_estudiante;
		this.id_carrera = id_carrera;
	}

	public int getId_estudiante() {
		return id_estudiante;
	}

	public void setId_estudiante(int id_estudiante) {
		this.id_estudiante = id_estudiante;
	}

	public int getId_carrera() {
		return id_carrera;
	}

	public void setId_carrera(int id_carrera) {
		this.id_carrera = id_carrera;
	}

	public static long getSerial() {
		return serial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_carrera;
		result = prime * result + id_estudiante;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante_CarreraId other = (Estudiante_CarreraId) obj;
		if (id_carrera != other.id_carrera)
			return false;
		if (id_estudiante != other.id_estudiante)
			return false;
		return true;
	}
    
}
