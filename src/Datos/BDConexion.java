
package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BDConexion {
    private Connection conexion=null;
    private Statement sentenciaSQL;
    private ResultSet resultado;
            
            public BDConexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
                String contolador="com.mysql.jdbc.Driver";
                Class.forName(contolador).newInstance();
                
                String URL="jdbc:mysql://localhost:3306/biblioteca";
                String usuario="root";
                String contraseña="";
                
                conexion=DriverManager.getConnection(URL,usuario,contraseña);
            }
            
            public Connection conectarse() {
                return conexion;
            }
            public void CerrarConexion() throws SQLException{
                if (resultado!=null){
                    resultado.close();
                }
                   if (sentenciaSQL!=null){
                       sentenciaSQL.close();
                   }
                      if (conexion!=null){
                          conexion.close();
                      }
                }
            }

