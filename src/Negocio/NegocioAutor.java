/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import javax.swing.JTable;
import Datos.MantenimientoAutores;
import Datos.Autores;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public class NegocioAutor {
    MantenimientoAutores mp;
   public ArrayList<Autores> consultarComboCarrera(){
        mp=new MantenimientoAutores();
        ArrayList<Autores> listaCarrera=mp.Llenarcombo();
        return listaCarrera;
   }

    public JTable PuenteBuscarTabla( JTable tablaProveedor){
       JTable tbl;
       mp=new MantenimientoAutores();
       tbl=mp.BuscarAutorTabla(tablaProveedor);
       return tbl;
    }


    public void PuenteRegistroNvo(Autores prov){
        mp = new MantenimientoAutores();
        mp.RegistroProv(prov); 
    }

    public void PuenteActualizar(Autores prov) throws SQLException {
        mp = new MantenimientoAutores();
        mp.Actualizar(prov);
    }

    public void PuenteEliminar(Autores prov) throws SQLException {
        mp = new MantenimientoAutores();
        mp.Eliminar(prov);
    }
    
    public ArrayList<Autores> consultarCombo() {
        mp = new MantenimientoAutores();
        ArrayList<Autores> lista = mp.Llenarcombo();
        return lista;
    }

    public ArrayList<Autores> PuenteLlenarTabla(int clv) {
        mp = new MantenimientoAutores();
        ArrayList<Autores> lista = mp.Llenartabla(clv);
        return lista;
    }
     public JTable PuenteMostrarTabla(JTable tabladatos){
        mp = new MantenimientoAutores();
        JTable tbl;
        tbl=mp.MostrarTabla(tabladatos);
        
        return tbl;
}
}