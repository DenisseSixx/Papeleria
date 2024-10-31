/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.Creditos;
import Datos.MantenimientoCreditos;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author user
 */
public class NegocioCreditos {
     MantenimientoCreditos mcc;
    
    public void PuenteRegistroNvo(Creditos cre){
        mcc = new MantenimientoCreditos();
        mcc.RegistroCreditos(cre);
    }

    public void PuenteActualizar(Creditos cre) throws SQLException {
       mcc = new MantenimientoCreditos();
        mcc.Actualizar(cre);
    }

    public void PuenteEliminar(Creditos cre) throws SQLException {
       mcc = new MantenimientoCreditos();
        mcc.Eliminar(cre);
    }
   

    public ArrayList<Creditos> PuenteLlenarTabla(int cre) {
         mcc = new MantenimientoCreditos();
        ArrayList<Creditos> lista = mcc.Llenartabla(cre);
        return lista;
    }
     public JTable PuenteMostrarTabla(JTable tabladatos){
        mcc = new MantenimientoCreditos();
        JTable tbl;
        tbl=mcc.MostrarTabla(tabladatos);
        
        return tbl;
}
}

