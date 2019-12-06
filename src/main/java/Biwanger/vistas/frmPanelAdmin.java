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
    private JLabel lblFoto;
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

        lblFoto =  new JLabel();
        lblFoto.setBounds(0, 0, pPrincipal.getWidth(), pPrincipal.getHeight());

        try {
            Image img = ImageIO.read(getClass().getResource("../../../../img/foto.jpg"));
            Image newimg = img.getScaledInstance( 500, 300,  java.awt.Image.SCALE_SMOOTH ) ;
            lblFoto.setIcon(new ImageIcon(newimg));
        } catch (Exception ex) {
        }

        pPrincipal.add(lblFoto);

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
                System.out.println(1);
                frPremiar ventana = new frPremiar (frame, controller);
                ventana.setVisible(true);
                setVisible(false);
            }
        });

        return menuBar;
    }
}
