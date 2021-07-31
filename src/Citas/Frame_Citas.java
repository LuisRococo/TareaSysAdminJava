
package Citas;

import Doctores.DatosDoctores;
import Interfaces.Frame_Menu;
import Paciente.DatosPaciente;
import com.toedter.calendar.JCalendar;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Frame_Citas extends javax.swing.JFrame {


    private int estadoID;
    private final String errorCamposIncompletos="No ha seleccionado unos o mas campos importantes";
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Frame_Citas() {
        initComponents();
        estadoID=0;
        estadoBotones(true);
        limpiarFormulario();
        actualizarTablaCitas(false);
        getDate();
    }
    
    private void setSigTurno(){
        if (cb_Doctor.getSelectedIndex()!=0 && cb_Turno.getSelectedIndex()!=0){
            txt_TurnoCita.setText(String.valueOf(getSigTurno()));
        } else {
            txt_TurnoCita.setText("");
        }
    }
    private boolean isvalidToInsert (){
        return cb_Doctor.getSelectedIndex()!=0 && cb_Paciente.getSelectedIndex()!=0 && cb_Turno.getSelectedIndex()!=0;
    }
    private int getSigTurno (){
        DatosCitas datos=new DatosCitas();
        int cita=datos.siguienteNumeroDeTurno(txt_IDDoctor.getText(), getDate(), getTurno()+"");
        return cita;
    }
    private char getTurno (){
        switch (String.valueOf(cb_Turno.getSelectedItem())) {
            case "Vespertino (V)":
                return 'V';
            case "Matutino (M)":
                return 'M';
            default:
                return 'X';
        }
    }
    
    private void estadoBotones (boolean estado){ //true-normal false-agregando
        btn_Agregar.setEnabled(!estado);
        btn_EstadoCancelada.setEnabled(estado);
        btn_EstadoActivar.setEnabled(estado);
        btn_Limpiar.setEnabled(!estado);
        btn_Nuevo.setEnabled(estado);
        btn_Cancelar.setEnabled(!estado);
        //demas
        cb_Doctor.setEnabled(!estado);
        cb_Paciente.setEnabled(!estado);
        cb_Turno.setEnabled(!estado);
        jDateChooser1.setEnabled(!estado);
        //Labels
        label1.setEnabled(!estado);
        label2.setEnabled(!estado);
        label3.setEnabled(!estado);
        label4.setEnabled(!estado);
        label5.setEnabled(!estado);
        label6.setEnabled(!estado);
        label7.setEnabled(!estado);
    }
    
//    private int getEstado (){
//        switch (String.valueOf(cb_Estado.getSelectedItem())){
//            case "Espera":
//                return 1;
//            case "Consultada":
//                return 2;
//            case "Cancelada":
//                return 3;
//            default:
//                return -1;
//        }
//    }
    
    private String getDate(){
        JCalendar calendar=jDateChooser1.getJCalendar();
        Date date=calendar.getDate();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public void limpiarFormulario() {
        actualizarComboBoxs();
        actualizarFechaJDate();
        txt_TurnoCita.setText("");
        txt_IDDoctor.setText("");
        txt_IDPaciente.setText("");
    }
    
    public void actualizarFechaJDate (){
        Calendar fecha=new GregorianCalendar();
        this.jDateChooser1.setCalendar(fecha);
        this.jDateChooser2.setCalendar(fecha);
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void actualizarComboBoxs() {
        estadoID=0;
        
        cb_Doctor.removeAllItems();
        cb_Paciente.removeAllItems();
        cb_Turno.removeAllItems();
        
        cb_Turno.addItem("-Seleccione-");
        cb_Turno.addItem("Matutino (M)");
        cb_Turno.addItem("Vespertino (V)");
        
        try {
            DatosDoctores doc = new DatosDoctores();
            DatosPaciente pac = new DatosPaciente();

            ResultSet rs_Doc = doc.buscarComboDoctor();
            ResultSet rs_Pac = pac.buscarComboPaciente();

            cb_Doctor.addItem("-Seleccione-");
            cb_Paciente.addItem("-Seleccione-");

            while (rs_Doc.next()) {
                cb_Doctor.addItem(rs_Doc.getString("nombreDoc"));
            }
            while (rs_Pac.next()){
                cb_Paciente.addItem(rs_Pac.getString("NomPac"));
            }
            
            doc.cerrarConeccion();
            pac.cerrarConeccion();
            estadoID=1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private int getCantPacPorFecha (){
        DatosCitas dc=new DatosCitas();
        return dc.cantDeCitasEnUnDiaPorUnPac(txt_IDPaciente.getText(), getDate());
    }
    
    @SuppressWarnings("UseSpecificCatch")
    private void arrojarIdPaciente() {
        try {
            DatosPaciente datos = new DatosPaciente();
            ResultSet result = datos.regresarIDPaciente(String.valueOf(this.cb_Paciente.getSelectedItem()));
            result.next();
            this.txt_IDPaciente.setText(String.valueOf(result.getInt("IdPac")));
            datos.cerrarConeccion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            txt_IDPaciente.setText("");
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    private void arrojarIdDoctor() {
        try {
            DatosDoctores datos = new DatosDoctores();
            ResultSet result = datos.regresarIDDoctor(String.valueOf(this.cb_Doctor.getSelectedItem()));
            result.next();
            this.txt_IDDoctor.setText(String.valueOf(result.getInt("idDoc")));
            datos.cerrarConeccion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            txt_IDDoctor.setText("");
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    private boolean insertarTablaCitas() {
        try {
            if (isvalidToInsert()) {
                String[] datos = {txt_IDPaciente.getText(), txt_IDDoctor.getText(), getDate(), getTurno() + "", txt_TurnoCita.getText(), "1"};
                DatosCitas dCitas = new DatosCitas();
                dCitas.insertarCita(datos);
                actualizarTablaCitas(false);
                return true;
            } else {
                JOptionPane.showMessageDialog(this, this.errorCamposIncompletos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        return false;
    }

    @SuppressWarnings("UseSpecificCatch")
    private boolean insertarTablaCitasProcedimiento() {
        try {
            if (isvalidToInsert()) {
                String[] datos = {txt_IDPaciente.getText(), txt_IDDoctor.getText(), getDate(), getTurno() + "", txt_TurnoCita.getText(), "1"};
                DatosCitas dCitas = new DatosCitas();
                dCitas.SPInsertarCita(datos);
                actualizarTablaCitas(false);
                return true;
            } else {
                JOptionPane.showMessageDialog(this, this.errorCamposIncompletos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        return false;
    }
    
    private void actualizarTablaCitas (boolean anteiores){
        try{
            Calendar calendar = jDateChooser2.getCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            DatosCitas datosC = new DatosCitas();
            ResultSet rs;
            if (anteiores){
                rs = datosC.actualizarTablaCitas(sdf.format(calendar.getTime()),true);
            } else {
                rs = datosC.actualizarTablaCitas(sdf.format(calendar.getTime()),false);
            }
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            datosC.cerrarConeccion();

            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    public boolean cambiarEstadoCita(String noEstado){
        int select=jTable1.getSelectedRow();
        if (select!=-1){
            DatosCitas datosC = new DatosCitas();
            datosC.cambiarEstadoCita(String.valueOf(jTable1.getModel().getValueAt(select, 0)), noEstado);
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "No tiene seleccionado en la tabla ninguna cita");
            return false;
        }
    }
    
    public boolean SPCancelarEstadoCita() {
        int select = jTable1.getSelectedRow();
        if (select != -1) {
            DatosCitas datosC = new DatosCitas();
            datosC.SPCambiarEstadoCita(String.valueOf(jTable1.getModel().getValueAt(select, 0)));
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "No tiene seleccionado en la tabla ninguna cita");
            return false;
        }
    }

//    private void mostrarCitasSP() {
//        try {
//            DatosCitas dt = new DatosCitas();
//            jTable1.setModel(DbUtils.resultSetToTableModel(dt.SPMostrarTablaCita()));
//            dt.cerrarConeccion();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e);
//        }
//    }
//
//    private void mostrarTablasCitasSP() {
//        try {
//            String fecha = this.getDate();
//            DatosCitas dt = new DatosCitas();
//            ResultSet rs = dt.SPMostrarVistaTablaCita(fecha);
//            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
//            dt.cerrarConeccion();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e);
//        }
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_Menu = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        cb_Doctor = new javax.swing.JComboBox<>();
        label2 = new javax.swing.JLabel();
        txt_IDDoctor = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        txt_IDPaciente = new javax.swing.JTextField();
        cb_Paciente = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        label5 = new javax.swing.JLabel();
        cb_Turno = new javax.swing.JComboBox<>();
        label6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        label7 = new javax.swing.JLabel();
        txt_TurnoCita = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        btn_Limpiar = new javax.swing.JButton();
        btn_Agregar = new javax.swing.JButton();
        btn_Nuevo = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btn_EstadoCancelada = new javax.swing.JButton();
        btn_EstadoActivar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Citas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Administrar Citas: ");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jPanel4.setBackground(new java.awt.Color(220, 220, 220));

        label1.setText("Doctor:  ");

        cb_Doctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_DoctorActionPerformed(evt);
            }
        });

        label2.setText("ID: ");

        txt_IDDoctor.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1)
                    .addComponent(label2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_Doctor, 0, 284, Short.MAX_VALUE)
                    .addComponent(txt_IDDoctor))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1)
                    .addComponent(cb_Doctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2)
                    .addComponent(txt_IDDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label3.setText("Paciente: ");

        label4.setText("ID: ");

        txt_IDPaciente.setEditable(false);

        cb_Paciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_PacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3)
                    .addComponent(label4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_IDPaciente)
                    .addComponent(cb_Paciente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3)
                    .addComponent(cb_Paciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label4)
                    .addComponent(txt_IDPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label5.setText("Turno: ");

        cb_Turno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_TurnoActionPerformed(evt);
            }
        });

        label6.setText("Fecha: ");

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        label7.setText("Turno Cita: ");

        txt_TurnoCita.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label6)
                        .addGap(8, 8, 8)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label5)
                        .addGap(9, 9, 9)
                        .addComponent(cb_Turno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TurnoCita, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label5)
                    .addComponent(cb_Turno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label6)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TurnoCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label7))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        btn_Limpiar.setText("Limpiar");
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
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

        btn_Cancelar.setText("Cancelar");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Cancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Nuevo)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Limpiar)
                    .addComponent(btn_Agregar)
                    .addComponent(btn_Nuevo)
                    .addComponent(btn_Cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(220, 220, 220));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_EstadoCancelada.setText("Cancelada");
        btn_EstadoCancelada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EstadoCanceladaActionPerformed(evt);
            }
        });

        btn_EstadoActivar.setText("Activar");
        btn_EstadoActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EstadoActivarActionPerformed(evt);
            }
        });

        jLabel3.setText("Cambiar estado a: ");

        jLabel2.setText("Filtrar:");

        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jButton1.setText("Mostrar Anteriores");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_EstadoCancelada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_EstadoActivar)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_EstadoCancelada)
                        .addComponent(btn_EstadoActivar)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jButton1))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        int opc = JOptionPane.showConfirmDialog(this, "¿Seguro deseas Limpiar el Formulario?", "ALERTA", JOptionPane.CANCEL_OPTION);
        if (opc == JOptionPane.OK_OPTION) {
            limpiarFormulario();
            actualizarTablaCitas(false);
        }
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void cb_PacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_PacienteActionPerformed
        if (this.estadoID==1){
            this.arrojarIdPaciente();
        }
    }//GEN-LAST:event_cb_PacienteActionPerformed

    private void cb_DoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_DoctorActionPerformed
        if (this.estadoID==1){
            this.arrojarIdDoctor();
            this.setSigTurno();
        }
    }//GEN-LAST:event_cb_DoctorActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        if (this.getCantPacPorFecha() == 0) {
            if (insertarTablaCitasProcedimiento()) {
                limpiarFormulario();
                estadoBotones(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Un Paciente no puede tener mas de una cita por dia");
        }
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void cb_TurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TurnoActionPerformed
        if (this.estadoID==1){
            setSigTurno();
        }
    }//GEN-LAST:event_cb_TurnoActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        if (this.estadoID==1){
            setSigTurno();
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void btn_EstadoCanceladaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EstadoCanceladaActionPerformed
        if (SPCancelarEstadoCita()){
            actualizarTablaCitas(false);
        }
    }//GEN-LAST:event_btn_EstadoCanceladaActionPerformed

    private void btn_EstadoActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EstadoActivarActionPerformed
        if (cambiarEstadoCita(String.valueOf(1))){
            actualizarTablaCitas(false);
        }
    }//GEN-LAST:event_btn_EstadoActivarActionPerformed

    private void btn_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NuevoActionPerformed
        estadoBotones(false);
    }//GEN-LAST:event_btn_NuevoActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        int opc = JOptionPane.showConfirmDialog(this, "¿Seguro deseas Cancelar el Formulario?", "ALERTA", JOptionPane.CANCEL_OPTION);
        if (opc == JOptionPane.OK_OPTION) {
            limpiarFormulario();
            estadoBotones(true);
        }
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        if (this.estadoID==1){
            actualizarTablaCitas(false);
        }
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Frame_Menu menu=new Frame_Menu();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        actualizarTablaCitas(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Frame_Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_Citas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_EstadoActivar;
    private javax.swing.JButton btn_EstadoCancelada;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Menu;
    private javax.swing.JButton btn_Nuevo;
    private javax.swing.JComboBox<String> cb_Doctor;
    private javax.swing.JComboBox<String> cb_Paciente;
    private javax.swing.JComboBox<String> cb_Turno;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JTextField txt_IDDoctor;
    private javax.swing.JTextField txt_IDPaciente;
    private javax.swing.JTextField txt_TurnoCita;
    // End of variables declaration//GEN-END:variables
}
