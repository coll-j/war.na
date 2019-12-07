package id.war.na;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Screen extends JFrame{
	ImageIcon icon = new ImageIcon("src/rsrc/icon_31.png");
	Game game;
	Menu menu;
	HowToPlay howto;
	JPanel panel = new JPanel();
	CardLayout layout = new CardLayout();
	
	public Screen()
	{
		setTitle("WAR.NA");
		setIconImage(icon.getImage());
		
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		panel.setLayout(layout);
		menu = new Menu(this);
		panel.add(menu, "menu");
		
		game = new Game(this);
		panel.add(game, "game");
		
		howto = new HowToPlay(this);
		panel.add(howto, "howto");
		
		layout.show(panel, "menu");
		add(panel);

		SwingUtilities.updateComponentTreeUI(this);
	}
}
