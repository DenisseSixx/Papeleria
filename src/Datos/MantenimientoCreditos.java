/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class MantenimientoCreditos {
     BDConexion con;
     ResultSet rs;
     PreparedStatement pst;
     Creditos cre; 
     
    private Statement sentenciaSQL;
    
    public void RegistroCreditos(Creditos cr ){           
            String insertar = "INSERT INTO tblcreditos(idcliente, folio, monto, idcredito, id)values(?,?,?,?,?)";
        try {
            con = new BDConexion();
            PreparedStatement pst = con.conectarse().prepareStatement(insertar);

            pst.setInt(1, cr.getIdcliente());
            pst.setInt(2, cr.getFolio());
            pst.setFloat(3, cr.getMonto());
            pst.setInt(4, cr.getIdcredito());
            pst.setInt(5, cr.getId());
          

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }
            con.CerrarConexion();
         } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InstantiationException e) {
            JOptionPane.showMessageDialog(null, "Error de instancia " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, "Mal acceso a la BD " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la sentencia " + e.getErrorCode(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void Eliminar(Creditos cr) {
        try {
            String sql = "DELETE FROM tblcreditos WHERE idcredito=" + cr.getIdcredito();
            con = new BDConexion();
            sentenciaSQL = con.conectarse().createStatement();
            int n = sentenciaSQL.executeUpdate(sql);
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }
            con.CerrarConexion();
            sentenciaSQL.close();
        } catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Verificacion", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public JTable BuscarProveedorTabla(JTable tablacreditos){
        try{
            String sql;
            con=new BDConexion();
            sentenciaSQL=con.conectarse().createStatement();
            sql="SELECT idcredito, monto FROM tblcreditos";
            ResultSet rs=sentenciaSQL.executeQuery(sql);
            ResultSetMetaData rsm=rs.getMetaData();
            
            int col= rsm.getColumnCount();
            DefaultTableModel modelo=new DefaultTableModel();
            for(int i=1;i<=2;i++){
                modelo.addColumn(rsm.getColumnLabel(i).toUpperCase());
            }
            while(rs.next()){
                String fila[]=new String[2];
                for(int j=0;j<2;j++){
                    fila[j]=rs.getString(j+1);
                }
                modelo.addRow(fila);
            }
            tablacreditos.setModel(modelo);
            rs.close();
            con.CerrarConexion();
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex){
            JOptionPane.showMessageDialog(null,"ERROR"+ex,"Verificar", JOptionPane.ERROR_MESSAGE);
        }
         return tablacreditos;     
    }

     public void Actualizar(Creditos cr) throws SQLException {
        String sql;
        sql = "UPDATE tblcreditos SET idcredito=?,folio=?,monto=?,idcliente=?,id=? where idcredito=?";
        try {
            con = new BDConexion();
            PreparedStatement pst = con.conectarse().prepareStatement(sql);

             pst.setInt(1, cr.getIdcliente());
            pst.setInt(2, cr.getFolio());
            pst.setFloat(3, cr.getMonto());
            pst.setInt(4, cr.getIdcredito());
            pst.setInt(5, cr.getId());
            pst.setInt(6, cr.getIdcliente());
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Verificacion", JOptionPane.ERROR_MESSAGE);
        }

        con.CerrarConexion();
    }
      public ArrayList<Creditos> Llenartabla(int clv) {
        ArrayList<Creditos> cr = new ArrayList<>();
        try {
            con = new BDConexion();
            String consola = "SELECT * FROM tblcreditos WHERE idcredito=" + clv;
            PreparedStatement pst = con.conectarse().prepareStatement(consola);
            ResultSet rs = pst.executeQuery();

            Creditos cre;
            if (rs.next()) {
                cre = new Creditos();
                cre.setIdcredito(rs.getInt("idcredito"));
                cre.setFolio(rs.getInt("folio"));
                cre.setId(rs.getInt("id"));
                cre.setIdcliente(rs.getInt("idcliente"));
                cre.setMonto(rs.getFloat("monto"));
              
               cr.add(cre);
            }
            rs.close();
            con.CerrarConexion();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Verificacion", JOptionPane.ERROR_MESSAGE);
        }
        return cr;
    }
       public JTable MostrarTabla(JTable tablaDatos){
        try{
              String sql;
            con=new BDConexion();
            sentenciaSQL=con.conectarse().createStatement();
            sql="SELECT * FROM tblcreditos";
            ResultSet rs=sentenciaSQL.executeQuery(sql);
            ResultSetMetaData rsm=rs.getMetaData();
            int col=rsm.getColumnCount();
            DefaultTableModel modelo=new DefaultTableModel();
          for(int i=1;i<col+1;i++){
                modelo.addColumn(rsm.getColumnLabel(i));
            }
            while(rs.next()){
                String fila[]=new String[col];
               for (int j = 0; j < col ; j++) {
                    fila[j]=rs.getString(j+1);
                }
                modelo.addRow(fila);
            }
            tablaDatos.setModel(modelo);
            rs.close();
            con.CerrarConexion();
            
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex){
          JOptionPane.showMessageDialog(null, "Error:"+ ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    return tablaDatos;
     
        }
        public int numSerieVentas() {
       int  idcredito = 0;
        try {
              String sql;
            con=new BDConexion();
            sentenciaSQL=con.conectarse().createStatement();
            sql="SELECT max(idcredito) FROM tblcreditos";
            ResultSet rs=sentenciaSQL.executeQuery(sql);

            while (rs.next()) {
                idcredito = rs.getInt(1);
            }
         } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada: " + ex, "error", JOptionPane.ERROR_MESSAGE);
        
    } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "Error de instancia: " + ex, "error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Mal acceso a la BD: " + ex, "error", JOptionPane.ERROR_MESSAGE);
} catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la sentencia: " + ex, "error", JOptionPane.ERROR_MESSAGE);
}
        return idcredito;
    }

}
