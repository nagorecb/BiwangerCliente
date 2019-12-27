package Biwanger.vistas;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frmClasificacionUsuarios extends JFrame implements ActionListener
{
    private JPanel pBotonera;
    private JPanel pPrincipal;
    private JPanel pTabla;
    private JTableHeader header;
    private JTable tabla;
    private DefaultTableModel tableModel;

    private JButton btnVolver;

    private clsController controller;

    private JFrame panelUsuario;
    private ArrayList<clsUsuario> lUsuarios;

    public frmClasificacionUsuarios(JFrame frame, clsController controller)
    {
        this.controller = controller;
        panelUsuario = frame;

        setSize(1066, 800);
        setResizable(false);

        pPrincipal = new JPanel();
        pPrincipal.setBorder(new EmptyBorder(0,0,0,0));
        pPrincipal.setLayout(new BorderLayout(0,0));
        setContentPane(pPrincipal);

        lUsuarios = controller.clasificacionUsuarios();

        pTabla = new JPanel();
        pTabla.setBorder(new EmptyBorder(40,40,40,40));
        pTabla.setLayout(new BorderLayout(0,0));

        String[] col = {"Usuario", "Puntuación"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        tabla = new JTable(tableModel);
        tabla.setEnabled(false);
        tabla.setRowHeight(25);

        for(clsUsuario aux: lUsuarios)
        {
            String user = aux.getEmail();
            int puntuacion = aux.getPuntuacion();

            Object[] data = {user, puntuacion};
            tableModel.addRow(data);
        }

        this.tableModel = tableModel;

        header = tabla.getTableHeader();
        header = tabla.getTableHeader();
        pPrincipal.add(pTabla, BorderLayout.CENTER);
        pTabla.add(header, BorderLayout.NORTH);
        pTabla.add(tabla, BorderLayout.CENTER);

        pBotonera = new JPanel();
        getContentPane().add(pBotonera, BorderLayout.NORTH);
        pBotonera.setLayout(new BorderLayout(0,0));

        btnVolver = new JButton("Volver");
        btnVolver.setActionCommand("VOLVER");
        btnVolver.addActionListener(this);
        pBotonera.add(btnVolver, BorderLayout.WEST);
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
               }
               break;
           }
    }
}