package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;
import Biwanger.objetosDominio.clsJugador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class frEstadisticas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pPrincipal;
	private JTableHeader header;
    private JTable table;
    private DefaultTableModel tableModel;

	clsController controller;
	clsUsuario usuario;
	
	public frEstadisticas(final clsController controller, clsUsuario usuario)
	{
		this.usuario = usuario;
		this.controller = controller;

		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize(1066, 800);
		setResizable(false);
		
		pPrincipal = new PanelConFondo ("/img/foto.jpg");
		pPrincipal.setBorder(new EmptyBorder(60, 40, 100, 40));
		getContentPane().add(pPrincipal, BorderLayout.CENTER);
		pPrincipal.setLayout(new BorderLayout(0, 0));
		
		 String[] col = {"Nombre","Posicion","Equipo","Pnt.","Goles","Asist.","T.A","T.R","P.Jug.","Estado"};
	     tableModel = new DefaultTableModel(col,0);
	     table = new JTable(tableModel);
	     
	     table.setRowHeight(25);
//	     table.setShowVerticalLines(false);
	     table.setEnabled(false);
	     
	     for (int i =0; i<usuario.getPlantilla().size();i++)
	        {
	    	 	clsJugador jugador = usuario.getPlantilla().get(i);
	    	 	
	    	 	String nombre = jugador.getNombre();
	    		int puntos = jugador.getPuntos();
	    		String posicion = jugador.getPosicion();
	    		String equipo = jugador.getEquipo();
	    		int numGoles = jugador.getNumGoles();
	    		int numAsistencias = jugador.getNumAsistencias();
	    		int numTarjetasAmarillas = jugador.getNumTarjetasAmarillas();
	    		int numTarjetasRojas = jugador.getNumTarjetasRojas();
	    		int numPartidosJugados = jugador.getNumPartidosJugados();
	    		String estado = jugador.getEstado();	
	    		
	    		Object [] data = {nombre, posicion, equipo, puntos, numGoles, numAsistencias, numTarjetasAmarillas, numTarjetasRojas, numPartidosJugados, estado};
	            tableModel.addRow(data);
	        }
	        header = table.getTableHeader();
	        header.setOpaque(false);
	        header.setBackground(Color.WHITE);
	        
	        pPrincipal.add(header, BorderLayout.NORTH);
	        pPrincipal.add(table, BorderLayout.CENTER);
		
		JMenuBar menuBar = menu();
		setJMenuBar(menuBar);
		
		
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
