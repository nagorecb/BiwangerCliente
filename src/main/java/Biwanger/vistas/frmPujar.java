package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frmPujar extends JFrame implements ActionListener
{
    private int altura = 550;
    private int anchura = 950;
    private int x = 100;
    private int y = 100;

    private JList ListJugadores;
    private JScrollPane scrollListJugadores;
    private modelJugadores modelJugadores;

    public frmPujar(clsController controller)
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Definimos el tamaÃƒÂ±o y la localizaciÃƒÂ³n central en la pantalla
        this.setSize(anchura, altura);
        this.setLocation(x, y);
        setResizable(false);
        setTitle("Biwanger Fantasy - Pujar");

        modelJugadores = new modelJugadores(controller.MostrarMercado());
        ListJugadores = new JList(modelJugadores);
    }


}
