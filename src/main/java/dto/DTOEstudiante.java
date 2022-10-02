package dto;

public class DTOEstudiante {

    private int nroDni;
    private int nroLibretaUniv;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;

    public DTOEstudiante(int nroDni, int nroLibretaUniv, String nombre, String apellido, int edad, String genero,
            String ciudad) {
        this.nroDni = nroDni;
        this.nroLibretaUniv = nroLibretaUniv;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
    }

    public int getNroDni() {
        return nroDni;
    }

    public int getNroLibretaUniv() {
        return nroLibretaUniv;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    @Override
    public String toString() {
        return "DTOEstudiante [nroDni=" + nroDni + ", nroLibretaUniv=" + nroLibretaUniv + ", nombre=" + nombre
                + ", apellido=" + apellido + ", edad=" + edad + ", genero=" + genero + ", ciudad=" + ciudad + "]";
    }

    

    
    
}
