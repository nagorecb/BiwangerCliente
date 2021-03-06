package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;
import Biwanger.objetosDominio.clsUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @brief Ventana principal del usuario. Le permite acceder a su funcionalidad:
 * - Consultar la clasificación de sus jugadores
 * -
 */
public class frmPanelUsuario extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JPanel pPrincipal;
    private PanelConFondo pFoto;
    private clsUsuario usuario;
    private JFrame frame;

    private int altura = 800;
    private int anchura = 1066;

    clsController controller;

    /**
     * Constructor de la ventana principal del usuario
     * @param controller Recibe el controlador para añadir funcionalidad
     * @param usuario Recibe el usuario que ha iniciado la sesión
     */
    public frmPanelUsuario(final clsController controller, clsUsuario usuario)
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.usuario = usuario;
        this.controller = controller;
        frame = this;

        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setSize(anchura, altura);
        setResizable(false);

        pPrincipal = new JPanel();
        getContentPane().add(pPrincipal, BorderLayout.CENTER);
        pPrincipal.setLayout(new BorderLayout(0, 0));

        pFoto = new PanelConFondo ("src/main/java/resources/foto.jpg");
        pPrincipal.add(pFoto);

        //MENU
        JMenuBar menuBar = menu();
        setJMenuBar(menuBar);
    }

    /**
     * Crea un menú con las funcionalidades a las que puede acceder el usuario
     * @return Devuelve un JMenuBar con las funcionalidades
     */
    private JMenuBar menu ()
    {
        //MENU
        JMenuBar menuBar = new JMenuBar();

        JMenu mnClasificacin = new JMenu("Clasificacion");
        menuBar.add(mnClasificacin);
        JMenuItem mntmTotal = new JMenuItem("Usuarios");
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
        mntmTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frmClasificacionUsuarios ventana = new frmClasificacionUsuarios(frame, controller);
                ventana.setVisible(true);
                setVisible(false);
            }
        });

        mntmMiEquipo_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<clsJugador> plantilla = controller.obtenerPlantilla(usuario);

                if(plantilla.size() == 0)
                {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "No tienes jugadores en tu plantilla",
                            "Jugadores insuficientes",
                            JOptionPane.WARNING_MESSAGE);
                }
                else {
                    frmClasificacionEquipo ventana = new frmClasificacionEquipo(frame, controller, usuario);
                    ventana.setVisible(true);
                    setVisible(false);
                }
            }
        });

        mntmConsultar.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<clsJugador> plantilla = controller.obtenerPlantilla(usuario);

                if(plantilla.size() < 12)
                {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Necesitas tener al menos 11 jugadores en tu plantilla para gestionar la alineación",
                            "Jugadores insuficientes",
                            JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    frConsultarAlineacion ventana = new frConsultarAlineacion(frame, controller, usuario);
                    ventana.setVisible(true);
                    setVisible(false);
                }
            }
        });

        mntmModificar.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<clsJugador> plantilla = controller.obtenerPlantilla(usuario);

                if(plantilla.size() < 12)
                {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Necesitas tener al menos 11 jugadores en tu plantilla para gestionar la alineación",
                            "Jugadores insuficientes",
                            JOptionPane.WARNING_MESSAGE);
                }
                else {
                    frModificarAlineacion ventana = new frModificarAlineacion(frame, controller, usuario);
                    ventana.setVisible(true);
                    setVisible(false);
                }
            }
        });

        mntmMiEquipo.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frEstadisticas ventana = new frEstadisticas (frame, controller, usuario);
                ventana.setVisible(true);
                setVisible(false);
            }
        });

        mntmComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frmPujar ventana = new frmPujar(frame,controller, usuario);
                ventana.setVisible(true);
                setVisible(false);
            }
        });

        mntmVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frmVentaJugador ventana = new frmVentaJugador(frame,controller, usuario);
                ventana.setVisible(true);
                setVisible(false);
            }
        });

        return menuBar;
    }

}