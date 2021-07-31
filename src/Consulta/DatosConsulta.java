
package Consulta;

import Coneccion.DBConexionSingleton;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DatosConsulta {

    ResultSet rs;
    PreparedStatement ps;
    private CallableStatement cs;
    private final Connection cn = DBConexionSingleton.getBDConeccion();

    @SuppressWarnings("UseSpecificCatch")
    public ResultSet getInfoParaConsulta (int idCita){
        this.rs=null;
        try{
            cs=cn.prepareCall("{call SpConsultas (@ACCION=1, @idCita="+idCita+")}");
            rs=cs.executeQuery();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }

    @SuppressWarnings("UseSpecificCatch")
    public boolean insertarRegistroConsulta(String datos[]) {
        try {
            cs=cn.prepareCall("{call SpConsultas (@ACCION=2,@idPac="+datos[0]+",@idDoc="+datos[1]+",@fechaCita='"+datos[2]+"',@diagnostico='"+datos[4]+"',@motivo='"+datos[3]+"')}");
            cs.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public String obtenerFechaCita (int idCita){
        try{
            cs=cn.prepareCall("{call SpConsultas (@ACCION=3,@idCita="+idCita+")}");
            rs=cs.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public boolean isConsultaEnEspera (int idCita){
        try{
            ps=cn.prepareCall("select count (*) from tblCitas where estado=1 and idCita="+idCita);
            rs=ps.executeQuery();
            rs.next();
            if (rs.getInt(1)>=1) return true;
            else return false;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
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
    public boolean modificarConsulta(String datos[]) {
        try {
            cs = cn.prepareCall("{call SpConsultas (@ACCION=4,@idConsulta="+datos[0]+",@motivo='"+datos[1]+"',@diagnostico='"+datos[2]+"')}");
            cs.execute();
            this.cerrarConeccion();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
