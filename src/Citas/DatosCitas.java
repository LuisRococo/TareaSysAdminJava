
package Citas;
import Coneccion.DBConexionSingleton;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class DatosCitas {
    
    ResultSet rs;
    PreparedStatement ps;
    private final Connection cn=DBConexionSingleton.getBDConeccion();
    private CallableStatement cs;
    
//    private final ConeccionBD conn = new ConeccionBD();
//    private final Connection cn = conn.coneccion();
    
    @SuppressWarnings("UseSpecificCatch")
    public void insertarCita (String [] datos){
        try{
            Statement st=cn.createStatement();
            st.executeUpdate("insert into tblCitas values ("+datos[0]+","+datos[1]+",'"+datos[2]+"','"+datos[3]+"',"+datos[4]+","+datos[5]+")");
            cerrarConeccion();
            JOptionPane.showMessageDialog(null, "Se ha podido insertar correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
    public ResultSet actualizarTablaCitas(String fecha, boolean anteriores) {
        try {
            if (anteriores) {
                ps = cn.prepareStatement("Select idCita,nombreDoc as 'Doctor',nomPac as 'Paciente',fecha as 'Fecha',turnoPaciente as 'Turno Paciente', Turno,Estado from ConsultaCitas "
                        + "where fecha <='" + fecha + "'");
            } else {
                ps = cn.prepareStatement("Select idCita,nombreDoc as 'Doctor',nomPac as 'Paciente',fecha as 'Fecha',turnoPaciente as 'Turno Paciente', Turno,Estado from ConsultaCitas "
                        + "where fecha='" + fecha + "'");
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    @SuppressWarnings("UseSpecificCatch")
    public int siguienteNumeroDeTurno (String IDdoctor,String fechaCita, String turnoCita){
        int turno=0;
        try {
            ps = cn.prepareStatement("Select isnull (Max(turnoPaciente),0) as 'TurnoPac' "
                    + "from tblCitas "
                    + "where idDoctor='" + IDdoctor + "' and fecha= '"+fechaCita+"' "
                    + "and turno='"+turnoCita+"'");
            rs = ps.executeQuery();
            rs.next();
            turno=rs.getInt(1)+1;
            this.cerrarConeccion();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return turno;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void cambiarEstadoCita (String idCita,String noEstado){
        try{
            Statement st=cn.createStatement();
            st.executeUpdate("update tblCitas set estado="+noEstado+" "
                    + "where idCita="+idCita);
            this.cerrarConeccion();
            JOptionPane.showMessageDialog(null, "Se ha logrado Modifiar el estado de la Cita");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public int cantDeCitasEnUnDiaPorUnPac (String idPac,String fecha){
        int cant=0;
        try{
            ps=cn.prepareStatement ("select count(*) as 'cantidad' from tblCitas as ci "
                    + "where ci.idPac="+idPac+" and ci.fecha='"+fecha+"'");
            rs=ps.executeQuery();
            rs.next();
            cant=rs.getInt(1);
            this.cerrarConeccion();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return cant;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void SPInsertarCita (String [] datos){
        try{
            cs=cn.prepareCall("{call SpCitas (@ACCION=3,@idPac="+datos[0]+",@iDoc="+datos[1]+",@fechaCita='"+datos[2]+"',@turnoCita='"+datos[3]+"',@turnoPac="+datos[4]+",@estatusCita="+datos[5]+")}");
            cs.execute();
            cerrarConeccion();
            JOptionPane.showMessageDialog(null, "Se ha podido insertar correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void SPCambiarEstadoCita(String idCita) {
        try {
            cs=cn.prepareCall("{call SpCitas (@ACCION=5,@idCita="+idCita+")}");
            cs.execute();
            this.cerrarConeccion();
            JOptionPane.showMessageDialog(null, "Se ha logrado Modifiar el estado de la Cita");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
//        @SuppressWarnings("UseSpecificCatch")
//    public ResultSet SPMostrarTablaCita (){
//        try{
//            cs=cn.prepareCall("{Call SpCitas(@Accion=1)}");
//            rs=cs.executeQuery();
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return rs;
//    }
//
//    @SuppressWarnings("UseSpecificCatch")
//    public ResultSet SPMostrarVistaTablaCita(String fecha) {
//        try {
//            cs = cn.prepareCall("{Call SpCitas(@Accion=4,@fechaCita='"+fecha+"')}");
//            rs = cs.executeQuery();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return rs;
//    }
}
