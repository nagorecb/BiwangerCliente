package Biwanger.objetosDominio;

import java.time.LocalDateTime;

public class clsPuja {

	private clsUsuario usuarioPuja;
	
	private clsJugador jugadorPuja;
	
	private double oferta; //dinero pujado
	private LocalDateTime fecha;
	
	public clsPuja() {
		super();
		this.usuarioPuja = null;
		this.jugadorPuja = null;
		this.oferta = 0.0;
		this.fecha = null;
	}
	
	
	public clsPuja(clsUsuario usuarioPuja, clsJugador jugadorPuja, double oferta, LocalDateTime fecha) {
		super();
		this.usuarioPuja = usuarioPuja;
		this.jugadorPuja = jugadorPuja;
		this.oferta = oferta;
		this.fecha = LocalDateTime.now();
	}
	
	
	public clsUsuario getUsuarioPuja() {
		return usuarioPuja;
	}
	public void setUsuarioPuja(clsUsuario usuarioPuja) {
		this.usuarioPuja = usuarioPuja;
	}
	public clsJugador getJugadorPuja() {
		return jugadorPuja;
	}
	public void setJugadorPuja(clsJugador jugadorPuja) {
		this.jugadorPuja = jugadorPuja;
	}
	public double getOferta() {
		return oferta;
	}
	public void setOferta(double oferta) {
		this.oferta = oferta;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jugadorPuja == null) ? 0 : jugadorPuja.hashCode());
		result = prime * result + ((usuarioPuja == null) ? 0 : usuarioPuja.hashCode());
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
		clsPuja other = (clsPuja) obj;
		if (jugadorPuja == null) {
			if (other.jugadorPuja != null)
				return false;
		} else if (!jugadorPuja.equals(other.jugadorPuja))
			return false;
		if (usuarioPuja == null) {
			if (other.usuarioPuja != null)
				return false;
		} else if (!usuarioPuja.equals(other.usuarioPuja))
			return false;
		return true;
	}
	
}