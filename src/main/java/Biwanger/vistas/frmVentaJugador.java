package Biwanger.vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
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
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;
import Biwanger.objetosDominio.clsUsuario;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.JLabel;

public class frmVentaJugador extends JInternalFrame implements ActionListener, ListSelectionListener
{
	private int altura = 300;
    private int anchura = 600;
    private int x = 100;
    private int y = 100;
    private JPanel panelLista;
    private JList listaPlantilla;
    private DefaultListModel listmodel = new DefaultListModel();
    private JScrollPane scroll;
    private JLabel lblPrecio;
    private JTextField tfPrecio;
    private JButton btnVender;
    private JPanel panelVenta;
    
    private double precio;
    private clsJugador jugadorVenta;
    private clsController controller;
    
	public frmVentaJugador (clsController controller, clsUsuario usuario)//Hay que pasar de Inicio de sesion y registro el usuario a la pantalla principal, y que esta le pase el usuario a las que las necesiten
	{
		this.controller = controller;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(anchura, altura));
        //this.setPreferredSize(new Dimension(anchura, altura));
        this.setLocation(x, y);
        setResizable(true);
        setTitle("Venta de jugadores");
        
        this.getContentPane().setLayout(new GridLayout ());
        
        panelLista = new JPanel();
        panelLista.setLayout(new FlowLayout());
        for (clsJugador j: usuario.getPlantilla())
        {
        	listmodel.addElement(j);
        }
        listaPlantilla = new JList (listmodel);           
        listaPlantilla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPlantilla.addListSelectionListener(this);
        
        scroll = new JScrollPane (listaPlantilla);
        scroll.setPreferredSize(new Dimension (anchura-anchura/2, altura - altura/4));
        panelLista.add(scroll);
       
        this.getContentPane().add(panelLista);
        
        panelVenta = new JPanel();
        panelVenta.setLayout(new FlowLayout());
        btnVender = new JButton ("Vender");
        btnVender.setEnabled(false);
        lblPrecio = new JLabel ("Precio de partida: ");
        
        panelVenta.add(lblPrecio);
        tfPrecio = new JTextField();
        tfPrecio.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				String texto = tfPrecio.getText();
				double parseo = 0;
				boolean esNum = parseoPrecio(texto);
				 if(!esNum)
				 {
		             JInternalFrame frame = (JInternalFrame)SwingUtilities.getRoot(tfPrecio);
					 JOptionPane.showMessageDialog(frame,
	                         "El precio debe ser un valor numerico",
	                         "Inserta un precio valido",
	                         JOptionPane.WARNING_MESSAGE);
				 }
				 else
				 {
					 if(!listaPlantilla.isSelectionEmpty())
					 {
						 btnVender.setEnabled(true);
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
        tfPrecio.addActionListener(this);
        tfPrecio.setActionCommand("Precio");
        
        tfPrecio.setPreferredSize(new Dimension(100,20));
        
        btnVender.addActionListener(this);
        btnVender.setActionCommand("VENDER");
        panelVenta.add(tfPrecio);
        panelVenta.add(btnVender);
        
        this.getContentPane().add(panelVenta);
        this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand())
		{
			
		case "VENDER":
			if(tfPrecio.getText().equals(""))
			{
				 JOptionPane.showMessageDialog(this,
                        "Inserta un valor para el precio",
                        "Inserta un precio valido",
                        JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				controller.venderJugador(precio,jugadorVenta);
				int index = listaPlantilla.getSelectedIndex();
				listmodel.remove(index);
				JOptionPane.showMessageDialog(this,
                        "Jugador puesto en venta con éxito",
                        "Jugador en venta",
                        JOptionPane.INFORMATION_MESSAGE);
			}
			
			break;
			
		default:
			break;
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		JList lista = (JList)e.getSource();
		jugadorVenta = (clsJugador) lista.getSelectedValue();
		
		if(!(tfPrecio.getText().equals(""))&&parseoPrecio(tfPrecio.getText()))
		{
			btnVender.setEnabled(true);
		}
		
	}

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
