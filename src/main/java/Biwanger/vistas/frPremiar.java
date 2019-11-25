package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class frPremiar extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;

    private JButton btnPremiar;
    private JPanel pPrincipal, pTabla;
    private JTableHeader header;
    private JTable tabla;
    private DefaultTableModel tableModel;

    clsController controller;

    private ArrayList<clsUsuario> listaUsuarios;
    
	public frPremiar(final clsController controller)
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize(1066, 800);
		setResizable(false);

		this.controller = controller;

		pPrincipal= new JPanel();
		pPrincipal.setBorder(new EmptyBorder(0, 0, 0, 0));
		pPrincipal.setLayout(new BorderLayout(0, 0));
        setContentPane(pPrincipal);
        
        pPrincipal.setLayout(new BorderLayout(0, 0));

        this.listaUsuarios = controller.obtenerTodosUsuarios();
        
    	pTabla = new PanelConFondo ("/img/foto.jpg");
    	pTabla.setBorder(new EmptyBorder(40, 40, 40, 40));
        pTabla.setLayout(new BorderLayout(0, 0));
        
        String[] col = {"Usuario","Puntuacion","Fondos"};
        DefaultTableModel tableModel = new DefaultTableModel(col,0);
        
        tabla = new JTable(tableModel);
        tabla.setEnabled(false);
        tabla.setRowHeight(25);
		
        for (int i =0; i<listaUsuarios.size();i++)
        {
            String user = listaUsuarios.get(i).getEmail();
            int puntos = listaUsuarios.get(i).getPuntuacion();
            double fondos = listaUsuarios.get(i).getFondos();
            
            Object [] data = {user,puntos,fondos};
            tableModel.addRow(data);
        }
        
        this.tableModel=tableModel;
        
        header = tabla.getTableHeader();
        pPrincipal.add(pTabla, BorderLayout.CENTER);
        pTabla.add(header, BorderLayout.NORTH);
        pTabla.add(tabla, BorderLayout.CENTER);
        
    	JMenuBar menuBar = menu();
		setJMenuBar(menuBar);
		
		btnPremiar = new JButton("Premiar");
		pPrincipal.add(btnPremiar, BorderLayout.SOUTH);
		
	    btnPremiar.setActionCommand("PREMIAR");
	    btnPremiar.addActionListener(this);
	}

	@Override
	 public void actionPerformed(ActionEvent e)
	    {
	        switch(e.getActionCommand())
	        {
	            case "PREMIAR":
	            {
	                tabla.setVisible(false);

					listaUsuarios = controller.premiarTresMejores();

					double fondos;
	                int num;

	                if (listaUsuarios.size()>3)	num = 3;
                	else num = listaUsuarios.size();
                	
                	for (int i =0; i<num;i++)
  	                {
  	                	fondos = listaUsuarios.get(i).getFondos();
  	                	tabla.getModel().setValueAt(fondos, i, 2);
  	                }
	   
	                tabla.setModel(tableModel);
	                tableModel.fireTableDataChanged();
	                tabla.setVisible(true);
	            }

	            default:
	                break;
	        }
	    }
	    
		
		private JMenuBar menu ()
		{
			//MENU
			JMenuBar menuBar = new JMenuBar();
			
			JMenu mnMenu = new JMenu("Menu");
			menuBar.add(mnMenu);
			
			JMenuItem mntmPremiarTresMejores = new JMenuItem("Premiar tres mejores");
			mnMenu.add(mntmPremiarTresMejores);
			
			JMenuItem mntmIntroducirPuntuacionJornada = new JMenuItem("Introducir puntuacion jornada");
			mnMenu.add(mntmIntroducirPuntuacionJornada);
			
			JMenuItem mntmGestionDeMercado = new JMenuItem("Gestion de mercado");
			mnMenu.add(mntmGestionDeMercado);
			
			
			//Escuchadores de botones
			mntmPremiarTresMejores.addActionListener( new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					frPremiar ventana = new frPremiar (controller, listaUsuarios);
					ventana.setVisible(true);
					dispose();
				}
			});		
							
			return menuBar;
		}
}
