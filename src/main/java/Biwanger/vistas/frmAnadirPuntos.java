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
 * @brief Ventana que permite al administrador añadir puntuación a los jugadores.
 */
public class frmAnadirPuntos extends JFrame implements ActionListener {
	private int altura = 800;
	private int anchura = 1066;
	private JPanel pPrincipal, pBotonera, pLista, pContenido, pPuntos;
	private JLabel lblNewLabel, lblPuntos, lblAsistencias, lblGoles, lblPartidos, lblTA, lblTR;
	private JButton btnPuntos, btnVolver;
	private JScrollPane scroll;
	private JTextField tfPuntos, tfAsistencias, tfGoles, tfPartidos, tfTA, tfTR;
	private JList listaJugadores;

	private ArrayList<clsJugador> jugadores;
	private DefaultListModel listmodel = new DefaultListModel();

	private clsController controller;
	private int puntos;
	private int asistencias;
	private int goles;
	private int partidos;
	private int TA;
	private int TR;
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

		pPuntos = new JPanel();
		pPuntos.setLayout(null);

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

		//Label Puntos
		lblPuntos = new JLabel ("Puntos a añadir: ");
		lblPuntos.setBounds(28, 67, 136, 28);
		lblPuntos.setForeground(Color.white);
		pPuntos.add(lblPuntos);

		//TextField Puntos
		tfPuntos = new JTextField("0");
		tfPuntos.setLocation(168, 64);
		tfPuntos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfPuntos.setSize(136, 31);
		pPuntos.add(tfPuntos);

		//Label asis
		lblAsistencias = new JLabel ("Asistencias a añadir: ");
		lblAsistencias.setBounds(28, 103, 170, 28);
		lblAsistencias.setForeground(Color.white);
		pPuntos.add(lblAsistencias);

		//TextField Asis
		tfAsistencias = new JTextField("0");
		tfAsistencias.setLocation(178, 100);
		tfAsistencias.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfAsistencias.setSize(125, 31);
		pPuntos.add(tfAsistencias);

		//Label Goles
		lblGoles = new JLabel ("Goles a añadir: ");
		lblGoles.setBounds(28, 139, 170, 28);
		lblGoles.setForeground(Color.white);
		pPuntos.add(lblGoles);

		//TextField Goles
		tfGoles = new JTextField("0");
		tfGoles.setLocation(141, 136);
		tfGoles.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfGoles.setSize(163, 31);
		pPuntos.add(tfGoles);

		//Label Partidos
		lblPartidos = new JLabel ("Partidos a añadir: ");
		lblPartidos.setBounds(28, 173, 170, 28);
		lblPartidos.setForeground(Color.white);
		pPuntos.add(lblPartidos);

		//TextField Partidos
		tfPartidos = new JTextField("0");
		tfPartidos.setLocation(168, 170);
		tfPartidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfPartidos.setSize(136, 31);
		pPuntos.add(tfPartidos);

		//Label TA
		lblTA = new JLabel ("Tarj. amarillas a añadir: ");
		lblTA.setBounds(28, 211, 175, 28);
		lblTA.setForeground(Color.white);
		pPuntos.add(lblTA);

		//TextField TA
		tfTA = new JTextField("0");
		tfTA.setLocation(215, 208);
		tfTA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfTA.setSize(85, 31);
		pPuntos.add(tfTA);

		//Label TR
		lblTR = new JLabel ("Tarj. rojas a añadir: ");
		lblTR.setBounds(28, 251, 175, 28);
		lblTR.setForeground(Color.white);
		pPuntos.add(lblTR);

		//TextField TR
		tfTR = new JTextField("0");
		tfTR.setLocation(215, 248);
		tfTR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfTR.setSize(85, 31);
		pPuntos.add(tfTR);

		btnPuntos = new JButton("Añadir");
		btnPuntos.setBounds(107, 322, 125, 28);
		btnPuntos.addActionListener(this);
		btnPuntos.setActionCommand("AÑADIR");
		pPuntos.add(btnPuntos);

		pContenido.add(pPuntos);

		pBotonera.setOpaque(false);
		pContenido.setOpaque(false);
		pLista.setOpaque(false);
		pPuntos.setOpaque(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand())
		{
			case "AÑADIR":
				if((tfPuntos.getText().equals(""))||!isNumeric(tfPuntos.getText(),"PUNTOS") && (tfGoles.getText().equals(""))||!isNumeric(tfGoles.getText(), "GOLES") &&  (tfAsistencias.getText().equals(""))||!isNumeric(tfAsistencias.getText(), "ASIST") && (tfPartidos.getText().equals(""))||!isNumeric(tfPartidos.getText(), "PARTIDOS") && (tfTA.getText().equals(""))||!isNumeric(tfTA.getText(), "TA") && (tfTR.getText().equals(""))||!isNumeric(tfTR.getText(), "TR"))
				{
					JOptionPane.showMessageDialog(this,
							"Inserta valores correctos",
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
					controller.anadirPuntos(jugador, puntos, asistencias, goles, partidos, TA, TR);

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
	 * @param cambio define qué se parsea
	 * @return Devuelve un true en caso de que sea Integer, false en caso contrario
	 */
	public boolean isNumeric(String texto, String cambio)
	{
		try {
			switch(cambio)
			{
				case "PUNTOS":
					puntos = Integer.parseInt(texto);
					return true;

				case "ASIST":
					asistencias = Integer.parseInt(texto);
					return true;

				case "GOLES":
					goles = Integer.parseInt(texto);
					return true;

				case "PARTIDOS":
					partidos = Integer.parseInt(texto);
					return true;

				case "TA":
					TA = Integer.parseInt(texto);
					return true;

				case "TR":
					TR = Integer.parseInt(texto);
					return true;

				default:
					return false;
			}
		}
		catch( NumberFormatException e1 ) {
			return false;
		}
	}
}
