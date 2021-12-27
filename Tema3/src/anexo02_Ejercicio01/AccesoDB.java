package anexo02_Ejercicio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccesoDB {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "demodb";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";
	private static Connection conexion;
	
	
	public void conectar() {
		
			try {
				Class.forName(driver);
				conexion = DriverManager.getConnection(url, username, password);
			} 
			
			catch (ClassNotFoundException e) {
				
				System.out.println("Clase no encontrada.");
			} 
			
			catch (SQLException e) {
				
				System.out.println("No se ha podido conectar a la base de datos.");
			}
			
	}
	
	public void desconectar() {
		
		if (conexion != null) {
			
			try {
				
				conexion.close();
			} 
			
			catch (SQLException e) {
				
				System.out.println("No se ha podido realizar la desconexion a la base de datos.");
			}
		}
	}
	
	public Empleado busquedaPorCodigo (int numero) {
		
		try {
			
			Empleado emp = new Empleado();
			PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM emp WHERE empno = ?");
			consulta.setInt(1, numero);
			
			ResultSet resultado = consulta.executeQuery();
			
			if (resultado.next()) {
				
				emp.setEmp_no(resultado.getInt(1));
				emp.setEname(resultado.getString(2));
				emp.setJob(resultado.getString(3));
				emp.setMgr(resultado.getInt(4));
				emp.setHiredate(resultado.getDate(5));
				emp.setSal(resultado.getDouble(6));
				emp.setComm(resultado.getDouble(7));
				emp.setDept_no(resultado.getInt(8));
				
				return emp;
				
			}
			
			return null;
			
		} 
		
		catch (SQLException e) {
			
			System.out.println(e.getMessage());
			return null;
		}
		
		
		
	}

	public ArrayList<Empleado> busquedaPorOficio (String oficio) {
		
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		
		
		PreparedStatement consulta;
		try {
			consulta = conexion.prepareStatement("SELECT * FROM emp WHERE job = ?");
			consulta.setString(1, oficio);
			
			ResultSet resultado = consulta.executeQuery();
			
			while (resultado.next()) {
				
				Empleado emp = new Empleado();
				emp.setEmp_no(resultado.getInt(1));
				emp.setEname(resultado.getString(2));
				emp.setJob(resultado.getString(3));
				emp.setMgr(resultado.getInt(4));
				emp.setHiredate(resultado.getDate(5));
				emp.setSal(resultado.getDouble(6));
				emp.setComm(resultado.getDouble(7));
				emp.setDept_no(resultado.getInt(8));
				lista.add(emp);
			}
			
			return lista;
			
			
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Empleado> busquedaPorAntiguedad (java.sql.Date fecha) {
		
		try {
			
			
			ArrayList<Empleado> lista = new ArrayList<Empleado>();
			
			PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM emp WHERE hiredate <= ?");
			consulta.setDate(1, fecha);
			
			ResultSet resultado = consulta.executeQuery();
			
			while (resultado.next()) {
				
				Empleado emp = new Empleado();
				emp.setEmp_no(resultado.getInt(1));
				emp.setEname(resultado.getString(2));
				emp.setJob(resultado.getString(3));
				emp.setMgr(resultado.getInt(4));
				emp.setHiredate(resultado.getDate(5));
				emp.setSal(resultado.getDouble(6));
				emp.setComm(resultado.getDouble(7));
				emp.setDept_no(resultado.getInt(8));
				lista.add(emp);
			}
			
			return lista;
			
			
		} 
		
		catch (SQLException e) {

			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public int insertarConBean(Empleado emp) {
			
		try {
			
			int validar = 0;
			
			PreparedStatement consulta = conexion.prepareStatement("INSERT INTO emp VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			consulta.setInt(1, emp.getEmp_no());
			consulta.setString(2, emp.getEname());
			consulta.setString(3, emp.getJob());
			consulta.setInt(4, emp.getMgr());
			consulta.setDate(5, emp.getHiredate());
			consulta.setDouble(6, emp.getSal());
			consulta.setDouble(7, emp.getComm());
			consulta.setInt(8, emp.getDept_no());
			
			validar = consulta.executeUpdate();
	
			return validar;	
		} 
		
		catch (SQLException e) {
			
			return e.getErrorCode();
	
		}
		
		
	}

	public int actualizarSalario(int departamento, double porcentaje) {
		
		try {
			
			int validar = 0;
			conexion.setAutoCommit(false);
			PreparedStatement consulta = conexion.prepareStatement("UPDATE emp SET SAL = SAL * ? WHERE deptno = ?");
			consulta.setDouble(1, porcentaje);
			consulta.setInt(2, departamento);
			
			validar = consulta.executeUpdate();
			
			if (validar == 0) {
				
				System.out.println("No existe el departamento " + departamento);
			}

			conexion.commit();
			return validar;
		} 
		
		catch (SQLException e) {
			
			return e.getErrorCode();
		}
		
	}
	
	public int borrarEmpleado (int numero) {
		
		try {
			
			int validar = 0;
			conexion.setAutoCommit(false);
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM emp WHERE EMPNO = ?");
			consulta.setInt(1, numero);
			
			validar = consulta.executeUpdate();
			
			if (validar == 0) {
				
				System.out.println("El empleado con número " + numero+ " no existe.");
			}
			
			conexion.commit();
			
			if (validar != 0) {
				
				System.out.println("Se ha borrado el empleado con el código " + numero + " de la tabla.");
			}
			return validar;
			
			
		} 
		
		catch (SQLException e) {
			
			return e.getErrorCode();
		}
	}

}




























