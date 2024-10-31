
package Datos;

import java.util.Date;
import javax.swing.JComboBox;


public class Prestamos {
    
    private int folio;

    private String fecha;
    private int clave;
    private int idcliente;


    public Prestamos(int folio, String fecha, int clave, int idcliente) {
        this.folio = folio;
        this.fecha = fecha;
        this.clave= clave;
        this.idcliente = idcliente;
 
 
    }

    public Prestamos() {
    
 
 
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

   
  

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getidcliente() {
        return idcliente;
    }

    public void setidcliente(int idcliente) {
        this.idcliente = idcliente;
    }



    public void setIdcliente(JComboBox<String> cbocliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}