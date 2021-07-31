
package Paciente;

import Coneccion.DBConexionSingleton;
import java.sql.*;
import javax.swing.JOptionPane;

public class DatosPaciente {
    
    ResultSet rs;
    PreparedStatement ps;
    private final Connection cn=DBConexionSingleton.getBDConeccion();
    
//    private final ConeccionBD conn = new ConeccionBD();
//    private final Connection cn = conn.coneccion();

    @SuppressWarnings("UseSpecificCatch")
    public ResultSet actualizarTablaPacientes() {
        try {
            ps = cn.prepareStatement("Select IdPac as 'ID', NomPac as 'Nombre', DirePac as 'Direccion', telPac as 'Telefono', emailPac as 'E-mail'FROM tblPaciente Order By NomPac");
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void insertarRegistroPaciente (String datos[]){
        try{
            Statement sentenciaInsert=cn.createStatement();
            sentenciaInsert.executeUpdate("insert into tblPaciente values ("+datos[0]+",'"+datos[1]+"','"+datos[2]+"','"+datos[3]+"','"+datos[4]+"')");
            JOptionPane.showMessageDialog(null, "Los Datos Fueron Registrados Satisfactoriamente");
            cerrarConeccion();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void modificarRegistroPaciente (String [] datos){
        try{
            Statement sentenciaMod=cn.createStatement();
            sentenciaMod.executeUpdate("Update tblPaciente set NomPac='"+datos[1]+"',DirePac='"+datos[2]+"',telPac='"+datos[3]+"',emailPac='"+datos[4]+"'"
                    + "where IdPac="+datos[0]);
            JOptionPane.showMessageDialog(null, "Los Datos Fueron Actualizados Satisfactoriamente");
            cerrarConeccion();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void eliminarRegistroPaciente (String[] datos){
        try{
            Statement sentenciaEliminar =cn.createStatement();
            sentenciaEliminar.executeUpdate("delete from tblPaciente where IdPac="+datos[0]);
            JOptionPane.showMessageDialog(null, "Los Datos Fueron Eliminados Satisfactoriamente");
            cerrarConeccion();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public ResultSet buscarRegistroPaciente (String nombrePaciente){
        try{
            ps =cn.prepareStatement("select IdPac as 'ID Paciente',NomPac as 'Nombre',DirePac as 'Direccion', telPac as 'Telefono', emailPac as 'Correo'"
                    + "from tblPaciente where NomPac like '"+nombrePaciente+"'");
            rs=ps.executeQuery();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public ResultSet buscarComboPaciente (){
        try{
            ps=cn.prepareStatement("select NomPac from tblPaciente order by NomPac");
            rs=ps.executeQuery();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }

    @SuppressWarnings("UseSpecificCatch")
    public void cerrarConeccion() {
        try {
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public ResultSet regresarIDPaciente (String nombre){
        try{
            ps=cn.prepareStatement("select * from tblPaciente where NomPac = '"+nombre+"'");
            rs=ps.executeQuery();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
}
