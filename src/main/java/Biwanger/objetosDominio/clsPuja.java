package Biwanger.objetosDominio;

import java.time.LocalDateTime;
import java.util.Objects;

public class clsPuja {

	private String emailUsuarioPuja;
	private int IdJugadorPuja;
	private double oferta;
	private LocalDateTime fecha;
	
	public clsPuja() {
		super();
		this.emailUsuarioPuja = null;
		this.IdJugadorPuja = 0;
		this.oferta = 0.0;
		this.fecha = null;
	}
	
	
	public clsPuja(String usuarioPuja, int jugadorPuja, double oferta) {
		super();
		this.emailUsuarioPuja = usuarioPuja;
		this.IdJugadorPuja = jugadorPuja;
		this.oferta = oferta;
		this.fecha = LocalDateTime.now();
	}
	
	
	public String getEmailUsuarioPuja() {
		return emailUsuarioPuja;
	}
	public void setEmailUsuarioPuja(String emailUsuarioPuja) {
		this.emailUsuarioPuja = emailUsuarioPuja;
	}
	public int getIdJugadorPuja() {
		return IdJugadorPuja;
	}
	public void setIdJugadorPuja(int idJugadorPuja) {
		this.IdJugadorPuja = idJugadorPuja;
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
		return emailUsuarioPuja.equals(clsPuja.emailUsuarioPuja) &&
				fecha.equals(clsPuja.fecha);
	}

	@Override
	public int hashCode() {
		return Objects.hash(emailUsuarioPuja, fecha);
	}
	
}