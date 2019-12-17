package Biwanger.objetosDominio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class clsPuja {

	private String emailUsuarioPuja;
	private int IdJugadorPuja;
	private double oferta;
	private String fecha;
	
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime fecha = LocalDateTime.now();
		this.fecha = fecha.format(formatter);
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
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