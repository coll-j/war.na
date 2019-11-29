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
		JButton play, howtoplay, quit;
		
		ImageIcon img = new ImageIcon("src/rsrc/warna_TITLE.jpg");
		Image imgt = img.getImage();
//		setLayout(new GridBagLayout());		
		
		f = frame;
		frame.setLayout(new BorderLayout());
		Rectangle r = frame.getBounds();
		imgt = imgt.getScaledInstance(r.width, r.height, Image.SCALE_DEFAULT);
		
		bg = new JLabel("", new ImageIcon(imgt), JLabel.CENTER);
//		bg = new JLabel();
		bg.setLayout(new GridBagLayout());
		

        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridwidth = GridBagConstraints.VERTICAL;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.anchor = GridBagConstraints.PAGE_END;
//        gbc.weighty = 1;


        play = addAButton("src/rsrc/button_default/PLAY.png");
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);
        
        howtoplay = addAButton("src/rsrc/button_default/HOW TO PLAY.png");
        howtoplay.setBorderPainted(false);
        howtoplay.setContentAreaFilled(false);
        
        quit = addAButton("src/rsrc/button_default/QUIT.png");
        quit.setBorderPainted(false);
        quit.setContentAreaFilled(false); 
        
        //button = new JButton("play");
//        gbc.insets(4,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        bg.add(play, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        bg.add(howtoplay, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        bg.add(quit, gbc);
        
		add(bg);
		ButtonHandler buttoh = new ButtonHandler();
		play.addActionListener(buttoh);
	}
	
	private JButton addAButton (String url) {
		JButton button = new JButton(new ImageIcon(url));
		return button;
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
