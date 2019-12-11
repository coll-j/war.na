package id.war.na;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HowToPlay extends JPanel {
	JLabel bg;
	Screen f;
	int pos = 0;
	String[] ImageList = {"src/rsrc/howtowarna/1.png",
						  "src/rsrc/howtowarna/2.png",
						  "src/rsrc/howtowarna/3.png",
						  "src/rsrc/howtowarna/4.png",
						  "src/rsrc/howtowarna/5.png",
						  "src/rsrc/howtowarna/6.png"};
	
	public HowToPlay(Screen frame) {
		JButton right, left, menu;
		
		ImageIcon img = new ImageIcon(ImageList[0]);
		Image imgt = img.getImage();
		
		f = frame;
		frame.setLayout(new BorderLayout());
		Rectangle r = frame.getBounds();
		imgt = imgt.getScaledInstance(r.width, r.height, Image.SCALE_DEFAULT);
		
		bg = new JLabel("", new ImageIcon(imgt), JLabel.CENTER);
		bg.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		right = addAButton("right", "src/rsrc/button_default/right.png");    
		left = addAButton("left", "src/rsrc/button_default/left.png");
		menu = addAButton("menu", "src/rsrc/button_default/menu.png");
		menu.setRolloverIcon(new ImageIcon("src/rsrc/button_highlighted/menu.png"));
		menu.setPressedIcon(new ImageIcon("src/rsrc/button_pressed/menu.png"));
		
		gbc.insets = new Insets(r.height * 5/256, 0, 0, r.width* -705/683);
		gbc.gridy = 1;
		gbc.gridx = 1;
		bg.add(right, gbc);
		
		gbc.insets = new Insets(r.height * 5/256, r.width * -625/683, 0, 0);
		gbc.gridy = 1;
		gbc.gridx = 2;
		bg.add(left, gbc);
		
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.insets = new Insets(r.height*10/11, 0, 0, 0);
		bg.add(menu,gbc);
		
		add(bg);
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
	
	private class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String action = e.getActionCommand();
			if(action.equals("right")) {
				pos++;
				if(pos > ImageList.length - 1) {
					pos = 0;
				}
				bg.setIcon(new ImageIcon(ImageList[pos]));
			}
			if(action.equals("left")) {
				pos--;
				if(pos < 0) {
					pos = ImageList.length - 1;
				}
				bg.setIcon(new ImageIcon(ImageList[pos]));
			}
			if(action.equals("menu")) {
				f.layout.show(f.panel, "menu");
			}
		}
		
	}

}
