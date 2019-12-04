package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;
import Biwanger.objetosDominio.clsPuja;
import Biwanger.objetosDominio.clsUsuario;

import javax.swing.*;
import java.awt.event.*;

public class frmPujar extends JFrame
{
    private clsJugador jugadorSeleccionado;


    private int altura = 550;
    private int anchura = 950;
    private int x = 100;
    private int y = 100;

    private JList ListJugadores;
    private JScrollPane scrollListJugadores;
    private modelJugadores modelJugadores;

    private JLabel lblSuma;
    private JTextField txtSuma;

    private JButton btnPujar;

    public frmPujar(clsController controller, clsUsuario usuario)
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Definimos el tamano y la localizacion central en la pantalla
        this.setSize(anchura, altura);
        this.setLocation(x, y);
        setResizable(false);
        setTitle("Biwanger Fantasy - Pujar");
        getContentPane().setLayout(null);

        modelJugadores = new modelJugadores(controller.MostrarMercado());
        ListJugadores = new JList(modelJugadores);

        scrollListJugadores = new JScrollPane();
        scrollListJugadores.setBounds(0, 0, 300, 510);
        getContentPane().add(scrollListJugadores);

        scrollListJugadores.add(ListJugadores);

        lblSuma = new JLabel("Introduce la suma de la puja: ");
        lblSuma.setBounds(550, 75, 225, 30);

        txtSuma = new JTextField();
        txtSuma.setBounds(550, 110, 225, 30);

        getContentPane().add(lblSuma);
        getContentPane().add(txtSuma);

        btnPujar = new JButton("Pujar");
        btnPujar.setBounds(605, 280, 115, 30);
        btnPujar.setEnabled(false);
        getContentPane().add(btnPujar);

        btnPujar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clsPuja puja = new clsPuja(usuario, jugadorSeleccionado, Double.parseDouble(txtSuma.getText()));

                controller.Pujar(puja);
            }
        });

        MouseListener mouseListener = new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                jugadorSeleccionado = (clsJugador) ((JList)e.getSource()).getSelectedValue();
                btnPujar.setEnabled(true);
            }

            public void mousePressed(MouseEvent arg0)
            {
                clsJugador seleccion = (clsJugador) ((JList)arg0.getSource()).getSelectedValue();
                btnPujar.setEnabled(true);
            }

        };
    }
}
