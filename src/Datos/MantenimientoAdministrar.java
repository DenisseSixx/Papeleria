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


public class MantenimientoAdministrar {
    Statement sentenciaSQL;
    ResultSet rs;
    BDConexion bd;
    Acceso acc;
    PreparedStatement pst;
    public String consultaUsuario(Acceso acc){
        String tipo="";
        try{
             bd= new BDConexion();
        String SQL="SELECT tipo FROM tbacceso WHERE password='"+acc.getContraseña()+"'AND nombre='"+acc.getNombre()+"'";
        sentenciaSQL=bd.conectarse().createStatement();
       rs=sentenciaSQL.executeQuery(SQL);
        if(rs.next()){
            tipo=rs.getString("tipo");
        }
        bd.CerrarConexion();
        sentenciaSQL.close();
        rs.close();
        
         } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return tipo;
    }
      public void RegistroNuevo(Acceso acc) {
        String insertar = " INSERT INTO tbacceso(id,nombre,password,tipo)values(?,?,?,?)";
        try {
            bd = new BDConexion();
            PreparedStatement pst = bd.conectarse().prepareStatement(insertar);
            pst.setInt(1, acc.getId());
            pst.setString(2, acc.getNombre());
            pst.setString(3, acc.getContraseña());
            pst.setString(4, acc.getTipo());
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "registro guardado", "confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }
            bd.CerrarConexion();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada: " + ex, "error", JOptionPane.ERROR_MESSAGE);
        
    } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "Error de instancia: " + ex, "error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Mal acceso a la BD: " + ex, "error", JOptionPane.ERROR_MESSAGE);
} catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la sentencia: " + ex, "error", JOptionPane.ERROR_MESSAGE);
}
    }
    public JTable MostrarTabla(JTable tablaDatos){
        try{
              String sql;
            bd=new BDConexion();
            sentenciaSQL=bd.conectarse().createStatement();
            sql="SELECT * FROM tbacceso";
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
            bd.CerrarConexion();
            
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex){
          JOptionPane.showMessageDialog(null, "Error:"+ ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    return tablaDatos;
     
        }
     public void Actualizar(Acceso acc) throws SQLException{
         String  sql;
      sql="UPDATE tbacceso SET id=?, nombre=?, password=?, tipo=? WHERE id=?";
         
         try{
             
             bd=new BDConexion();
             PreparedStatement pst=bd.conectarse().prepareStatement(sql);
            
            pst.setInt(1, acc.getId());
            pst.setString(2, acc.getNombre());
            pst.setString(3, acc.getContraseña());
            pst.setString(4, acc.getTipo());
            pst.setInt(5, acc.getId());
            
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null,"Registro modificado","Confirmación", JOptionPane.INFORMATION_MESSAGE);
            }
                
         }catch(HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
           JOptionPane.showMessageDialog(null,"ERROR","Verificar", JOptionPane.ERROR_MESSAGE);  
         }
         bd.CerrarConexion();
     }
     public void Eliminar(Acceso acc){
         try{
    
                     String sql="DELETE FROM tbacceso WHERE id="+acc.getId();
                     bd=new BDConexion();
                     sentenciaSQL=bd.conectarse().createStatement();
                     int n=sentenciaSQL.executeUpdate(sql);
                     if(n>0){
                          JOptionPane.showMessageDialog(null,"Registro Eliminado ","Confirmacion", JOptionPane.INFORMATION_MESSAGE); 
                     }
                     bd.CerrarConexion();
                     sentenciaSQL.close();
         }catch(HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
             JOptionPane.showMessageDialog(null,"ERROR"+e,"Verificar", JOptionPane.ERROR_MESSAGE);
         }
     }
      public ArrayList<Acceso> Llenartabla(int clv) {
        ArrayList<Acceso> acc = new ArrayList<>();
        try {
            bd = new BDConexion();
            String consola = "SELECT * FROM tbacceso WHERE id=" + clv;
            PreparedStatement pst = bd.conectarse().prepareStatement(consola);
            ResultSet rs = pst.executeQuery();

            Acceso usu;
            if (rs.next()) {
                usu = new Acceso();
                usu.setId(rs.getInt("id"));
                usu.setNombre(rs.getString("nombre"));
                usu.setContraseña(rs.getString("password"));
                usu.setTipo(rs.getString("tipo"));
                acc.add(usu);
            }
            rs.close();
            bd.CerrarConexion();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Verificacion", JOptionPane.ERROR_MESSAGE);
        }
        return acc;
    }

     public ArrayList<Acceso>Llenarcombo(){
      ArrayList<Acceso>  cbacceso=new ArrayList<>();
    try {
        String consulta="SELECT * FROM tbacceso";
        bd=new BDConexion();
        pst=bd.conectarse().prepareStatement(consulta);
        rs=pst.executeQuery();
        
        while(rs.next()){
         acc =new Acceso();
          acc.setId(rs.getInt("id"));
          acc.setNombre(rs.getString("nombre"));
          cbacceso.add(acc);
        }
    }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error: "+e);
    }
    return cbacceso;
}
}
