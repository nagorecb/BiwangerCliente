package Biwanger.objetosDominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class clsJugador implements Serializable
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
	
	private clsUsuario usuarioDueno;
	private List <clsPuja> pujasRealizadas;
	
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
		this.pujasRealizadas = null;
	}
	
	public clsJugador(int id, String nombre, int puntos, String posicion, double precio, String equipo,
			boolean alineado, int numGoles, int numAsistencias, int numTarjetasAmarillas, int numTarjetasRojas,
			int numPartidosJugados, String estado, boolean enVenta, clsUsuario usuarioDueno,
			List<clsPuja> pujasRealizadas) {
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
		this.pujasRealizadas = pujasRealizadas;
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



	public clsUsuario getUsuarioDueno() {
		return usuarioDueno;
	}



	public void setUsuarioDueno(clsUsuario usuarioDueno) {
		this.usuarioDueno = usuarioDueno;
	}



	public List<clsPuja> getPujasRealizadas() {
		return pujasRealizadas;
	}



	public void setPujasRealizadas(List<clsPuja> pujasRealizadas) {
		this.pujasRealizadas = pujasRealizadas;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		clsJugador other = (clsJugador) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}