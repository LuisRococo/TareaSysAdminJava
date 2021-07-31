
package Coneccion;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BackUp {
    
    private final String rutaHome="C:\\Program Files\\Microsoft SQL Server\\MSSQL11.SQLEXPRESS\\MSSQL\\Backup\\BDClinica.bak";
    private final String rutaLocal="C:\\Program Files\\Microsoft SQL Server\\MSSQL12.MSSQLSERVER\\MSSQL\\Backup\\BDClinica.bak";
    
    private final Connection cn=DBConexionSingleton.getBDConeccion();
    
//    private final ConeccionBD conn = new ConeccionBD();
//    private final Connection cn = conn.coneccion();
    
    @SuppressWarnings("UseSpecificCatch")
    public void realizarBackUp (){
        try{
            Statement st=cn.createStatement();
            st.executeUpdate("BACKUP DATABASE [BDClinica] TO DISK = '"+rutaLocal+"' WITH NOFORMAT, INIT, NAME = 'BD-Completa Base de datos Copia de seguridad', SKIP, NOREWIND, NOUNLOAD, STATS = 10");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
