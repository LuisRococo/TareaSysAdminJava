
package Coneccion;
import java.sql.*;
import javax.swing.JOptionPane;

public class ConeccionBD {
    
    Connection cn;
    public String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; //driver para usar (hace referencia a la libreria que acabamos de descargar)
    
    public String cadena="jdbc:sqlserver://10.21.12.34\\SQLEXPRESS_W7:1433;databaseName=BDClinica";
    public String cadenaHome="jdbc:sqlserver://DESKTOP-4SGIQQI\\SQLEXPRESS:1433;databaseName=BDClinica";
    public String cadenaLocal="jdbc:sqlserver://DESKTOP-LHJM6HT\\SQLEXPRESS:1433;databaseName=BDClinica";
    
    public String usuario="Medico";
    public String password="Medico123";
    
    @SuppressWarnings("UseSpecificCatch")
    public Connection coneccion (){
        try{
            Class.forName(driver);
            cn=DriverManager.getConnection(cadenaHome,usuario,password);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return cn;
    }
    
    /*
    create table tblConsulta (
	idConsulta int primary key identity not null,
	idPac int foreign key references tblPaciente (IdPac) not null,
	idDoctor int foreign key references tblDoctor (idDoc) not null,
	fechaCita datetime not null,
	diagnostico varchar (50) not null,
	motivo varchar (50) not null
)
    */
    
    /*
ALTER procedure [dbo].[SpUsuariosDoctores] 
@ACCION int =null,
@Contraseña varchar (20) =null,
@Usuario varchar(20) =null,
@idDoc int = null,
@idPac int=null,
@fecha datetime =null,
@idCita int=null
as

if @ACCION=1 --Insertar Usuario Doctor
begin
	insert into tblUsuariosDoctores values (@idDoc,@Usuario,@Contraseña)
end

if @ACCION=2 --Comprobar Inicio Sesion Doctores
begin
	select * from tblUsuariosDoctores
	where idUsuario=@Usuario and contraseña=@Contraseña
end

if @ACCION=3 --Vista Citas de un doctor
begin
	select c.nomPac as 'Paciente',c.turnoPaciente as 'Turno Paciente',c.Estado,c.idCita from citasDeDoctor as c
	where idDoctor=@idDoc and fecha=@fecha
end
IF @ACCION=4 --AGARRAR DIAGNOSTICO, ID CONSULTA Y MOTIVO POR MEDIO DE DATOS DE CITA
begin
	select c.idConsulta,c.diagnostico,c.motivo from tblConsulta as C
	where c.idDoctor=@idDoc and c.idPac=@idPac and c.fechaCita=@fecha
end
if @ACCION=5 --Obtener idPac y fecha
begin
	select c.idPac,c.fecha from tblCitas as c
	where c.idCita=@idCita
end
     */
    
    /*
        ALTER procedure [dbo].[SpConsultas]
@ACCION int=null,
@idCita int =null,
@idConsulta int =null,
@idDoc int =null,
@idPac int =null,
@fechaCita datetime =null,
@diagnostico varchar (50)=null,
@motivo varchar(50)=null
as
if @ACCION=1 --CONSULTA
begin
	select A.IdPac,A.nomPac,A.direPac,A.telPac,B.nombreDoc,Convert (varchar,C.fecha,101) as 'Fecha' from tblPaciente as A,tblDoctor as B,tblCitas as C--Ese conver es para darle formato a la fecha (Que no se muestre los min,sec,horas)
	where A.IdPac=C.idPac and B.idDoc=C.idDoctor and C.idCita=@idCita
end
if @ACCION=2 --AGREGAR CONSULTA A TBL_CONSULTAS
begin
	insert into tblConsulta values (@idPac,@idDoc,@fechaCita,@diagnostico,@motivo)
end
if @ACCION=3 --FECHA (NO USADO)
begin
	select c.fecha from tblCitas as c
	where c.idCita=@idCita
end
if @ACCION=4
begin
	update tblConsulta
	set diagnostico=@diagnostico, motivo=@motivo
	where idConsulta=@idConsulta
end
    */
}
