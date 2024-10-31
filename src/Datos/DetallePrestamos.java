
package Datos;

import java.sql.Date;


public class DetallePrestamos {
    
  
    private int folio;
    private int clave;
    private String titulo;
 
 
    private int idcliente;
    private String fecha;

    public DetallePrestamos(int folio, int clave, String titulo, int idcliente, String fecha) {
       
        this.folio = folio;
        this.clave = clave;
        this.titulo=titulo;
      
 
        this.idcliente=idcliente;
        this.fecha = fecha;
    }
    public DetallePrestamos(){
        
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public int getidcliente() {
        return idcliente;
    }
 //public void getidcliente(int idcliente) {
   //     this.idcliente = idcliente;
    //}
    public void setidcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}