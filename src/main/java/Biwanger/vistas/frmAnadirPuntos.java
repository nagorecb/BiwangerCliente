package Biwanger.vistas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;
import java.awt.BorderLayout;

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

	private ArrayList<clsJugador> listaJugadores;
	private clsController controller;
	private JPanel pPrincipal;
	private JPanel pBotonera;
	private JButton btnVolver;

	private JFrame panelAdmin;

	public frmAnadirPuntos (JFrame frame, clsController controller)
	{
		panelAdmin = frame;

		this.listaJugadores = controller.obtenerTodosJugadores();
		this.controller = controller;

		setUndecorated(true);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//mipantalla=Toolkit.getDefaultToolkit();
		//screenSize=mipantalla.getScreenSize();
		setSize(1066,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Añadir puntos a un jugador");
		setResizable(true);

		//Parte superior
		int posX = (int) (screenSize.width*0.05);
		int posY = (int) (screenSize.height*0.1);
		int width = (int) (screenSize.width*0.9);
		int heigth = (int) (screenSize.height*0.1);

		//listajugadores
		posX = (int) (screenSize.width*0.05);
		posY = (int) (screenSize.height*0.25);
		width = (int) (screenSize.width*0.9);
		heigth = (int) (screenSize.height*0.2);

		model = new DefaultListModel();

		if(!listaJugadores.isEmpty())
		{
			for (clsJugador jugador : listaJugadores)
			{
				model.addElement(jugador.getNombre());
			}
		}
		getContentPane().setLayout(new BorderLayout(0, 0));

		pPrincipal = new JPanel();
		getContentPane().add(pPrincipal, BorderLayout.CENTER);
		pPrincipal.setLayout(null);

		//formulario
		panelFormulario = new JPanel();
		panelFormulario.setBounds(0, 0, 0, 0);
		pPrincipal.add(panelFormulario);
		panelFormulario.setLayout(new GridLayout(1, 1));

		spinnerPuntos = new JSpinner(new SpinnerNumberModel());
		spinnerPuntos.setBounds(30, 30, 30, 30);
		spinnerPuntos.setFont(spinnerPuntos.getFont().deriveFont(18f));

		lblPuntos = new JLabel("Puntos a añadir: ");
		lblPuntos.setFont(lblPuntos.getFont().deriveFont(18f));

		panelFormulario.add(lblPuntos);
		panelFormulario.add(spinnerPuntos);

		panelListaJugadores= new JPanel();
		panelListaJugadores.setBounds(0, 0, 0, 0);
		pPrincipal.add(panelListaJugadores);
		panelListaJugadores.setLayout(new GridLayout(1,1));

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

		pBotonera = new JPanel();
		getContentPane().add(pBotonera, BorderLayout.NORTH);
		pBotonera.setLayout(new BorderLayout(0, 0));

		btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("VOLVER");
		btnVolver.addActionListener(this);
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
				break;

			default:
				break;
		}
	}
}