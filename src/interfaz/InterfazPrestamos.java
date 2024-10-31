/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Datos.Clientes;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import Datos.DetallePrestamos;
import Datos.Libros;
import Datos.MantenimientoLibros;
import Datos.Prestamos;
import Datos.MantenimientoPrestamos;
import Negocio.NegocioClientes;
import Negocio.NegocioLibros;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InterfazPrestamos extends javax.swing.JFrame {
 NegocioClientes nc;
 NegocioLibros np;
 ArrayList<Clientes> lista = null;

 
    DefaultComboBoxModel modeloCombo;
    Libros libro = new Libros();
    MantenimientoLibros ma = new MantenimientoLibros();
    MantenimientoPrestamos mv= new MantenimientoPrestamos();
    Prestamos prestamo = new Prestamos();
    DetallePrestamos detalleVentas = new DetallePrestamos();
    DefaultTableModel modeloTabla;
  //  DecimalFormat decimal = new DecimalFormat("0.00");
int idcliente;
    int clave;
    int cantidad;
    int stock;
    String titulo;
 //   double total;
    //double totalPagar;
    String fecha;
    int folio;
double NPags;

    public InterfazPrestamos() {
        
        initComponents();
        diseñoTabla();
        diseñoCantidad();
        calendario();
        generarNumeroSerie();
        items();
        items2();
        items3();
        items4();
    }
public void items(){
    TextAutoCompleter textAutoCompleter = new TextAutoCompleter(txtBuscarLibro);
   
    try {
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
        PreparedStatement pst = conexion.prepareStatement("select clave from tblibros");
    ResultSet rs = pst.executeQuery();
while (rs.next()){
    textAutoCompleter.addItem(rs.getString("clave"));
}
   }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Error "+e.getMessage());
}        
     
}
public void items2(){
    TextAutoCompleter textAutoCompleter = new TextAutoCompleter(txtIdPro);
   
    try {
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
        PreparedStatement pst = conexion.prepareStatement("select clave from tblibros");
    ResultSet rs = pst.executeQuery();
while (rs.next()){
    textAutoCompleter.addItem(rs.getString("clave"));
}
   }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Error "+e.getMessage());
}        
     
}
public void items4(){
    TextAutoCompleter textAutoCompleter = new TextAutoCompleter(txtIdus);
    try {
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
        PreparedStatement pst = conexion.prepareStatement("select idcliente from tbclientes");

    ResultSet rs = pst.executeQuery();
while (rs.next()){
    textAutoCompleter.addItem(rs.getString("idcliente"));
}
   }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Error "+e.getMessage());
}        
}
public void items3(){
    TextAutoCompleter textAutoCompleter = new TextAutoCompleter(txtTitulo);
   
    try {
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
        PreparedStatement pst = conexion.prepareStatement("select titulo from tblibros");
    ResultSet rs = pst.executeQuery();
while (rs.next()){
    textAutoCompleter.addItem(rs.getString("titulo"));
}
   }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Error "+e.getMessage());
}        
     
}


    private void diseñoCantidad() {
        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel();
        modeloSpinner.setValue(1);
        modeloSpinner.setMinimum(1);
        modeloSpinner.setMaximum(20);
        cantidadlibros.setModel(modeloSpinner);
        comboLibros.setVisible(false);
    }

    private void diseñoTabla() { 
        modeloTabla = new DefaultTableModel();
        tablaPrestamos.setModel(modeloTabla); 

     
        modeloTabla.addColumn("Orden #");
        modeloTabla.addColumn("C.O.D");
        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("No. Paginas");
        modeloTabla.addColumn("id cliente");

  
        int anchoColummna[] = {5, 5, 220, 15, 20};
        boolean cambiarAncho = false;

        for (int i = 0; i < anchoColummna.length; i++) {
            tablaPrestamos.getColumnModel().getColumn(i).setPreferredWidth(anchoColummna[i]);
            tablaPrestamos.getColumnModel().getColumn(i).setResizable(cambiarAncho);
        }
    }

    private void calendario() {
        Date fechaActual = new Date();
        SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat gregorian = new SimpleDateFormat("dd-MM-yyyy");
        String fechaSqlFormato = formatoSQL.format(fechaActual);
        String fechaGregorian = gregorian.format(fechaActual);
        txtFecha.setText(String.valueOf(fechaGregorian));
        fecha = fechaSqlFormato;
    }

    private void generarNumeroSerie() {
        int folio = mv.numSerieVentas();
        if (folio == 0) {
            txtSerie.setText("0000001");
        } else {
            int incremento = folio;
            incremento = incremento + 1;
            txtSerie.setText("00000" + incremento);
       
    }
    }
    private void buscarLibros() {
        if (txtBuscarLibro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelComponentes, "Debe ingresar un titulo a buscar", "Falta titulo", JOptionPane.WARNING_MESSAGE);
        } else {
            String buscarLibros = txtBuscarLibro.getText();
            Vector<Libros> vectorLibros = new Vector<>();
            vectorLibros = ma.buscarLibros(buscarLibros);
            if (vectorLibros.isEmpty()) {
                JOptionPane.showMessageDialog(panelComponentes, "No existe este titulo", "No se encontro", JOptionPane.ERROR_MESSAGE);
            } else {
                modeloCombo = new DefaultComboBoxModel(vectorLibros);
                comboLibros.setModel(modeloCombo);
            }

        }
    }

    private void seleccionarLibroComboBox() {
        Libros LibroActual = (Libros) comboLibros.getSelectedItem();
        txtIdPro.setText(String.valueOf(LibroActual.getClave()));
        txtTitulo.setText(LibroActual.getTitulo());
        txtStock.setText(String.valueOf(LibroActual.getStock()));
    }

    public void agregarLibroTabla() {
        if (txtTitulo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelComponentes, "Primero busque un titulo", "Busque titulo", JOptionPane.WARNING_MESSAGE);
        } else {
            int item = 0;
            item = item + 1;
            clave = Integer.parseInt(txtIdPro.getText());
            String Titulo = txtTitulo.getText();
            NPags = Double.parseDouble(txtNPags.getText());
            cantidad = Integer.parseInt(cantidadlibros.getValue().toString());
            stock = Integer.parseInt(txtStock.getText());
         
            ArrayList listaLibros = new ArrayList();
            if (stock > 0) {
                listaLibros.add(item);
                listaLibros.add(clave);
                listaLibros.add(Titulo);
                listaLibros.add(cantidad);
                 listaLibros.add(NPags);
                Object[] objDatos = new Object[6];
                objDatos[0] = listaLibros.get(0);
                objDatos[1] = listaLibros.get(1);
                objDatos[2] = listaLibros.get(2);
                objDatos[3] = listaLibros.get(3);
                objDatos[4] = listaLibros.get(4);
             //   objDatos[5] = listaLibros.get(5);
                modeloTabla.addRow(objDatos);
               
              
                limpiarTextoLibro();

            } else {
                JOptionPane.showMessageDialog(panelComponentes, "Libro en el almacen no disponible", "Falta Stock", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarPrestamo() {
       /*if (txtTotalPagar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelComponentes, "Agrege productos para hacer la venta", "Agregar productos", JOptionPane.WARNING_MESSAGE);
        } else {*/
            folio = Integer.parseInt(txtSerie.getText());
   
            prestamo.setFolio(folio);
            prestamo.setFecha(fecha);
            prestamo.setidcliente(idcliente);
 
            prestamo.setClave(clave);
            if (mv.guardarPrestamos(prestamo) > 0) {
                JOptionPane.showMessageDialog(panelComponentes, "Prestamo Realizado", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                guardarDetallePrestamo();
                actualizarStock();
                generarNumeroSerie();
                limpiarPrestamo();
            } else {
                JOptionPane.showMessageDialog(panelComponentes, "No se guardo el prestamo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    

    public void guardarDetallePrestamo() {
       String idPrestamo = mv.idPrestamo();
        int idPrestamos = Integer.parseInt(idPrestamo);
        for (int i = 0; i < tablaPrestamos.getRowCount(); i++) {
            int idprod = Integer.parseInt(tablaPrestamos.getValueAt(i, 1).toString());
            String nomPro = tablaPrestamos.getValueAt(i, 2).toString();
            //float precioProducto = Float.parseFloat(tablaPrestamos.getValueAt(i, 4).toString());
           //float  precioTotal =  Float.parseFloat(tablaPrestamos.getValueAt(i, 5).toString());
           
            detalleVentas.setFolio(idPrestamos);
            detalleVentas.setClave(idprod);
            detalleVentas.setTitulo(nomPro);
            //detalleVentas.setTotal(precioTotal);
            //detalleVentas.setFecha(fecha);
            mv.guardarDetalleprestamos(detalleVentas);
        }
    }
  

    public void actualizarStock() {
        int idlibro;
        int datosRecibidos[] = new int[2];
        int idActualizar;
        int cant;
        int cantidadGuardada;
        int stockActual;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            idlibro = Integer.parseInt(tablaPrestamos.getValueAt(i, 1).toString());
            datosRecibidos = ma.BuscarLibroPorID(idlibro);
            idActualizar = datosRecibidos[0];
            cant = datosRecibidos[1];
            cantidadGuardada = ma.obtenerStockActual(idActualizar);           
            stockActual = cantidadGuardada - cant;
            ma.actualizarStock(stockActual, idActualizar);
        }
    }

    public void limpiarPrestamo() {
        txtBuscarLibro.setText(null);
        txtTitulo.setText(null);
        txtNPags.setText(null);
        txtStock.setText(null);
        cantidadlibros.setValue(1);
        txtIdPro.setText(null);
        modeloTabla.setRowCount(0);
    }

    private void limpiarTextoLibro() {
        txtBuscarLibro.setText(null);
        txtTitulo.setText(null);
        txtNPags.setText(null);
        txtStock.setText(null);
        cantidadlibros.setValue(1);
        txtBuscarLibro.requestFocus();
        txtIdPro.setText(null);
        
    }
    /**
     * Creates new form InterfazVentas
     */
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTabla = new javax.swing.JPanel();
        panelComponentes = new javax.swing.JPanel();
        lblCodigoProducto = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtBuscarLibro = new javax.swing.JTextField();
        cantidadlibros = new javax.swing.JSpinner();
        btnBuscarLibro = new javax.swing.JButton();
        btnAgregarLibro = new javax.swing.JButton();
        lblProducto = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        lblNombreProducto = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        txtIdPro = new javax.swing.JTextField();
        txtNPags = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtIdus = new javax.swing.JTextField();
        panelTitulos = new javax.swing.JPanel();
        lblDireccion = new javax.swing.JLabel();
        lblSerie = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        lblTipoNegocio = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboLibros = new javax.swing.JComboBox<>();
        panelVenta = new javax.swing.JPanel();
        btnLimipiarTodo = new javax.swing.JButton();
        btnGenerarVenta = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPrestamos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTabla.setBackground(new java.awt.Color(204, 255, 255));
        panelTabla.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 424, 1190, -1));

        panelComponentes.setBackground(new java.awt.Color(0, 204, 204));
        panelComponentes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelComponentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigoProducto.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblCodigoProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblCodigoProducto.setText("Codigo:");
        panelComponentes.add(lblCodigoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 74, -1));

        lblPrecio.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecio.setText("No. Paginas:");
        panelComponentes.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, -1));

        lblCantidad.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidad.setText("Cantidad:");
        panelComponentes.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        txtBuscarLibro.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtBuscarLibro.setToolTipText("Ingrese el nombre del Libro");
        txtBuscarLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarLibroKeyTyped(evt);
            }
        });
        panelComponentes.add(txtBuscarLibro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 290, 26));

        cantidadlibros.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cantidadlibros.setToolTipText("Seleccione la cantidad prestada");
        panelComponentes.add(cantidadlibros, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 70, 27));

        btnBuscarLibro.setBackground(new java.awt.Color(255, 255, 204));
        btnBuscarLibro.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnBuscarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar.png"))); // NOI18N
        btnBuscarLibro.setText("Buscar");
        btnBuscarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLibroActionPerformed(evt);
            }
        });
        panelComponentes.add(btnBuscarLibro, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 150, 70));

        btnAgregarLibro.setBackground(new java.awt.Color(255, 255, 204));
        btnAgregarLibro.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnAgregarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Agregar.png"))); // NOI18N
        btnAgregarLibro.setText("Agregar");
        btnAgregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLibroActionPerformed(evt);
            }
        });
        panelComponentes.add(btnAgregarLibro, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 150, 70));

        lblProducto.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblProducto.setText("Titulo:");
        panelComponentes.add(lblProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, 20));

        lblStock.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblStock.setForeground(new java.awt.Color(255, 255, 255));
        lblStock.setText("Stock:");
        panelComponentes.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 106, -1));

        lblBuscar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscar.setText("Buscar clave:");
        panelComponentes.add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, -1));

        lblNombreProducto.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblNombreProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreProducto.setText("Cliente:");
        panelComponentes.add(lblNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 106, -1));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        panelComponentes.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 340, 220));

        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });
        panelComponentes.add(Regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, 110, 40));

        txtIdPro.setToolTipText("Codigo Del Libro");
        txtIdPro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdProMouseClicked(evt);
            }
        });
        txtIdPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdProKeyTyped(evt);
            }
        });
        panelComponentes.add(txtIdPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 240, 30));

        txtNPags.setToolTipText("Numero De Paginas");
        txtNPags.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNPagsMouseClicked(evt);
            }
        });
        txtNPags.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNPagsKeyTyped(evt);
            }
        });
        panelComponentes.add(txtNPags, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 210, 30));

        txtTitulo.setToolTipText("Ingrese el titulo");
        txtTitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTituloKeyTyped(evt);
            }
        });
        panelComponentes.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 190, 30));

        txtStock.setToolTipText("Ingrese el Stock");
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });
        panelComponentes.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 110, 30));

        txtIdus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdusKeyTyped(evt);
            }
        });
        panelComponentes.add(txtIdus, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 240, 30));

        getContentPane().add(panelComponentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 162, -1, 256));

        panelTitulos.setBackground(new java.awt.Color(0, 204, 204));
        panelTitulos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelTitulos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDireccion.setFont(new java.awt.Font("Arial Narrow", 1, 16)); // NOI18N
        lblDireccion.setForeground(new java.awt.Color(255, 255, 255));
        lblDireccion.setText("Direccion: Arenillas - Calle Juan Montalvo y Calle Esmeraldas");
        panelTitulos.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 147, -1, -1));

        lblSerie.setFont(new java.awt.Font("Arial Narrow", 1, 16)); // NOI18N
        lblSerie.setForeground(new java.awt.Color(255, 255, 255));
        lblSerie.setText("N° de Serie");
        panelTitulos.add(lblSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 186, -1, -1));

        txtSerie.setEditable(false);
        txtSerie.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        panelTitulos.add(txtSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 184, 100, 25));

        lblTipoNegocio.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTipoNegocio.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoNegocio.setText("Biblioteca Alejandría");
        panelTitulos.add(lblTipoNegocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        panelTitulos.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 90, 240, 50));

        jLabel5.setBackground(new java.awt.Color(255, 255, 204));
        jLabel5.setFont(new java.awt.Font("Lucida Calligraphy", 1, 48)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Prestamos");
        jLabel5.setOpaque(true);
        panelTitulos.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1190, 80));

        comboLibros.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        comboLibros.setMaximumSize(new java.awt.Dimension(250, 32767));
        comboLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLibrosActionPerformed(evt);
            }
        });
        panelTitulos.add(comboLibros, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 270, 26));

        getContentPane().add(panelTitulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 1190, 145));

        panelVenta.setBackground(new java.awt.Color(204, 255, 255));
        panelVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLimipiarTodo.setBackground(new java.awt.Color(255, 255, 204));
        btnLimipiarTodo.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnLimipiarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Borrar.png"))); // NOI18N
        btnLimipiarTodo.setText("Limpiar");
        btnLimipiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimipiarTodoActionPerformed(evt);
            }
        });
        panelVenta.add(btnLimipiarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 190, 50));

        btnGenerarVenta.setBackground(new java.awt.Color(255, 255, 204));
        btnGenerarVenta.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Palomita.png"))); // NOI18N
        btnGenerarVenta.setText("Aceptar");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });
        panelVenta.add(btnGenerarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 170, 50));

        btnSalir.setBackground(new java.awt.Color(255, 255, 204));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panelVenta.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 50));

        getContentPane().add(panelVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 1190, 60));

        tablaPrestamos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tablaPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Orden #", "C.O.D", "Titulo", "Cantidad", "No. Paginas", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPrestamos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaPrestamos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 1170, 100));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLibroActionPerformed
        try {
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
        PreparedStatement pst = conexion.prepareStatement("select * from tblibros where clave=?");
        pst.setString(1,txtBuscarLibro.getText().trim());
        ResultSet rs = pst.executeQuery();
    if(rs.next()){
        txtNPags.setText(rs.getString("npaginas"));
        txtTitulo.setText(rs.getString("titulo"));
        txtStock.setText(rs.getString("stock"));
    }else{
        JOptionPane.showMessageDialog(null, "Proovedor No Registrado");
    }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Error "+e.getMessage());
       }
    }//GEN-LAST:event_btnBuscarLibroActionPerformed

    private void btnAgregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLibroActionPerformed
        agregarLibroTabla();
    }//GEN-LAST:event_btnAgregarLibroActionPerformed

    private void btnLimipiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimipiarTodoActionPerformed
        limpiarPrestamo();
    }//GEN-LAST:event_btnLimipiarTodoActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        guardarPrestamo();
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void comboLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLibrosActionPerformed
        seleccionarLibroComboBox();
    }//GEN-LAST:event_comboLibrosActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
this.dispose();
    }//GEN-LAST:event_RegresarActionPerformed

    private void txtBuscarLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarLibroKeyTyped
 int key = evt.getKeyChar();
            boolean numeros = key>=48 && key <=57;
            if(!numeros){
                evt.consume();
            }
            if(txtBuscarLibro.getText().trim().length()==11){
                evt.consume();
            }        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarLibroKeyTyped

    private void txtIdProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProKeyTyped
 int key = evt.getKeyChar();
            boolean numeros = key>=48 && key <=57;
            if(!numeros){
                evt.consume();
            }
            if(txtIdPro.getText().trim().length()==11){
                evt.consume();
            }        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProKeyTyped

    private void txtNPagsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNPagsKeyTyped
 int key = evt.getKeyChar();
            boolean numeros = key>=48 && key <=57;
            if(!numeros){
                evt.consume();
            }
            if(txtNPags.getText().trim().length()==4){
                evt.consume();
            }        // TODO add your handling code here:
    }//GEN-LAST:event_txtNPagsKeyTyped

    private void txtTituloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTituloKeyTyped
int key = evt.getKeyChar();
        boolean mayusculas = key>=65 && key<=90;
        boolean minusculas = key>=97 && key<=122;
        
        boolean espacio = key==32;
        
        if(!(minusculas||mayusculas||espacio)){            
            evt.consume();
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloKeyTyped

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        int key = evt.getKeyChar();
        boolean numeros = key>=48 && key <=57;
        if(!numeros){
            evt.consume();
        }
        if(txtStock.getText().trim().length()==4){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtIdProMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdProMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProMouseClicked

    private void txtNPagsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNPagsMouseClicked
        try {
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
        PreparedStatement pst = conexion.prepareStatement("select * from tblibros where clave=?");
        pst.setString(1,txtBuscarLibro.getText().trim());
        ResultSet rs = pst.executeQuery();
    if(rs.next()){
        txtNPags.setText(rs.getString("npaginas"));
        txtTitulo.setText(rs.getString("titulo"));
        txtStock.setText(rs.getString("stock"));
    }else{
        JOptionPane.showMessageDialog(null, "Proovedor No Registrado");
    }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Error "+e.getMessage());
       }        // TODO add your handling code here:
    }//GEN-LAST:event_txtNPagsMouseClicked

    private void txtIdusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdusKeyTyped
        
        int key = evt.getKeyChar();
            boolean numeros = key>=48 && key <=57;
            if(!numeros){
                evt.consume();
            }
            if(txtIdus.getText().trim().length()==11){
                evt.consume();
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdusKeyTyped

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
            java.util.logging.Logger.getLogger(InterfazPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazPrestamos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Regresar;
    private javax.swing.JButton btnAgregarLibro;
    private javax.swing.JButton btnBuscarLibro;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnLimipiarTodo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JSpinner cantidadlibros;
    private javax.swing.JComboBox<String> comboLibros;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCodigoProducto;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTipoNegocio;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JPanel panelTitulos;
    private javax.swing.JPanel panelVenta;
    private javax.swing.JTable tablaPrestamos;
    private javax.swing.JTextField txtBuscarLibro;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdPro;
    private javax.swing.JTextField txtIdus;
    private javax.swing.JTextField txtNPags;
    private javax.swing.JTextField txtSerie;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
