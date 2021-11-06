package sesion01_Ejercicio03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoDB {
	
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "baloncesto";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";
	
	private static Connection conexion;
	
	public void conecta() {
		
		try {
			
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, username, password);
		} 
		
		catch (ClassNotFoundException e) {
			
			System.out.println("No se ha encontrado la clase.");
			System.out.println(e.getMessage());
		} 
		
		catch (SQLException e) {
			
			System.out.println("No se ha podido conectar a la base de datos.");
			System.out.println(e.getMessage());
		}
	
	}
	
	public void desconectar() {
		
		if (conexion != null) {
			
			try {
				conexion.close();
			} 
			
			catch (SQLException e) {
				
				System.out.println("No se ha podido realizar la desconexion a la base de datos");
				System.out.println(e.getMessage());
			}
		}
	}

	public ResultSet buscarPorLocalidad(String localidad) {
		
		try {
			
			PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM SOCIO WHERE localidad = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			consulta.setString(1, localidad);
			
			ResultSet resultado = consulta.executeQuery();
				
			return resultado;
			
		} 
		
		catch (SQLException e) {
			
			System.out.println("No se ha podido realizar la consulta.");
			System.out.println(e.getMessage());
		}
		
		return null;
	}

}
