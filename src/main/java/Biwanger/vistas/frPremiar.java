package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * @brief Ventana que permite premiar a los tres usuarios con mayor puntuación.
 */
public class frPremiar extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;

    private JButton btnPremiar,btnVolver;
    private JPanel panel,pPrincipal, pTabla;
    private JTableHeader header;
    private JTable tabla;
    private DefaultTableModel tableModel;

    private clsController controller;

    private JFrame panelAdmin;
    private ArrayList<clsUsuario> listaUsuarios;

	/**
	 * Constructor de la ventana que permite premiar a los tres usuarios con mayor puntuación.
	 * @param frame Recibe la ventana principal desde el que ha sido lanzado
	 * @param controller Recibe el controlador para añadir funcionalidad
	 */
	public frPremiar(JFrame frame, clsController controller)
	{
		this.controller = controller;
		panelAdmin = frame;

		setUndecorated(true);
//		setDefaultCloseOperation(Frame.NORMAL);
		setSize(1066, 800);
		setResizable(false);

		pPrincipal = new PanelConFondo("src/main/java/resources/fondo.jpg");
//		pPrincipal.setBorder(new EmptyBorder(0, 0, 0, 0));
		pPrincipal.setLayout(new BorderLayout(0, 0));
        setContentPane(pPrincipal);
        
        pPrincipal.setLayout(new BorderLayout(0, 0));

        this.listaUsuarios = controller.obtenerTodosUsuarios();

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

		btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("VOLVER");
		btnVolver.addActionListener(this);
		panel.add(btnVolver, BorderLayout.WEST);

		panel.setOpaque(false);
		pTabla.setOpaque(false);
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
	                int num = listaUsuarios.size();
                	
                	for (int i =0; i<num;i++)
  	                {
  	                	fondos = listaUsuarios.get(i).getFondos();
  	                	tabla.getModel().setValueAt(fondos, i, 2);
  	                }

	                tabla.setModel(tableModel);
	                tableModel.fireTableDataChanged();
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
