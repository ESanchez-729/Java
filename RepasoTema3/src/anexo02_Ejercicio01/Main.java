package anexo02_Ejercicio01;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		AccesoDB db = new AccesoDB();
		ArrayList<Empleado> lista;

		db.conectar();
		
		//apartado C
		//System.out.println(db.busquedaPorCodigo(17));
		
		//apartado D
		//System.out.println(db.busquedaPorOficio("Profe"));
		
		//apartado Dv2
		//se puede simplificar usando el metodo en vez del array list en el foreach
		/*lista = db.busquedaPorOficio("CLERK");
		
		for (Empleado emp : lista) {
			
			System.out.println(emp);
		}*/
		
		
		//apartado E
		//System.out.println("Empleados contratados antes del 22 de Febrero de 1981");
		//System.out.println("=====================================================");
		java.util.Date fecha = null;
		java.util.Date fecha2 = null;
		java.sql.Date sqlFecha = null;
		java.sql.Date sqlFecha2 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			
			fecha = sdf.parse("1981/02/22");
			sqlFecha = new java.sql.Date(fecha.getTime());
			fecha2 = sdf.parse("2001/03/21");
			sqlFecha2 = new java.sql.Date(fecha2.getTime());	
		}
		
		catch (ParseException p) {
			
			System.out.println("Error al convertir la fecha");
		}
		
		/*lista = db.busquedaPorAntiguedad(sqlFecha);
		for (Empleado emp : lista) {
			
			System.out.println(emp);
		}*/
		
		//apartado F
		//Empleado e = new Empleado(287, "Joze Lui", "Quesero", 7839, sqlFecha2, 1400.00, 780.40, 30);
		//System.out.println(db.insertarConBean(e));
		
		//apartado I
		//System.out.println(db.actualizarSalario(10, 1.7));
		
		//apartado J
		//System.out.println(db.borrarEmpleado(14));

	}

}
