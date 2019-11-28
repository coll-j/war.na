package id.war.na;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Menu extends JPanel {
	
	Screen f;
	public Menu(Screen frame)
	{
		JLabel bg;
		JButton button;
		
		ImageIcon img = new ImageIcon("src/rsrc/warna_TITLE.jpg");
		Image imgt = img.getImage();
//		setLayout(new GridBagLayout());		
		
		f = frame;
		Rectangle r = frame.getBounds();
		imgt = imgt.getScaledInstance(r.width, r.height, Image.SCALE_DEFAULT);
		
		bg = new JLabel("", new ImageIcon(imgt), JLabel.CENTER);
//		bg = new JLabel();
		bg.setLayout(new GridBagLayout());
		

        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.anchor = GridBagConstraints.PAGE_END;
//        gbc.weighty = 0.1;
//        gbc.gridy = 2;

        button = new JButton("play");
        bg.add(button, gbc);
        
		add(bg);
		button.addActionListener(new ButtonHandler());
	}
	
	private class ButtonHandler implements ActionListener 
	   {
	      // handle button event
	      @Override
	      public void actionPerformed(ActionEvent event)
	      {
	         System.out.println("go to game");
	         f.layout.show(f.panel, "game");
	      }
	   } 
}
