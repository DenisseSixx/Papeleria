/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author user
 */
public class Autores {

 
   private int claveautor;
   private String nombre;
   private String rfc;
   private long telefono;
    private String direccion;
     private String correo;
      

   public Autores(int claveautor, String nombre, String rfc, long telefono, String direccion, String correo) {
        this.claveautor = claveautor;
        this.nombre = nombre;
        this.rfc = rfc;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }
   public Autores(){
       
   }
    public int getClaveautor() {
        return claveautor;
    }

    public void setClaveautor(int claveautor) {
        this.claveautor = claveautor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
