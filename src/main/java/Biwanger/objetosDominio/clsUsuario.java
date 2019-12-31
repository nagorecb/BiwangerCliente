package Biwanger.objetosDominio;

import java.util.ArrayList;
import java.util.Objects;
/**
 * @brief Clase del objeto Usuario
 */
public class clsUsuario
{
	private static final long serialVersionUID = 1L;
	
    private String email;
    private String password;
    private int puntuacion;
    private double fondos;
    private String formacion;

	/**
	 * Constructor de usuario sin parametros
	 */
	public clsUsuario() {
		this.email = null;
		this.password =  null;
		this.formacion = null;
		this.fondos = 0.0;
		puntuacion = 0;
	}

	/**
	 * Constructor de usuario con parametros
	 * @param email del usuario
	 * @param password contraseña del usuario
	 * @param puntuacion del usuario
	 * @param fondos dinero disponible del usuario
	 * @param formacion del equipo del usuario
	 */
	public clsUsuario(String email, String password, int puntuacion, double fondos, String formacion) {
		super();
		this.email = email;
		this.password = password;
		this.puntuacion = puntuacion;
		this.fondos = fondos;
		this.formacion = formacion;
	}

	/**
	 * Método que devuelve el email del usuario
	 * @return email del usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Método que establece el email del usuario
	 * @param email del usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Método que devuelve la contraseña del usuario
	 * @return contraseña del usuario
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Método que establece la contraseña del usuario
	 * @param password o contraseña del usuario
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Método que devuelve la puntuación del usuario
	 * @return puntuación del usuario
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Método que establece la puntuación del usuario
	 * @param puntuacion del usuario
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * Método que devuelve los fondos del usuario
	 * @return fondos del usuario
	 */
	public double getFondos() {
		return fondos;
	}

	/**
	 * Método que establece los fondos del usuario
	 * @param fondos del usuario
	 */
	public void setFondos(double fondos) {
		this.fondos = fondos;
	}

	/**
	 * Método que devuelve la formación del equipo del usuario
	 * @return formación del equipo del usuario en modo (x-x-x)
	 */
	public String getFormacion() {
		return formacion;
	}

	/**
	 * Método que establece la formación del equipo del usuario
	 * @param formacion del equipo del usuario en modo (x-x-x)
	 */
	public void setFormacion(String formacion) {
		this.formacion = formacion;
	}

	/**
	 * Método para sumar el dinero adquirido de la venta a los fondos del usuario
	 * @param precioVenta precio por el que el usuario ha vendido al jugador
	 */
	public void SumarFondos (double precioVenta)
	{
		this.fondos +=precioVenta;
	}
	/**
	 * Método para restar el dinero perdido por la compra a los fondos del usuario
	 * @param precioCompra precio por el que el usuario ha comprado al jugador
	 */
	public void RestarFondos (double precioCompra)
	{
		this.fondos -=precioCompra;
	}

	/**
	 * Método que devuelve una lista de los jugadores del usuario que juegan en una posición
	 * @param posicion del campo de los jugadores requeridos
	 * @param plantilla lista de los jugadores de un usuario
	 * @return una lista con los jugadores del usuario de la posición solicitada
	 */
	public ArrayList<clsJugador> getPosicion(String posicion, ArrayList<clsJugador> plantilla)
	{
		ArrayList<clsJugador> lPosicion = new ArrayList <clsJugador>();
		for (int i=0;i<plantilla.size();i++)
		{
			if (plantilla.get(i).getPosicion().equals(posicion))
				lPosicion.add(plantilla.get(i));
		}
		return lPosicion;
	}
	/**
	 * Método que compara dos objetos
	 * @param o objeto que se pretende comparar con el actual
	 * @return true si son el mismo objeto, false si no lo son
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		clsUsuario that = (clsUsuario) o;
		return email.equals(that.email);
	}

	/**
	 * Método que genera un número único para identificar al objeto
	 * @return el hash del identificador del jugador
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(email);
	}
}