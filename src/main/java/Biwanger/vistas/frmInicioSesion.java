package Biwanger.vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;
import java.awt.Font;

/**
 * @brief Ventana que permite a los usuario y al administrador iniciar sesión en el sistema.
 * Permite también acceder a la ventana de registro.
 */
public class frmInicioSesion extends JFrame implements ActionListener
{

    private JTextField tfEmail;
    private JPasswordField tfpassword;
    private JLabel lbEmail;
    private JLabel lbpassword;
    private JLabel lbRegistrarse;

    private JButton btnIniciarSesion;

    private JPanel panelUsuario;
    private JPanel panelBotonera;


    private int altura = 300;
    private int anchura = 600;
    private int x = 100;
    private int y = 100;

    clsController controller;

    /**
     * Constructor de la ventana de inicio de sesion
     * @param controller Recibe el controlador para añadir funcionalidad
     */
    public frmInicioSesion (final clsController controller)
    {
    	this.controller = controller;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Definimos el tamano y la localizacion central en la pantalla
        this.setSize(anchura, altura);
        this.setLocation(x, y);
        setResizable(false);
        setTitle("Biwanger Fantasy - Inicio de sesión");

        //Panel usuario
        panelUsuario= new JPanel();
        panelUsuario.setBounds(0, 0, 609, 162);
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

        lbpassword = new JLabel("Contraseña");
        lbpassword.setBounds(15, 112, 122, 28);
        lbpassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panelUsuario.add(lbpassword);

        tfpassword= new JPasswordField();
        tfpassword.setLocation(164, 109);
        tfpassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tfpassword.setSize(391, 34);
        panelUsuario.add(tfpassword);


        //Panel botonera
        panelBotonera = new JPanel();
        panelBotonera.setBounds(0, 160, 609, 117);
        panelBotonera.setLayout(null);
        panelBotonera.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lbRegistrarse = new JLabel("¿No tienes cuenta? Regístrate haciendo click sobre este texto");
        lbRegistrarse.setForeground(Color.BLUE);
        lbRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panelBotonera.add(lbRegistrarse);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 23));
        panelBotonera.add(btnIniciarSesion);


        lbRegistrarse.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame)SwingUtilities.getRoot(component);
                frmRegistro registro = new frmRegistro(frame, controller);
                frame.setVisible(false);
                registro.setVisible(true);
            }
        });
        btnIniciarSesion.setActionCommand("INICIO");
        btnIniciarSesion.addActionListener(this);
        getContentPane().setLayout(null);

        this.getContentPane().add(panelUsuario);
        this.getContentPane().add(panelBotonera);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "INICIO":
            {
                String email = tfEmail.getText();
                String password = tfpassword.getText();

                if(email.equals("")||password.equals(""))
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
                                "Por favor, introduce tu contraseÃ±a",
                                "Rellena todos los campos",
                                JOptionPane.WARNING_MESSAGE);
                        tfpassword.setBorder((Border) new LineBorder(Color.red));
                    }
                }
                else
                {
                    clsUsuario usuarioIniciado;
                    usuarioIniciado = controller.inicioSesion(email, password);
                    
                    if(usuarioIniciado == null)
                    {
                    	JOptionPane.showMessageDialog(this,
                                "Credenciales incorrectas, por favor, intentalo de nuevo",
                                "Credenciales incorrectas",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else if(usuarioIniciado.getEmail().equals("ADMIN"))
                    {
                        this.setVisible(false);
                        frmPanelAdmin ventanaAdmin = new frmPanelAdmin(controller);
                        ventanaAdmin.setVisible(true);
                    }
                    else 
                    {
                        this.setVisible(false);
                        frmPanelUsuario ventanaUsuario = new frmPanelUsuario(controller, usuarioIniciado);
                        ventanaUsuario.setVisible(true);
                    }
                    
                }
            }

            default:
                break;
        }
    }
}