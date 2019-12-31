package Biwanger.objetosDominio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
/**
 * @brief Clase del objeto Puja
 */
public class clsPuja {
	private String emailUsuarioPuja;
	private int IdJugadorPuja;
	private double oferta;
	private String fecha;

	/**
	 * Constructor de puja sin parametros
	 */
	public clsPuja() {
		super();
		this.emailUsuarioPuja = null;
		this.IdJugadorPuja = 0;
		this.oferta = 0.0;
		this.fecha = null;
	}

	/**
	 * Constructor de puja con parametros
	 * @param usuarioPuja email del usuario que puja por el jugador
	 * @param jugadorPuja id del jugador por el que se puja
	 * @param oferta cantidad de dinero que el usuario ofrece por el jugador
	 */
	public clsPuja(String usuarioPuja, int jugadorPuja, double oferta) {
		super();
		this.emailUsuarioPuja = usuarioPuja;
		this.IdJugadorPuja = jugadorPuja;
		this.oferta = oferta;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime fecha = LocalDateTime.now();
		this.fecha = fecha.format(formatter);
	}

	/**
	 * Método que devuelve el email del usuario que ha hecho la puja
	 * @return email del usuario
	 */
	public String getEmailUsuarioPuja() {
		return emailUsuarioPuja;
	}
	/**
	 * Método que establece el email del usuario que puja
	 * @param emailUsuarioPuja email del usuario que puja
	 */
	public void setEmailUsuarioPuja(String emailUsuarioPuja) {
		this.emailUsuarioPuja = emailUsuarioPuja;
	}

	/**
	 * Método que devuelve el identificador del jugador por el que se ha hecho la puja
	 * @return identificador del jugador
	 */
	public int getIdJugadorPuja() {
		return IdJugadorPuja;
	}
	/**
	 * Método que establece el id del jugador por el que se puja
	 * @param idJugadorPuja identificador del jugador por el que se puja
	 */
	public void setIdJugadorPuja(int idJugadorPuja) {
		this.IdJugadorPuja = idJugadorPuja;
	}

	/**
	 * Método que devuelve la cantidad ofertada por el jugador
	 * @return cantidad ofertada por el jugador
	 */
	public double getOferta() {
		return oferta;
	}
	/**
	 * Método que establece la cantidad de dinero que se ofrece por el jugador
	 * @param oferta cantidad de dinero que se ofrece por el jugador
	 */
	public void setOferta(double oferta) {
		this.oferta = oferta;
	}

	/**
	 * Método que devuelve la fecha en la que se realizó la puja
	 * @return fecha de la oferta
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * Método que establece la fecha en la que se realiza la puja
	 * @param fecha en la que se realiza la puja
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
		clsPuja clsPuja = (clsPuja) o;
		return emailUsuarioPuja.equals(clsPuja.emailUsuarioPuja) &&
				fecha.equals(clsPuja.fecha);
	}

	/**
	 * Método que genera un número único para identificar al objeto
	 * @return el hash del identificador de la puja
	 */
	@Override
	public int hashCode() {
		return Objects.hash(emailUsuarioPuja, fecha);
	}
	
}