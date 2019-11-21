package Biwanger.vistas;

import Biwanger.objetosDominio.clsJugador;

import javax.swing.*;
import java.util.ArrayList;

public class modelJugadores extends DefaultListModel<clsJugador>
{
    ArrayList<clsJugador> JugadoresModel = new ArrayList<clsJugador>();

    /**
     * El constructor inicializa el atributo HashSet del ListModel.
     *
     * @param lista recibe la lista que contendrá el ListModel
     */
    public modelJugadores(ArrayList<clsJugador> lista)
    {
        JugadoresModel = lista;
    }

    /**
     * Devuelve el objeto que se encuentra en el índice especificado.
     *
     * @param index la posición del elemento
     * @return Devuelve un objeto de tipo clsArchivo
     */
    public clsJugador getElementAt(int index)
    {
        ArrayList<clsJugador> arrayArchivos = new ArrayList<clsJugador>();;

        for(clsJugador aux: JugadoresModel)
        {
            arrayArchivos.add(aux);
        }

        return arrayArchivos.get(index);
    }

    /**
     * Permite acceder al número total de elementos de la lista
     *
     * @return Devuelve un int que contiene el número de elementos
     */
    public int getSize()
    {
        return JugadoresModel.size();
    }

    /**
     * Permite añadir un elemento a la lista
     *
     * @param element recibe el objeto clsArchivo que debe añadir
     */
    public void addElement(clsJugador element)
    {
        JugadoresModel.add(element);
        fireContentsChanged(this, JugadoresModel.size(), JugadoresModel.size());
    }

    /**
     * Permite actualizar el atributo HashSet del ListModel.
     *
     * @param lista recibe la nueva lista que se quiere introducir
     */
    public void setLista(ArrayList<clsJugador> lista)
    {
        JugadoresModel = lista;
        fireContentsChanged(this, JugadoresModel.size(), JugadoresModel.size());
    }
}
