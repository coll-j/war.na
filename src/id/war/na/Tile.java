package id.war.na;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Random;

public class Tile extends JPanel{
	private static final Color CREAM1 = new Color(255, 254, 228);	
	private static final Color YELLOW1 = new Color(255, 232, 156);
	private static final Color RED1 = new Color(235, 162, 150);
	private static final Color MAGENTA1 = new Color(209, 164, 255);
	private static final Color BLUE1 = new Color(160, 219, 235);
	private static final Color GREEN1 = new Color(194, 255, 181);
	private static final Color BLACK1 = new Color(70, 70, 70);
	
	Font Bebas = new Font("Bebas", Font.PLAIN, 40);
	Font BebasTitle = new Font("Bebas", Font.PLAIN, 70);
	
	private Color[][] well;
	private ArrayList<Color> wheel = new ArrayList<Color>();
	private final Color[] color_lib = {BLUE1, GREEN1, RED1, YELLOW1, MAGENTA1};
	private Random r = new Random();
	
	private int window_w = 1024;
	private int window_h = 600;
	
	private int mainX;
	private int mainY;
	private int wheelX;
	private int wheelY;
		
	private int box_size;

	private Color p1;
	private Color p2;
	
	private int turn = 1;
	
	public Tile(int width, int height)
	{
		window_w = width;
		window_h = height;
	}
	
	public Tile(Rectangle r)
	{
		window_w = r.width;
		window_h = r.height;
		
		box_size = (int) 30 * window_w / 1024;
		mainX = (int) (window_w/2) - ((box_size + 1) * 5);
		mainY = (int) 70 * window_h / 600;
		wheelX = (int) (window_w/2) - ((box_size + 1) * 3);
		wheelY = (int) 490 * window_h / 600;
		
		well = new Color[10][10];
	}
	
	public void init() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				well[i][j] = color_lib[r.nextInt(color_lib.length)];
			}
		}
		
		wheel.clear();
		for(int i = 0; i < 6; i++)
		{
			wheel.add(color_lib[r.nextInt(color_lib.length)]);
		}
		
		p1 = color_lib[r.nextInt(color_lib.length)];
		p2 = color_lib[r.nextInt(color_lib.length)];
		while(p2.equals(p1))
			p2 = color_lib[r.nextInt(color_lib.length)];
		System.out.println(p1 + " " + p2);
		
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(CREAM1);
		g.fillRect(0, 0, window_w, window_h);

		// Paint the tiles
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				g.setColor(well[i][j]);
				g.fillRect((box_size + 1)*i + mainX, (box_size + 1)*j + mainY, box_size, box_size);
			}
		}
		
		// Paint the color wheel
		g.setColor(BLACK1);
		g.fillRect(wheelX, wheelY - 2, ((box_size + 1) * 5) + (box_size + 12), (box_size + 4));
		int count = -1;
		
		for(Color c : wheel)
		{
			g.setColor(c);
			if(count < 0)
			{
				g.setColor(BLACK1);
				g.fillRect(wheelX - 3, wheelY - 5, (box_size + 14), (box_size + 14));
				g.setColor(c);
				g.fillRect(wheelX - 1, wheelY - 3, (box_size + 10), (box_size + 10));
			}
			else
				g.fillRect(wheelX + (box_size + 11) + (count * (box_size + 1)), wheelY, box_size, box_size);
			
			count++;
		}
		
		// Adding player components
		playerComponent(g, turn);
		
	}
	
	public void playerComponent(Graphics g, int c) {
		int whose; 
		
		if(g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		
	    if (c%2 == 0) whose = 2;
	    else whose = 1;
	    
	    // whose turn now
		drawCenteredString("Player   " + "     " + "   turn", window_w, window_h + wheelY - 7*box_size, g, Bebas, BLACK1);
		drawCenteredString(" " + whose + " ", window_w + box_size, window_h + wheelY - 7*box_size, g, Bebas, BLACK1);
		
		// player 1 components
		drawCenteredString("Player   1", mainX, window_h/2 - 2*box_size, g, BebasTitle, BLACK1);
		drawCenteredString("target", mainX, window_h/2 + 3*box_size, g, Bebas, BLACK1);
		drawCenteredString("score", mainX, window_h/2 + 10*box_size, g, Bebas, BLACK1);
		
		// player 1 target color
		g.setColor(BLACK1);
		g.fillRect(mainX/2 - box_size/2 - 7, window_h/2 - 2*box_size - 12, box_size + 14, box_size + 14);
		g.setColor(p1);
		g.fillRect(mainX/2 - box_size/2 - 5, window_h/2 - 2*box_size - 10, box_size + 10, box_size + 10);
		
		// player 1 score
		g.setColor(p1);
		g.fillRect(mainX/2 - box_size, window_h/2 + 1*box_size, 2*box_size, 1*box_size + 15);
		drawCenteredString("123", mainX, window_h/2 + 13*box_size, g, Bebas, CREAM1);
		
		// player 2 components
		drawCenteredString("Player   2", window_w + 2*mainX - 2*box_size, window_h/2 - 2*box_size, g, BebasTitle, BLACK1);
		drawCenteredString("target", window_w + 2*mainX - 2*box_size, window_h/2 + 3*box_size, g, Bebas, BLACK1);
		drawCenteredString("score", window_w + 2*mainX - 2*box_size, window_h/2 + 10*box_size, g, Bebas, BLACK1);
		
		// player 2 target color
		g.setColor(BLACK1);
		g.fillRect((window_w - mainX) + (mainX/2 - box_size/2) - 7, window_h/2 - 2*box_size - 12, box_size + 14, box_size + 14);
		g.setColor(p2);
		g.fillRect((window_w - mainX) + (mainX/2 - box_size/2) - 5, window_h/2 - 2*box_size - 10, box_size + 10, box_size + 10);
		
		// player 2 score
		g.setColor(p2);
		g.fillRect((window_w - mainX) + (mainX/2 - box_size), window_h/2 + 1*box_size, 2*box_size, 1*box_size + 15);
		drawCenteredString("134", window_w + 2*mainX - 2*box_size, window_h/2 + 13*box_size, g, Bebas, CREAM1);
		
	}
	
	public void drawCenteredString(String s, int w, int h, Graphics g, Font font, Color color) {
	    g.setFont(font);
	    g.setColor(color);
		
		FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	}

	private void setTile(int x, int y)
	{
		int x_index = (int) (x - mainX)/(box_size + 1);
		int y_index = (int) ((y - mainY)/(box_size + 1));
		System.out.println(x_index + " " + y_index);
		if (x_index < 0 || x_index > 9 || y_index < 0 || y_index > 9)
			return;
		Color c = wheel.get(0);
		wheel.remove(0);
		wheel.add(color_lib[r.nextInt(color_lib.length)]);

		if(!(c.equals(well[x_index][y_index])))
			floodFill(x_index, y_index, well[x_index][y_index], c);
		repaint();
		turn++;
	}
	
	private void setTileColor(Color c, int x, int y)
	{
		
		if(x >= 0 && x <= 9 && x >= 0 && x <= 9)
			well[x][y] = c;
	}
	
	private void floodFill(int x, int y, Color before, Color target)
	{
		if (x < 0 || x > 9 || y < 0 || y > 9)
		{
			return;
		}
		
		if(!(well[x][y].equals(before)))
		{
			return;
		}
		
		setTileColor(target, x, y);

		floodFill(x+1, y, before, target);
		floodFill(x-1, y, before, target);
		floodFill(x, y+1, before, target);
		floodFill(x, y-1, before, target);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        System.out.println(mouseX + " " + mouseY);
        setTile(mouseX, mouseY);
	}
	
	private boolean allSame()
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(!(well[i][j].equals(well[0][0])))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public void cheat()
	{
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				well[i][j] = RED1;
			}
		}
		well[0][0] = YELLOW1;
		
		wheel.clear();
		for(int i = 0; i < 6; i++)
		{
			wheel.add(RED1);
		}
		
		repaint();
	}

	public int getWinner()
	{
		if(allSame())
		{
			if(well[0][0].equals(p1))
				return 1;
			else if(well[0][0].equals(p2))
				return 2;
		}
		
		return 0;
	}
}
