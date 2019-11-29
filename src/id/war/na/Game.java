package id.war.na;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JPanel{
	
	private Tile tile;
	private int cheat_key = 0;
	private Rectangle bound;
	
	Screen f;
	
	Object[] options = {"New War", "Quit"};
	
	public Game(Screen frame)
	{
		JButton button = new JButton("menu");
		
		bound = frame.getBounds();
		f = frame;
		tile = new Tile(bound);
		initGame();
		
		tile.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.weighty = 0.1;
        
        tile.add(button, gbc);
        button.addActionListener(new ButtonHandler());
		setLayout(new CardLayout());
	}
	
	private void initGame()
	{
		
		//System.out.println("" + r.width + " " + r.height);
		newGame();
		
		addMouseListener(new MouseHandler());
		addKeyListener(new KeyHandler());
	}
	
	public void newGame()
	{
		if(tile.isVisible()) remove(tile);
		tile.init();
		add(tile);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public boolean isPlaying()
	{
		return !(tile.allSame());
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
				if(isPlaying())
				{
					System.out.println("thay");
					tile.mouseClicked(e);
					if(!(isPlaying()))
					{
//						int n = JOptionPane.showOptionDialog(Game.this, 
//								"You won the War of Warna", "Congratulations!", 
//								JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon, options, options[1]);
//						if(n == 0) newGame();
//						else dispose();
					}
				}
	          }
		}
	}
	private class KeyHandler implements KeyListener
	{
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			System.out.println("wwwwx");
			if (key == KeyEvent.VK_W)
			{
				cheat_key++;
				System.out.println("wwwww");
				if(cheat_key >= 5)
				{
					cheat_key = 0;
					tile.cheat();
				}
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {
			
		}
		
	}
	
	private class ButtonHandler implements ActionListener 
	   {
	      // handle button event
	      @Override
	      public void actionPerformed(ActionEvent event)
	      {
	         System.out.println("go to menu");
	         f.layout.show(f.panel, "menu");
	      }
	   } 
}
