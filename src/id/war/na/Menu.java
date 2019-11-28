package id.war.na;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame {
	private int window_w = 1920;
	private int window_h = 1080;
	
	private int mainX;
	private int mainY;
	
	ImageIcon img;
	
	public Menu()
	{
		img = new ImageIcon("src/rsrc/warna_TITLE.jpg");
		Image imgt = img.getImage();
		setResizable(true);
		//imgt.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		img = new ImageIcon(imgt);
		setSize(1366, 768);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(img));
	}
	
	@Override
	public void paintComponents(Graphics g) {
//		Image imgt = img.getImage();
//		g.drawImage(imgt, 0, 0, null);
//		super.paintComponents(g);
//		g.drawImage(new ImageIcon(Menu.class.getResource("warna_TITLE.jpg")).getImage(), 0, 0, window_w, window_h, this);
	}
	
	
}
