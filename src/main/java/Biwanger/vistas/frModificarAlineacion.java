package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;
import Biwanger.objetosDominio.clsJugador;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

public class frModificarAlineacion extends JFrame implements ActionListener
{	
	private static final long serialVersionUID = 1L;
	private JPanel panel,pCampo,pDefensa,pPortero,pFormacion,pMedio,pDelantero;
	private JLabel lblSistemaDeJuego;
	
	private JButton btnGuardar,b1,b2,b3,b4;
	
	private JComboBox<clsJugador> comboBox;
	private ArrayList<JComboBox> combos = new ArrayList();
	
	private FlowLayout flowLayout;
	private ArrayList <clsJugador> porteros,defensas,medios,delanteros;
	
	private String formacion;
	private clsUsuario usuario;
	clsController controller;
	
	public frModificarAlineacion(final clsController controller, clsUsuario usuario)
	{
		this.usuario = usuario;
		
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize(1066, 800);
		setResizable(false);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
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
		
		pCampo = new PanelConFondo("/img/cesped.jpg");
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
		
		porteros = usuario.getPosicion("PORTERO");
		defensas = usuario.getPosicion("DEFENSA");
		medios = usuario.getPosicion("MEDIO");
		delanteros = usuario.getPosicion("DELANTERO");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setActionCommand("GUARDAR");
		btnGuardar.addActionListener(this);
		getContentPane().add(btnGuardar, BorderLayout.SOUTH);
		
		if (usuario.getFormacion()!=null)
			formarTitulares(usuario.getFormacion());
		
		JMenuBar menuBar = menu();
		setJMenuBar(menuBar);
				
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
			
			case "GUARDAR":
			{
				guardarFormacion();
				
				int once=0;
				for (int i=0; i<usuario.getPlantilla().size();i++)
				{
					if(usuario.getPlantilla().get(i).isAlineado())
							once++;
				}
				
				if (once!=11)
				{
					JOptionPane.showMessageDialog(null, "Alineaci�n indevida. Jugador repetido", "Error", JOptionPane.DEFAULT_OPTION);
					for (int i=0; i<usuario.getPlantilla().size();i++)
					{
						usuario.getPlantilla().get(i).setAlineado(false);
					}			
				}
				
				else
				{
					JOptionPane.showMessageDialog(null, "Alineaci�n guardada con �xito", "Guardado", JOptionPane.DEFAULT_OPTION);
					usuario.setFormacion(formacion);
					controller.modificarAlineacion(usuario);
					controller.modificarFormacion(usuario);
				}
				break;
			}

			default:
				break;
	    }
	}
	
	
	public void limpiar()
	{
		pPortero.removeAll();
		pDefensa.removeAll();
		pMedio.removeAll();
		pDelantero.removeAll();
		flowLayout.setVgap(120);
		
		pFormacion.revalidate();
		pFormacion.repaint();
	}
	
	public void formarTitulares(String formacion)
	{
		int def= Integer.parseInt(""+formacion.charAt(0));
		int med= Integer.parseInt(""+formacion.charAt(2));
		int del= Integer.parseInt(""+formacion.charAt(4));
		
		if (def>4|med>4|del>4)
			flowLayout.setVgap(70);
		else
			flowLayout.setVgap(120);
		
		comboBox = new JComboBox<clsJugador>();
		pPortero.add(comboBox);
		comboBox.addItem(porteros.get(0));
		
		for (int i=0; i<def; i++)
		{
			comboBox = new JComboBox<clsJugador>();
			pDefensa.add(comboBox);
     		comboBox.addItem(defensas.get(i));
		}
		pDefensa.setLayout(new GridLayout(def, 1, 100, 100));
		
		for (int i=0; i<med; i++)
		{
			comboBox = new JComboBox<clsJugador>();
			pMedio.add(comboBox);
     		comboBox.addItem(medios.get(i));
		}
		pMedio.setLayout(new GridLayout(med, 1, 100, 100));
		
		for (int i=0; i<del; i++)
		{
			comboBox = new JComboBox<clsJugador>();
			pDelantero.add(comboBox);
     		comboBox.addItem(delanteros.get(i));
		}
		pDelantero.setLayout(new GridLayout(del, 1, 100, 100));
		
		pFormacion.revalidate();
		pFormacion.repaint();	
	}
	
	
	public void formar(String formacion) 
	{
		limpiar();
		
		int def= Integer.parseInt(""+formacion.charAt(0));
		int med= Integer.parseInt(""+formacion.charAt(2));
		int del= Integer.parseInt(""+formacion.charAt(4));
		
		if (def>4|med>4|del>4)
			flowLayout.setVgap(70);
		else
			flowLayout.setVgap(100);
		
		comboBox = new JComboBox<clsJugador>();
		combos.add(comboBox);
		for (int i=0; i<porteros.size();i++)
		{
			pPortero.add(comboBox);
			comboBox.addItem(porteros.get(i));	
		}
		
		for (int i=0; i<def; i++)
		{
			comboBox = new JComboBox<clsJugador>();
			combos.add(comboBox);
			pDefensa.add(comboBox);
			for (int j=0; j<defensas.size();j++)
			{
				comboBox.addItem(defensas.get(j));
			}
		}
		pDefensa.setLayout(new GridLayout(def, 1, 100, 100));
		
		for (int i=0; i<med; i++)
		{
			comboBox = new JComboBox<clsJugador>();
			combos.add(comboBox);
			pMedio.add(comboBox);
			for (int j=0; j<medios.size();j++)
			{
				comboBox.addItem(medios.get(j));
			}
		}
		pMedio.setLayout(new GridLayout(med, 1, 100, 100));
		
		for (int i=0; i<del; i++)
		{
			comboBox = new JComboBox<clsJugador>();
			combos.add(comboBox);
			pDelantero.add(comboBox);
			for (int j=0; j<delanteros.size();j++)
			{
				comboBox.addItem(delanteros.get(j));
			}
		}
		pDelantero.setLayout(new GridLayout(del, 1, 100, 100));
		
		pFormacion.revalidate();
		pFormacion.repaint();		
	}
	
	public void guardarFormacion()
	{
		for (int i=0; i<usuario.getPlantilla().size();i++)
		{
			usuario.getPlantilla().get(i).setAlineado(false);
			
			for (JComboBox comboBox : combos)
			{
				if(usuario.getPlantilla().get(i)==comboBox.getSelectedItem())
				{
					usuario.getPlantilla().get(i).setAlineado(true);
					System.out.println(usuario.getPlantilla().get(i));
				}
				
			}
		}
	}
	
	private JMenuBar menu ()
	{
		//MENU
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mnClasificacin = new JMenu("Clasificacion");
		menuBar.add(mnClasificacin);
		JMenuItem mntmTotal = new JMenuItem("Total");
		mnClasificacin.add(mntmTotal);
		JMenuItem mntmMiEquipo_1 = new JMenuItem("Mi equipo");
		mnClasificacin.add(mntmMiEquipo_1);
		JMenu mnAlineacin = new JMenu("Alineacion");
		menuBar.add(mnAlineacin);
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mnAlineacin.add(mntmConsultar);
		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mnAlineacin.add(mntmModificar);
		JMenu mnEstadsticas = new JMenu("Estadisticas");
		menuBar.add(mnEstadsticas);
		JMenuItem mntmMiEquipo = new JMenuItem("Mi equipo");
		mnEstadsticas.add(mntmMiEquipo);
		JMenu mnMercado = new JMenu("Mercado");
		menuBar.add(mnMercado);
		JMenuItem mntmComprar = new JMenuItem("Comprar");
		mnMercado.add(mntmComprar);
		JMenuItem mntmVender = new JMenuItem("Vender");
		mnMercado.add(mntmVender);
		
		//Escuchadores de botones
		
		mntmConsultar.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frConsultarAlineacion ventana = new frConsultarAlineacion (controller,usuario);
				ventana.setVisible(true);
				dispose();
			}
		});
						
		mntmModificar.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frModificarAlineacion ventana = new frModificarAlineacion (controller,usuario);
				ventana.setVisible(true);
				dispose();
			}
		});
				
		mntmMiEquipo.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frEstadisticas ventana = new frEstadisticas (controller,usuario);
				ventana.setVisible(true);
				dispose();
			}	
		});
				
		return menuBar;
	}
}
