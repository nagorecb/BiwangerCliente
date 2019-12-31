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
import Biwanger.objetosDominio.clsUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ventana que premite poner un jugador en venta
 */
public class frmVentaJugador extends JFrame implements ActionListener, ListSelectionListener
{
	private int altura = 800;
	private int anchura = 1066;
	private JPanel pPrincipal, pBotonera, pLista, pContenido, pVenta, pTexto;
	private JLabel lblNewLabel, lblPrecio;
	private JButton btnVender, btnVolver;
	private JScrollPane scroll;
	private JTextField tfPrecio;

	private JList listaPlantilla;
	private DefaultListModel listmodel = new DefaultListModel();

	private double precio;

	private clsJugador jugadorVenta;
	private clsController controller;

	private JFrame panelUsuario;

	/**
	 * Constructor de la ventana que permite poner un jugador en venta
	 *
	 * @param frame      Recibe la ventana principal del que se le ha llamado
	 * @param controller Recibe el controlador para añadir la funcionalidad
	 * @param usuario    Recibe el usuario que quiere poner un jugador en venta
	 */
	public frmVentaJugador(JFrame frame, clsController controller, clsUsuario usuario)//Hay que pasar de Inicio de sesion y registro el usuario a la pantalla principal, y que esta le pase el usuario a las que las necesiten
	{
		this.controller = controller;
		panelUsuario = frame;

		setUndecorated(true);
		setSize(anchura, altura);
		setResizable(false);
		setTitle("Vender jugadores");

		pPrincipal = new PanelConFondo("src/main/java/resources/fondo.jpg");
		getContentPane().add(pPrincipal, BorderLayout.CENTER);
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

		ArrayList<clsJugador> plantilla = controller.obtenerPlantilla(usuario);
		for (clsJugador j : plantilla) {
			if (!j.isEnVenta())
				listmodel.addElement(j);
		}
		listaPlantilla = new JList(listmodel);
		listaPlantilla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaPlantilla.addListSelectionListener(this);

		scroll = new JScrollPane(listaPlantilla);
		//PreferedSize
		pLista.add(scroll);

		lblNewLabel = new JLabel("\n JUGADORES DE TU PLANTILLA \n");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pLista.add(lblNewLabel, BorderLayout.NORTH);

		pVenta = new JPanel();
		pContenido.add(pVenta);
		GridBagLayout gbl_pVenta = new GridBagLayout();
		gbl_pVenta.columnWidths = new int[]{533, 0};
		gbl_pVenta.rowHeights = new int[]{777, 0};
		gbl_pVenta.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pVenta.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pVenta.setLayout(gbl_pVenta);
		pVenta.setBorder(new EmptyBorder(0, 50, 0, 50));

		pTexto = new JPanel();
		GridBagConstraints gbc_pTexto = new GridBagConstraints();
		gbc_pTexto.fill = GridBagConstraints.HORIZONTAL;
		gbc_pTexto.gridx = 0;
		gbc_pTexto.gridy = 0;
		pVenta.add(pTexto, gbc_pTexto);

		lblPrecio = new JLabel("Precio de partida:");
		pTexto.add(lblPrecio);

		tfPrecio = new JTextField();
		tfPrecio.addActionListener(this);
		tfPrecio.setActionCommand("Precio");
		pTexto.add(tfPrecio);
		tfPrecio.setColumns(10);

		btnVender = new JButton("Vender");
		btnVender.addActionListener(this);
		btnVender.setActionCommand("VENDER");
		pTexto.add(btnVender);

		pBotonera.setOpaque(false);
		pContenido.setOpaque(false);
		pLista.setOpaque(false);
		pVenta.setOpaque(false);
		pTexto.setBackground(Color.white);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {

			case "VENDER":
				if ((tfPrecio.getText().equals(""))||(!parseoPrecio(tfPrecio.getText())))
				{
					JOptionPane.showMessageDialog(this,
							"Inserta un valor correcto para el precio",
							"Inserta un precio valido",
							JOptionPane.WARNING_MESSAGE);
				}
				else if (listaPlantilla.isSelectionEmpty())
				{
					JOptionPane.showMessageDialog(this,
							"Selecciona el jugador que deseas vender",
							"Selecciona algún jugador",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					controller.venderJugador(precio, jugadorVenta);
					int index = listaPlantilla.getSelectedIndex();
					listmodel.remove(index);
					JOptionPane.showMessageDialog(this,
							"Jugador puesto en venta con éxito",
							"Jugador en venta",
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
		jugadorVenta = (clsJugador) lista.getSelectedValue();

		if(!(tfPrecio.getText().equals(""))&&parseoPrecio(tfPrecio.getText()))
		{
			btnVender.setEnabled(true);
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
