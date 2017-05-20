package info.androidhive.saluDate.ConexionService;

/**
 * Created by gustavo on 18/05/17.
 */

public class POJOperson {
    private String nombre;
    private String apellido;
    private int edad;
    private String carrera;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    @Override
    public String toString() {
        return "POJOperson{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}

