package Biwanger.vistas;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelConFondo extends JPanel 
{

	private static final long serialVersionUID = 1L;
	ImageIcon imagen;
	String nombre;
	
	public PanelConFondo(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public void paint (Graphics g)
	{
		Dimension tamanio = getSize();
		imagen = new ImageIcon (getClass().getResource(nombre));
		g.drawImage(imagen.getImage(), 0, 0, tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paint(g);
	}

}
