package Biwanger.vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import Biwanger.controladores.clsController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPanelAdmin extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JPanel pPrincipal;
    private PanelConFondo panelFoto;
    private JMenuBar menuBar;
    private JMenu mnMenu;
    private JMenuItem mntmPremiarTresMejores,mntmIntroducirPuntuacionJornada, mntmGestionDeMercado;
    private JFrame frame;

    clsController controller;

    /**
     * Constructor de la ventana de premiar tres mejores
     */
    public frmPanelAdmin(final clsController controller)
    {
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setSize(1066, 800);
        setResizable(false);
        frame = this;

        this.controller=controller;

        pPrincipal = new JPanel();
        getContentPane().add(pPrincipal, BorderLayout.CENTER);
        pPrincipal.setLayout(new BorderLayout(0, 0));

        panelFoto = new PanelConFondo("src/main/java/resources/foto.jpg");

        pPrincipal.add(panelFoto);

        menuBar = menu();
        setJMenuBar(menuBar);
    }

    private JMenuBar menu ()
    {
        //MENU
        JMenuBar menuBar = new JMenuBar();

        mnMenu = new JMenu("Menu");
        menuBar.add(mnMenu);

        mntmPremiarTresMejores = new JMenuItem("Premiar tres mejores");
        mnMenu.add(mntmPremiarTresMejores);

        mntmIntroducirPuntuacionJornada = new JMenuItem("Introducir puntuacion jornada");
        mnMenu.add(mntmIntroducirPuntuacionJornada);

        mntmGestionDeMercado = new JMenuItem("Gestion de mercado");
        mnMenu.add(mntmGestionDeMercado);


        //Escuchadores de botones
        mntmPremiarTresMejores.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frPremiar ventana = new frPremiar (frame, controller);
                ventana.setVisible(true);
                setVisible(false);
            }
        });

        mntmIntroducirPuntuacionJornada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frmAnadirPuntos ventana = new frmAnadirPuntos(frame, controller);
                ventana.setVisible(true);
                setVisible(false);
            }
        });

        mntmGestionDeMercado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frmCrearJugador ventana = new frmCrearJugador(frame, controller);
                ventana.setVisible(true);
            }
        });

        return menuBar;
    }
}
