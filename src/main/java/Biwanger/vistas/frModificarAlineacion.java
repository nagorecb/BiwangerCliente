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

public class frModificarAlineacion extends JFrame implements ActionListener
{	
	private static final long serialVersionUID = 1L;
	private JPanel panel,superior,pCampo,pDefensa,pPortero,pFormacion,pMedio,pDelantero;
	private JLabel lblSistemaDeJuego;
	
	private JButton btnGuardar,btnVolver,b1,b2,b3,b4;
	
	private JComboBox<clsJugador> comboBox;
	private ArrayList<JComboBox> combos = new ArrayList();
	
	private FlowLayout flowLayout;
	private ArrayList <clsJugador> porteros,defensas,medios,delanteros;
	
	private String formacion;
	private clsUsuario usuario;

	private JFrame panelUsuario;
	clsController controller;
	
	public frModificarAlineacion(JFrame frame, clsController controller, clsUsuario usuario)
	{
		this.usuario = usuario;
		this.controller = controller;
		panelUsuario=frame;

		setUndecorated(true);
//		setDefaultCloseOperation(Frame.NORMAL);
		setSize(1066, 800);
		setResizable(false);

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

		superior = new JPanel();
		getContentPane().add(superior, BorderLayout.NORTH);
		superior.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		superior.add(panel);

		porteros = usuario.getPosicion("PORTERO");
		defensas = usuario.getPosicion("DEFENSA");
		medios = usuario.getPosicion("MEDIO");
		delanteros = usuario.getPosicion("DELANTERO");

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
			formarTitulares(usuario.getFormacion());
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
				super.setVisible(true);
				dispose();
				break;
			}
			case "GUARDAR":
			{
				guardarFormacion();

				int once=0;
				for (int i=0; i<usuario.getPlantilla().size();i++)
				{
					if(usuario.getPlantilla().get(i).isAlineado())
					{
						once++;
					}
				}

				if (once!=11)
				{
					JOptionPane.showMessageDialog(null, "Alineación indevida. Jugador repetido", "Error", JOptionPane.DEFAULT_OPTION);
					for (int i=0; i<usuario.getPlantilla().size();i++)
					{
						usuario.getPlantilla().get(i).setAlineado(false);
					}
				}

				else
				{
					JOptionPane.showMessageDialog(null, "Alineación guardada con éxito", "Guardado", JOptionPane.DEFAULT_OPTION);
					//guardar cambios (atributo Usuario.formacion y Jugador.Alineado
					//BD --> usuario.formacion
					usuario.setFormacion(formacion);
					//BD --> jugador.alineado

					panelUsuario.setVisible(true);
					this.dispose();
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
		flowLayout.setVgap(140);
		
		pFormacion.revalidate();
		pFormacion.repaint();
	}
	
	public void formarTitulares(String formacion)
	{
		int def= Integer.parseInt(""+formacion.charAt(0));
		int med= Integer.parseInt(""+formacion.charAt(2));
		int del= Integer.parseInt(""+formacion.charAt(4));
		
		if (def>4|med>4|del>4)
			flowLayout.setVgap(90);
		else
			flowLayout.setVgap(140);

		comboBox = new JComboBox<clsJugador>();
		pPortero.add(comboBox);
		comboBox.addItem(porteros.get(0));
		combos.add(comboBox);

		for (int i=0; i<def; i++)
		{
			comboBox = new JComboBox<clsJugador>();
			combos.add(comboBox);
			pDefensa.add(comboBox);
			comboBox.addItem(defensas.get(i));
		}
		pDefensa.setLayout(new GridLayout(def, 1, 100, 100));

		for (int i=0; i<med; i++)
		{
			comboBox = new JComboBox<clsJugador>();
			combos.add(comboBox);
			pMedio.add(comboBox);
			comboBox.addItem(medios.get(i));
		}
		pMedio.setLayout(new GridLayout(med, 1, 100, 100));

		for (int i=0; i<del; i++)
		{
			comboBox = new JComboBox<clsJugador>();
			combos.add(comboBox);
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
}
