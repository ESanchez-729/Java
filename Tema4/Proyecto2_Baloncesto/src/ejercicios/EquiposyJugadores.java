package ejercicios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.Equipos;
import clases.Jugadores;
import clases.SessionFactoryUtil;

public class EquiposyJugadores {
	
	static SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
    static Session session;
    static List<String> listaEquipos = new ArrayList<String>();
    
    
	public static void main(String[] args) {
		
		session = sesion.openSession();
		
		Query consulta = session.createQuery("Select nombre from Equipos");
		
		listaEquipos = consulta.list();
		
		System.out.println("Número de equipos: " + listaEquipos.size());
		
		for(String equipo : listaEquipos) {
			
			System.out.println("=====================================");
			System.out.println("Equipo: " + equipo);
			List<Jugadores> listaJugadores = new ArrayList<Jugadores>();
			Query consultaJugadores = session.createQuery("from Jugadores where Nombre_equipo = :nombre").setString("nombre", equipo);
			listaJugadores = consultaJugadores.list();
			
			for(Jugadores jugador : listaJugadores) {
				
				int codigo = jugador.getCodigo();
				Query consultaMediaPuntos = session.createQuery("Select avg(puntosPorPartido) from Estadisticas where jugador = :codigo").setInteger("codigo", codigo);
				Double mediaPuntos = (Double) consultaMediaPuntos.list().get(0);
				//System.out.println(jugador.getCodigo() + ", " + jugador.getNombre() + ": " + mediaPuntos);
				System.out.printf(jugador.getCodigo() + ", " + jugador.getNombre() + ": %.2f\n" , mediaPuntos);
			}
			
			System.out.println(".....................................");
		}

	}

}
