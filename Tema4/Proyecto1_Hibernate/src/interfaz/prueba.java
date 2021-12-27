package interfaz;

import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import primero.SessionFactoryUtil;

public class prueba {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduce el numero de departamento: ");
		int dept = teclado.nextInt();
		
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		String consulta = "update Empleados e set salario = salario * 1.5 where e.departamentos.deptNo = :dept";
		Query query = session.createQuery(consulta);
		query.setInteger("dept", dept);
		query.executeUpdate();
		
		//System.out.println("Filas modificadas: " + modif);
		
		tx.commit();
		session.close();
		
	}

}
