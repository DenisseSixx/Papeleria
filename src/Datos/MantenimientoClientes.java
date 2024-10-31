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
public class MantenimientoClientes {
  private Statement sentenciaSQL;
    ResultSet rs;
    BDConexion bd;
    Clientes cli;
    PreparedStatement pst;
  
    public void RegistroCliente(Clientes clie ) {
            String insertar = "INSERT INTO tbclientes(idcliente,nombre,dni,telefono,direccion,email)values(?,?,?,?,?,?)";
        try {
            bd = new BDConexion();
            PreparedStatement pst = bd.conectarse().prepareStatement(insertar);
            pst.setInt(1, clie.getIdcliente())  ;
            pst.setString(2, clie.getNombre());
            pst.setString(3, clie.getDni());
            pst.setLong(4, clie.getTelefono());
            pst.setString(5, clie.getDireccion());
            pst.setString(6, clie.getEmail());

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }
            bd.CerrarConexion();
            
          } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InstantiationException e) {
            JOptionPane.showMessageDialog(null, "Error de instancia" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, "Mal acceso a la BD" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la sentencia" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    } public JTable BuscarClientesTabla(JTable tablaclientes){
        try{
            String sql;
            bd=new BDConexion();
            sentenciaSQL=bd.conectarse().createStatement();
            sql="SELECT idcliente, nombre FROM tbclientes";
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
            tablaclientes.setModel(modelo);
            rs.close();
            bd.CerrarConexion();
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex){
            JOptionPane.showMessageDialog(null,"ERROR"+ex,"Verificar", JOptionPane.ERROR_MESSAGE);
        }
         return tablaclientes;     
    }
     public ArrayList<Clientes>Llenarcombo(){
      ArrayList<Clientes>  cbclientes=new ArrayList<>();
    try {
        String consulta="SELECT * FROM tbclientes";
        bd=new BDConexion();
        pst=bd.conectarse().prepareStatement(consulta);
        rs=pst.executeQuery();
        
        while(rs.next()){
          cli=new Clientes();
          cli.setIdcliente(rs.getInt("idcliente"));
          cli.setNombre(rs.getString("nombre"));
          cbclientes.add(cli);
        }
    }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error: "+e);
    }
    return cbclientes;
    }
      public void Eliminar(Clientes cli) {
        try {
            String sql = "DELETE FROM tbclientes WHERE idcliente=" + cli.getIdcliente();
             bd = new BDConexion();
            sentenciaSQL = bd.conectarse().createStatement();
            int n = sentenciaSQL.executeUpdate(sql);
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }
            bd.CerrarConexion();
            sentenciaSQL.close();
        } catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Verificacion", JOptionPane.ERROR_MESSAGE);
        }
    }
       public void Actualizar(Clientes cli) throws SQLException {
        String sql;
        sql = "UPDATE tbclientes SET idcliente=?,nombre=?,dni=?,telefono=?,direccion=?,email=? where idcliente=? ";
        try {
            bd = new BDConexion();
            PreparedStatement pst = bd.conectarse().prepareStatement(sql);

            pst.setInt(1, cli.getIdcliente())  ;
            pst.setString(2, cli.getNombre());
            pst.setString(3, cli.getDni());
            pst.setLong(4, cli.getTelefono());
            pst.setString(5, cli.getDireccion());
            pst.setString(6, cli.getEmail());
            pst.setInt(7, cli.getIdcliente())  ;
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Verificacion", JOptionPane.ERROR_MESSAGE);
        }

        bd.CerrarConexion();
    }
        public ArrayList<Clientes> Llenartabla(int clv) {
        ArrayList<Clientes> clie = new ArrayList<>();
        try {
            bd = new BDConexion();
            String consola = "SELECT * FROM tbclientes WHERE idcliente=" + clv;
            PreparedStatement pst = bd.conectarse().prepareStatement(consola);
            ResultSet rs = pst.executeQuery();

            Clientes cli;
            if (rs.next()) {
                cli = new Clientes();
                cli.setIdcliente(rs.getInt("idcliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setDni(rs.getString("dni"));
                cli.setTelefono(rs.getLong("telefono"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setEmail(rs.getString("email"));
                clie.add(cli);
            }
            rs.close();
            bd.CerrarConexion();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Verificacion", JOptionPane.ERROR_MESSAGE);
        }
        return clie;
    }
      public JTable MostrarTabla(JTable tabladatos) {
        try {
            String sql;
            bd = new BDConexion();
            sentenciaSQL = bd.conectarse().createStatement();
            sql = "SELECT * FROM tbclientes";
            ResultSet rs = sentenciaSQL.executeQuery(sql);
            ResultSetMetaData rsm = rs.getMetaData();
            int col = rsm.getColumnCount();
            DefaultTableModel modelo = new DefaultTableModel();
            for(int i=1;i<col+1;i++){
                modelo.addColumn(rsm.getColumnLabel(i));//Poner encabezados de la tabla
            }
            while (rs.next()) {
                String[] fila = new String[col];
                for (int j = 0; j < col ; j++) {
                    fila[j] = rs.getString(j + 1);
                }
                modelo.addRow(fila);
            }
            tabladatos.setModel(modelo);
            rs.close();
            bd.CerrarConexion();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException errs) {
            JOptionPane.showMessageDialog(null, "Error: " + errs, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return tabladatos;
    }
     
        }



  


