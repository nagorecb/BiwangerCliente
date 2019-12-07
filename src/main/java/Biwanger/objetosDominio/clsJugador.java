package Biwanger.objetosDominio;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private LocalDateTime fechaVenta;
	private String usuarioDueno;
	
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
	
	public clsJugador(int id, String nombre, int puntos, String posicion, double precio, String equipo,
			boolean alineado, int numGoles, int numAsistencias, int numTarjetasAmarillas, int numTarjetasRojas,
			int numPartidosJugados, String estado, boolean enVenta, String usuarioDueno, LocalDateTime fecha) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public boolean isAlineado() {
		return alineado;
	}

	public void setAlineado(boolean alineado) {
		this.alineado = alineado;
	}

	public int getNumGoles() {
		return numGoles;
	}

	public void setNumGoles(int numGoles) {
		this.numGoles = numGoles;
	}

	public int getNumAsistencias() {
		return numAsistencias;
	}

	public void setNumAsistencias(int numAsistencias) {
		this.numAsistencias = numAsistencias;
	}

	public int getNumTarjetasAmarillas() {
		return numTarjetasAmarillas;
	}

	public void setNumTarjetasAmarillas(int numTarjetasAmarillas) {
		this.numTarjetasAmarillas = numTarjetasAmarillas;
	}

	public int getNumTarjetasRojas() {
		return numTarjetasRojas;
	}

	public void setNumTarjetasRojas(int numTarjetasRojas) {
		this.numTarjetasRojas = numTarjetasRojas;
	}

	public int getNumPartidosJugados() {
		return numPartidosJugados;
	}

	public void setNumPartidosJugados(int numPartidosJugados) {
		this.numPartidosJugados = numPartidosJugados;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isEnVenta() {
		return enVenta;
	}

	public void setEnVenta(boolean enVenta) {
		this.enVenta = enVenta;
	}

	public String getUsuarioDueno() {
		return usuarioDueno;
	}

	public void setUsuarioDueno(String usuarioDueno) {
		this.usuarioDueno = usuarioDueno;
	}

	public void setFechaVenta(LocalDateTime fecha)
	{
		this.fechaVenta = fecha;
	}

	public LocalDateTime getFechaVenta()
	{
		return fechaVenta;
	}

	@Override
	public String toString()
	{
		return  nombre + " - " + puntos + " - " + posicion + " - " + equipo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		clsJugador that = (clsJugador) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
}