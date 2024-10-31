package Datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class MantenimientoPrestamos {

    BDConexion con;
    PreparedStatement ps;
    ResultSet rs;
    int resultado = 0;
     private Statement sentenciaSQL;

    public int numSerieVentas() {
       int folio = 0;
        try {
              String sql;
            con=new BDConexion();
            sentenciaSQL=con.conectarse().createStatement();
            sql="SELECT max(folio) FROM tbprestamos";
            ResultSet rs=sentenciaSQL.executeQuery(sql);

            while (rs.next()) {
                folio = rs.getInt(1);
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
        return folio;
    }

    public int guardarPrestamos(Prestamos ven) {
        String insertar = "INSERT INTO tbprestamos(folio, fecha, clave, idcliente) VALUES (?,?,?,?)";
        try {
            con = new BDConexion();
            PreparedStatement pst = con.conectarse().prepareStatement(insertar);
            pst.setInt(1, ven.getFolio());

            pst.setString(2, ven.getFecha());
            pst.setInt(3, ven.getClave());
            pst.setInt(4, ven.getidcliente());
  
            resultado = pst.executeUpdate();
        }catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada: " + ex, "error", JOptionPane.ERROR_MESSAGE);
        
    } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "Error de instancia: " + ex, "error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Mal acceso a la BD: " + ex, "error", JOptionPane.ERROR_MESSAGE);
} catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la sentencia: " + ex, "error", JOptionPane.ERROR_MESSAGE);
}
        return resultado;
    }

    public int guardarDetalleprestamos(DetallePrestamos dellateVenta) {
          String insertar = "INSERT INTO tbldetalle(folio, clave, titulo, idcliente, fecha) VALUES (?,?,?,?,?)";
        try {
            con = new BDConexion();
            PreparedStatement pst = con.conectarse().prepareStatement(insertar);
            pst.setInt(1, dellateVenta.getFolio());
            pst.setInt(2, dellateVenta.getClave());
            pst.setString(3, dellateVenta.getTitulo());
            pst.setInt(4, dellateVenta.getidcliente());
            pst.setString(5, dellateVenta.getFecha());
            resultado = pst.executeUpdate();
        } catch (ClassNotFoundException ex) {
           
        
    } catch (InstantiationException ex) {
          
    } catch (IllegalAccessException ex) {
        
} catch (SQLException ex) {
                 }
        return resultado;
    }
    
    public String idPrestamo() {
        String idPrestamo = null;
    
        try {
            String sql;
              sentenciaSQL=con.conectarse().createStatement();
            sql="SELECT max(folio) FROM tbprestamos";
            ResultSet rs=sentenciaSQL.executeQuery(sql);
            while (rs.next()) {
                idPrestamo = rs.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Error en : " + e);
        }
        return idPrestamo;
    }
}
