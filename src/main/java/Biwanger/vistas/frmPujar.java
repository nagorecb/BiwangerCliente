package Biwanger.vistas;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;
import Biwanger.objetosDominio.clsPuja;
import Biwanger.objetosDominio.clsUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ventana que permite pujar al usuario
 */
public class frmPujar extends JFrame implements ActionListener, ListSelectionListener
{
    private int altura = 800;
    private int anchura = 1066;
    private JPanel pPrincipal, pBotonera, pLista, pContenido, pCompra, pTexto;
    private JLabel lblNewLabel, lblPuja;
    private JButton btnPujar, btnVolver;
    private JScrollPane scroll;
    private JTextField tfPuja;

    private JList listaPlantilla;
    private DefaultListModel listmodel = new DefaultListModel();

    private double precio;
    private clsJugador jugadorPuja;
    private clsController controller;
    private clsUsuario usuario;

    private JFrame panelUsuario;

    /**
     * Constructor de la ventana de pujar
     * @param frame Recibe la ventana principal desde el que ha sido lanzado
     * @param controller Recibe el controlador para añadir la funcionalidad
     * @param usuario Recibe el usuario que quiere realizar la puja
     */
    public frmPujar (JFrame frame, clsController controller, clsUsuario usuario)
    {
        this.usuario = usuario;
        this.controller = controller;
        panelUsuario = frame;

        setUndecorated(true);
        setSize(anchura, altura);
        setResizable(false);
        setTitle("Comprar jugadores");

        pPrincipal = new PanelConFondo ("src/main/java/resources/fondo.jpg");
        getContentPane().add(pPrincipal,BorderLayout.CENTER);
        pPrincipal.setLayout(new BorderLayout(0, 0));

        pBotonera = new JPanel();
        pPrincipal.add(pBotonera, BorderLayout.NORTH);
        pBotonera.setLayout(new BorderLayout(0, 0));

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(this);
        btnVolver.setActionCommand("VOLVER");
        pBotonera.add(btnVolver, BorderLayout.WEST);

        pContenido = new JPanel();
        pContenido.setBorder(new EmptyBorder(40, 40, 40, 40));
        pPrincipal.add(pContenido, BorderLayout.CENTER);
        pContenido.setLayout(new GridLayout(1, 0, 0, 0));

        pLista = new JPanel();
        pContenido.add(pLista);
        pLista.setLayout(new BorderLayout(0, 0));

        ArrayList<clsJugador> jugadores = controller.MostrarMercado();
        for (clsJugador j: jugadores)
        {
                listmodel.addElement(j);
        }
        listaPlantilla = new JList (listmodel);
        listaPlantilla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPlantilla.addListSelectionListener(this);

        scroll = new JScrollPane(listaPlantilla);
        //PreferedSize
        pLista.add(scroll);

        lblNewLabel = new JLabel("\n JUGADORES A LA VENTA \n");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pLista.add(lblNewLabel, BorderLayout.NORTH);

        pCompra = new JPanel();
        pContenido.add(pCompra);
        GridBagLayout gbl_pVenta = new GridBagLayout();
        gbl_pVenta.columnWidths = new int[]{533, 0};
        gbl_pVenta.rowHeights = new int[]{777, 0};
        gbl_pVenta.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_pVenta.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        pCompra.setLayout(gbl_pVenta);
        pCompra.setBorder(new EmptyBorder(0, 50, 0, 50));

        pTexto = new JPanel();
        GridBagConstraints gbc_pTexto = new GridBagConstraints();
        gbc_pTexto.fill = GridBagConstraints.HORIZONTAL;
        gbc_pTexto.gridx = 0;
        gbc_pTexto.gridy = 0;
        pCompra.add(pTexto, gbc_pTexto);

        lblPuja = new JLabel("Importe de puja:");
        pTexto.add(lblPuja);

        tfPuja = new JTextField();
        tfPuja.addActionListener(this);
        tfPuja.setActionCommand("Precio");
        pTexto.add(tfPuja);
        tfPuja.setColumns(10);

        btnPujar = new JButton("Pujar");
        btnPujar.addActionListener(this);
        btnPujar.setActionCommand("PUJAR");
        pTexto.add(btnPujar);

        pBotonera.setOpaque(false);
        pContenido.setOpaque(false);
        pLista.setOpaque(false);
        pCompra.setOpaque(false);
        pTexto.setBackground(Color.white);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand())
        {

            case "PUJAR":
                if((tfPuja.getText().equals(""))||(!parseoPrecio(tfPuja.getText())))
                {
                    JOptionPane.showMessageDialog(this,
                            "Inserta un valor correcto para la puja",
                            "Inserta un valor valido",
                            JOptionPane.WARNING_MESSAGE);
                }
                else if (listaPlantilla.isSelectionEmpty())
                {
                    JOptionPane.showMessageDialog(this,
                            "Selecciona el jugador que deseas comprar",
                            "Selecciona algún jugador",
                            JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    clsPuja puja = new clsPuja(usuario.getEmail(), jugadorPuja.getId(), Double.parseDouble(tfPuja.getText()));
                    controller.Pujar(puja);
                    int index = listaPlantilla.getSelectedIndex();
                    listmodel.remove(index);
                    JOptionPane.showMessageDialog(this,
                            "Puja realizada con éxito",
                            "Puja",
                            JOptionPane.INFORMATION_MESSAGE);
                    panelUsuario.setVisible(true);
                    this.dispose();
                }
                break;

            case "VOLVER":
                panelUsuario.setVisible(true);
                this.dispose();
                break;

            default:
                break;
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        JList lista = (JList)e.getSource();
        jugadorPuja = (clsJugador) lista.getSelectedValue();

        if(!(tfPuja.getText().equals(""))&&parseoPrecio(tfPuja.getText()))
        {
            btnPujar.setEnabled(true);
        }
    }

    /**
     * Realiza el parseo de String a Double
     * @param texto Recibe un String con el precio
     * @return Devuelve un Double con el precio
     */
    public boolean parseoPrecio (String texto)
    {
        try {
            precio = Double.parseDouble(texto);
            return true;
        }
        catch( NumberFormatException e1 ) {

            return false;
        }
    }
}

