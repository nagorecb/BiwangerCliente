package Biwanger.objetosDominio;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * @brief Clase del objeto Jugador
 */
public class clsJugador
{
	private int id;
	private String nombre;
	private int puntos;
	private String posicion;
	private double precio;
	private String equipo;
	private boolean alineado;
	private int numGoles;
	private int numAsistencias;
	private int numTarjetasAmarillas;
	private int numTarjetasRojas;
	private int numPartidosJugados;
	private String estado;	//para saber si está lesionado o no
	private boolean enVenta;
	private String fechaVenta;
	private String usuarioDueno;

	/**
	 * Constructor de jugador sin parametros
	 */
	public clsJugador() {
		super();
		this.id = 0;
		this.nombre = null;
		this.puntos = 0;
		this.posicion = null;
		this.precio = 0.0;
		this.equipo = null;
		this.alineado = false;
		this.numGoles = 0;
		this.numAsistencias = 0;
		this.numTarjetasAmarillas = 0;
		this.numTarjetasRojas = 0;
		this.numPartidosJugados = 0;
		this.estado = null;
		this.enVenta = false;
		this.usuarioDueno = null;
		this.fechaVenta = null;
	}

	/**
	 * Constructor de jugador con parametros
	 * @param id identificador
	 * @param nombre nombre del jugador
	 * @param puntos del jugador
	 * @param posicion del campo que ocupa el jugador
	 * @param precio o valor de mercado del jugador
	 * @param equipo en el que juega
	 * @param alineado boolean que indica si es o no alineado
	 * @param numGoles número de goles
	 * @param numAsistencias número de asistencias
	 * @param numTarjetasAmarillas número de tarjetas amarillas
	 * @param numTarjetasRojas número de tarjetas rojas
	 * @param numPartidosJugados número de partidos jugados
	 * @param estado string que describe el estado de forma del jugador
	 * @param enVenta boolean que indica si está o no en venta
	 * @param usuarioDueno email del usuario dueño
	 * @param fecha fecha de venta del jugador
	 */
	public clsJugador(int id, String nombre, int puntos, String posicion, double precio, String equipo,
			boolean alineado, int numGoles, int numAsistencias, int numTarjetasAmarillas, int numTarjetasRojas,
			int numPartidosJugados, String estado, boolean enVenta, String usuarioDueno, String fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
		this.posicion = posicion;
		this.precio = precio;
		this.equipo = equipo;
		this.alineado = alineado;
		this.numGoles = numGoles;
		this.numAsistencias = numAsistencias;
		this.numTarjetasAmarillas = numTarjetasAmarillas;
		this.numTarjetasRojas = numTarjetasRojas;
		this.numPartidosJugados = numPartidosJugados;
		this.estado = estado;
		this.enVenta = enVenta;
		this.usuarioDueno = usuarioDueno;
		this.fechaVenta = fecha;
	}

	/**
	 * Método que devuelve el identificador del jugador
	 * @return id identificador del jugador
	 */
	public int getId() {
		return id;
	}

	/**
	 * Método que establece el id del jugador
	 * @param id identificador del jugador
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Método que devuelve el nombre del jugador
	 * @return nombre del jugador
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre del jugador
	 * @param nombre del jugador
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve los puntos del jugador
	 * @return puntos del jugador
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * Método que establece los puntos del jugador
	 * @param puntos del jugador
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	/**
	 * Método que devuelve la posición del jugador
	 * @return posicion del jugador
	 */
	public String getPosicion() {
		return posicion;
	}

	/**
	 * Método que establece la posición del jugador
	 * @param posicion del jugador
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	/**
	 * Método que devuelve el precio del jugador
	 * @return precio del jugador
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Método que establece el precio del jugador
	 * @param precio del jugador
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Método que devuelve el equipo del jugador
	 * @return nombre del equipo
	 */
	public String getEquipo() {
		return equipo;
	}

	/**
	 * Método que establece el equipo del jugador
	 * @param equipo del jugador
	 */
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	/**
	 * Método que responde a si el jugador está alineado o no
	 * @return true si es alineado, false si no lo es
	 */
	public boolean isAlineado() {
		return alineado;
	}

	/**
	 * Método que establece si el jugador es o no alineado
	 * @param alineado true o false en función de si es o no alineado
	 */
	public void setAlineado(boolean alineado) {
		this.alineado = alineado;
	}

	/**
	 * Método que devuelve el número de goles que ha metido el jugador
	 * @return número de goles
	 */
	public int getNumGoles() {
		return numGoles;
	}

	/**
	 * Método que establece el número de goles del jugador
	 * @param numGoles número de goles del jugador
	 */
	public void setNumGoles(int numGoles) {
		this.numGoles = numGoles;
	}

	/**
	 * Método que devuelve el número de asistencias que ha dado el jugador
	 * @return número de asistencias
	 */
	public int getNumAsistencias() {
		return numAsistencias;
	}

	/**
	 * Método que establece el número de asistencias del jugador
	 * @param numAsistencias número de asistencias del jugador
	 */
	public void setNumAsistencias(int numAsistencias) {
		this.numAsistencias = numAsistencias;
	}

	/**
	 * Método que devuelve el númer de tarjetas amarillas que tiene el jugador
	 * @return número de tarjetas amarillas
	 */
	public int getNumTarjetasAmarillas() {
		return numTarjetasAmarillas;
	}

	/**
	 * Método que establece el número de tarjetas amarillas del jugador
	 * @param numTarjetasAmarillas número de tarjetas amarillas del jugador
	 */
	public void setNumTarjetasAmarillas(int numTarjetasAmarillas) {
		this.numTarjetasAmarillas = numTarjetasAmarillas;
	}

	/**
	 * Método que devuelve el número de tarjetas rojas que tiene el jugador
	 * @return número de tarjetas rojas
	 */
	public int getNumTarjetasRojas() {
		return numTarjetasRojas;
	}

	/**
	 * Método que establece el número de tarjetas rojas del jugador
	 * @param numTarjetasRojas número de tarjetas rojas del jugador
	 */
	public void setNumTarjetasRojas(int numTarjetasRojas) {
		this.numTarjetasRojas = numTarjetasRojas;
	}

	/**
	 * Método que devuelve el número de partidos jugados por el jugador
	 * @return número de partidos jugados
	 */
	public int getNumPartidosJugados() {
		return numPartidosJugados;
	}

	/**
	 * Método que establece el número de partidos jugados por el jugador
	 * @param numPartidosJugados número de partidos jugados
	 */
	public void setNumPartidosJugados(int numPartidosJugados) {
		this.numPartidosJugados = numPartidosJugados;
	}

	/**
	 * Método que devuelve el estado de forma del jugador
	 * @return estado del jugador
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Método que establece el estado de forma del jugador
	 * @param estado de forma del jugador
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Método que responde a si el jugador está en venta o no
	 * @return true si está en venta, false si no lo está
	 */
	public boolean isEnVenta() {
		return enVenta;
	}

	/**
	 * Método que establece si está o no en venta el jugador
	 * @param enVenta true si está en venta, false si no lo está
	 */
	public void setEnVenta(boolean enVenta) {
		this.enVenta = enVenta;
	}

	/**
	 * Método que devuelve el identificativo del dueño del jugador
	 * @return email del usuario dueño
	 */
	public String getUsuarioDueno() {
		return usuarioDueno;
	}

	/**
	 * Método que establece el usuario dueño del jugador
	 * @param usuarioDueno email del usuario dueño
	 */
	public void setUsuarioDueno(String usuarioDueno) {
		this.usuarioDueno = usuarioDueno;
	}

	/**
	 * Método que establece la fecha en la que se pone en venta al jugador
	 * @param fecha en la que se pone al jugador en venta
	 */
	public void setFechaVenta(String fecha)
	{
		this.fechaVenta = fecha;
	}

	/**
	 * Método que devuelve la fecha en que se pusó en venta al jugador
	 * @return fecha en la que se ha puesto al jugador en venta
	 */
	public String getFechaVenta()
	{
		return fechaVenta;
	}

	/**
	 * Método para mostrar la información completa del jugador
	 * @return un string con los atributos nombre, puntos, posición y equipo
	 */
	@Override
	public String toString()
	{
		return  nombre + " - " + puntos + " - " + posicion + " - " + equipo;
	}
	/**
	 * Método que compara dos objetos
	 * @param o objeto que se pretende comparar con el actual
	 * @return true si son el mismo objeto, false si no lo son
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		clsJugador that = (clsJugador) o;
		return id == that.id;
	}
	/**
	 * Método que genera un número único para identificar al objeto
	 * @return el hash del identificador del jugador
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
}