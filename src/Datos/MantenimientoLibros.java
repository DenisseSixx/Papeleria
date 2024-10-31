/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class MantenimientoLibros {
PreparedStatement pst;
    BDConexion con;
    private Statement sentenciaSQL;
     ResultSet rs;
    public void RegistroNuevo(Libros lib) {
        String insertar = " INSERT INTO tblibros(clave,titulo,stock,NPags,fecha_ingreso,nopedido,codigobarras,ruta_imagen,claveAutor,imagen)values(?,?,?,?,?,?,?,?,?,?)";
        try {
            FileInputStream archivofoto;
            archivofoto = new FileInputStream(lib.getImagen());
            con = new BDConexion();
            PreparedStatement pst = con.conectarse().prepareStatement(insertar);
              pst.setInt(1, lib.getClave());
            pst.setString(2, lib.getTitulo());
            pst.setInt(3, lib.getStock());
            pst.setInt(4, lib.getNPags());
            pst.setDate(5, lib.getFecha_Ingreso());
            pst.setInt(6, lib.getNopedido());
            pst.setString(7, lib.getCodBarras());
            pst.setString(8, lib.getRutaImagen());
            pst.setInt(9, lib.getClaveAutor());
            pst.setBinaryStream(10, archivofoto);
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "registro guardado", "confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }
            con.CerrarConexion();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada: " + ex, "error", JOptionPane.ERROR_MESSAGE);
        
    } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "Error de instancia: " + ex, "error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Mal acceso a la BD: " + ex, "error", JOptionPane.ERROR_MESSAGE);
} catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la sentencia: " + ex, "error", JOptionPane.ERROR_MESSAGE);
} catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error archivo de imagen no encontrada: " + ex, "error", JOptionPane.ERROR_MESSAGE);
}
    }
    public JTable MostrarTabla(JTable tablaDatos){
        try{
              String sql;
            con=new BDConexion();
            sentenciaSQL=con.conectarse().createStatement();
            sql="SELECT * FROM tblibros";
            ResultSet rs=sentenciaSQL.executeQuery(sql);
            ResultSetMetaData rsm=rs.getMetaData();
            int col=rsm.getColumnCount();
            DefaultTableModel modelo=new DefaultTableModel();
            for (int i = 1; i <= col; i++) {
                modelo.addColumn(rsm.getColumnLabel(i));
            }
            while(rs.next()){
                String fila[]=new String[col];
                  for (int j = 0; j < col; j++) {
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
      public ImageIcon ObtenerImagen(int clv) throws SQLException {
        InputStream is = null;
        ImageIcon img = null;

        String sql = "SELECT imagen FROM tblibros WHERE clave=" + clv;
        try {
            con = new BDConexion();
            sentenciaSQL = con.conectarse().createStatement();
            ResultSet rs = sentenciaSQL.executeQuery(sql);
            if (rs.next()) {
                is = rs.getBinaryStream(1);
                BufferedImage bi = ImageIO.read(is);
                img = new ImageIcon(bi);
            }
               
        }catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
          JOptionPane.showMessageDialog(null, "Error:"+ e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        con.CerrarConexion();
        sentenciaSQL.close();
        return img;
            
        }
     public void Actualizar(Libros lib) throws SQLException{
         String sql="UPDATE tbibros,SET clave=?, titulo=?,\"\n"+
                 "\"stock=?, NPags=?,fecha_ingreso=?, nopedido=?,\"\n"+
                 "\"codigobarras=?,ruta_imagen=?,clave_prov=?,imagen=? WHERE clave=?";
         
         try{
             FileInputStream archivofoto;
             archivofoto=new FileInputStream(lib.getImagen());
             
             con=new BDConexion();
             PreparedStatement pst=con.conectarse().prepareStatement(sql);
              pst.setInt(1, lib.getClave());
            pst.setString(2, lib.getTitulo());
            pst.setInt(3, lib.getStock());

            pst.setInt(4, lib.getNPags());
            pst.setDate(5, lib.getFecha_Ingreso());
            pst.setInt(6, lib.getNopedido());
            pst.setString(7, lib.getCodBarras());
            pst.setString(8, lib.getRutaImagen());
            pst.setInt(9, lib.getClaveAutor());
            pst.setBinaryStream(10, archivofoto);
            pst.setInt(11, lib.getClave());
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null,"Registro modificado","Confirmación", JOptionPane.INFORMATION_MESSAGE);
            }
                
         }catch(HeadlessException | FileNotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
           JOptionPane.showMessageDialog(null,"ERROR actualizaer tabla","Verificar", JOptionPane.ERROR_MESSAGE);  
         }
         con.CerrarConexion();
     }
     public void Eliminar(Libros lib){
         try{
    
                     String sql="DELETE FROM tblibros WHERE clave="+lib.getClave();
                     con=new BDConexion();
                     sentenciaSQL=con.conectarse().createStatement();
                     int n=sentenciaSQL.executeUpdate(sql);
                     if(n>0){
                          JOptionPane.showMessageDialog(null,"Registro Eliminado ","Confirmacion", JOptionPane.INFORMATION_MESSAGE); 
                     }
                     con.CerrarConexion();
                     sentenciaSQL.close();
         }catch(HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
             JOptionPane.showMessageDialog(null,"ERROR"+e,"Verificar", JOptionPane.ERROR_MESSAGE);
         }
     }
     public Vector<Libros> buscarLibros(String buscarLibros) {
        Libros lib;       
        Vector<Libros> vectorLibros = new Vector<Libros>();
         try{
            String sql= "select * from tblibros where clave like '%" + buscarLibros + "%'";
            con=new BDConexion();
            pst=con.conectarse().prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                lib = new Libros();
                lib.setClave(rs.getInt("clave"));
                lib.setTitulo(rs.getString("titulo"));
                lib.setStock(rs.getInt("stock"));
                vectorLibros.add(lib);
            }
           con.CerrarConexion();
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex){
            JOptionPane.showMessageDialog(null,"ERROR nombe"+ex,"Verificar", JOptionPane.ERROR_MESSAGE);
        }
        return vectorLibros;
    
    
     }
            
    public int[] BuscarLibroPorID(int idLibro) {
        int datos[] = new int[2];
        try {
            String sql="SELECT , stock FROM tblibros WHERE clave=?";
            con=new BDConexion();
            pst=con.conectarse().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getInt("clave");
                datos[1] = rs.getInt("stock");
            }
            con.CerrarConexion();
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex){
            
        }
        return datos;
    }
       public int obtenerStockActual(int idLibros){
        int stockActual = 0;
        try {
            String sql;
            con=new BDConexion();
            sentenciaSQL=con.conectarse().createStatement();
           sql = "SELECT stock FROM tblibros WHERE clave=?";
            rs = pst.executeQuery(sql);
           pst.setInt(1, idLibros);
            while (rs.next()) {
                stockActual = rs.getInt("stock");
            }
            con.CerrarConexion();
        }  catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex){
         
        }
        return stockActual;
    }

    public int actualizarStock(int cantidadActual, int idLibros) {
        int resultado = 0;
        try {
            String sql;
            con=new BDConexion();
            sentenciaSQL=con.conectarse().createStatement();
            sql = "UPDATE tblibros SET stock=? WHERE clave=?";
            pst.setInt(1, cantidadActual);
            pst.setInt(2, idLibros);
            resultado = pst.executeUpdate();
            con.CerrarConexion();
        }  catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex){
          
        }
        return resultado;
    }
}
    





