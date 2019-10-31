package Biwanger.vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class frmPanelAdmin extends JFrame implements ActionListener
{
    private static Logger logger = Logger.getLogger(frmPanelAdmin.class.getName());
    private static Handler handlerPantalla;
    private static Handler handlerArchivo;

    private JButton btnPremiar;
    private JPanel contentPane;
    private JTableHeader header;
    private JTable table;
    private DefaultTableModel tableModel;

    private int altura = 300;
    private int anchura = 600;
    private int x = 100;
    private int y = 100;


    /**
     * Constructor de la ventana de premiar tres mejores
     */
    public frmPanelAdmin(clsController controller, ArrayList <clsUsuario> listaUsuarios)
    {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setSize(anchura, altura);
        this.setLocation(x, y);
        setResizable(false);
        setTitle("Premiar Tres Mejores");

        contentPane= new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        String[] col = {"Usuario","Puntuacion","Fondos"};
        DefaultTableModel tableModel = new DefaultTableModel(col,0);
        table = new JTable(tableModel);
        for (int i =0; i<listaUsuarios.size();i++)
        {
            String user = listaUsuarios.get(i).getEmail();
            int puntos = listaUsuarios.get(i).getPuntuacion();
            double fondos = listaUsuarios.get(i).getFondos();

            Object [] data = {user,puntos,fondos};
            tableModel.addRow(data);
        }
        header = table.getTableHeader();
        contentPane.add(header, BorderLayout.NORTH);
        contentPane.add(table, BorderLayout.CENTER);

        JButton btnPremiar = new JButton("Premiar");
        contentPane.add(btnPremiar, BorderLayout.SOUTH);


        btnPremiar.setActionCommand("PREMIAR");
        btnPremiar.addActionListener(this);
        getContentPane().setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "PREMIAR":
            {
                //metodo PREMIAR
                table.setVisible(false);
                tableModel.setRowCount(0);
                for (int i =0; i<listaUsuarios.size();i++)
                {
                    String user = listaUsuarios.get(i).getEmail();
                    int puntos = listaUsuarios.get(i).getPuntuacion();
                    double fondos = listaUsuarios.get(i).getFondos();
                    Object [] data = {user,puntos,fondos};
                    tableModel.addRow(data);
                }
                table.setModel(tableModel);
                tableModel.fireTableDataChanged();
                table.setVisible(true);
            }

            default:
                break;
        }
    }
}
