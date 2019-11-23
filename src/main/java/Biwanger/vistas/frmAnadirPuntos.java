package Biwanger.vistas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
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
	
	private List<clsJugador> listaJugadores;
	private clsController controller;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public frmAnadirPuntos (clsController controller, List<clsJugador> listaJugadores)
	{
		
		this.listaJugadores=listaJugadores;
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
		int posY = (int) (screenSize.height*0.02);
		int width = (int) (screenSize.width*0.9);
		int heigth = (int) (screenSize.height*0.1);
		
		//formulario
		panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout(2, 4));
		panelFormulario.setBounds(posX, posY, width, heigth);
		getContentPane().add(panelFormulario);
		
		spinnerPuntos = new JSpinner(new SpinnerNumberModel()); 
		spinnerPuntos.setBounds(70, 70, 50, 40); 
		
		lblPuntos = new JLabel("Puntos a añadir: ");
		lblPuntos.setFont(lblPuntos.getFont().deriveFont(18f));
		
		panelFormulario.add(lblPuntos);
		panelFormulario.add(spinnerPuntos);
		
		//listajugadores
		posX = (int) (screenSize.width*0.05);
		posY = (int) (screenSize.height*0.25);
		width = (int) (screenSize.width*0.7);
		heigth = (int) (screenSize.height*0.5);
		
		panelListaJugadores= new JPanel();
		panelListaJugadores.setLayout(new GridLayout());
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
			
			default:
				break;
		}
	}
	
}
