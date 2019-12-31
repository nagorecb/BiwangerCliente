package Biwanger.objetosDominio;

import java.util.ArrayList;

/**
 * @brief Clase del objeto lista de pujas
 * @details Clase necesaria para intercambiar listas de pujas entre cliente y servidor
 */
public class clsPujaLista
{
    private ArrayList<clsPuja> lPujas;

    /**
     * Constructor de la lista de pujas sin parametros
     */
    public clsPujaLista() {}

    /**
     * Constructor de la lista de pujas con parametro
     * @param param lista de pujas
     */
    public clsPujaLista(ArrayList param)
    {
        lPujas = param;
    }

    /**
     * Método que devuelve una lista de pujas
     * @return lista de pujas
     */
    public ArrayList<clsPuja> getlPujas()
    {
        return lPujas;
    }
}
