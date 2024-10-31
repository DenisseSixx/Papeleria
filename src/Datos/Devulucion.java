package Datos;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Devulucion {

    String sql;
    Statement sentenciaSQL;
    ResultSet rs;
    BDConexion con;

    public void Comprar(Libros prod) {
        String sql = "UPDATE tblibros SET stock=? WHERE clave=?";
        try {

            con = new BDConexion();
            PreparedStatement pst = con.conectarse().prepareStatement(sql);

            pst.setInt(1, prod.getStock());
            pst.setInt(2, prod.getClave());

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }

            con.CerrarConexion();
            
        } catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Verificacion", JOptionPane.ERROR_MESSAGE);
        }
    }

}
