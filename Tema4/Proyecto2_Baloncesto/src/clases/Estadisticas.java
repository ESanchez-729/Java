package clases;
// Generated 7 dic 2021 17:09:24 by Hibernate Tools 5.4.32.Final

/**
 * Estadisticas generated by hbm2java
 */
public class Estadisticas implements java.io.Serializable {

	private EstadisticasId id;
	private Jugadores jugadores;
	private Float puntosPorPartido;
	private Float asistenciasPorPartido;
	private Float taponesPorPartido;
	private Float rebotesPorPartido;

	public Estadisticas() {
	}

	public Estadisticas(EstadisticasId id, Jugadores jugadores) {
		this.id = id;
		this.jugadores = jugadores;
	}

	public Estadisticas(EstadisticasId id, Jugadores jugadores, Float puntosPorPartido, Float asistenciasPorPartido,
			Float taponesPorPartido, Float rebotesPorPartido) {
		this.id = id;
		this.jugadores = jugadores;
		this.puntosPorPartido = puntosPorPartido;
		this.asistenciasPorPartido = asistenciasPorPartido;
		this.taponesPorPartido = taponesPorPartido;
		this.rebotesPorPartido = rebotesPorPartido;
	}

	public EstadisticasId getId() {
		return this.id;
	}

	public void setId(EstadisticasId id) {
		this.id = id;
	}

	public Jugadores getJugadores() {
		return this.jugadores;
	}

	public void setJugadores(Jugadores jugadores) {
		this.jugadores = jugadores;
	}

	public Float getPuntosPorPartido() {
		return this.puntosPorPartido;
	}

	public void setPuntosPorPartido(Float puntosPorPartido) {
		this.puntosPorPartido = puntosPorPartido;
	}

	public Float getAsistenciasPorPartido() {
		return this.asistenciasPorPartido;
	}

	public void setAsistenciasPorPartido(Float asistenciasPorPartido) {
		this.asistenciasPorPartido = asistenciasPorPartido;
	}

	public Float getTaponesPorPartido() {
		return this.taponesPorPartido;
	}

	public void setTaponesPorPartido(Float taponesPorPartido) {
		this.taponesPorPartido = taponesPorPartido;
	}

	public Float getRebotesPorPartido() {
		return this.rebotesPorPartido;
	}

	public void setRebotesPorPartido(Float rebotesPorPartido) {
		this.rebotesPorPartido = rebotesPorPartido;
	}

}
