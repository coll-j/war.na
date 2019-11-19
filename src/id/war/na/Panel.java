package id.war.na;

import java.awt.Color;

import java.awt.Graphics;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Random;

public class Panel extends JPanel{

	private Color[][] well;
	private ArrayList<Color> wheel = new ArrayList<Color>();
	private final Color[] color_lib = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA};
	private Random r = new Random();
	
	private int mainX = (1024/2) - (31 * 5);
	private int mainY = 70;
	private int wheelX = (1024/2) - (31 * 3);
	private int wheelY = 450;
	
	public void init() {
		well = new Color[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				well[i][j] = color_lib[r.nextInt(color_lib.length)];
			}
		}
		
		for(int i = 0; i < 6; i++)
		{
			wheel.add(color_lib[r.nextInt(color_lib.length)]);
		}
	}
	
	public void paintComponent(Graphics g)
	{
		// Paint the well
		g.setColor(new Color(224,224,224));
		g.fillRect(0, 0, 1024, 600);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				g.setColor(well[i][j]);
				g.fillRect(31*i + mainX, 31*j + mainY, 30, 30);
			}
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(wheelX, wheelY - 2, (31 * 5) + 42, 34);
		int count = -1;
		for(Color c : wheel)
		{
			g.setColor(c);
			if(count < 0)
			{
				g.setColor(Color.BLACK);
				g.fillRect(wheelX - 3, wheelY - 5, 44, 44);
				g.setColor(c);
				g.fillRect(wheelX - 1, wheelY - 3, 40, 40);
			}
			else
				g.fillRect(wheelX + 41 + (count * 31), wheelY, 30, 30);
			
			count++;
		}
		
	}

	public void setTile(int x, int y)
	{
		x -= 7; //offset
		int x_index = (int) (x - mainX)/31;
		int y_index = (int) ((y - mainY)/31) - 1;
		
		if (x_index < 0 || x_index > 9 || y_index < 0 || y_index > 9)
			return;
		Color c = wheel.get(0);
		wheel.remove(0);
		wheel.add(color_lib[r.nextInt(color_lib.length)]);

		System.out.println(well[x_index][y_index] + " " + c);
		floodFill(x_index, y_index, well[x_index][y_index], c);
//		repaint();
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
			System.out.println("ooi");			
			return;
		}
		
		System.out.println(well[x][y] + " " + before);
		if(!(well[x][y].equals(before)))
		{
			System.out.println("same as bfr");
			return;
		}
		
//		setTileColor(target, x, y);
		System.out.println("init color");
		well[x][y] = target;
		
		floodFill(x+1, y, before, target);
		floodFill(x-1, y, before, target);
		floodFill(x, y+1, before, target);
		floodFill(x, y-1, before, target);
	}
	
	private boolean similarColor(Color one, Color two)
	{
		if(one.getRed() == two.getRed() && one.getGreen() == two.getGreen() && one.getBlue() == two.getBlue())
		{
			System.out.println("how comeee");
			System.out.println(one.getRed() + " " + two.getRed() + '\n'
								+ one.getGreen() + " " + two.getGreen() + '\n'
								+ one.getBlue() + " " + two.getBlue());
			return true;
		}
		
		return false;
	}
}
