package sesion01_Ejercicio02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoDB {

	
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "sample";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";
	
	private static Connection conexion;
	
	public void conectar()  {
		
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, username, password);
		} 
		
		catch (ClassNotFoundException e) {
			
			System.out.println("No se ha podido encontrar la clase.");
			System.out.println(e.getMessage());
		} 
		
		catch (SQLException e) {
			
			System.out.println("No se ha podido conectar a la base de datos.");
			System.out.println(e.getMessage());
		}
		
	}
	
	public void desconectar()  {
		
		if (conexion != null) { 
			
			try {
				
				conexion.close();
			} 
			
			catch (SQLException e) {
				
				System.out.println("No se ha podido desconectar de la base de datos.");
				System.out.println(e.getMessage());
			} 
		}
	}
	
	public boolean validar (String usuario, String contraseña) {
		
		
		
		try {
			
			PreparedStatement consulta = conexion.prepareStatement("SELECT username, password FROM USUARIO WHERE username = ? AND password = ?");
			consulta.setString(1, usuario);
			consulta.setString(2, contraseña);
			
			ResultSet resultado = consulta.executeQuery();
			
			if (resultado.next()) {
				
				return true;
			}
			
		} 
		
		catch (SQLException e) {
			
			System.out.println("La consulta no se ha podido realizar.");
			System.out.println(e.getMessage());
		}
		
		
		return false;
		
		
	}
}
