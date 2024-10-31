
package Datos;

import java.sql.Date;

public class Libros {
    private int clave;
    private String titulo;
    private int stock;
    private int NPags;
    private int claveAutor;
    private Date fecha_Ingreso;
    private int nopedido;
    private String CodBarras;
    private String rutaImagen;
    private String imagen;

    public Libros(int clave, String titulo, int stock,  int NPags, int claveAutor, Date fecha_Ingreso, int nopedido, String CodBarras, String rutaImagen, String imagen) {
        this.clave = clave;
        this.titulo = titulo;
        this.stock = stock;
        this.NPags = NPags;
        this.claveAutor = claveAutor;
        this.fecha_Ingreso = fecha_Ingreso;
        this.nopedido = nopedido;
        this.CodBarras = CodBarras;
        this.rutaImagen = rutaImagen;
        this.imagen = imagen;
    }

    public Libros(){
        
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String descripcion) {
        this.titulo = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getNPags() {
        return NPags;
    }

    public void setNPags(int NPags) {
        this.NPags = NPags;
    }

    public int getClaveAutor() {
        return claveAutor;
    }

    public void setClaveAutor(int claveAutor) {
        this.claveAutor = claveAutor;
    }

    public Date getFecha_Ingreso() {
        return fecha_Ingreso;
    }

    public void setFecha_Ingreso(Date fecha_Ingreso) {
        this.fecha_Ingreso = fecha_Ingreso;
    }

    public int getNopedido() {
        return nopedido;
    }

    public void setNopedido (int nopedido) {
        this.nopedido = nopedido;
    }

    public String getCodBarras() {
        return CodBarras;
    }

    public void setCodBarras(String CodBarras) {
        this.CodBarras = CodBarras;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
           
}
