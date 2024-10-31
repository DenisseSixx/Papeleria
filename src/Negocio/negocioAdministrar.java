
package Negocio;

import Datos.MantenimientoAdministrar;
import Datos.Acceso;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;



public class negocioAdministrar {
    MantenimientoAdministrar ma;
    public String puenteAcceso(Acceso acc){
        ma=new MantenimientoAdministrar();
        String tipo=ma.consultaUsuario(acc);
        return tipo;
    }
   //  public JTable PuenteBuscarTabla( JTable tablaProveedor){
     //  JTable tbl;
      // ma=new MantenimientoAdministrar();
      // tbl=ma.BuscarProveedorTabla(tablaProveedor);
      // return tbl;
    


    public void PuenteRegistroNvo(Acceso acc){
        ma = new MantenimientoAdministrar();
        ma.RegistroNuevo(acc); 
    }

    public void PuenteActualizar(Acceso acc) throws SQLException {
        ma = new MantenimientoAdministrar();
        ma.Actualizar(acc);
    }

    public void PuenteEliminar(Acceso acc) throws SQLException {
        ma = new MantenimientoAdministrar();;
        ma.Eliminar(acc);
    }
    
    public ArrayList<Acceso> consultarCombo() {
        ma = new MantenimientoAdministrar();
        ArrayList<Acceso> lista = ma.Llenarcombo();
        return lista;
    }

    public ArrayList<Acceso> PuenteLlenarTabla(int clv) {
        ma = new MantenimientoAdministrar();
        ArrayList<Acceso> lista = ma.Llenartabla(clv);
        return lista;
    }
     public JTable PuenteMostrarTabla(JTable tabladatos){
        ma=new MantenimientoAdministrar();
        JTable tbl;
        tbl=ma.MostrarTabla(tabladatos);
        
        return tbl;
}
}
