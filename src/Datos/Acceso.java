
package Datos;


public class Acceso {
    private int id;
    private String nombre;
    private String contraseña;
    private String tipo;

    public Acceso(int id, String nombre, String contraseña, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }

    public Acceso() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
