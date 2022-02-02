package ejemplo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class AccesoBdatos {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public void conectar() {
		emf = Persistence.createEntityManagerFactory("db/empleados.odb");
		em = emf.createEntityManager();
	}
	public void desconectar() {
		em.close();
		emf.close();
	}
	
	public DepartamentoEntity buscarDepartamento(int numDepartamento) {
		
		return em.find(DepartamentoEntity.class, numDepartamento);
	}// de método buscarDepartamento
	//
	
	@SuppressWarnings("deprecation")
	public void imprimirDepartamento (int numDepartamento) {
		
		DepartamentoEntity d = buscarDepartamento(numDepartamento);
		
		if (d==null)
			
			System.out.println("No existe el Departamento " + numDepartamento);
		
		else {
			
			Set <EmpleadoEntity> empleados = d.getEmpleados();
			
			String datos="Datos del departamento " + numDepartamento + ": ";
			datos += "Nombre: " + d.getNombre() + " - Localidad: " + d.getLocalidad()+ "\n";
			
			if (empleados.isEmpty()) 
				
				datos+="No tiene empleados en este momento";
			
			else {			
				
				datos+="Lista de empleados"+ "\n";
				datos+="*******************";
			}
			
			for (EmpleadoEntity empleado :empleados) {
				
				datos+= "\nNúmero de empleado: " + empleado.getEmpnoId()+ "\n";
				datos+= "Nombre: " + empleado.getNombre()+ "\n";
				datos+= "Oficio: " + empleado.getOficio()+ "\n";
				
				if (empleado.getDirId()==null) 
					
					datos+= "Jefe: No tiene"+ "\n";
				
				else 
					
					datos+= "Jefe: "+ empleado.getDirId().getNombre()+ "\n";
				
				datos+= "Año de alta: " + (empleado.getAlta().getYear()+1900)+ "\n";	
				datos+= "Salario: "+ empleado.getSalario()+ "\n";
				
				if (empleado.getComision() ==null)
					
					datos+= "Comisión: No tiene"+ "\n";
				
				else
					
					datos+= "Comisión: "+ empleado.getComision()+ "\n";
			}
			
			System.out.println(datos);
		}
	} // de método imprimirDepartamento
	
	public boolean insertarDepartamento (DepartamentoEntity d) {
		if (buscarDepartamento(d.getDptoId())!=null)
			return false;
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
		return true;
	} // de insertarDepartamento
	
	public boolean modificarDepartamento (DepartamentoEntity d) {
		DepartamentoEntity departamentoBuscado=buscarDepartamento(d.getDptoId());
		if (departamentoBuscado==null)
			return false;
		em.getTransaction().begin();
		departamentoBuscado.setNombre(d.getNombre());
		departamentoBuscado.setLocalidad(d.getLocalidad());
		em.persist (departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento
	
	// Si tiene empleados lo borraría igual, dejando en estos el número de Departamento
	// pero el resto de los atributos del departamento a null. Por eso no dejo borrar
	
	public boolean borrarDepartamentos  (int numDepartamento) {
		DepartamentoEntity departamentoBuscado=buscarDepartamento(numDepartamento);
		if (departamentoBuscado==null || !departamentoBuscado.getEmpleados().isEmpty() )
			return false;
		em.getTransaction().begin();
		em.remove(departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento
	
	public void demoJPQL() {
		
		/*Query q1 = em.createQuery("SELECT COUNT(d) FROM DepartamentoEntity d");
        System.out.println("Total Departamentos: " + q1.getSingleResult());
        //
        TypedQuery<Long> tq1 = em.createQuery(
        	      "SELECT COUNT(d) FROM DepartamentoEntity d", Long.class);
        System.out.println("Total Departamentos: " + tq1.getSingleResult());
        //
        TypedQuery<DepartamentoEntity>tq2 =
	            em.createQuery("SELECT d FROM DepartamentoEntity d", DepartamentoEntity.class);
	        List<DepartamentoEntity> l2 = tq2.getResultList();
	        for (DepartamentoEntity r2 : l2) {
	            System.out.println("Nombre :  " + r2.getNombre()+ ", Localidad: "+ r2.getLocalidad());
	        }
	    //
        TypedQuery<Object[]>tq3 =
	            em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity  d", Object[].class);
	        List<Object[]> l3 = tq3.getResultList();
	        for (Object[] r3 : l3) {
	            System.out.println(
	            "Nombre :  " + r3[0] + ", Localidad: " + r3[1]);
	    }    
	    //
	      TypedQuery<Object[]>tq4 =
		            em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity d"
		            		+ " WHERE d.dptoId != :n", Object[].class);
	        		tq4.setParameter("n", 10);
		        List<Object[]> l4 = tq4.getResultList();
		        for (Object[] r4 : l4) {
		            System.out.println(
		            "Nombre :  " + r4[0] + ", Localidad: " + r4[1]);
		    }*/ 
		
		
		//================================================================================================================================
		
		//EJERCICIO 1
		/*TypedQuery<EmpleadoEntity> consulta = em.createQuery("SELECT jupiter FROM EmpleadoEntity jupiter", EmpleadoEntity.class);
		List<EmpleadoEntity> listaEmpleados = consulta.getResultList();
		
		for(EmpleadoEntity emp : listaEmpleados) {
			
			System.out.println(emp.getNombre() + " - " + emp.getAlta());
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 1 v2
		/*TypedQuery<Object[]> consultaObjeto = em.createQuery("SELECT emp.nombre, emp.alta FROM EmpleadoEntity emp", Object[].class);
		List<Object[]> listaEmps = consultaObjeto.getResultList();
		
		for(Object[] obj : listaEmps) {
			
			for(Object o : obj) {
				
				System.out.print( " - " + o);
			}
		
			System.out.println("");
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 2
		/*TypedQuery<EmpleadoEntity> consultaEmpCarrera = em.createQuery("SELECT emp FROM EmpleadoEntity emp WHERE emp.nombre LIKE '%Carrera%'", EmpleadoEntity.class);
		List<EmpleadoEntity> listEmp = consultaEmpCarrera.getResultList();
		
		for(EmpleadoEntity emp : listEmp) {
			
			System.out.println(emp.getNombre() + " - " + emp.getAlta());
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 3
		/*TypedQuery<EmpleadoEntity> consultaDepartamento = em.createQuery("SELECT emp FROM EmpleadoEntity emp WHERE emp.oficio = 'Empleado' AND emp.departamento.nombre = 'I+D'", EmpleadoEntity.class);
		List<EmpleadoEntity> listEmpDep = consultaDepartamento.getResultList();
		
		for(EmpleadoEntity emp : listEmpDep) {
			
			System.out.println(emp.getNombre() + " - " + emp.getOficio() + " - " + emp.getDepartamento().getNombre());
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 4
		/*TypedQuery<EmpleadoEntity> consultaPorAno = em.createQuery("SELECT emp FROM EmpleadoEntity emp WHERE year(emp.alta) > 2002", EmpleadoEntity.class);
		List<EmpleadoEntity> listAlta = consultaPorAno.getResultList();
		
		for(EmpleadoEntity emp : listAlta) {
			
			System.out.println(emp.getNombre() + " - " + emp.getAlta());
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 5
		/*TypedQuery<EmpleadoEntity> consultaOrdenada = em.createQuery("SELECT emp FROM EmpleadoEntity emp ORER BY emp.departamento.nombre", EmpleadoEntity.class);
		List<EmpleadoEntity> listOrdenada = consultaOrdenada.getResultList();
		
		for(EmpleadoEntity emp : listOrdenada) {
			
			System.out.println(emp.getDepartamento().getNombre() + " - " + emp.getNombre());
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 6
		/*TypedQuery<Object[]> consultaAgrupada = em.createQuery("SELECT departamento.nombre, count(emp), sum(salario), max(salario) FROM EmpleadoEntity emp GROUP BY departamento ORDER BY count(emp) DESC", Object[].class);
		List<Object[]> listAgrupada = consultaAgrupada.getResultList();
		
		for(Object[] obj : listAgrupada) {
			
			for(Object o : obj) {
				
				System.out.print( " - " + o);
				
			}
			
			System.out.println("");
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 7
		/*TypedQuery<Object[]> consultaAgrupada = em.createQuery("SELECT departamento.nombre, count(emp), sum(salario), max(salario) FROM EmpleadoEntity emp GROUP BY departamento HAVING count(emp) >= 5 ORDER BY count(emp) DESC", Object[].class);
		List<Object[]> listAgrupada = consultaAgrupada.getResultList();
		
		for(Object[] obj : listAgrupada) {
			
			for(Object o : obj) {
				
				System.out.print( " - " + o);
				
			}
			
			System.out.println("");
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 8
		/*TypedQuery<EmpleadoEntity> consultaJefeEmp = em.createQuery("SELECT emp FROM EmpleadoEntity emp WHERE emp.dirId IS NOT NULL", EmpleadoEntity.class);
		List<EmpleadoEntity> listJefes = consultaJefeEmp.getResultList();
		
		for(EmpleadoEntity jupiter : listJefes) {
			
			System.out.println(jupiter.getNombre() + " // " + " Su jefe es: " + jupiter.getDirId().getNombre() + " // " + " Departamento: " + jupiter.getDepartamento().getDptoId());
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 9
		/*TypedQuery<Object[]> consultaAgrupadaComprobando = em.createQuery("SELECT departamento.nombre, count(emp) FROM EmpleadoEntity emp GROUP BY departamento HAVING count(emp) > 0 ORDER BY count(emp) DESC", Object[].class);
		List<Object[]> listAgrupadaComprobando = consultaAgrupadaComprobando.getResultList();
		
		for(Object[] obj : listAgrupadaComprobando) {
			
			for(Object o : obj) {
				
				System.out.print( " - " + o);
				
			}
			
			System.out.println("");
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 10
		/*TypedQuery<DepartamentoEntity> consultaDepartamentos = em.createQuery("SELECT dep FROM DepartamentoEntity dep", DepartamentoEntity.class);
		List<DepartamentoEntity> listDepartamentos = consultaDepartamentos.getResultList();
		
		for(DepartamentoEntity dep : listDepartamentos) {
			
			System.out.println(dep.getNombre() + " - " + dep.getEmpleados().size());
			
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 11
		/*TypedQuery<Object[]> consultaDESCASC = em.createQuery("SELECT emp.departamento.dptoId, emp.nombre, emp.salario FROM EmpleadoEntity emp ORDER BY departamento.dptoId DESC, emp.salario", Object[].class);
		List<Object[]> listDeCosasPorQueMeAburro = consultaDESCASC.getResultList();
		
		for(Object[] ojete : listDeCosasPorQueMeAburro) {
			
			for(Object ojeto : ojete) {
				
				System.out.print(" - " + ojeto);
			}
			
			System.out.println("");
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 12
		/*TypedQuery<EmpleadoEntity> consultaJefeEmp = em.createQuery("SELECT emp FROM EmpleadoEntity emp WHERE emp.dirId IS NULL", EmpleadoEntity.class);
		List<EmpleadoEntity> listJefes = consultaJefeEmp.getResultList();
		
		for(EmpleadoEntity jupiter : listJefes) {
			
			System.out.println(jupiter.getEmpnoId() + " - " + jupiter.getNombre());
		}*/
		
		//================================================================================================================================
		
		//EJERCICIO 13
		/*TypedQuery<DepartamentoEntity> consultaJefeEmp = em.createQuery("SELECT emp.departamento FROM EmpleadoEntity emp WHERE emp.empnoId = 1039", DepartamentoEntity.class);
		List<DepartamentoEntity> listJefes = consultaJefeEmp.getResultList();
		
		for(DepartamentoEntity saturno : listJefes) {
			
			System.out.println(saturno.getDptoId() + " - " + saturno.getNombre());
		}*/
		
		//Ejercicio10v2
		/*TypedQuery<DepartamentoEntity> cantidubi = em.createQuery("SELECT dep FROM DepartamentoEntity dep", DepartamentoEntity.class);
		List<DepartamentoEntity> hola = cantidubi.getResultList();
		
		for (DepartamentoEntity o : hola) {
			
			System.out.println(o.getNombre() + " - " + o.getEmpleados().size());
		}*/
		
		//Ejercicio11v2
		/*TypedQuery<Object[]> holiwi = em.createQuery("Select departamento.dptoId, nombre, salario FROM EmpleadoEntity ORDER BY departamento.dptoId DESC, salario ASC", Object[].class);
		List<Object[]> lista = holiwi.getResultList();
		
		for (Object[] o : lista) {
			
			System.out.println(o[0] + " - " + o[1] + " - " + o[2]);
		}*/
		
		//Ejercicio12v2
		/*TypedQuery<EmpleadoEntity> howo = em.createQuery("SELECT emp FROM EmpleadoEntity emp WHERE dirId is NULL", EmpleadoEntity.class);
		List<EmpleadoEntity> listowo = howo.getResultList();
		
		for (EmpleadoEntity emp : listowo) {
			
			System.out.println(emp.getEmpnoId() + " - " + emp.getNombre());
		}*/
		
		//Ejercicio13v2
		//TypedQuery<DepartamentoEntity> huwu = em.createQuery("SELECT dep FROM DepartamentoEntity dep WHERE empleados.empnoId = 1039", DepartamentoEntity.class);
		//List<DepartamentoEntity> listuwu = huwu.getResultList();
		
		/*for(DepartamentoEntity dep : listuwu) {
			
			System.out.println(dep.getDptoId() + " - " + dep.getNombre());
		}*/
		
		
	}// de demoJPQL
	
	public int incrementarSalario(int cantidad) {
		
		em.getTransaction().begin();
		Query q = em.createQuery("UPDATE EmpleadoEntity SET salario = salario + :din");
		q.setParameter("din", cantidad);
		int filasModif = q.executeUpdate();
		em.getTransaction().commit();
		
		return filasModif; 
	}
	
	public int incrementarSalarioOficio(String oficio, int cantidad) {
		
		em.getTransaction().begin();
		Query q = em.createQuery("UPDATE EmpleadoEntity SET salario = salario + :din WHERE oficio = :ofi");
		q.setParameter("din", cantidad);
		q.setParameter("ofi", oficio);
		int filasModif = q.executeUpdate();
		em.getTransaction().commit();
		
		return filasModif;
	}
	
	public int incrementarSalarioDepartamento(int numDepartamento, int cantidad) {
		
		em.getTransaction().begin();
		Query q = em.createQuery("UPDATE EmpleadoEntity SET salario = salario + :din WHERE departamento.getDptoId() = :num");
		q.setParameter("din", cantidad);
		q.setParameter("num", numDepartamento);
		int filasModif = q.executeUpdate();
		em.getTransaction().commit();
		
		return filasModif;
		
		
	}
	
	
	public int borrarDepartamento(int numDepartamento) {
		
		em.getTransaction().begin();
		Query q = em.createQuery("DELETE FROM DepartamentoEntity WHERE dptoId = :ide");
		q.setParameter("ide", numDepartamento);
		int filasModif = q.executeUpdate();
		em.getTransaction().commit();
		
		return filasModif;
	}
	
//--------------------------------------------------------------------------------------------------------------
	
	
	
	
} // de la clase
