package Biwanger.objetosDominio;

import java.util.ArrayList;

/**
 * @brief Clase del objeto lista de jugadores
 * @details Clase necesaria para intercambiar listas de jugadores entre cliente y servidor
 */
public class clsJugadorLista
{
    private ArrayList<clsJugador> lJugadores;

    /**
     * Constructor de la lista de jugadores sin parametros
     */
    public clsJugadorLista() {}

    /**
     * Constructor de la lista de jugadores
     * @param param lista de jugadores
     */
    public clsJugadorLista(ArrayList param)
    {
        lJugadores = param;
    }

    /**
     * Método que devuelve una lista de jugadores
     * @return lista de jugadores
     */
    public ArrayList<clsJugador> getlJugadores()
    {
        return lJugadores;
    }
}
