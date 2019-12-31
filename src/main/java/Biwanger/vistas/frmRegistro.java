package Biwanger.vistas;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Handler;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import Biwanger.controladores.*;
import java.awt.Font;

/**
 * Ventana de registro. Permite registrarse al usuario.
 */
public class frmRegistro extends JFrame implements ActionListener
{
    private static Logger logger = Logger.getLogger(frmRegistro.class.getName());
    private static Handler handlerPantalla;
    private static Handler handlerArchivo;

    private JTextField tfEmail;
    private JPasswordField tfPassword;
    private JPasswordField tfPasswordConfirm;
    private JLabel lbEmail;
    private JLabel lbPassword;
    private JLabel lbPasswordConfirm;

    private JButton btnRegistro;
    private JButton btnVolver;

    private JPanel panelUsuario;
    private JPanel panelBotonera;


    private int altura = 361;
    private int anchura = 603;
    private int x = 100;
    private int y = 100;

    private clsController controller;
    private JFrame ventanaInicio;

    /**
     * Constructor de la ventana de registro
     * @param frame Recibe la ventana principal desde el que ha sido lanzado
     * @param controller Recibe el controlador para añadir funcionalidad
     */
    public frmRegistro (JFrame frame, clsController controller)
    {
    	this.controller = controller;
    	ventanaInicio = frame;
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Definimos el tamaño y la localización central en la pantalla
        this.setSize(anchura, altura);
        this.setLocation(x, y);
        setResizable(false);
        setTitle("Biwanger Fantasy - Registro");

        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 597, 332);
        getContentPane().add(panel);
        panel.setLayout(null);

        //Arriba
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(0, 0, 89, 23);
        panel.add(btnVolver);
        btnVolver.setActionCommand("VOLVER");
        btnVolver.addActionListener(this);

        //Panel de usuario
        panelUsuario = new JPanel();
        panelUsuario.setBounds(0, 22, 597, 222);
        panel.add(panelUsuario);
        panelUsuario.setLayout(null);

        lbEmail = new JLabel("Email:");
        lbEmail.setBounds(15, 48, 62, 28);
        lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panelUsuario.add(lbEmail);

        tfEmail = new JTextField ();
        tfEmail.setLocation(119, 45);
        tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tfEmail.setSize(436, 34);
        panelUsuario.add(tfEmail);
        tfEmail.setColumns(10);

        lbPassword = new JLabel("Contraseña:");
        lbPassword.setBounds(15, 112, 122, 28);
        lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panelUsuario.add(lbPassword);

        tfPassword = new JPasswordField();
        tfPassword.setLocation(164, 109);
        tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tfPassword.setSize(391, 34);
        panelUsuario.add(tfPassword);

        lbPasswordConfirm = new JLabel("Confirma contraseña:");
        lbPasswordConfirm.setBounds(15, 176, 207, 28);
        lbPasswordConfirm.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panelUsuario.add(lbPasswordConfirm);

        tfPasswordConfirm = new JPasswordField();
        tfPasswordConfirm.setLocation(218, 175);
        tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tfPasswordConfirm.setSize(337, 34);
        panelUsuario.add(tfPasswordConfirm);

        //Panel botonera
        panelBotonera = new JPanel();
        panelBotonera.setBounds(0, 243, 594, 89);
        panel.add(panelBotonera);
        panelBotonera.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnRegistro = new JButton("Registrarse");
        btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 23));
        panelBotonera.add(btnRegistro);
        btnRegistro.setActionCommand("REGISTRO");
        btnRegistro.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "VOLVER":
            {
                ventanaInicio.setVisible(true);
                this.dispose();
            }
            break;
            case "REGISTRO":
            {
                String email = tfEmail.getText();
                String password = tfPassword.getText();
                String passwordConfirm = tfPasswordConfirm.getText();

                if(email.equals("")||password.equals("")||passwordConfirm.equals(""))
                {
                    if (email.equals(""))
                    {
                        JOptionPane.showMessageDialog(this,
                                "Por favor, introduce tu email",
                                "Rellena todos los campos",
                                JOptionPane.WARNING_MESSAGE);

                        tfEmail.setBorder((Border)( new LineBorder(Color.red,1)));
                    }
                    if (password.equals(""))
                    {
                        JOptionPane.showMessageDialog(this,
                                "Por favor, introduce tu contraseña",
                                "Rellena todos los campos",
                                JOptionPane.WARNING_MESSAGE);
                        tfPassword.setBorder((Border) new LineBorder(Color.red));
                    }
                    if (passwordConfirm.equals(""))
                    {
                        JOptionPane.showMessageDialog(this,
                                "Por favor, introduce la confirmación de tu contraseña",
                                "Rellena todos los campos",
                                JOptionPane.WARNING_MESSAGE);
                        tfPassword.setBorder((Border) new LineBorder(Color.red));
                    }
                }
                else if (!password.equals(passwordConfirm))
                {
                    JOptionPane.showMessageDialog(this,
                            "Las contraseñas no coinciden",
                            "Por favor, cambia las contraseñas para que coincidan",
                            JOptionPane.WARNING_MESSAGE);
                    tfPassword.setBorder((Border) new LineBorder(Color.red));
                }
                else
                {
                    String okay = controller.registro(email, password);

                    if(okay.equals("OK"))
                    {
                        ventanaInicio.setVisible(true);
                        this.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,
                                "Este email ya está registrado",
                                "Por favor, inicia sesión",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            break;

            default:
                break;
        }
    }
}