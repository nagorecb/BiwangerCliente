package Biwanger.objetosDominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class clsUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	
    private String email;
    private String password;
    private int puntuacion;
    private double fondos;
    private String formacion;
    
    private List <clsJugador> plantilla;
    private List <clsPuja> pujas;
    
    public clsUsuario() {
    	this.email = null;
        this.password =  null;
        this.fondos = 0.0;
        this.plantilla = new ArrayList <clsJugador>();
        this.formacion = null;
        puntuacion = 0;
	}

	public clsUsuario(String email, String password, int puntuacion, double fondos, List<clsJugador> plantilla, String formacion) {
		super();
		this.email = email;
		this.password = password;
		this.puntuacion = puntuacion;
		this.fondos = fondos;
		this.plantilla = plantilla;
		this.formacion=formacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public double getFondos() {
		return fondos;
	}

	public void setFondos(double fondos) {
		this.fondos = fondos;
	}

	public List<clsJugador> getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(List<clsJugador> plantilla) {
		this.plantilla = plantilla;
	}

	public String getFormacion() {
		return formacion;
	}

	public void setFormacion(String formacion) {
		this.formacion = formacion;
	}

	public ArrayList<clsJugador> getPosicion(String posicion)
	{
		ArrayList <clsJugador> lPosicion = new ArrayList <clsJugador>();
		for (int i=0;i<plantilla.size();i++)
		{
			if (plantilla.get(i).getPosicion()==posicion)
				lPosicion.add(plantilla.get(i));
		}
		return lPosicion;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		clsUsuario that = (clsUsuario) o;
		return email.equals(that.email);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(email);
	}
}