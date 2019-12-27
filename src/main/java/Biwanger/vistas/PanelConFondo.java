package Biwanger.vistas;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Panel con una imagen de fondo
 */
public class PanelConFondo extends JPanel 
{

	private static final long serialVersionUID = 1L;
	ImageIcon imagen;
	String nombre;

	/**
	 * Constructor del panel con una imagen de fondo
	 * @param nombre Recibe el nombre del panel
	 */
	public PanelConFondo(String nombre) 
	{
		this.nombre = nombre;
		setOpaque(false);
		setLayout(new FlowLayout());
	}
	
	public void paint (Graphics g)
	{
		Dimension tamanio = getSize();
		imagen = new ImageIcon(nombre);
		g.drawImage(imagen.getImage(), 0, 0, tamanio.width, tamanio.height, null);

		super.paint(g);
	}

}
