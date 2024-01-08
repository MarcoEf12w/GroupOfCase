/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.Cliente;
import Clases.Producto;
import Clases.Proveedor;
import DAO.ClienteDAO;
import DAO.ProductoDAO;
import DAO.ProveedorDAO;
import java.awt.Dialog;
import java.awt.Window;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mpolo
 */
public class FrmBusqueda extends javax.swing.JDialog {
FrmPrincipal frm ;

    public FrmPrincipal getFrm() {
        return frm;
    }

    public void setFrm(FrmPrincipal frm) {
        this.frm = frm;
    }
    public FrmBusqueda() {
    }

    /**
     * Creates new form FrmBusqueda
     */
    public FrmBusqueda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public FrmBusqueda(Window parent, Dialog.ModalityType modal) {
        super(parent, modal);
        initComponents();
        tableBusqueda.getColumnModel().getColumn(2).setMinWidth(0);
        tableBusqueda.getColumnModel().getColumn(2).setMaxWidth(0);
        tableBusqueda.getColumnModel().getColumn(2).setWidth(0);
        this.setLocationRelativeTo(parent);
    }
    
    
    
    private String tipoBusqueda;
    private Object objetoBuscar;
    public Cliente clienteBuscar;

    public Object getObjetoBuscar() {
        return objetoBuscar;
    }

    public void setObjetoBuscar(Object objetoBuscar) {
        this.objetoBuscar = objetoBuscar;
    }

    public String getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbBusquedaCriterio = new javax.swing.JComboBox<>();
        txtBusquedaDescripcion = new javax.swing.JTextField();
        btnBusquedaBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBusqueda = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Criterio:");

        jLabel2.setText("Búsqueda:");

        cmbBusquedaCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CÓDIGO", "DESCRIPCIÓN" }));

        btnBusquedaBuscar.setText("Buscar");
        btnBusquedaBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbBusquedaCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBusquedaDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusquedaBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBusquedaCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusquedaDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnBusquedaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 590, 110));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tableBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Obj"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBusqueda.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBusquedaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBusqueda);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 590, 260));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBusquedaBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaBuscarActionPerformed
        // TODO add your handling code here:
        if (txtBusquedaDescripcion.getText().isEmpty()) {
            System.out.println("Ingrese descripción");
            return;
        }
        limpiarTabla(tableBusqueda);
        String busqueda = txtBusquedaDescripcion.getText();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) tableBusqueda.getModel();
        switch (tipoBusqueda) {
            case "VENTA-CLIENTE":
                List<Cliente> lstCliente;
                if (cmbBusquedaCriterio.getSelectedIndex() == 0) {
                    Integer codigo = 0;
                    try {
                        codigo = Integer.parseInt(txtBusquedaDescripcion.getText().toString());
                    } catch (Exception e) {
                        codigo = 0;
                    }
                    lstCliente = ClienteDAO.obtenerClientesPorCodigo(codigo);
                } else {
                    lstCliente = ClienteDAO.obtenerClientesPorNombre(busqueda);
                }
                
                for (int i = 0; i < lstCliente.size(); i++) {
                    Object[] obj = new Object[3];
                    obj[0] = lstCliente.get(i).getId();
                    obj[1] = lstCliente.get(i).getApellido() + " " + lstCliente.get(i).getNombre();
                    obj[2] = lstCliente.get(i);
                    modelo.addRow(obj);
                }
                tableBusqueda.setModel(modelo);
                break;
            case "VENTA-PRODUCTO":
                List<Producto> lstProducto;
                if (cmbBusquedaCriterio.getSelectedIndex() == 0) {
                    Integer codigo = 0;
                    try {
                        codigo = Integer.parseInt(txtBusquedaDescripcion.getText().toString());
                    } catch (Exception e) {
                        codigo = 0;
                    }
                    lstProducto = ProductoDAO.obtenerProductosPorCodigo(codigo);
                } else {
                    lstProducto = ProductoDAO.obtenerProductosPorNombre(busqueda);
                }
                                
                for (int i = 0; i < lstProducto.size(); i++) {
                    Object[] obj = new Object[3];
                    obj[0] = lstProducto.get(i).getId();
                    obj[1] = lstProducto.get(i).getDescripcion();
                    obj[2] = lstProducto.get(i);
                    modelo.addRow(obj);
                }
                tableBusqueda.setModel(modelo);
                break;
                
            case "INGRESOMERCADERIA-PROVEEDOR":
                List<Proveedor> lstProveedor;
                if (cmbBusquedaCriterio.getSelectedIndex() == 0) {
                    Integer codigo = 0;
                    try {
                        codigo = Integer.parseInt(txtBusquedaDescripcion.getText().toString());
                    } catch (Exception e) {
                        codigo = 0;
                    }
                    lstProveedor = ProveedorDAO.obtenerProveedoresPorCodigo(codigo);
                } else {
                    lstProveedor = ProveedorDAO.obtenerProveedoresPorNombre(busqueda);
                }
                
                for (int i = 0; i < lstProveedor.size(); i++) {
                    Object[] obj = new Object[3];
                    obj[0] = lstProveedor.get(i).getId();
                    obj[1] = lstProveedor.get(i).getNombreApellido();
                    obj[2] = lstProveedor.get(i);
                    modelo.addRow(obj);
                }
                tableBusqueda.setModel(modelo);
                break;
    
            default:
            //throw new AssertionError();
        }

    }//GEN-LAST:event_btnBusquedaBuscarActionPerformed

    private void tableBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBusquedaMouseClicked
        // TODO add your handling code here:
         if (evt.getClickCount() == 2) {
            int fila = tableBusqueda.rowAtPoint(evt.getPoint());
            
            switch (tipoBusqueda) {
            case "VENTA-CLIENTE":
               frm.clienteBuscar = (Cliente) tableBusqueda.getValueAt(fila, 2);
               break;
            case "VENTA-PRODUCTO":
               frm.productoBuscar = (Producto) tableBusqueda.getValueAt(fila, 2);
                break;
    
            default:
            //throw new AssertionError();
        }

            
            
            
            
            this.setVisible(false);
            
         }
    }//GEN-LAST:event_tableBusquedaMouseClicked

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
            java.util.logging.Logger.getLogger(FrmBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmBusqueda dialog = new FrmBusqueda(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    private void limpiarTabla(JTable tabla ){
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.removeRow(i);
            i--;
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusquedaBuscar;
    private javax.swing.JComboBox<String> cmbBusquedaCriterio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBusqueda;
    private javax.swing.JTextField txtBusquedaDescripcion;
    // End of variables declaration//GEN-END:variables
}