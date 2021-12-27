package anexo03_ejercicios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main1 {

	private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String database = "pedidos";
    private static String hostname = "localhost";
    private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "root";
	
	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		try {
			
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(url, username, password);
			
			System.out.println("Introduce el número de pedido que deseas consultar: ");
			int pedido = teclado.nextInt();
			
			CallableStatement consulta = conexion.prepareCall("SELECT importePedido(?)");
			consulta.setInt(1, pedido);
			
			ResultSet resultado = consulta.executeQuery();
			
			if (resultado.next()) {
				
				if (resultado.getDouble(1) != -1) {
					
					System.out.println("El total del pedido " + pedido + " es de: " + resultado.getDouble(1));
				}
				
				else {
					
					System.out.println("El pedido con número " + pedido + " no existe.");
				}
				
				resultado.close();
				consulta.close();
				conexion.close();
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
