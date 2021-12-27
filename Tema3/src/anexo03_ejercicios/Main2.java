package anexo03_ejercicios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main2 {
	
	private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String database = "pedidos";
    private static String hostname = "localhost";
    private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database +
									"?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "root";

	public static void main(String[] args) {
		
		
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(url, username, password);
			
			Scanner teclado = new Scanner(System.in);
			
			System.out.println("Introduce el número de la categoría: ");
			int categoria = teclado.nextInt();
			
			CallableStatement consulta = conexion.prepareCall("CALL productosCategoriaBajoMinimos(?)");
			consulta.setInt(1, categoria);
			
			ResultSet resultado = consulta.executeQuery();
			
			if (resultado.next()) {
				
				System.out.println(" Nombre producto - Precio - Existencias - Minimo ");
				System.out.println("-------------------------------------------------");
				System.out.println(resultado.getString(1) + " | " + resultado.getInt(2) + " | " + resultado.getInt(3) + " | " + resultado.getInt(4) + " | ");
				
				while (resultado.next()) {
					
					System.out.println(resultado.getString(1) + " | " + resultado.getInt(2) + " | " + resultado.getInt(3) + " | " + resultado.getInt(4) + " | ");
				}
			}
			
			else {
				
				System.out.println("La categoria " + categoria + " no existe o no tiene productos bajo mínimos." );
			}
		} 
		
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
