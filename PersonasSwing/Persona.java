package PersonasSwing;
public class Persona {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;

    public Persona(String nombre, String apellidos, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " - " + telefono + " - " + direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
}
