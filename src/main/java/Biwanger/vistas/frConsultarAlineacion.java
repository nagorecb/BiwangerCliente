package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;
import Biwanger.objetosDominio.clsJugador;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class frConsultarAlineacion extends JFrame implements ActionListener
{	
	private static final long serialVersionUID = 1L;
	private JPanel panel,pCampo,pDefensa,pPortero,pFormacion,pMedio,pDelantero;
	
	private JButton btnModificar, btnVolver;
	private JComboBox comboBox;
	private FlowLayout flowLayout;

	private ArrayList<clsJugador> porteros,defensas, centrocampistas,delanteros;
	
	private clsUsuario usuario;

	private JFrame panelUsuario;
	clsController controller;

	public frConsultarAlineacion(JFrame frame, clsController controller, clsUsuario usuario)
	{
		panelUsuario = frame;
		this.usuario = usuario;
		this.controller = controller;

		setUndecorated(true);
		//setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize(1066, 800);
		setResizable(false);

		pCampo = new PanelConFondo("src/main/java/resources/cesped.jpg");
		pCampo.setForeground(new Color(0, 128, 0));
		pCampo.setBorder(null);
		getContentPane().add(pCampo, BorderLayout.CENTER);
		
		pFormacion = new JPanel();
		flowLayout = (FlowLayout) pFormacion.getLayout();
		flowLayout.setVgap(100);
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

		ArrayList<clsJugador> plantilla = controller.obtenerPlantilla(usuario);

		porteros = usuario.getPosicion("Portero", plantilla);
		defensas = usuario.getPosicion("Defensa", plantilla);
		centrocampistas = usuario.getPosicion("Centrocampista", plantilla);
		delanteros = usuario.getPosicion("Delantero", plantilla);

		btnModificar = new JButton("Modificar");
		btnModificar.setActionCommand("MODIFICAR");
		btnModificar.addActionListener(this);
		getContentPane().add(btnModificar, BorderLayout.SOUTH);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("VOLVER");
		btnVolver.addActionListener(this);
		panel.add(btnVolver, BorderLayout.WEST);

		if (usuario.getFormacion()!=null)
		{
			formar(usuario.getFormacion());
		}
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{	
			case "MODIFICAR":
			{
				frModificarAlineacion ventana = new frModificarAlineacion (panelUsuario,controller, usuario);
				ventana.setVisible(true);
				this.dispose();
				break;
			}

			case "VOLVER":
			{
				panelUsuario.setVisible(true);
				this.dispose();
				break;
			}

			default:
				break;
	    }	
	}
	
	public void formar(String formacion)
	{
		int def= Integer.parseInt(""+formacion.charAt(0));
		int med= Integer.parseInt(""+formacion.charAt(2));
		int del= Integer.parseInt(""+formacion.charAt(4));
		
		if (def>4|med>4|del>4)
			flowLayout.setVgap(90);
		else
			flowLayout.setVgap(140);
		
		comboBox = new JComboBox();
		pPortero.add(comboBox);
		comboBox.addItem(porteros.get(0).getNombre());
		
		for (int i=0; i<def; i++)
		{
			comboBox = new JComboBox();
			pDefensa.add(comboBox);
     		comboBox.addItem(defensas.get(i).getNombre());
		}
		pDefensa.setLayout(new GridLayout(def, 1, 100, 100));
		
		for (int i=0; i<med; i++)
		{
			comboBox = new JComboBox();
			pMedio.add(comboBox);
     		comboBox.addItem(centrocampistas.get(i).getNombre());
		}
		pMedio.setLayout(new GridLayout(med, 1, 100, 100));
		
		for (int i=0; i<del; i++)
		{
			comboBox = new JComboBox();
			pDelantero.add(comboBox);
     		comboBox.addItem(delanteros.get(i).getNombre());
		}
		pDelantero.setLayout(new GridLayout(del, 1, 100, 100));
		
		pFormacion.revalidate();
		pFormacion.repaint();	
	}
}
