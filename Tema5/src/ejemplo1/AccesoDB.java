package ejemplo1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class AccesoDB {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	
	public void demoJPQL() {
		
		//Ejercicio1
		
		/*TypedQuery<EmpleadoEntity> q = em.createQuery("SELECT emp FROM EmpleadoEntity emp", EmpleadoEntity.class);
		List<EmpleadoEntity> lista = q.getResultList();
		
		for (EmpleadoEntity emp : lista) {
			
			System.out.println(emp.getNombre() + " - " + emp.getAlta());
		}*/
		
		//Ejercicio2
		
		/*TypedQuery<Object[]> q = em.createQuery("SELECT nombre, alta FROM EmpleadoEntity WHERE upper(nombre) LIKE upper('%carrera%')", Object[].class);
		List<Object[]> lista = q.getResultList();
		
		for (Object[] o : lista) {
			
			System.out.println(o[0] + " - " + o[1]);
		}*/
		
		//Ejercicio3
		
		/*TypedQuery<EmpleadoEntity> q = em.createQuery("SELECT emp FROM EmpleadoEntity emp WHERE departamento.nombre = 'I+D' AND emp.oficio = 'Empleado'", EmpleadoEntity.class);
		List<EmpleadoEntity> lista = q.getResultList();
		
		for(EmpleadoEntity emp : lista) {
			
			System.out.println(emp.getNombre() + " - " + emp.getOficio() + " - " + emp.getDepartamento().getNombre());
		}*/
		
		//Ejercicio4
		
		/*TypedQuery<EmpleadoEntity> q = em.createQuery("SELECT emp FROM EmpleadoEntity emp WHERE year(emp.alta) > 2002", EmpleadoEntity.class);
		List<EmpleadoEntity> lista = q.getResultList();
		
		for (EmpleadoEntity emp : lista) {
			
			System.out.println(emp.getNombre() + " - " + emp.getAlta());
		}*/
		
		//Ejercicio5
		
		/*TypedQuery<EmpleadoEntity> q = em.createQuery("SELECT emp FROM EmpleadoEntity emp ORDER BY departamento.nombre", EmpleadoEntity.class);
		List<EmpleadoEntity> lista = q.getResultList();
		
		for (EmpleadoEntity emp : lista) {
			
			System.out.println(emp.getDepartamento() + " - " + emp.getNombre());	
		}*/
		
		//Ejercicio6
		
		/*TypedQuery<Object[]> q = em.createQuery("SELECT departamento.nombre, count(empnoId), sum(salario), max(salario) FROM EmpleadoEntity WHERE departamento IS NOT NULL GROUP BY departamento.nombre", Object[].class);
		List<Object[]> lista = q.getResultList();
		
		for (Object[] o : lista) {
			
			System.out.println(o[0] + " - " + o[1] + " - " + o[2] + " - " + o[3]);
		}*/
		
		//Ejercicio7
		
		
		
	}
}
