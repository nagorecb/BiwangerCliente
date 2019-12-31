package Biwanger.objetosDominio;

import java.util.ArrayList;

/**
 * @brief Clase del objeto lista de usuarios
 * @details Clase necesaria para intercambiar listas de usuarios entre cliente y servidor
 */
public class clsUsuarioLista
{
	private ArrayList<clsUsuario> lUsuarios;

	/**
	 * Constructor de la lista de usuarios con parametro
	 * @param param lista de usuarios
	 */
	public clsUsuarioLista(ArrayList param)
	{
		lUsuarios = param;
	}

	/**
	 * Constructor de lista sin parametros
	 */
	public clsUsuarioLista()
	{

	}

	/**
	 * Método que devuelve una lista de usuarios
	 * @return lista de usuarios
	 */
	public ArrayList<clsUsuario> getlUsuarios()
	{
		ArrayList<clsUsuario> lista = new ArrayList<clsUsuario>();

		for(clsUsuario aux: lUsuarios)
		{
			lista.add(aux);
		}

		return lista;
	}

	/**
	 * Método que genera un número único para identificar al objeto
	 * @return el hash del identificador de la lista de usuarios
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lUsuarios == null) ? 0 : lUsuarios.hashCode());
		return result;
	}

	/**
	 * Método que compara dos objetos
	 * @param obj objeto que se pretende comparar con el actual
	 * @return true si son el mismo objeto, false si no lo son
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		clsUsuarioLista other = (clsUsuarioLista) obj;
		if (lUsuarios == null) {
			if (other.lUsuarios != null)
				return false;
		} else if (!lUsuarios.equals(other.lUsuarios))
			return false;
		return true;
	}
    
}
