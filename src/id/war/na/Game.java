package id.war.na;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import id.war.na.Menu.ButtonHandler;

public class Game extends JPanel{
	
	private Tile tile;
	private int cheat_key = 0;
	private Rectangle bound;
	Font Bebas = new Font("Bebas", Font.TRUETYPE_FONT, 40);
	public static final Color CREAM1 = new Color(255, 254, 228);
	
	ImageIcon icon = new ImageIcon("src/rsrc/icon_31.png");
	
	Screen f;
	
	Object[] options = {"New War", "Quit"};
	
	public Game(Screen frame)
	{
		JButton menu = addAButton("menu","src/rsrc/button_default/MENU.png");
        menu.setRolloverIcon(new ImageIcon("src/rsrc/button_highlighted/MENU.png"));
        menu.setPressedIcon(new ImageIcon("src/rsrc/button_pressed/MENU.png"));
        
		addMouseListener(new MouseHandler());
		
		bound = frame.getBounds();
		f = frame;
		tile = new Tile(bound);
		
		tile.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 0.1;
        gbc.insets = new Insets(630, 0, 0, 0);
  
        tile.add(menu, gbc);
        menu.addActionListener(new ButtonHandler());
		setLayout(new CardLayout());
		
		initGame();
	}
	
	private void initGame()
	{
		newGame();
	}
	
	public void cheatCount()
	{
		cheat_key++;
		if(cheat_key >= 5)
		{
			cheat_key = 0;
			activateCheat();
		}
	}
	
	private void activateCheat()
	{
		tile.cheat();
	}
	
	public void newGame()
	{
		if(tile.isVisible()) remove(tile);
		tile.init();
		add(tile);
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private class MouseHandler implements MouseListener
	{
		Object[] options = {"New Game", "Quit"};

		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(tile.getWinner() == 0)
				{
					tile.mouseClicked(e);
					if(tile.getWinner() != 0)
					{
						int n = JOptionPane.showOptionDialog(Game.this, 
								String.format("Player %d won the War of Warna!", tile.getWinner()), "Congratulations!", 
								JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon, options, options[1]);
						if(n == 0) newGame();
						else System.exit(1);;
					}
				}
	          }
		}
	}
	
	private JButton addAButton (String name,String url) {
		JButton button = new JButton(new ImageIcon(url));
		
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setActionCommand(name);
		
		ButtonHandler buttoh = new ButtonHandler();
		button.addActionListener(buttoh);
		
		return button;
	}
	
	private class ButtonHandler implements ActionListener 
	   {
	      // handle button event
	      @Override
	      public void actionPerformed(ActionEvent event)
	      {
	         f.layout.show(f.panel, "menu");
	      }
	   } 
}
