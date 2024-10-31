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
public class MantenimientoProveedores {
     BDConexion con;
     ResultSet rs;
     PreparedStatement pst;
     Autores pro; 
     
    private Statement sentenciaSQL;
    
    public void RegistroProv(Autores prov ){           
            String insertar = "INSERT INTO tblproveedores(clave_prov,nombre,rfc,telefono,direccion,correo)values(?,?,?,?,?,?)";
        try {
            con = new BDConexion();
            PreparedStatement pst = con.conectarse().prepareStatement(insertar);

            pst.setInt(1, prov.getClaveautor());
            pst.setString(2, prov.getNombre());
            pst.setString(3, prov.getRfc());
            pst.setLong(4, prov.getTelefono());
            pst.setString(5, prov.getDireccion());
            pst.setString(6, prov.getCorreo());

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
            JOptionPane.showMessageDialog(null, "Error en la sentencia, dato repetido " + e.getErrorCode(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void Eliminar(Autores prov) {
        try {
            String sql = "DELETE FROM tbautor WHERE clave_prov=" + prov.getClaveautor();
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
    
    
    public JTable BuscarProveedorTabla(JTable tablaProveedor){
        try{
            String sql;
            con=new BDConexion();
            sentenciaSQL=con.conectarse().createStatement();
            sql="SELECT claveautor, nombre FROM tbautor";
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
            tablaProveedor.setModel(modelo);
            rs.close();
            con.CerrarConexion();
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex){
            JOptionPane.showMessageDialog(null,"ERROR"+ex,"Verificar", JOptionPane.ERROR_MESSAGE);
        }
         return tablaProveedor;     
    }
    public ArrayList<Autores>Llenarcombo(){
      ArrayList<Autores>  cbproveedor=new ArrayList<>();
    try {
        String consulta="SELECT * FROM tbautor";
        con=new BDConexion();
        pst=con.conectarse().prepareStatement(consulta);
        rs=pst.executeQuery();
        
        while(rs.next()){
          pro=new Autores();
          pro.setClaveautor(rs.getInt("claveautor"));
          pro.setNombre(rs.getString("nombre"));
          cbproveedor.add(pro);
        }
    }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error: "+e);
    }
    return cbproveedor;
    }
     public void Actualizar(Autores prov) throws SQLException {
        String sql;
        sql = "UPDATE tbautor SET claveautor=?,nombre=?,rfc=?,telefono=?,direccion=?,correo=? WHERE clave_prov=?";
        try {
            con = new BDConexion();
            PreparedStatement pst = con.conectarse().prepareStatement(sql);

            pst.setInt(1, prov.getClaveautor());
            pst.setString(2, prov.getNombre());
            pst.setString(3, prov.getRfc());
            pst.setLong(4, prov.getTelefono());
            pst.setString(5, prov.getDireccion());
            pst.setString(6, prov.getCorreo());     
            pst.setInt(7, prov.getClaveautor());

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Verificacion", JOptionPane.ERROR_MESSAGE);
        }

        con.CerrarConexion();
    }
      public ArrayList<Autores> Llenartabla(int clv) {
        ArrayList<Autores> prov = new ArrayList<>();
        try {
            con = new BDConexion();
            String consola = "SELECT * FROM tbautor WHERE claveautor=" + clv;
            PreparedStatement pst = con.conectarse().prepareStatement(consola);
            ResultSet rs = pst.executeQuery();

            Autores pro;
            if (rs.next()) {
                pro = new Autores();
                pro.setClaveautor(rs.getInt("claveautor"));
                pro.setNombre(rs.getString("nombre"));
                pro.setRfc(rs.getString("rfc"));
                pro.setTelefono(rs.getLong("telefono"));
                pro.setDireccion(rs.getString("direccion"));
                pro.setCorreo(rs.getString("correo"));
                prov.add(pro);
            }
            rs.close();
            con.CerrarConexion();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Verificacion", JOptionPane.ERROR_MESSAGE);
        }
        return prov;
    }
      public JTable MostrarTabla(JTable tablaDatos){
        try{
              String sql;
            con=new BDConexion();
            sentenciaSQL=con.conectarse().createStatement();
            sql="SELECT * FROM tbautor";
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

}
