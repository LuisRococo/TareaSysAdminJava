
package Doctores;

import Coneccion.BackUp;
import Interfaces.Frame_Menu;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Frame_AdmiDoctores extends javax.swing.JFrame {

    private ResultSet rs;
    private boolean modoModificar;
    
    public Frame_AdmiDoctores() {
        initComponents();
        setHabilitadosTextFields(false);
        setBotonesHabilitados((byte)1);
        actualizarTabla();
        modoModificar=false;
    }
    
    private void actualizarTabla (){
        try{
            DatosDoctores FuncActualizarTablaDoctores = new DatosDoctores();
            rs = FuncActualizarTablaDoctores.actualizarTablaDoctores();
            tbl_Listado.setModel(DbUtils.resultSetToTableModel(rs));
            FuncActualizarTablaDoctores.cerrarConeccion();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private boolean insertarTablaDoctores (){
        String datos[]={txt_ID.getText(),txt_Nombre.getText(),txt_Telefono.getText(),txt_Especialidad.getText(),txt_Cedula.getText()};
         try{
            DatosDoctores insertarATabla = new DatosDoctores();
            insertarATabla.insertarRegistroDoctor(datos);
            this.actualizarTabla();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
         return false;
    }

    private void modificarTablaDoctores() {
        String datos[] = {txt_ID.getText(), txt_Nombre.getText(), txt_Telefono.getText(), txt_Especialidad.getText(), txt_Cedula.getText()};
        try {
            DatosDoctores modificarRegistro = new DatosDoctores();
            modificarRegistro.modificarRegistroDoctor(datos);
            this.actualizarTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void eliminarTablaDoctor() {
        String datos[] = {txt_ID.getText()};
        try {
            DatosDoctores eliminarRegistro = new DatosDoctores();
            eliminarRegistro.eliminarRegistroDoctor(datos);
            this.actualizarTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void buscarTablaDoctor (){
        try{
            DatosDoctores buscar = new DatosDoctores();
            String[] datos = {"%" + txt_BuscarNombre.getText() + "%"};
            rs = buscar.buscarRegistroDoctor(datos[0]);
            tbl_Listado.setModel(DbUtils.resultSetToTableModel(rs));
            buscar.cerrarConeccion();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setHabilitadosTextFields (boolean habilitados){
        txt_Cedula.setEditable(habilitados);
        txt_Especialidad.setEditable(habilitados);
        txt_ID.setEditable(habilitados);
        txt_Nombre.setEditable(habilitados);
        txt_Telefono.setEditable(habilitados);
    }
    
    private void limpiarTexFields (){
        txt_Cedula.setText("");
        txt_Especialidad.setText("");
        txt_ID.setText("");
        txt_Nombre.setText("");
        txt_Telefono.setText("");
    }
    
    private void setBotonesHabilitados (byte modo){ //1 normal,2 modificar,3 nuevo,4 tablaSelect
        switch (modo) {
            case 1:
                btn_Agregar.setEnabled(false);
                btn_Cancelar.setEnabled(false);
                btn_Eliminar.setEnabled(false);
                btn_Mod.setEnabled(false);
                btn_Nuevo.setEnabled(true);
                btn_BuscarName.setEnabled(true);
                txt_BuscarNombre.setEnabled(true);
                break;
            case 2:
                btn_Agregar.setEnabled(false);
                btn_Cancelar.setEnabled(true);
                btn_Eliminar.setEnabled(false);
                btn_Mod.setEnabled(true);
                btn_Nuevo.setEnabled(false);
                btn_BuscarName.setEnabled(false);
                txt_BuscarNombre.setEnabled(false);
                break;
            case 3:
                btn_Agregar.setEnabled(true);
                btn_Cancelar.setEnabled(true);
                btn_Eliminar.setEnabled(false);
                btn_Mod.setEnabled(false);
                btn_Nuevo.setEnabled(false);
                btn_BuscarName.setEnabled(false);
                txt_BuscarNombre.setEnabled(false);
                break;
            case 4:
                btn_Agregar.setEnabled(false);
                btn_Cancelar.setEnabled(true);
                btn_Eliminar.setEnabled(true);
                btn_Mod.setEnabled(true);
                btn_Nuevo.setEnabled(true);
                btn_BuscarName.setEnabled(true);
                txt_BuscarNombre.setEnabled(true);
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btn_Menu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_BuscarNombre = new javax.swing.JTextField();
        btn_BuscarName = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_Nombre = new javax.swing.JTextField();
        txt_Especialidad = new javax.swing.JTextField();
        txt_Cedula = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_Telefono = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Listado = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btn_Mod = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Nuevo = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JButton();
        btn_Agregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Doctores");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Administrar Doctores");

        btn_Menu.setText("Menu");
        btn_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Menu)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btn_Menu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Nombre Doctor: ");

        txt_BuscarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_BuscarNombreKeyPressed(evt);
            }
        });

        btn_BuscarName.setText("Buscar");
        btn_BuscarName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BuscarNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_BuscarNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_BuscarName)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_BuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_BuscarName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("ID Doctor:");

        jLabel3.setText("Nombre:");

        jLabel5.setText("Cedula:");

        jLabel7.setText("Especialidad:");

        txt_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_IDKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_IDKeyTyped(evt);
            }
        });

        txt_Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_NombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_NombreKeyTyped(evt);
            }
        });

        txt_Especialidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_EspecialidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_EspecialidadKeyTyped(evt);
            }
        });

        txt_Cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_CedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CedulaKeyTyped(evt);
            }
        });

        jLabel8.setText("Telefono: ");

        txt_Telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_TelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_TelefonoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Especialidad, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(txt_ID, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_Nombre)
                    .addComponent(txt_Cedula, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_Telefono))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_Especialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_Cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_Listado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_Listado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ListadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Listado);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_Mod.setText("Modificar");
        btn_Mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModActionPerformed(evt);
            }
        });

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        btn_Nuevo.setText("Nuevo");
        btn_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NuevoActionPerformed(evt);
            }
        });

        btn_Cancelar.setText("Cancelar");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });

        btn_Agregar.setText("Agregar");
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Agregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Mod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Cancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Nuevo)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Mod)
                    .addComponent(btn_Eliminar)
                    .addComponent(btn_Nuevo)
                    .addComponent(btn_Cancelar)
                    .addComponent(btn_Agregar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MenuActionPerformed
        Frame_Menu frame=new Frame_Menu();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btn_MenuActionPerformed

    private void btn_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NuevoActionPerformed
        limpiarTexFields();
        setBotonesHabilitados((byte)3);
        setHabilitadosTextFields(true);
    }//GEN-LAST:event_btn_NuevoActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        limpiarTexFields();
        setHabilitadosTextFields(false);
        setBotonesHabilitados((byte)1);
        modoModificar=false;
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        if (insertarTablaDoctores()) {
            this.setHabilitadosTextFields(false);
            setBotonesHabilitados((byte) 1);
            this.limpiarTexFields();
        }
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        int opc = JOptionPane.showConfirmDialog(this, "Seguro deseas Eliminar al Doctor: "+txt_Nombre.getText(), "ALERTA", JOptionPane.CANCEL_OPTION);
        if (opc == JOptionPane.OK_OPTION) {
            setBotonesHabilitados((byte)1);
            this.eliminarTablaDoctor();
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_ModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModActionPerformed
        if (modoModificar) {
            modificarTablaDoctores();
            modoModificar=false;
            setHabilitadosTextFields(modoModificar);
            setBotonesHabilitados((byte)1);
        } else {
            setHabilitadosTextFields(true);
            txt_ID.setEditable(false);
            setBotonesHabilitados((byte)2);
            modoModificar=true;
        }
    }//GEN-LAST:event_btn_ModActionPerformed

    private void tbl_ListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ListadoMouseClicked
        int seleccion=tbl_Listado.rowAtPoint(evt.getPoint());
        setBotonesHabilitados((byte)4);
        txt_ID.setText(String.valueOf(tbl_Listado.getValueAt(seleccion, 0)));
        txt_Nombre.setText(String.valueOf(tbl_Listado.getValueAt(seleccion, 1)));
        txt_Telefono.setText(String.valueOf(tbl_Listado.getValueAt(seleccion, 2)));
        txt_Especialidad.setText(String.valueOf(tbl_Listado.getValueAt(seleccion, 3)));
        txt_Cedula.setText(String.valueOf(tbl_Listado.getValueAt(seleccion, 4)));
        
    }//GEN-LAST:event_tbl_ListadoMouseClicked

    private void btn_BuscarNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BuscarNameActionPerformed
        buscarTablaDoctor();
    }//GEN-LAST:event_btn_BuscarNameActionPerformed

    private void txt_BuscarNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarNombreKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) buscarTablaDoctor();
    }//GEN-LAST:event_txt_BuscarNombreKeyPressed

    private void txt_IDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IDKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) txt_Nombre.requestFocus();
    }//GEN-LAST:event_txt_IDKeyPressed

    private void txt_NombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) txt_Especialidad.requestFocus();
    }//GEN-LAST:event_txt_NombreKeyPressed

    private void txt_EspecialidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EspecialidadKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) txt_Cedula.requestFocus();
    }//GEN-LAST:event_txt_EspecialidadKeyPressed

    private void txt_CedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CedulaKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) txt_Telefono.requestFocus();
    }//GEN-LAST:event_txt_CedulaKeyPressed

    private void txt_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IDKeyTyped
        char c=evt.getKeyChar();
        if (c<'0'||c>'9') evt.consume();
    }//GEN-LAST:event_txt_IDKeyTyped

    private void txt_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyTyped
        if (txt_Nombre.getText().length()>=35) evt.consume();
    }//GEN-LAST:event_txt_NombreKeyTyped

    private void txt_EspecialidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EspecialidadKeyTyped
        if (txt_Especialidad.getText().length()>=45) evt.consume();
    }//GEN-LAST:event_txt_EspecialidadKeyTyped

    private void txt_CedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CedulaKeyTyped
        if (txt_Cedula.getText().length()>=45) evt.consume();
    }//GEN-LAST:event_txt_CedulaKeyTyped

    private void txt_TelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelefonoKeyTyped
        if (txt_Telefono.getText().length()>=18) evt.consume();
    }//GEN-LAST:event_txt_TelefonoKeyTyped

    private void txt_TelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelefonoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (insertarTablaDoctores()) {
                this.setHabilitadosTextFields(false);
                setBotonesHabilitados((byte) 1);
                this.limpiarTexFields();
            }
        }
    }//GEN-LAST:event_txt_TelefonoKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Frame_Menu menu=new Frame_Menu();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_AdmiDoctores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_BuscarName;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Menu;
    private javax.swing.JButton btn_Mod;
    private javax.swing.JButton btn_Nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Listado;
    private javax.swing.JTextField txt_BuscarNombre;
    private javax.swing.JTextField txt_Cedula;
    private javax.swing.JTextField txt_Especialidad;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_Telefono;
    // End of variables declaration//GEN-END:variables
}
