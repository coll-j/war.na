package id.war.na;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Menu extends JFrame {
	
	public Menu()
	{
		JLabel bg;
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ImageIcon img = new ImageIcon("src/rsrc/warna_TITLE.jpg");
		Image imgt = img.getImage();
		setVisible(true);
		
		Rectangle r = getBounds();
		imgt = imgt.getScaledInstance(r.width, r.height, Image.SCALE_DEFAULT);
		
		bg = new JLabel("", new ImageIcon(imgt), JLabel.CENTER);
		add(bg);
	
		SwingUtilities.updateComponentTreeUI(this);
	}
}
