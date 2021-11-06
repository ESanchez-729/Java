package sesion01_Ejercicio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoBD {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "baloncesto";
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
	
	public void consultaPorLocalidad(String localidad) {
		
		try {
			
			PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM SOCIO WHERE localidad = ?", ResultSet.TYPE_SCROLL_SENSITIVE, 
																											  ResultSet.CONCUR_UPDATABLE);
			consulta.setString(1, localidad);
			
			ResultSet resultado = consulta.executeQuery();
			obtenerDatos(resultado);
		} 
		
		catch (SQLException e) {
			
			System.out.println("No se ha podido realizar la consulta.");
			System.out.println(e.getMessage());
		}
		
	}
	
	public void obtenerDatos(ResultSet resul) {
		
		try {
			
			Socio s = new Socio();
			
			while (resul.next()) {
				
				s.setSocioID(resul.getInt("socioID"));
				s.setNombre(resul.getString("nombre"));
				s.setEstatura(resul.getInt("estatura"));
				s.setEdad(resul.getInt("edad"));
				s.setLocalidad(resul.getString("localidad"));
				
				System.out.println(s.toString());
			}
			
			resul.last();
			System.out.println("========================");
			System.out.println("Total de filas: " + resul.getRow());
			
			resul.close();
			
		} 
		
		catch (SQLException e) {
			
			System.out.println("No se han podido obtener los datos del ResultSet");
			System.out.println(e.getMessage());
		}
	}
}
