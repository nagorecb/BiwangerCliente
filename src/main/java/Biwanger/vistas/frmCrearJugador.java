package Biwanger.vistas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;

public class frmCrearJugador extends JFrame implements ActionListener {

	private clsController controller;
	private Toolkit mipantalla;
	private Dimension screenSize;
	private JPanel panelFormulario;
	private JTextField textFieldNombre;
	private JTextField textFieldPosicion;
	private JTextField textFieldPrecio;
	private JTextField textFieldEquipo;
	private JLabel lblNombre;
	private JLabel lblPosicion;
	private JLabel lblPrecio;
	private JLabel lblEquipo;
	private JPanel panelGuardar;
	private JButton botonGuardar;
	@SuppressWarnings("rawtypes")
	private JComboBox comboPosicion;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public frmCrearJugador(clsController controller)
	{
		this.controller = controller;
		
		mipantalla=Toolkit.getDefaultToolkit();
		screenSize=mipantalla.getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Añadir jugador");
		setResizable(true);
		
		//Layout 
		getContentPane().setLayout(null);
		
		//Parte superior
		int posX = (int) (screenSize.width*0.05);
		int posY = (int) (screenSize.height*0.02);
		int width = (int) (screenSize.width*0.9);
		int heigth = (int) (screenSize.height*0.3);
		
		//formulario
		panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout(4, 0));
		panelFormulario.setBounds(posX, posY, width, heigth);
		getContentPane().add(panelFormulario);
		
		textFieldNombre = new JTextField();
		comboPosicion = new JComboBox();
		comboPosicion.addItem("delantero");
		comboPosicion.addItem("centrocampista");
		comboPosicion.addItem("defensa");
		comboPosicion.addItem("portero");
		textFieldPrecio = new JTextField();
		textFieldEquipo = new JTextField();
		textFieldNombre.setFont(textFieldNombre.getFont().deriveFont(18f));
		comboPosicion.setFont(comboPosicion.getFont().deriveFont(18f));
		textFieldPrecio.setFont(textFieldPrecio.getFont().deriveFont(18f));
		textFieldEquipo.setFont(textFieldEquipo.getFont().deriveFont(18f));
		
		lblNombre = new JLabel("Nombre: ");
		lblPosicion = new JLabel("Posición: ");
		lblPrecio = new JLabel("Precio: ");
		lblEquipo = new JLabel("Equipo: ");
		lblNombre.setFont(lblNombre.getFont().deriveFont(18f));
		lblPosicion.setFont(lblPosicion.getFont().deriveFont(18f));
		lblPrecio.setFont(lblPrecio.getFont().deriveFont(18f));
		lblEquipo.setFont(lblEquipo.getFont().deriveFont(18f));
		
		//todos los demas campos se inicializaran con valores predeterminados
		
		panelFormulario.add(lblNombre);
		panelFormulario.add(textFieldNombre);
		panelFormulario.add(lblPosicion);
		panelFormulario.add(comboPosicion);
		panelFormulario.add(lblPrecio);
		panelFormulario.add(textFieldPrecio);
		panelFormulario.add(lblEquipo);
		panelFormulario.add(textFieldEquipo);
		
		posX = (int) (screenSize.width*0.85);
		posY = (int) (screenSize.height*0.35);
		width = (int) (screenSize.width*0.1);
		heigth = (int) (screenSize.height*0.05);
		
		panelGuardar = new JPanel();
		panelGuardar.setLayout(new FlowLayout());
		panelGuardar.setBounds(posX, posY, width, heigth);
		getContentPane().add(panelGuardar);
		
		botonGuardar = new JButton("Guardar");
		botonGuardar.setFont(botonGuardar.getFont().deriveFont(18f));
		panelGuardar.add(botonGuardar);
		
		botonGuardar.setActionCommand("guardar");
		botonGuardar.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand())
		{
			case "guardar":
				String nombre = textFieldNombre.getText();
				String posicion = comboPosicion.getToolTipText();
				String precio = textFieldPrecio.getText();
				String equipo = textFieldEquipo.getText();
				
				clsJugador nuevoJugador = new clsJugador();
				nuevoJugador.setNombre(nombre);
				nuevoJugador.setPosicion(posicion);
				nuevoJugador.setPrecio(Integer.valueOf(precio));
				nuevoJugador.setEquipo(equipo);
				
				this.controller.guardarNuevoJugador(nuevoJugador);
				
				break;
			
			default:
				break;
		}
	}

}