
package Paciente;

import java.sql.*;
import Interfaces.Frame_Menu;
import com.sun.glass.events.KeyEvent;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


public class Frame_AdmiPacientes extends javax.swing.JFrame {

    private ResultSet rs;
    private boolean modoModificar;
    
    public Frame_AdmiPacientes() {
        initComponents();
        setHabilitadosTextFields(false);
        actualizarTabla();
        setBotonesHabilitados((byte)1);
        modoModificar=false;
    }
    
    private void actualizarTabla (){
        try{
            DatosPaciente FuncActualizarTablaPacientes = new DatosPaciente();
            rs = FuncActualizarTablaPacientes.actualizarTablaPacientes();
            tbl_Listada.setModel(DbUtils.resultSetToTableModel(rs));
            FuncActualizarTablaPacientes.cerrarConeccion();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private boolean insertarTablaPacientes (){
        String datos[]={txt_ID.getText(),txt_Nombre.getText(),txt_Correo.getText(),txt_Tel.getText(),txt_Direccion.getText()};
         try{
            DatosPaciente insertarATabla = new DatosPaciente();
            insertarATabla.insertarRegistroPaciente(datos);
            this.actualizarTabla();
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
         return false;
    }
    
    private void modificarTablaPacientes (){
        String datos[]={txt_ID.getText(),txt_Nombre.getText(),txt_Direccion.getText(),txt_Tel.getText(),txt_Correo.getText()};
        try{
            DatosPaciente modificarRegistro =new DatosPaciente();
            modificarRegistro.modificarRegistroPaciente(datos);
            this.actualizarTabla();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void eliminarTablaPacientes (){
        String datos[]={txt_ID.getText()};
        try{
            DatosPaciente eliminarRegistro=new DatosPaciente();
            eliminarRegistro.eliminarRegistroPaciente(datos);
            this.actualizarTabla();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void buscarDatosPacientes() {
        try {
            DatosPaciente buscar = new DatosPaciente();
            String[] datos = {"%" + txt_BuscarNombre.getText() + "%"};
            rs = buscar.buscarRegistroPaciente(datos[0]);
            tbl_Listada.setModel(DbUtils.resultSetToTableModel(rs));
            buscar.cerrarConeccion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void setHabilitadosTextFields (boolean habilitados){
        txt_Nombre.setEditable(habilitados);
        txt_Correo.setEditable(habilitados);
        txt_Direccion.setEditable(habilitados);
        txt_ID.setEditable(habilitados);
        txt_Tel.setEditable(habilitados);
    }
    
    private void limpiarTexFields (){
        txt_Correo.setText("");
        txt_Direccion.setText("");
        txt_ID.setText("");
        txt_Nombre.setText("");
        txt_Tel.setText("");
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

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btn_Menu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_Nombre = new javax.swing.JTextField();
        txt_Correo = new javax.swing.JTextField();
        txt_Tel = new javax.swing.JTextField();
        txt_Direccion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_BuscarNombre = new javax.swing.JTextField();
        btn_BuscarName = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Listada = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btn_Eliminar = new javax.swing.JButton();
        btn_Mod = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JButton();
        btn_Agregar = new javax.swing.JButton();
        btn_Nuevo = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Pacientes");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Administrar Pacientes");

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

        jLabel1.setText("ID Paciente:");

        jLabel2.setText("Nombre: ");

        jLabel5.setText("Tel: ");

        jLabel3.setText("E-Mail: ");

        jLabel7.setText("Direccion: ");

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

        txt_Correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_CorreoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CorreoKeyTyped(evt);
            }
        });

        txt_Tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_TelKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_TelKeyTyped(evt);
            }
        });

        txt_Direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_DireccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_DireccionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addComponent(txt_Tel)
                    .addComponent(txt_Correo)
                    .addComponent(txt_ID)
                    .addComponent(txt_Nombre))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_Tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Nombre Paciente: ");

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
                .addComponent(jLabel4)
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
                    .addComponent(jLabel4)
                    .addComponent(txt_BuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_BuscarName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_Listada.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Listada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ListadaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Listada);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        btn_Mod.setText("Modificar");
        btn_Mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModActionPerformed(evt);
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

        btn_Nuevo.setText("Nuevo");
        btn_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NuevoActionPerformed(evt);
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
                    .addComponent(btn_Eliminar)
                    .addComponent(btn_Mod)
                    .addComponent(btn_Cancelar)
                    .addComponent(btn_Agregar)
                    .addComponent(btn_Nuevo))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    
    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        if (insertarTablaPacientes()) {
            this.setHabilitadosTextFields(false);
            this.setBotonesHabilitados((byte) 1);
            this.limpiarTexFields();
        }
    }//GEN-LAST:event_btn_AgregarActionPerformed

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

    private void tbl_ListadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ListadaMouseClicked
        int seleccion =tbl_Listada.rowAtPoint(evt.getPoint());
        setBotonesHabilitados((byte)4);
        txt_ID.setText(String.valueOf(tbl_Listada.getValueAt(seleccion, 0)));
        txt_Nombre.setText(String.valueOf(tbl_Listada.getValueAt(seleccion, 1)));
        txt_Correo.setText(String.valueOf(tbl_Listada.getValueAt(seleccion, 2)));
        txt_Tel.setText(String.valueOf(tbl_Listada.getValueAt(seleccion, 3)));
        txt_Direccion.setText(String.valueOf(tbl_Listada.getValueAt(seleccion, 4)));
    }//GEN-LAST:event_tbl_ListadaMouseClicked

    private void btn_ModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModActionPerformed
        if (modoModificar) {
            modificarTablaPacientes();
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

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        int opc=JOptionPane.showConfirmDialog(this,"Seguro deseas Eliminar al Paciente: "+txt_Nombre.getText(),"ALERTA",JOptionPane.CANCEL_OPTION);
        if (opc==JOptionPane.OK_OPTION){
            setBotonesHabilitados((byte)1);
            this.eliminarTablaPacientes();
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    //KEY PRESSED
    private void txt_BuscarNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarNombreKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            buscarDatosPacientes();
        }
    }//GEN-LAST:event_txt_BuscarNombreKeyPressed

    private void txt_IDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IDKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) txt_Nombre.requestFocus();
    }//GEN-LAST:event_txt_IDKeyPressed

    private void txt_NombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) txt_Correo.requestFocus();
    }//GEN-LAST:event_txt_NombreKeyPressed

    private void txt_CorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CorreoKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) txt_Tel.requestFocus();
    }//GEN-LAST:event_txt_CorreoKeyPressed

    private void txt_TelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) txt_Direccion.requestFocus();
    }//GEN-LAST:event_txt_TelKeyPressed

    //KEY TYPED
    private void txt_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyTyped
        if (txt_Nombre.getText().length()>=35) evt.consume();
    }//GEN-LAST:event_txt_NombreKeyTyped

    private void txt_CorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CorreoKeyTyped
        if (txt_Correo.getText().length()>=30) evt.consume();
    }//GEN-LAST:event_txt_CorreoKeyTyped

    private void txt_TelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelKeyTyped
        if (txt_Tel.getText().length()>=18) evt.consume();
    }//GEN-LAST:event_txt_TelKeyTyped

    private void txt_DireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DireccionKeyTyped
        if (txt_Direccion.getText().length()>=100) evt.consume();
    }//GEN-LAST:event_txt_DireccionKeyTyped

    private void txt_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IDKeyTyped
        char c=evt.getKeyChar();
        if (c<'0'||c>'9') evt.consume();
    }//GEN-LAST:event_txt_IDKeyTyped

    //BTN BUSCAR
    private void btn_BuscarNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BuscarNameActionPerformed
        buscarDatosPacientes();
    }//GEN-LAST:event_btn_BuscarNameActionPerformed

    private void txt_DireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DireccionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (insertarTablaPacientes()) {
                this.setHabilitadosTextFields(false);
                this.setBotonesHabilitados((byte) 1);
                this.limpiarTexFields();
            }
        }
    }//GEN-LAST:event_txt_DireccionKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Frame_Menu menu=new Frame_Menu();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

   
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
            java.util.logging.Logger.getLogger(Frame_AdmiPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_AdmiPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_AdmiPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_AdmiPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_AdmiPacientes().setVisible(true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Listada;
    private javax.swing.JTextField txt_BuscarNombre;
    private javax.swing.JTextField txt_Correo;
    private javax.swing.JTextField txt_Direccion;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_Tel;
    // End of variables declaration//GEN-END:variables
}
