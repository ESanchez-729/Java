package sesion01_Ejercicio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaSociosBaloncesto {

	private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String database = "baloncesto";
    private static String hostname = "localhost";
    private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "root";
	
	public static void main(String[] args) {
		
		try {
			
			//Establecemos conexion a la base de datos
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			
			//Creamos el statement (consulta) y el ResultSet (resultado de ejecutar la consulta)
			
			try {
				PreparedStatement consulta = conn.prepareStatement("SELECT * from SOCIO");
				ResultSet resultado = consulta.executeQuery();
				
				System.out.println("LISTA DE SOCIOS");
	    		System.out.println("============================");
	    		
	    		int contadorTotal = 0;
	    		
	    		
	    		//Vamos obteniendo cada fila de datos que obtenemos del resultset
	    		while (resultado.next()) {
	    			
	    			int socioID = resultado.getInt("socioID");
	    			String nombre = resultado.getString("nombre");
	    			int estatura = resultado.getInt("estatura");
	    			int edad = resultado.getInt("edad");
	    			String localidad = resultado.getString("localidad");
	    			contadorTotal ++;
	    			
	    			System.out.println("ID: " + socioID + "|" + "Nombre: " + nombre + "|" + "Estatura: " + estatura + "|" + "Edad: " + edad + "|" + "Localidad: " + localidad);
	    			
	    		}
	    		
	    		System.out.println("==================================");
	    		System.out.println("Total de socios: " + contadorTotal);
			}
			
			catch (SQLException e){
				
				System.out.println(e.getMessage());
			}
		} 
		
		catch (ClassNotFoundException e) {
			
			System.out.println("No se ha encontrado la clase.");
			
		}
		
		catch (SQLException e) {
			
			System.out.println("No se ha podido realizar la conexión. Verifique los datos e inténtelo de nuevo.");
		}
	}
}
