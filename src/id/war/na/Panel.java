package id.war.na;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Cursor;

import javax.swing.JPanel;
import java.util.Collections;
import java.util.Random;

public class Panel extends JPanel{

	private Color[][] well;
	private final Color[] color_lib = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA};
	private Random r = new Random();
	
	private int mainX = (1024/2) - (31 * 5);
	private int mainY = 70;
	public void init() {
		well = new Color[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				well[i][j] = color_lib[r.nextInt(color_lib.length)];
			}
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
		
	}

	public void setTileColor(Color c, int x, int y)
	{
		System.out.println(x + " " + y + "\n");
		
		x -= 7; //offset
		int x_index = (int) (x - mainX)/31;
		int y_index = (int) ((y - mainY)/31) - 1;
		
		if(x_index >= 0 && x_index <= 9 && y_index >= 0 && y_index <= 9)
			well[x_index][y_index] = c;
		System.out.println(x_index + " " + y_index + "\n");
		
		repaint();
	}
}
