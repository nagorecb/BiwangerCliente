package Biwanger.ObjetosDominio;

import java.util.ArrayList;

public class clsUsuarioLista
{
    private ArrayList<clsUsuario> lUsuarios;

    public clsUsuarioLista(ArrayList param)
    {
        lUsuarios = param;
    }

    public clsUsuarioLista()
    {

    }

    public ArrayList<clsUsuario> getlUsuarios()
    {
        ArrayList<clsUsuario> lista = new ArrayList<clsUsuario>();

        for(clsUsuario aux: lUsuarios)
        {
            lista.add(aux);
        }

        return lista;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lUsuarios == null) ? 0 : lUsuarios.hashCode());
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
		clsUsuarioLista other = (clsUsuarioLista) obj;
		if (lUsuarios == null) {
			if (other.lUsuarios != null)
				return false;
		} else if (!lUsuarios.equals(other.lUsuarios))
			return false;
		return true;
	}
    
    
}
