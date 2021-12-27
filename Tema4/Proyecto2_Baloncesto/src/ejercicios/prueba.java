package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.Equipos;
import clases.EstadisticasId;
import clases.Jugadores;
import clases.Partidos;
import clases.SessionFactoryUtil;

public class prueba {

	static SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
    static Session session;

    public static void main(String[] args) {
        
        session = sesion.openSession();
        
        Scanner s = new Scanner(System.in);
        
        System.out.println("Ponga usted el codigillo ese: ");
        
        int codigo_jugador = s.nextInt();
        
        Jugadores jugador = (Jugadores) session.get(Jugadores.class, codigo_jugador);
        
        Query query = session.createQuery("from Estadisticas where jugador = :cod").setInteger("cod", jugador.getCodigo());
        List<clases.Estadisticas> lista_Estadisticas = new ArrayList<clases.Estadisticas>();
        lista_Estadisticas = query.list();
        
        int cont = 0;
        
        System.out.println("Datos del Jugador: " + jugador.getCodigo());
        System.out.println("Nombre: " + jugador.getNombre());
        System.out.println("Equipo: " + jugador.getEquipos().getNombre());
        System.out.println("Temporada        Ptos        Asis        Tap            Reb");
        System.out.println("=======================================================================");
        
        for (clases.Estadisticas stats : lista_Estadisticas) {

            System.out.println(stats.getId().getTemporada() + "        " + stats.getPuntosPorPartido() + "        " + stats.getAsistenciasPorPartido() + "    " +
            stats.getTaponesPorPartido() + "    " + stats.getRebotesPorPartido());
            cont++;
            
        }
        
        System.out.println("=======================================================================");
        System.out.println("Num de Registros: " + cont);
        System.out.println("=======================================================================");
        
    }
}
