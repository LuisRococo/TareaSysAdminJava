package Coneccion;
import java.sql.*;
import javax.swing.JOptionPane;

public class DBConexionSingleton {
    
    private static Connection cn=null;
    
    private static final String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver"; //driver para usar (hace referencia a la libreria que acabamos de descargar)
    private static final String CADENA="jdbc:sqlserver://10.21.12.34\\SQLEXPRESS_W7:1433;databaseName=BDClinica";
    private static final String CADENA_HOME="jdbc:sqlserver://DESKTOP-4SGIQQI\\SQLEXPRESS:1433;databaseName=BDClinica";
    private static final String CADENA_LOCAL="jdbc:sqlserver://DESKTOP-LHJM6HT\\SQLEXPRESS:1433;databaseName=BDClinica";
    
    private static final String USUARIO="Medico";
    private static final String PASSWORD="Medico123";
    
    private DBConexionSingleton (){}
    
    @SuppressWarnings("UseSpecificCatch")
    public static Connection getBDConeccion (){
        try{
            cn=null;
            Class.forName(DRIVER);
            cn=DriverManager.getConnection(CADENA_LOCAL, USUARIO, PASSWORD);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return cn;
    }
}
