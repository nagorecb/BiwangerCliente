package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Handler;
import java.util.logging.Logger;


public class frmPanelUsuario extends JFrame
{
    private static Logger logger = Logger.getLogger(frmPanelUsuario.class.getName());
    private static Handler handlerPantalla;
    private static Handler handlerArchivo;

    private static final long serialVersionUID = 1L;
    private JPanel pPrincipal;
    private PanelConFondo pFoto;
    private clsUsuario usuario;

    private int altura = 800;
    private int anchura = 1066;
    private int x = 100;
    private int y = 100;

    clsController controller;

    /**
     * Constructor de la ventana de registro o inicio de sesion
     */
    public frmPanelUsuario(final clsController controller, clsUsuario usuario)
    {
        this.usuario = usuario;
        this.controller = controller;

        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setSize(1066, 800);
        setResizable(false);

        pPrincipal = new JPanel();
        getContentPane().add(pPrincipal, BorderLayout.CENTER);
        pPrincipal.setLayout(new BorderLayout(0, 0));

        pFoto = new PanelConFondo ("/img/foto.jpg");
        pPrincipal.add(pFoto);


        //MENU
        JMenuBar menuBar = menu();
        setJMenuBar(menuBar);
    }

    private JMenuBar menu ()
    {
        //MENU
        JMenuBar menuBar = new JMenuBar();

        JMenu mnClasificacin = new JMenu("Clasificacion");
        menuBar.add(mnClasificacin);
        JMenuItem mntmTotal = new JMenuItem("Total");
        mnClasificacin.add(mntmTotal);
        JMenuItem mntmMiEquipo_1 = new JMenuItem("Mi equipo");
        mnClasificacin.add(mntmMiEquipo_1);
        JMenu mnAlineacin = new JMenu("Alineacion");
        menuBar.add(mnAlineacin);
        JMenuItem mntmConsultar = new JMenuItem("Consultar");
        mnAlineacin.add(mntmConsultar);
        JMenuItem mntmModificar = new JMenuItem("Modificar");
        mnAlineacin.add(mntmModificar);
        JMenu mnEstadsticas = new JMenu("Estadisticas");
        menuBar.add(mnEstadsticas);
        JMenuItem mntmMiEquipo = new JMenuItem("Mi equipo");
        mnEstadsticas.add(mntmMiEquipo);
        JMenu mnMercado = new JMenu("Mercado");
        menuBar.add(mnMercado);
        JMenuItem mntmComprar = new JMenuItem("Comprar");
        mnMercado.add(mntmComprar);
        JMenuItem mntmVender = new JMenuItem("Vender");
        mnMercado.add(mntmVender);

        //Escuchadores de botones

        mntmConsultar.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frConsultarAlineacion ventana = new frConsultarAlineacion (controller,usuario);
                ventana.setVisible(true);
                dispose();
            }
        });

        mntmModificar.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frModificarAlineacion ventana = new frModificarAlineacion (controller,usuario);
                ventana.setVisible(true);
                dispose();
            }
        });

        mntmMiEquipo.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frEstadisticas ventana = new frEstadisticas (controller,usuario);
                ventana.setVisible(true);
                dispose();
            }
        });

        return menuBar;
    }

}