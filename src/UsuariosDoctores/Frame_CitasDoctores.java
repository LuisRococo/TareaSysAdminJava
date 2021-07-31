
package UsuariosDoctores;

import Consulta.DatosConsulta;
import Consulta.Frame_Consulta;
import Interfaces.Frame_Menu;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Frame_CitasDoctores extends javax.swing.JFrame {

    private final int idDoctor;
    public Frame_CitasDoctores(int idDoctor) {
        initComponents();
        this.idDoctor=idDoctor;
        tablaCitas();
    }

    private String getDate() {
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    public void actualizar (){
        tablaCitas();
    }
    
    private void tablaCitas (){
        try{
            String datos[]={String.valueOf(idDoctor),getDate()};
            DatosUsuariosDoc dt=new DatosUsuariosDoc();
            ResultSet rs=dt.tablaCitas(datos);
            tbl_Citas.setModel(DbUtils.resultSetToTableModel(rs));
            dt.cerrarConeccion();
            
            tbl_Citas.getColumnModel().getColumn(3).setMaxWidth(0);
            tbl_Citas.getColumnModel().getColumn(3).setMinWidth(0);
            tbl_Citas.getColumnModel().getColumn(3).setPreferredWidth(0);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    @SuppressWarnings("UseSpecificCatch")
    private void entrarAFrameConsulta() {
        ResultSet rs;
        DatosUsuariosDoc dt=new DatosUsuariosDoc();
        DatosConsulta dtCon=new DatosConsulta();
        int idPaciente;
        String fecha;
        int pos = tbl_Citas.getSelectedRow();
        try {
            if (pos == -1) {
                JOptionPane.showMessageDialog(this, "ERROR: Necesita seleccionar un registro de la tabla");
            } else {
                if (dtCon.isConsultaEnEspera(Integer.valueOf(tbl_Citas.getModel().getValueAt(pos, 3).toString()))) {
                    dtCon.cerrarConeccion();
                    Frame_Consulta fr = new Frame_Consulta(Integer.valueOf(tbl_Citas.getModel().getValueAt(pos, 3).toString()), idDoctor, this);
                    fr.setLocationRelativeTo(null);
                    fr.setVisible(true);
                } else {
                    dtCon.cerrarConeccion();
                    rs = dt.obtenerIdDoc(tbl_Citas.getModel().getValueAt(pos, 3).toString());
                    rs.next();
                    idPaciente = rs.getInt("idPac");
                    fecha = rs.getString("fecha");
                    String datos[] = {String.valueOf(idDoctor), String.valueOf(idPaciente), fecha};
                    rs = dt.obtenerDatosConsulta(datos);
                    rs.next();
                    Frame_Consulta fr = new Frame_Consulta(Integer.valueOf(tbl_Citas.getModel().getValueAt(pos, 3).toString()), idDoctor,rs.getString("motivo"),rs.getString("diagnostico"),rs.getInt("idConsulta"), this);
                    fr.setLocationRelativeTo(null);
                    fr.setVisible(true);
                    dt.cerrarConeccion();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_Menu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Citas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_admiConsulta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Citas Doctor");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Citas De Este Día: ");

        btn_Menu.setText("Menu");
        btn_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                .addComponent(btn_Menu)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btn_Menu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_Citas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        tbl_Citas.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Citas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CitasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Citas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        btn_admiConsulta.setText("Administrar Consulta");
        btn_admiConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_admiConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_admiConsulta)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_admiConsulta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        Frame_Menu menu=new Frame_Menu();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btn_MenuActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Frame_Menu menu=new Frame_Menu();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void btn_admiConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_admiConsultaActionPerformed
        this.entrarAFrameConsulta();
    }//GEN-LAST:event_btn_admiConsultaActionPerformed

    private void tbl_CitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CitasMouseClicked
        if (evt.getClickCount()==2){
            entrarAFrameConsulta();
        }
    }//GEN-LAST:event_tbl_CitasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Menu;
    private javax.swing.JButton btn_admiConsulta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Citas;
    // End of variables declaration//GEN-END:variables
}
