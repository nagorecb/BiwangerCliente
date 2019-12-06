package Biwanger.objetosDominio;

import java.time.LocalDateTime;
import java.util.Objects;

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
	
	
	public clsPuja(clsUsuario usuarioPuja, clsJugador jugadorPuja, double oferta) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		clsPuja clsPuja = (clsPuja) o;
		return usuarioPuja.equals(clsPuja.usuarioPuja) &&
				fecha.equals(clsPuja.fecha);
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuarioPuja, fecha);
	}
	
}