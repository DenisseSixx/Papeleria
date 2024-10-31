/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import javax.swing.JTable;
import Datos.MantenimientoClientes;
import Datos.Clientes;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public class NegocioClientes {
    MantenimientoClientes mc;
   public ArrayList<Clientes> consultarComboCarrera(){
        mc=new MantenimientoClientes();
        ArrayList<Clientes> listaCarrera=mc.Llenarcombo();
        return listaCarrera;
   }

    public JTable PuenteBuscarTabla( JTable tablaclientes){
       JTable tbl;
       mc=new MantenimientoClientes();
       tbl=mc.BuscarClientesTabla(tablaclientes);
       return tbl;
    }
        public void PuenteRegistroNvo(Clientes clie){
        mc = new MantenimientoClientes();
        mc.RegistroCliente(clie); 
    }

    public void PuenteActualizar(Clientes clie) throws SQLException {
        mc = new MantenimientoClientes();
        mc.Actualizar(clie);
    }

    public void PuenteEliminar(Clientes clie) throws SQLException {
        mc = new MantenimientoClientes();
        mc.Eliminar(clie);
    }
    
    public ArrayList<Clientes> consultarCombo() {
        mc = new MantenimientoClientes();
        ArrayList<Clientes> lista = mc.Llenarcombo();
        return lista;
    }

    public ArrayList<Clientes> PuenteLlenarTabla(int clv) {
        mc = new MantenimientoClientes();
        ArrayList<Clientes> lista = mc.Llenartabla(clv);
        return lista;
    }
     public JTable PuenteMostrarTabla(JTable tabladatos){
         mc = new MantenimientoClientes();
        JTable tbl;
        tbl=mc.MostrarTabla(tabladatos);
        
        return tbl;
}
}

