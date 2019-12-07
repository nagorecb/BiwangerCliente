package Biwanger.objetosDominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class clsUsuarioLista implements Serializable
{
    private List<clsUsuario> lUsuarios;

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

    public void setlUsuarios(List<clsUsuario> lUsuarios)
    {
        this.lUsuarios = lUsuarios;
    }
}
