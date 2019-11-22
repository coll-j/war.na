package id.war.na;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Game extends JFrame{
	
	private Tile tile;
	ImageIcon icon = new ImageIcon("src/rsrc/icon_31.png");
	
	Object[] options = {"New War", "Quit"};
	
	public Game()
	{
		initGame();
	}
	
	private void initGame()
	{
		setTitle("WAR.NA");
		setIconImage(icon.getImage());

		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setCursor(Cursor.CROSSHAIR_CURSOR);
		
		setVisible(true);
		
		Rectangle r = getBounds();
		tile = new Tile(r.width, r.height);
		newGame();
		
		addMouseListener(new MouseHandler());
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
					tile.mouseClicked(e);
					if(!(isPlaying()))
					{
						int n = JOptionPane.showOptionDialog(Game.this, 
								"You won the War of Warna", "Congratulations!", 
								JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon, options, options[1]);
						if(n == 0) newGame();
						else dispose();
					}
				}
	          }
		}
	}
}
