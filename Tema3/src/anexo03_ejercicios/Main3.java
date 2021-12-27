package anexo03_ejercicios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Type;

public class Main3 {
	
	private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String database = "pedidos";
    private static String hostname = "localhost";
    private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database +
									"?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "root";

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		try {
			
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(url, username, password);
			
			System.out.println("Introduce el codigo de la categoria: ");
			int categoria = teclado.nextInt();
			
			System.out.println("Introduce la cantidad que deseas aumentar: ");
			int cantidad = teclado.nextInt();
			
			CallableStatement consulta = conexion.prepareCall("CALL incrementarPrecioCategoria(?, ?, ?)");
			consulta.setInt(1, categoria);
			consulta.setInt(2, cantidad);
			consulta.registerOutParameter(3, Types.NUMERIC);
			
			consulta.execute();
			
			int resul = consulta.getInt(3);
			

			switch (resul) {
			
				case -1: System.out.println("No existe ningun producto conn la categoria " + categoria);
						 break;
						
				case 0: System.out.println("No se ha actualizado ningun producto de la caterogria " + categoria);
						break;
						
				default: System.out.println("Se ha aumentado el precio un " + cantidad +"%" + " para " + resul + " de la categoria -> " + categoria );
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
