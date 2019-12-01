package Biwanger.vistas;

import javax.swing.*;
import Biwanger.controladores.clsController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPanelAdmin extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JPanel pPrincipal;
    private PanelConFondo pFoto;
    private JMenuBar menuBar;
    private JMenu mnMenu;
    private JMenuItem mntmPremiarTresMejores,mntmIntroducirPuntuacionJornada, mntmGestionDeMercado;

    clsController controller;

    /**
     * Constructor de la ventana de premiar tres mejores
     */
    public frmPanelAdmin(final clsController controller)
    {
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setSize(1066, 800);
        setResizable(false);

        this.controller=controller;

        pPrincipal = new JPanel();
        getContentPane().add(pPrincipal, BorderLayout.CENTER);
        pPrincipal.setLayout(new BorderLayout(0, 0));

        pFoto = new PanelConFondo ("/img/foto.jpg");
        pPrincipal.add(pFoto);

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
                frPremiar ventana = new frPremiar (controller);
                ventana.setVisible(true);
                setVisible(false);
            }
        });

        return menuBar;
    }
}
