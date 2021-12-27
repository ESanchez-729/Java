package ejercicios;

import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.SessionFactoryUtil;

public class Estadisticas {
	
	static SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	static Session session;

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduce el código del jugador que deseas consultar: ");
		int codigo = teclado.nextInt();
		
		try {
			
			session = sesion.openSession();
			
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
