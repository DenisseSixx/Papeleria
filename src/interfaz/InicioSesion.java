/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;


import Datos.Acceso;
import Negocio.negocioAdministrar;
import com.imagen.ClaseImagen;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
 

public class InicioSesion extends javax.swing.JFrame {

    ClaseImagen img=new ClaseImagen();

    Principal pp;

    public InicioSesion() {
        initComponents();
        this.setSize(430, 580);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
      
        img.AjustarImagenL(jlFondo, "Imagenes", "fondo", "jpg");
        img.AjustarImagenL(lbLogo, "Imagenes", "logo", "png");
      items();
    }

public void items(){
    TextAutoCompleter textAutoCompleter = new TextAutoCompleter(txtUsu);
    try {
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
        PreparedStatement pst = conexion.prepareStatement("select nombre from tbacceso");

    ResultSet rs = pst.executeQuery();
while (rs.next()){
    textAutoCompleter.addItem(rs.getString("nombre"));
}
   }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Error "+e.getMessage());
}        
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pfCon = new javax.swing.JPasswordField();
        txtUsu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jlFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pfCon.setToolTipText("Ingrese contraseña");
        jPanel1.add(pfCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 160, -1));

        txtUsu.setToolTipText("Ingrese nombre de usuario");
        jPanel1.add(txtUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 160, -1));

        jLabel1.setText("Usuario:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, -1, -1));

        jLabel2.setText("Contraseña:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));
        jPanel1.add(lbLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 190, 130));

        btnIngresar.setMnemonic('i');
        btnIngresar.setText("Ingresar");
        btnIngresar.setToolTipText("Dar clic para ingresar o alt+i");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 90, 40));

        btnSalir.setMnemonic('s');
        btnSalir.setText("Salir");
        btnSalir.setToolTipText("Dar clic para salir o alt+s");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, 90, 40));

        jlFondo.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.add(jlFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 580));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0); 
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed

          int intento = 0;
        if (intento < 3) {
           // try {
                Acceso acc = new Acceso();
                String pass = new String(pfCon.getPassword());
                acc.setNombre(txtUsu.getText());
                negocioAdministrar na = new negocioAdministrar();
                acc.setContraseña(pass);
                String tipo = na.puenteAcceso(acc);
                acc.setTipo(tipo);
                if (tipo.equalsIgnoreCase("completo")) {
                    JOptionPane.showMessageDialog(null, "Bievenido " + acc.getNombre());
                    pp = new Principal(acc);
                        pp.setVisible(true);
                        this.dispose();
                } else if (tipo.equalsIgnoreCase("basico")) {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + acc.getNombre());
                   pp = new Principal(acc);
                       pp.setVisible(true);
                      this.dispose();
                } else {
                    intento++;
                }

            //} catch (Exception e) {
              //  JOptionPane.showMessageDialog(null, "Error: " + e);
         //  }
        } else{ 
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error Acceso", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
 
    }//GEN-LAST:event_btnIngresarActionPerformed
  
           
            
   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlFondo;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JPasswordField pfCon;
    private javax.swing.JTextField txtUsu;
    // End of variables declaration//GEN-END:variables
}
