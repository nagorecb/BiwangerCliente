package Biwanger.vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;

/**
 * Ventana que permite al administrador añadir nuevos jugadores en el sistema.
 */
public class frmCrearJugador extends JFrame implements ActionListener {

	private clsController controller;
	private JFrame frame;

	private JPanel pPrincipal,panelFormulario, panelGuardar, pNombre, pPosicion, pPrecio, pEquipo, pEstado, pBotonera;
	private JTextField textFieldNombre, textFieldPosicion, textFieldPrecio, textFieldEquipo;
	private JLabel lblNombre, lblPosicion, lblPrecio, lblEquipo, lblEstado, lblCrearNuevoJugador;
	private JButton botonGuardar, btnVolver;
	private JComboBox comboPosicion, comboEstado;

	private int anchura = 1066;
	private int altura = 800;

	/**
	 * Constructor de la ventana que permite al administrador añadir nuevos jugadores en el sistema.
	 *
	 * @param frame      Recibe la ventana principal del que se le ha llamado
	 * @param controller Recibe el controlador para añadir la funcionalidad
	 */
	public frmCrearJugador(JFrame frame, clsController controller) {
		this.controller = controller;
		this.frame = frame;

		setUndecorated(true);
		setSize(anchura, altura);
		setResizable(false);
		setTitle("Añadir jugador");

		pPrincipal = new PanelConFondo ("src/main/java/resources/fondo.jpg");
		getContentPane().add(pPrincipal,BorderLayout.CENTER);
		pPrincipal.setLayout(new BorderLayout(0, 0));

		panelFormulario = new JPanel();
		panelFormulario.setBorder(new EmptyBorder(50, 50, 50, 50));
		panelFormulario.setLayout(new GridLayout(5, 0));
		pPrincipal.add(panelFormulario, BorderLayout.CENTER);

		pNombre = new JPanel();
		pNombre.setBorder(new EmptyBorder(30, 50, 30, 50));
		panelFormulario.add(pNombre);
		pNombre.setLayout(new GridLayout(0, 2, 0, 0));

		lblNombre = new JLabel("Nombre: ");
		pNombre.add(lblNombre);
		lblNombre.setFont(lblNombre.getFont().deriveFont(18f));

		textFieldNombre = new JTextField();
		pNombre.add(textFieldNombre);
		textFieldNombre.setFont(textFieldNombre.getFont().deriveFont(18f));

		pPosicion = new JPanel();
		pPosicion.setBorder(new EmptyBorder(30, 50, 30, 50));
		panelFormulario.add(pPosicion);
		pPosicion.setLayout(new GridLayout(0, 2, 0, 0));
		lblPosicion = new JLabel("Posición: ");
		pPosicion.add(lblPosicion);
		lblPosicion.setFont(lblPosicion.getFont().deriveFont(18f));
		comboPosicion = new JComboBox();
		pPosicion.add(comboPosicion);
		comboPosicion.addItem("Delantero");
		comboPosicion.addItem("Centrocampista");
		comboPosicion.addItem("Defensa");
		comboPosicion.addItem("Portero");
		comboPosicion.setFont(comboPosicion.getFont().deriveFont(18f));

		pPrecio = new JPanel();
		pPrecio.setBorder(new EmptyBorder(30, 50, 30, 50));
		panelFormulario.add(pPrecio);
		pPrecio.setLayout(new GridLayout(0, 2, 0, 0));
		lblPrecio = new JLabel("Precio: ");
		pPrecio.add(lblPrecio);
		lblPrecio.setFont(lblPrecio.getFont().deriveFont(18f));
		textFieldPrecio = new JTextField();
		pPrecio.add(textFieldPrecio);
		textFieldPrecio.setFont(textFieldPrecio.getFont().deriveFont(18f));

		pEquipo = new JPanel();
		pEquipo.setBorder(new EmptyBorder(30, 50, 30, 50));
		panelFormulario.add(pEquipo);
		pEquipo.setLayout(new GridLayout(0, 2, 0, 0));
		lblEquipo = new JLabel("Equipo: ");
		pEquipo.add(lblEquipo);
		lblEquipo.setFont(lblEquipo.getFont().deriveFont(18f));
		textFieldEquipo = new JTextField();
		pEquipo.add(textFieldEquipo);
		textFieldEquipo.setFont(textFieldEquipo.getFont().deriveFont(18f));

		pEstado = new JPanel();
		pEstado.setBorder(new EmptyBorder(30, 50, 30, 50));
		panelFormulario.add(pEstado);
		pEstado.setLayout(new GridLayout(0, 2, 0, 0));
		lblEstado = new JLabel("Estado: ");
		pEstado.add(lblEstado);
		lblEstado.setFont(comboPosicion.getFont().deriveFont(18f));
		comboEstado = new JComboBox();
		pEstado.add(comboEstado);
		comboEstado.addItem("Buen estado");
		comboEstado.addItem("Lesionado");
		comboEstado.setFont(comboPosicion.getFont().deriveFont(18f));

		panelGuardar = new JPanel();
		panelGuardar.setLayout(new FlowLayout());
		pPrincipal.add(panelGuardar, BorderLayout.SOUTH);

		botonGuardar = new JButton("Guardar");
		botonGuardar.setFont(botonGuardar.getFont().deriveFont(18f));
		panelGuardar.add(botonGuardar);
		botonGuardar.setActionCommand("GUARDAR");
		botonGuardar.addActionListener(this);

		pBotonera = new JPanel();
		pPrincipal.add(pBotonera, BorderLayout.NORTH);
		pBotonera.setLayout(new BorderLayout(0, 0));

		btnVolver = new JButton("Volver");
		pBotonera.add(btnVolver, BorderLayout.WEST);
		btnVolver.setActionCommand("VOLVER");
		btnVolver.addActionListener(this);

		lblCrearNuevoJugador = new JLabel("AÑADIR NUEVO JUGADOR");
		lblCrearNuevoJugador.setForeground(Color.WHITE);
		lblCrearNuevoJugador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCrearNuevoJugador.setHorizontalAlignment(SwingConstants.CENTER);
		pBotonera.add(lblCrearNuevoJugador, BorderLayout.CENTER);

		lblNombre.setForeground(Color.WHITE);
		lblEquipo.setForeground(Color.WHITE);
		lblEstado.setForeground(Color.WHITE);
		lblPosicion.setForeground(Color.WHITE);
		lblPrecio.setForeground(Color.WHITE);

		panelFormulario.setOpaque(false);
		panelGuardar.setOpaque(false);
		pBotonera.setOpaque(false);
		pEquipo.setOpaque(false);
		pEstado.setOpaque(false);
		pNombre.setOpaque(false);
		pPosicion.setOpaque(false);
		pPrecio.setOpaque(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "GUARDAR":
			{
				String nombre = textFieldNombre.getText();
				String posicion = (String) comboPosicion.getSelectedItem();
				String precio = textFieldPrecio.getText();
				String equipo = textFieldEquipo.getText();
				String estado = (String) comboEstado.getSelectedItem();

				if(!soloLetras(nombre)||!isDouble(precio))
				{
					JOptionPane.showMessageDialog(this,
							"Introduzca valores correctos",
							"Valor incorrecto",
							JOptionPane.WARNING_MESSAGE);
				}

				else if (nombre.equals("")||posicion.equals("")||precio.equals("")||equipo.equals("")||estado.equals(""))
				{
					JOptionPane.showMessageDialog(this,
							"Rellene todos los campos",
							"Campos vacíos",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					clsJugador nuevoJugador = new clsJugador();
					nuevoJugador.setNombre(nombre);
					nuevoJugador.setPosicion(posicion);
					nuevoJugador.setPrecio(Integer.valueOf(precio));
					nuevoJugador.setEquipo(equipo);
					nuevoJugador.setEnVenta(true);
					LocalDateTime fecha = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
					nuevoJugador.setFechaVenta(fecha.format(formatter));
					nuevoJugador.setEstado(estado);

					this.controller.guardarNuevoJugador(nuevoJugador);
					JOptionPane.showMessageDialog(this,
							"Jugador guardado con éxito",
							"Crear jugador",
							JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(true);
					this.dispose();
				}
			}
			break;

			case "VOLVER": {
				frame.setVisible(true);
				this.dispose();
				break;
			}
			default:
				break;
		}
	}

	/**
	 * Realiza el parseo de String a Double
	 *
	 * @param texto Recibe un String con el precio
	 * @return Devuelve un Double con el precio
	 */
	public boolean isDouble(String texto) {
		try {
			Double.parseDouble(texto);
			return true;
		} catch (NumberFormatException e1) {
			return false;
		}
	}

	/**
	 * Metodo para comprobar si los caracteres introducidos en una cadena son solo letras o no.
	 *
	 * @param cadena String a comprobar
	 * @return true si solo contiene letras, false en caso contrario
	 */
	static boolean soloLetras(String cadena)
	{
		for (int i = 0; i < cadena.length(); i++) {
			char caracter = cadena.toUpperCase().charAt(i);
			int valorASCII = (int) caracter;
			if ((valorASCII!=32)&&(valorASCII<128||valorASCII>165)&&(valorASCII<65||valorASCII>90))
				return false; //Se ha encontrado un caracter que no es letra
		}
		return true;
	}
}
