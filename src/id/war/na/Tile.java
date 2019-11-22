package id.war.na;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.MouseInfo;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Random;

public class Tile extends JPanel{

	private Color[][] well;
	private ArrayList<Color> wheel = new ArrayList<Color>();
	private final Color[] color_lib = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA};
	private Random r = new Random();
	
	private int window_w = 1024;
	private int window_h = 600;
	
	private int mainX;
	private int mainY;
	private int wheelX;
	private int wheelY;
	
	private int box_size;
	
	public Tile(int width, int height)
	{
		window_w = width;
		window_h = height;
	}
	
	public void init() {
		box_size = (int) 30 * window_w / 1024;
		mainX = (int) (window_w/2) - ((box_size + 1) * 5);
		mainY = (int) 70 * window_h / 600;
		wheelX = (int) (window_w/2) - ((box_size + 1) * 3);
		wheelY = (int) 450 * window_h / 600;
		
		well = new Color[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				well[i][j] = color_lib[r.nextInt(color_lib.length)];
			}
		}
		well[0][0] = Color.YELLOW;
		
		wheel.clear();
		for(int i = 0; i < 6; i++)
		{
			wheel.add(color_lib[r.nextInt(color_lib.length)]);
		}
		
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(224,224,224));
		g.fillRect(0, 0, window_w, window_h);

		// Paint the tiles
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				g.setColor(well[i][j]);
				g.fillRect((box_size + 1)*i + mainX, (box_size + 1)*j + mainY, box_size, box_size);
			}
		}
		
		// Paint the color wheel
		g.setColor(Color.BLACK);
		g.fillRect(wheelX, wheelY - 2, ((box_size + 1) * 5) + (box_size + 12), (box_size + 4));
		int count = -1;
		for(Color c : wheel)
		{
			g.setColor(c);
			if(count < 0)
			{
				g.setColor(Color.BLACK);
				g.fillRect(wheelX - 3, wheelY - 5, (box_size + 14), (box_size + 14));
				g.setColor(c);
				g.fillRect(wheelX - 1, wheelY - 3, (box_size + 10), (box_size + 10));
			}
			else
				g.fillRect(wheelX + (box_size + 11) + (count * (box_size + 1)), wheelY, box_size, box_size);
			
			count++;
		}
		
	}

	private void setTile(int x, int y)
	{
		int x_index = (int) (x - mainX)/(box_size + 1);
		int y_index = (int) ((y - mainY)/(box_size + 1));
		
		if (x_index < 0 || x_index > 9 || y_index < 0 || y_index > 9)
			return;
		Color c = wheel.get(0);
		wheel.remove(0);
		wheel.add(color_lib[r.nextInt(color_lib.length)]);

		floodFill(x_index, y_index, well[x_index][y_index], c);
		repaint();
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
        setTile(mouseX, mouseY);
	}
	
	public boolean allSame()
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
}
