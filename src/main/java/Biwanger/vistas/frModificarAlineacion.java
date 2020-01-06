package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;
import Biwanger.objetosDominio.clsJugador;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

/**
 * Ventana que permite al usuario modificar la alineación de sus jugadores.
 */
public class frModificarAlineacion extends JFrame implements ActionListener
{	
	private static final long serialVersionUID = 1L;
	private JPanel panel,superior,pCampo,pDefensa,pPortero,pFormacion,pMedio,pDelantero;
	private JLabel lblSistemaDeJuego;
	
	private JButton btnGuardar,btnVolver,b1,b2,b3,b4;
	
	private JComboBox comboBox;
	private ArrayList<JComboBox> combos = new ArrayList();
	
	private FlowLayout flowLayout;
	private ArrayList <clsJugador> porteros,defensas,medios,delanteros;
	
	private String formacion;
	private clsUsuario usuario;

	private JFrame panelUsuario;
	clsController controller;

	ArrayList<clsJugador> plantilla;

	/**
	 * Constructor de la ventana que permite al usuario modificar la alineación de sus jugadores.
	 *
	 * @param frame Recibe la ventana principal del que se le ha llamado
	 * @param controller Recibe el controlador para añadir la funcionalidad
	 * @param usuario Recibe el usuario cuya alineación hay que consultar
	 */
	public frModificarAlineacion(JFrame frame, clsController controller, clsUsuario usuario)
	{
		this.usuario = usuario;
		this.controller = controller;
		panelUsuario=frame;
		this.formacion = usuario.getFormacion();

		setUndecorated(true);
//		setDefaultCloseOperation(Frame.NORMAL);
		setSize(1066, 800);
		setResizable(false);

		pCampo = new PanelConFondo("src/main/java/resources/cesped.jpg");
		pCampo.setForeground(new Color(0, 128, 0));
		pCampo.setBorder(null);
		getContentPane().add(pCampo, BorderLayout.CENTER);

		pFormacion = new JPanel();
		flowLayout = (FlowLayout) pFormacion.getLayout();
		flowLayout.setHgap(150);
		pFormacion.setOpaque(false);
		pCampo.add(pFormacion);

		pPortero = new JPanel();
		pPortero.setOpaque(false);
		pFormacion.add(pPortero);

		pDefensa = new JPanel();
		pDefensa.setOpaque(false);
		pFormacion.add(pDefensa);

		pMedio = new JPanel();
		pMedio.setOpaque(false);
		pFormacion.add(pMedio);

		pDelantero = new JPanel();
		pDelantero.setOpaque(false);
		pFormacion.add(pDelantero);

		superior = new JPanel();
		getContentPane().add(superior, BorderLayout.NORTH);
		superior.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		superior.add(panel);

		plantilla = controller.obtenerPlantilla(usuario);

		porteros = usuario.getPosicion("Portero", plantilla);
		defensas = usuario.getPosicion("Defensa", plantilla);
		medios = usuario.getPosicion("Centrocampista", plantilla);
		delanteros = usuario.getPosicion("Delantero", plantilla);

		btnGuardar = new JButton("Guardar");
		getContentPane().add(btnGuardar, BorderLayout.SOUTH);

		btnVolver = new JButton("Volver");
		superior.add(btnVolver, BorderLayout.WEST);

		lblSistemaDeJuego = new JLabel("Sistema de Juego");
		panel.add(lblSistemaDeJuego);

		b1 = new JButton("4-3-3");
		panel.add(b1);
		b2 = new JButton("4-4-2");
		panel.add(b2);
		b3 = new JButton("4-5-1");
		panel.add(b3);
		b4 = new JButton("3-5-2");
		panel.add(b4);

		b1.setActionCommand("4-3-3");
		b1.addActionListener(this);
		b2.setActionCommand("4-4-2");
		b2.addActionListener(this);
		b3.setActionCommand("4-5-1");
		b3.addActionListener(this);
		b4.setActionCommand("3-5-2");
		b4.addActionListener(this);

		btnGuardar.setActionCommand("GUARDAR");
		btnGuardar.addActionListener(this);
		btnVolver.setActionCommand("VOLVER");
		btnVolver.addActionListener(this);

		if (usuario.getFormacion()!=null)
		{
			formar(usuario.getFormacion());
			titulares();
		}
	}

	/**
	 * Coloca como los elementos seleccionados de los combo boxes los jugadores alineados
	 */
	public void titulares()
	{
		int contadorCombos = 0;
		int contadorJugadores = 0;

		for (int i=0;i<porteros.size();i++)
		{
			if (porteros.get(i).isAlineado())
			{
				combos.get(contadorCombos).setSelectedIndex(contadorJugadores);
				contadorCombos++;
			}

			contadorJugadores++;
		}

		contadorJugadores = 0;

		for(int i=0;i<defensas.size();i++)
		{
			if(defensas.get(i).isAlineado())
			{
				combos.get(contadorCombos).setSelectedIndex(contadorJugadores);
				contadorCombos++;
			}

			contadorJugadores++;
		}

		contadorJugadores = 0;

		for(int i=0;i<medios.size();i++)
		{
			if(medios.get(i).isAlineado())
			{
				combos.get(contadorCombos).setSelectedIndex(contadorJugadores);
				contadorCombos++;
			}

			contadorJugadores++;
		}

		contadorJugadores = 0;

		for(int i=0;i<delanteros.size();i++)
		{
			if(delanteros.get(i).isAlineado())
			{
				combos.get(contadorCombos).setSelectedIndex(contadorJugadores);
				contadorCombos++;
			}

			contadorJugadores++;
		}
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case "4-3-3":
			{
				formacion = "4-3-3";
				formar(formacion);
				break;
	        		
			}
			case "4-4-2":
			{
				formacion = "4-4-2";
				formar(formacion);
				break;
			}			
			case "4-5-1":
			{
				formacion = "4-5-1";
				formar(formacion);	
				break;
			}	
			case "3-5-2":
			{
				formacion = "3-5-2";
				formar(formacion);
				break;
			}

			case "VOLVER":
			{
				panelUsuario.setVisible(true);
				dispose();
				break;
			}
			case "GUARDAR":
			{
				guardarFormacion();
				int once=0;
				for (int i=0; i<plantilla.size();i++)
				{
					if(plantilla.get(i).isAlineado())
					{
						once++;
					}
				}
				System.out.println(once);
				if (once!=11)
				{
					JOptionPane.showMessageDialog(null, "Alineación indebida. Jugador repetido", "Error", JOptionPane.DEFAULT_OPTION);
					for (int i=0; i<plantilla.size();i++)
					{
						plantilla.get(i).setAlineado(false);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Alineación guardada con éxito", "Guardado", JOptionPane.DEFAULT_OPTION);

					usuario.setFormacion(formacion);
					controller.modificarAlineacion(plantilla);
					controller.modificarFormacion(usuario);

					this.dispose();
					panelUsuario.setVisible(true);
				}
				break;
			}

			default:
				break;
	    }
	}

	/**
	 * Vacía los paneles de la formación
	 */
	public void limpiar()
	{
		pPortero.removeAll();
		pDefensa.removeAll();
		pMedio.removeAll();
		pDelantero.removeAll();
		//flowLayout.setVgap(140);
		
		pFormacion.revalidate();
		pFormacion.repaint();
	}

	/**
	 * Popula los combo boxes con todos los jugadores
	 * @param formacion Recibe la formación del usuario
	 */
	public void formar(String formacion) 
	{
		limpiar();
		combos.clear();
		
		int def= Integer.parseInt(""+formacion.charAt(0));
		int med= Integer.parseInt(""+formacion.charAt(2));
		int del= Integer.parseInt(""+formacion.charAt(4));
		
		if (def>4|med>4|del>4)
			flowLayout.setVgap(90);
		else
			flowLayout.setVgap(140);
		
		comboBox = new JComboBox();
		combos.add(comboBox);
		for (int i=0; i<porteros.size();i++)
		{
			pPortero.add(comboBox);
			comboBox.addItem(porteros.get(i).getNombre());
		}
		
		for (int i=0; i<def; i++)
		{
			comboBox = new JComboBox();
			combos.add(comboBox);
			pDefensa.add(comboBox);
			for (int j=0; j<defensas.size();j++)
			{
				comboBox.addItem(defensas.get(j).getNombre());
			}
		}
		pDefensa.setLayout(new GridLayout(def, 1, 100, 100));
		
		for (int i=0; i<med; i++)
		{
			comboBox = new JComboBox();
			combos.add(comboBox);
			pMedio.add(comboBox);
			for (int j=0; j<medios.size();j++)
			{
				comboBox.addItem(medios.get(j).getNombre());
			}
		}
		pMedio.setLayout(new GridLayout(med, 1, 100, 100));
		
		for (int i=0; i<del; i++)
		{
			comboBox = new JComboBox();
			combos.add(comboBox);
			pDelantero.add(comboBox);
			for (int j=0; j<delanteros.size();j++)
			{
				comboBox.addItem(delanteros.get(j).getNombre());
			}
		}
		pDelantero.setLayout(new GridLayout(del, 1, 100, 100));
		
		pFormacion.revalidate();
		pFormacion.repaint();		
	}

	/**
	 * Guarda la alineación del usuario
	 */
	public void guardarFormacion()
	{
		for (int i=0; i<plantilla.size();i++)
		{
			plantilla.get(i).setAlineado(false);
			
			for (JComboBox comboBox : combos)
			{
				if(plantilla.get(i).getNombre()==comboBox.getSelectedItem())
				{
					System.out.println(plantilla.get(i));
					plantilla.get(i).setAlineado(true);
				}
				
			}
		}
	}
}
