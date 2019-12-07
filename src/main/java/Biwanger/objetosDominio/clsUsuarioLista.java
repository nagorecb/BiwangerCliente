package Biwanger.objetosDominio;

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
}
