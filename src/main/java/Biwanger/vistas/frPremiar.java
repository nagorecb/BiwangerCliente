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

    private JButton btnPremiar,btnVolver;
    private JPanel panel,pPrincipal, pTabla;
    private JTableHeader header;
    private JTable tabla;
    private DefaultTableModel tableModel;

    clsController controller;

    private JFrame panelAdmin;
    private ArrayList<clsUsuario> listaUsuarios;


	public frPremiar(JFrame frame, clsController controller)
	{
		System.out.println(2);
		this.controller = controller;
		panelAdmin = frame;

		setUndecorated(true);
//		setDefaultCloseOperation(Frame.NORMAL);
		setSize(1066, 800);
		setResizable(false);

		pPrincipal= new JPanel();
		pPrincipal.setBorder(new EmptyBorder(0, 0, 0, 0));
		pPrincipal.setLayout(new BorderLayout(0, 0));
        setContentPane(pPrincipal);
        
        pPrincipal.setLayout(new BorderLayout(0, 0));
		System.out.println(22);
        this.listaUsuarios = controller.obtenerTodosUsuarios();
		System.out.println(3);
    	pTabla = new JPanel();
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
		System.out.println(4);
        header = tabla.getTableHeader();
        pPrincipal.add(pTabla, BorderLayout.CENTER);
        pTabla.add(header, BorderLayout.NORTH);
        pTabla.add(tabla, BorderLayout.CENTER);
		
		btnPremiar = new JButton("Premiar");
		pPrincipal.add(btnPremiar, BorderLayout.SOUTH);
	    btnPremiar.setActionCommand("PREMIAR");
	    btnPremiar.addActionListener(this);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		System.out.println(5);
		btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("VOLVER");
		btnVolver.addActionListener(this);
		panel.add(btnVolver, BorderLayout.WEST);
		System.out.println(6);
	}

	@Override
	 public void actionPerformed(ActionEvent e)
	    {
	        switch(e.getActionCommand())
	        {
	            case "PREMIAR":
	            {
	            	System.out.println(1111111);
	                tabla.setVisible(false);

					listaUsuarios = controller.premiarTresMejores();
					System.out.println(222222222);

					double fondos;
	                int num;

	                if (listaUsuarios.size()>3)	num = 3;
                	else num = listaUsuarios.size();
					System.out.println(3333333);
                	
                	for (int i =0; i<num;i++)
  	                {
  	                	fondos = listaUsuarios.get(i).getFondos();
  	                	tabla.getModel().setValueAt(fondos, i, 2);
  	                }
					System.out.println(44444444);
	   
	                tabla.setModel(tableModel);
					System.out.println(55555555);
	                tableModel.fireTableDataChanged();
					System.out.println(66666666);
	                tabla.setVisible(true);
	            }
				break;

				case "VOLVER":
				{
					panelAdmin.setVisible(true);
					this.dispose();
				}
				break;

	            default:
	                break;
	        }
	    }
}
