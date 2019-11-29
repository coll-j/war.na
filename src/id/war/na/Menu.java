package id.war.na;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

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


        play = addAButton("play","src/rsrc/button_default/PLAY.png");
        
        howtoplay = addAButton("howtoplay","src/rsrc/button_default/HOW TO PLAY.png");
        
        quit = addAButton("quit","src/rsrc/button_default/QUIT.png");
        
        gbc.insets = new Insets(150, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        bg.add(play, gbc);
        
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        bg.add(howtoplay, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        bg.add(quit, gbc);
        
		add(bg);
	}
	
	private JButton addAButton (String name,String url) {
		JButton button = new JButton(new ImageIcon(url));
		
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setActionCommand(name);
		
		ButtonHandler buttoh = new ButtonHandler();
		button.addActionListener(buttoh);
		
		MouseHandler mouseh = new MouseHandler();
		button.addMouseListener(mouseh);
		
		
		return button;
	}
	
	private class MouseHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
	private class ButtonHandler implements ActionListener 
	   {
	      // handle button event
	      @Override
	      public void actionPerformed(ActionEvent event)
	      {
	    	 String action = event.getActionCommand();
	    	 
	         if(action.equals("play")) {
	        	 System.out.println("go to game");
	        	 f.layout.show(f.panel, "game");
	         }
	         else if(action.equals("quit")) {
	        	 System.out.println("quit");
	        	 System.exit(1);
	         }
	         else if(action.equals("howtoplay")) {
	        	 System.out.println("howto");
	         }
	      }
	   } 
}
