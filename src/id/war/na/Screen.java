package id.war.na;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Screen extends JFrame{
	ImageIcon icon = new ImageIcon("src/rsrc/icon_31.png");
	Game game;
	Menu menu;
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
		
		layout.show(panel, "menu");
		add(panel);

		SwingUtilities.updateComponentTreeUI(this);
		
		addKeyListener(new KeyHandler());
		setFocusable(true);
	}
	
	private class KeyHandler implements KeyListener
	{
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_W)
			{
				game.cheatCount();
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
		
	}
}
