package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;
import Biwanger.objetosDominio.clsPuja;
import Biwanger.objetosDominio.clsUsuario;

import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class frmPujar extends JFrame
{
    private clsJugador jugadorSeleccionado;


    private int altura = 800;
    private int anchura = 1066;
    private int x = 100;
    private int y = 100;

    private JList ListJugadores;
    private JScrollPane scrollListJugadores;
    private modelJugadores modelJugadores;
    private JPanel pPrincipal, pBotonera;

    private JLabel lblSuma;
    private JTextField txtSuma;

    private JButton btnPujar, btnVolver;
    private JFrame panelUsuario;

    public frmPujar(JFrame frame,clsController controller, clsUsuario usuario)
    {
        panelUsuario = frame;

        setUndecorated(true);
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(anchura, altura);
        //this.setLocation(x, y);
        setResizable(false);
        setTitle("Biwanger Fantasy - Pujar");
        getContentPane().setLayout(new BorderLayout(0, 0));

        modelJugadores = new modelJugadores(controller.MostrarMercado());
        ListJugadores = new JList(modelJugadores);

        pPrincipal = new JPanel();
        pPrincipal.setLayout(null);
        getContentPane().add(pPrincipal,BorderLayout.CENTER);

        scrollListJugadores = new JScrollPane();
        scrollListJugadores.setBounds(0, 0, 300, 510);
        pPrincipal.add(scrollListJugadores);
        scrollListJugadores.add(ListJugadores);

        lblSuma = new JLabel("Introduce la suma de la puja: ");
        lblSuma.setBounds(550, 75, 225, 30);

        txtSuma = new JTextField();
        txtSuma.setBounds(550, 110, 225, 30);

        pPrincipal.add(lblSuma);
        pPrincipal.add(txtSuma);

        btnPujar = new JButton("Pujar");
        btnPujar.setBounds(605, 280, 115, 30);
        btnPujar.setEnabled(false);
        pPrincipal.add(btnPujar);

        pBotonera = new JPanel();
        getContentPane().add(pBotonera, BorderLayout.NORTH);
        pBotonera.setLayout(new BorderLayout(0, 0));

        btnVolver = new JButton("Volver");
        pBotonera.add(btnVolver, BorderLayout.WEST);

        btnPujar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clsPuja puja = new clsPuja(usuario.getEmail(), jugadorSeleccionado.getId(), Double.parseDouble(txtSuma.getText()));
                controller.Pujar(puja);
                panelUsuario.setVisible(true);
                dispose();
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
