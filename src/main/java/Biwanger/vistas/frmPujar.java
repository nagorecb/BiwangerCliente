package Biwanger.vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;
import Biwanger.objetosDominio.clsPuja;
import Biwanger.objetosDominio.clsUsuario;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;

/**
 * Ventana que permite pujar al usuario
 */
public class frmPujar extends JFrame implements ActionListener, ListSelectionListener
{
    private int altura = 800;
    private int anchura = 1066;
    private int x = 100;
    private int y = 100;
    private JPanel panelLista;
    private JList listaPlantilla;
    private DefaultListModel listmodel = new DefaultListModel();
    private JScrollPane scroll;
    private JLabel lblPrecio;
    private JTextField tfPuja;
    private JButton btnPujar, btnVolver;
    private JPanel panelVenta, pPrincipal, pBotonera;

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
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(anchura, altura);
        //this.setPreferredSize(new Dimension(anchura, altura));
        //this.setLocation(x, y);
        setResizable(false);
        setTitle("Comprar jugadores");

        pPrincipal=new JPanel();
        getContentPane().add(pPrincipal);
        pPrincipal.setLayout(new GridLayout ());

        panelLista = new JPanel();
        panelLista.setLayout(new FlowLayout());

        ArrayList<clsJugador> jugadores = controller.MostrarMercado();
        for (clsJugador j: jugadores)
        {
                listmodel.addElement(j);
        }
        listaPlantilla = new JList (listmodel);
        listaPlantilla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPlantilla.addListSelectionListener(this);

        scroll = new JScrollPane (listaPlantilla);
        scroll.setPreferredSize(new Dimension (anchura-anchura/2, altura - altura/4));
        panelLista.add(scroll);

        pPrincipal.add(panelLista);

        panelVenta = new JPanel();
        panelVenta.setLayout(new FlowLayout());
        btnPujar = new JButton ("Pujar");
        btnPujar.setEnabled(false);
        lblPrecio = new JLabel ("Importe de puja: ");

        panelVenta.add(lblPrecio);
        tfPuja = new JTextField();
        tfPuja.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String texto = tfPuja.getText();
                double parseo = 0;
                boolean esNum = parseoPrecio(texto);
                if(!esNum)
                {
                    JInternalFrame frame = (JInternalFrame)SwingUtilities.getRoot(tfPuja);
                    JOptionPane.showMessageDialog(frame,
                            "El precio debe ser un valor numerico",
                            "Inserta un precio valido",
                            JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    if(!listaPlantilla.isSelectionEmpty())
                    {
                        btnPujar.setEnabled(true);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
            }
        });
        tfPuja.addActionListener(this);
        tfPuja.setActionCommand("Precio");

        tfPuja.setPreferredSize(new Dimension(100,20));

        btnPujar.addActionListener(this);
        btnPujar.setActionCommand("PUJAR");
        panelVenta.add(tfPuja);
        panelVenta.add(btnPujar);

        pBotonera = new JPanel();
        getContentPane().add(pBotonera, BorderLayout.NORTH);
        pBotonera.setLayout(new BorderLayout(0, 0));
        btnVolver = new JButton("Volver");
        pBotonera.add(btnVolver, BorderLayout.WEST);
        btnVolver.addActionListener(this);
        btnVolver.setActionCommand("VOLVER");

        pPrincipal.add(panelVenta);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand())
        {

            case "PUJAR":
                if(tfPuja.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this,
                            "Inserta un valor para LA PUJA",
                            "Inserta un precio valido",
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
                }
                panelUsuario.setVisible(true);
                this.dispose();
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

