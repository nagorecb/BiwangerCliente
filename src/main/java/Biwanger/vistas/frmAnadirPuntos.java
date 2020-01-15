package Biwanger.vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;

/**
 * Ventana que permite al administrador añadir puntuación a los jugadores.
 */
public class frmAnadirPuntos extends JFrame implements ActionListener {
	private int altura = 800;
	private int anchura = 1066;
	private JPanel pPrincipal, pBotonera, pLista, pContenido, pPuntos, pTexto;
	private JLabel lblNewLabel, lblPuntos;
	private JButton btnPuntos, btnVolver;
	private JScrollPane scroll;
	private JTextField tfPuntos;
	private JList listaJugadores;

	private ArrayList<clsJugador> jugadores;
	private DefaultListModel listmodel = new DefaultListModel();

	private clsController controller;
	private int puntos;
	private clsJugador jugadorPuntos;

	private JFrame panelAdmin;

	/**
	 * Constructor de la ventana que permite al administrador añadir puntuación a los jugadores.
	 * @param frame Recibe la ventana principal del que se le ha llamado
	 * @param controller Recibe el controlador para añadir la funcionalidad
	 */
	public frmAnadirPuntos (JFrame frame, clsController controller)
	{
		panelAdmin = frame;
		this.jugadores = controller.obtenerTodosJugadores();
		this.controller = controller;

		setUndecorated(true);
		setSize(anchura, altura);
		setResizable(false);
		setTitle("Añadir puntos a jugadores");

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

		listmodel = new DefaultListModel();

		if(!jugadores.isEmpty())
		{
			for (clsJugador jugador : jugadores)
			{
				listmodel.addElement(jugador.getNombre());
			}
		}
		listaJugadores = new JList(listmodel);
		listaJugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scroll = new JScrollPane(listaJugadores);
		pLista.add(scroll);

		lblNewLabel = new JLabel("\n LISTA DE JUGADORES \n");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pLista.add(lblNewLabel, BorderLayout.NORTH);

		pPuntos = new JPanel();
		pContenido.add(pPuntos);
		GridBagLayout gbl_pVenta = new GridBagLayout();
		gbl_pVenta.columnWidths = new int[]{533, 0};
		gbl_pVenta.rowHeights = new int[]{777, 0};
		gbl_pVenta.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pVenta.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pPuntos.setLayout(gbl_pVenta);
		pPuntos.setBorder(new EmptyBorder(0, 50, 0, 50));

		pTexto = new JPanel();
		GridBagConstraints gbc_pTexto = new GridBagConstraints();
		gbc_pTexto.fill = GridBagConstraints.HORIZONTAL;
		gbc_pTexto.gridx = 0;
		gbc_pTexto.gridy = 0;
		pPuntos.add(pTexto, gbc_pTexto);
		lblPuntos = new JLabel("Puntos a añadir:");
		pTexto.add(lblPuntos);
		JLabel lblTarjetas = new JLabel("Tarjetas amarillas a añadir:");
		pTexto.add(lblTarjetas);

		tfPuntos = new JTextField();
		tfPuntos.addActionListener(this);
		tfPuntos.setActionCommand("Puntos");
		pTexto.add(tfPuntos);
		tfPuntos.setColumns(10);

		JTextField tfTarjetas = new JTextField();
		pTexto.add(tfTarjetas);
		tfTarjetas.setColumns(10);

		btnPuntos = new JButton("Añadir puntos");
		btnPuntos.addActionListener(this);
		btnPuntos.setActionCommand("AÑADIR");
		pTexto.add(btnPuntos);

		pBotonera.setOpaque(false);
		pContenido.setOpaque(false);
		pLista.setOpaque(false);
		pPuntos.setOpaque(false);
		pTexto.setBackground(Color.white);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand())
		{
			case "AÑADIR":
				if((tfPuntos.getText().equals(""))||(!isNumeric(tfPuntos.getText())))
				{
					JOptionPane.showMessageDialog(this,
							"Inserta un valor correcto al puntuar",
							"Valor erroneo",
							JOptionPane.WARNING_MESSAGE);
				}
				else if (listaJugadores.isSelectionEmpty())
				{
					JOptionPane.showMessageDialog(this,
							"Selecciona el jugador que deseas puntuar",
							"Ningún jugador seleccionado",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					int index = listaJugadores.getSelectedIndex();
					clsJugador jugador = jugadores.get(index);
					controller.anadirPuntos(jugador, puntos);

					JOptionPane.showMessageDialog(this,
							"Puntos añadidos con éxito",
							"Añadir puntos",
							JOptionPane.INFORMATION_MESSAGE);
					panelAdmin.setVisible(true);
					this.dispose();
				}
				break;

			case "VOLVER":
				panelAdmin.setVisible(true);
				dispose();
				break;

			default:
				break;
		}
	}
	/**
	 * Realiza el parseo de String a Integer
	 * @param texto Recibe un String con los puntos
	 * @return Devuelve un true en caso de que sea Integer, false en caso contrario
	 */
	public boolean isNumeric(String texto)
	{
		try {
			puntos = Integer.parseInt(texto);
			return true;
		}
		catch( NumberFormatException e1 ) {
			return false;
		}
	}
}
