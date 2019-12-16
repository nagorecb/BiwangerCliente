package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;
import Biwanger.objetosDominio.clsJugador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class frEstadisticas extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JPanel panel,pPrincipal;
	private JTableHeader header;
    private JTable table;
    private DefaultTableModel tableModel;
	private JButton btnVolver;

	private JFrame panelUsuario;
	clsController controller;
	clsUsuario usuario;
	
	public frEstadisticas(JFrame frame, clsController controller, clsUsuario usuario)
	{
		this.usuario = usuario;
		this.controller = controller;
		panelUsuario=frame;

		setUndecorated(true);
		//setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize(1066, 800);
		setResizable(false);

		pPrincipal = new PanelConFondo ("src/main/java/resources/foto.jpg");
		pPrincipal.setBorder(new EmptyBorder(60, 40, 100, 40));
		getContentPane().add(pPrincipal, BorderLayout.CENTER);
		pPrincipal.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("VOLVER");
		btnVolver.addActionListener(this);
		panel.add(btnVolver, BorderLayout.WEST);
		
		 String[] col = {"Nombre","Posicion","Equipo","Pnt.","Goles","Asist.","T.A","T.R","P.Jug.","Estado"};
	     tableModel = new DefaultTableModel(col,0);
	     table = new JTable(tableModel);
	     
	     table.setRowHeight(25);
//	     table.setShowVerticalLines(false);
	     table.setEnabled(false);

		ArrayList<clsJugador> plantilla = controller.obtenerPlantilla(usuario);

	     for (int i =0; i<plantilla.size();i++)
	        {
	    	 	clsJugador jugador = plantilla.get(i);
	    	 	
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
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
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

}
