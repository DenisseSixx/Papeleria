/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.MantenimientoLibros;
import Datos.Libros;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author user
 */
public class NegocioLibros {
    MantenimientoLibros mp;
    
    public void PuenteRegistroNvo(Libros libro){
        mp=new MantenimientoLibros();
        mp.RegistroNuevo(libro);
    }
    public JTable PuenteMostrarTabla(JTable tabladatos){
        mp=new MantenimientoLibros();
        JTable tbl;
        tbl=mp.MostrarTabla(tabladatos);
        
        return tbl;
                
  
    }
    public ImageIcon puenteObtenerImagen(int clv){
         mp=new MantenimientoLibros();
         ImageIcon img=null;
         try{
             img=mp.ObtenerImagen(clv);
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error: " +e);
    }
         return img;
}
   public void PuenteActualizar(Libros libro) throws SQLException{
       mp=new MantenimientoLibros();
       mp.Actualizar(libro);
       
   }
   public void PuenteEliminar (Libros libro ) throws SQLException{
       mp=new MantenimientoLibros();
       mp.Eliminar(libro);

}
   public Vector<Libros> consultarCombo() {
        mp = new MantenimientoLibros();
        String buscarLibros = null;
        Vector<Libros> lista = mp.buscarLibros(buscarLibros);
        return lista;
}
}