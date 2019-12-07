package Biwanger.objetosDominio;

import java.util.ArrayList;

public class clsJugadorLista
{
    private ArrayList<clsJugador> lJugadores;

    public clsJugadorLista() {}

    public clsJugadorLista(ArrayList param)
    {
        lJugadores = param;
    }

    public ArrayList<clsJugador> getlJugadores()
    {
        return lJugadores;
    }
}
