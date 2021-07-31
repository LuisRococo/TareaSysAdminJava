
package Doctores;

import Coneccion.DBConexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class DatosDoctores {
    
    ResultSet rs;
    PreparedStatement ps;
    
    private final Connection cn=DBConexionSingleton.getBDConeccion();
    
//    private final ConeccionBD conn = new ConeccionBD();
//    private final Connection cn = conn.coneccion();
    
    @SuppressWarnings("UseSpecificCatch")
    public ResultSet actualizarTablaDoctores() {
        try {
            ps = cn.prepareStatement("Select idDoc as 'ID', nombreDoc as 'Nombre', telDoc as 'Telefono', especialidad as 'Especialidad', cedula as 'Cedula' FROM tblDoctor Order By nombreDoc");
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void insertarRegistroDoctor(String datos[]) {
        try {
            Statement sentenciaInsert = cn.createStatement();
            sentenciaInsert.executeUpdate("insert into tblDoctor values (" + datos[0] + ",'" + datos[1] + "','" + datos[2] + "','" + datos[3] + "','" + datos[4] + "')");
            JOptionPane.showMessageDialog(null, "Los Datos Fueron Registrados Satisfactoriamente");
            cerrarConeccion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public void modificarRegistroDoctor(String[] datos) {
        try {
            Statement sentenciaMod = cn.createStatement();
            sentenciaMod.executeUpdate("Update tblDoctor set nombreDoc='" + datos[1] + "',telDoc='" + datos[2] + "',especialidad='" + datos[3] + "',cedula='" + datos[4] + "'"
                    + "where idDoc=" + datos[0]);
            JOptionPane.showMessageDialog(null, "Los Datos Fueron Actualizados Satisfactoriamente");
            cerrarConeccion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public void eliminarRegistroDoctor(String[] datos) {
        try {
            Statement sentenciaEliminar = cn.createStatement();
            sentenciaEliminar.executeUpdate("delete from tblDoctor where idDoc=" + datos[0]);
            JOptionPane.showMessageDialog(null, "Los Datos Fueron Eliminados Satisfactoriamente");
            cerrarConeccion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public ResultSet buscarRegistroDoctor(String nombreDoctor) {
        try {
            ps = cn.prepareStatement("select idDoc as 'ID Paciente',nombreDoc as 'Nombre',telDoc as 'Telefono', especialidad as 'Especialidad', cedula as 'Cedula'"
                    + "from tblDoctor where nombreDoc like '" + nombreDoctor + "'");
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public ResultSet buscarComboDoctor (){
        try{
            ps=cn.prepareStatement("select nombreDoc from tblDoctor order by nombreDoc");
            rs=ps.executeQuery();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void cerrarConeccion (){
        try {
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public ResultSet regresarIDDoctor(String nombre) {
        try {
            ps = cn.prepareStatement("select * from tblDoctor where nombreDoc = '" + nombre + "'");
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
}
