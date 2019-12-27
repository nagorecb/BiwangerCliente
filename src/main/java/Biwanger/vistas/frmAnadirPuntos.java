package Biwanger.vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;

/**
 * Ventana que permite al administrador añadir puntuación a los jugadores.
 */
public class frmAnadirPuntos extends JFrame implements ActionListener
{
	private Dimension screenSize;
	private JPanel panelFormulario;
	private JLabel lblPuntos;
	private JPanel panelListaJugadores;
	private DefaultListModel model;
	private JList jListJugadores;
	private JScrollPane scrollLista;
	private JButton btnAnadir;
	private static Toolkit mipantalla;
	private JSpinner spinnerPuntos;
	private JButton btnVolver;
	private JPanel pBotonera = new JPanel();

	private ArrayList<clsJugador> listaJugadores;
	private clsController controller;

	private JFrame panelAdmin;

	/**
	 * Constructor de la ventana que permite al administrador añadir puntuación a los jugadores.
	 * @param frame Recibe la ventana principal del que se le ha llamado
	 * @param controller Recibe el controlador para añadir la funcionalidad
	 */
	public frmAnadirPuntos (JFrame frame, clsController controller)
	{
		panelAdmin = frame;

		this.listaJugadores = controller.obtenerTodosJugadores();
		this.controller = controller;

		mipantalla=Toolkit.getDefaultToolkit();
		screenSize=mipantalla.getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Añadir puntos a un jugador");
		setResizable(true);

		//Layout
		getContentPane().setLayout(null);

		//Parte superior
		int posX = (int) (screenSize.width*0.05);
		int posY = (int) (screenSize.height*0.1);
		int width = (int) (screenSize.width*0.9);
		int heigth = (int) (screenSize.height*0.1);

		//formulario
		panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout(1, 1));
		panelFormulario.setBounds(posX, posY, width, heigth);
		getContentPane().add(panelFormulario);

		spinnerPuntos = new JSpinner(new SpinnerNumberModel());
		spinnerPuntos.setBounds(30, 30, 30, 30);
		spinnerPuntos.setFont(spinnerPuntos.getFont().deriveFont(18f));

		lblPuntos = new JLabel("Puntos a añadir: ");
		lblPuntos.setFont(lblPuntos.getFont().deriveFont(18f));

		panelFormulario.add(lblPuntos);
		panelFormulario.add(spinnerPuntos);

		//listajugadores
		posX = (int) (screenSize.width*0.05);
		posY = (int) (screenSize.height*0.25);
		width = (int) (screenSize.width*0.9);
		heigth = (int) (screenSize.height*0.2);

		panelListaJugadores= new JPanel();
		panelListaJugadores.setLayout(new GridLayout(1,1));
		panelListaJugadores.setBounds(posX, posY, width, heigth);
		getContentPane().add(panelListaJugadores);

		model = new DefaultListModel();

		if(!listaJugadores.isEmpty())
		{

			for (clsJugador jugador : listaJugadores)
			{
				model.addElement(jugador.getNombre());
			}
		}
		jListJugadores = new JList(model);
		jListJugadores.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));

		scrollLista = new JScrollPane(jListJugadores);
		scrollLista.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));

		//boton añadir
		btnAnadir = new JButton("Añadir puntos");
		btnAnadir.setFont(btnAnadir.getFont().deriveFont(18f));

		btnAnadir.setActionCommand("anadirPuntos");
		btnAnadir.addActionListener(this);

		panelListaJugadores.add(scrollLista);
		panelListaJugadores.add(btnAnadir);

		btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("VOLVER");
		btnVolver.addActionListener(this);

		getContentPane().add(pBotonera, BorderLayout.NORTH);
		pBotonera.setLayout(new BorderLayout(0, 0));

		pBotonera.add(btnVolver, BorderLayout.WEST);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand())
		{
			case "anadirPuntos":
				int index = jListJugadores.getSelectedIndex();
				clsJugador jugador = listaJugadores.get(index);
				int puntos= (int) spinnerPuntos.getValue();

				this.controller.anadirPuntos(jugador, puntos);

				break;

			case "VOLVER":
				panelAdmin.setVisible(true);
				dispose();
				JOptionPane.showMessageDialog(null, "Puntos añadidos con exito", "Guardado", JOptionPane.DEFAULT_OPTION);
				break;

			default:
				break;
		}
	}

}
