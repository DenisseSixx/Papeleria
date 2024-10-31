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
public class Creditos {
    private int idcredito;
   private int folio;
   private float monto;
    private int idcliente;
     private int id;

    public Creditos(int idcredito, int folio, float monto, int idcliente, int id) {
        this.idcredito = idcredito;
        this.folio = folio;
        this.monto = monto;
        this.idcliente = idcliente;
        this.id = id;
    }

    public Creditos() {
    }

    public int getIdcredito() {
        return idcredito;
    }

    public void setIdcredito(int idcredito) {
        this.idcredito = idcredito;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
