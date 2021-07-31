
package UsuariosDoctores;

import Coneccion.DBConexionSingleton;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DatosUsuariosDoc {
    
    ResultSet rs;
    PreparedStatement ps;
    private CallableStatement cs; 
    private final Connection cn=DBConexionSingleton.getBDConeccion();
    
    @SuppressWarnings("UseSpecificCatch")
    public boolean insertarUsuarioDoc  (String datos[]){
        try{
            cs=cn.prepareCall("{call [SpUsuariosDoctores] (@ACCION=1,@idDoc="+datos[0]+",@Usuario='"+datos[1]+"',@Contraseña='"+datos[2]+"')}");
            cs.execute();
            this.cerrarConeccion();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public ResultSet inicioSesion  (String datos[]){
        this.rs=null;
        try{
            cs=cn.prepareCall("{call [SpUsuariosDoctores] (@ACCION=2,@Usuario='"+datos[0]+"',@Contraseña='"+datos[1]+"')}");
            rs=cs.executeQuery();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void cerrarConeccion (){
        try{
            cn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public ResultSet tablaCitas (String datos []){
        rs=null;
        try{
            cs=cn.prepareCall("{call SpUsuariosDoctores (@ACCION=3,@idDoc="+datos[0]+",@fecha='"+datos[1]+"')}");
            rs=cs.executeQuery();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    
        @SuppressWarnings("UseSpecificCatch")
    public ResultSet obtenerDatosConsulta (String datos []){
        rs=null;
        try{
            cs=cn.prepareCall("{call SpUsuariosDoctores (@ACCION=4,@idDoc="+datos[0]+",@idPac="+datos[1]+",@fecha='"+datos[2]+"')}");
            rs=cs.executeQuery();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }

    @SuppressWarnings("UseSpecificCatch")
    public ResultSet obtenerIdDoc(String idCita) {
        rs = null;
        try {
            cs = cn.prepareCall("{call SpUsuariosDoctores (@ACCION=5,@idCita=" + idCita +")}");
            rs = cs.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
}
