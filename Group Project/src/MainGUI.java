import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainGUI {
	private Map map;
	private static JFrame mainFrame;
	private static JLabel background;
	private static JLayeredPane layers;
	private static JButton prev;
	private static JButton next;
	
	public static void main(String args[]) {
		new MainGUI();
	}
	
	public MainGUI() {
		map = new Map();
		mainFrame = new JFrame("Western Geographical Information System");
		background = new JLabel("", map.getImageIcon(), JLabel.CENTER);
		layers = new JLayeredPane();
		prev = new JButton("< Previous");
		next = new JButton("Next >");
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(map.getImageIcon().getIconWidth(), map.getImageIcon().getIconHeight());
		mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setVisible(true);
		
		mainFrame.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				drawGUI();
			}

			public void componentMoved(ComponentEvent e) {
				drawGUI();
				
			}

			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		background.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				scroll(e);
			}
        });
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextButtonClicked(e);
            }
        });
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevButtonClicked(e);
            }
        });
		
		drawGUI();
	}
	
	public void drawGUI() {
		layers.removeAll();
		mainFrame.remove(layers);
		
		// Background maps
		background.setBounds(0, 0, map.getImageIcon().getIconWidth(), map.getImageIcon().getIconHeight());
		background.setLocation(mainFrame.getWidth()/2 - map.getImageIcon().getIconWidth()/2, mainFrame.getHeight()/2 - map.getImageIcon().getIconHeight()/2);
		layers.setPreferredSize(new Dimension(mainFrame.getWidth(), mainFrame.getHeight()));
		layers.add(background, 1);
		
		/* Navigation bar
		navBar.setBounds(10, 10, 400, 40);
		navBar.setBackground(new Color(255, 250, 245));
		navBar.setBorder(BorderFactory.createLineBorder(Color.black));
		dropdown.setBounds(10, 8, 25, 25);
		searchBar.setBounds(50, 8, 300, 25);
		// make remove text when focused
		searchBar.setText("Search");
		searchBar.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		navBar.add(dropdown);
		navBar.add(searchBar);
		layers.add(navBar, 0);
		*/
		
		// Buttons
		next.setBounds(mainFrame.getWidth() - 150, mainFrame.getHeight() - 75, 100, 25);
		layers.add(next, 0);
		prev.setBounds(25, mainFrame.getHeight() - 75, 100, 25);
    	layers.add(prev, 0);
    	
    	mainFrame.add(layers);
		mainFrame.repaint();
	}
	
	public void scroll(MouseWheelEvent e) {
		// Stuck in loop at this step (if we call without a parameter it scales but incorrectly)
		// Trying to get image to scale down
		map.resize(e.getWheelRotation());
		drawGUI();
		System.out.println(e.getWheelRotation());
	}
	
	public void nextButtonClicked(ActionEvent e) {
		System.out.println(e.getActionCommand());

		map.nextFloor();
    	background = new JLabel("", map.getImageIcon(), JLabel.CENTER);
		
    	drawGUI();
    	
    	System.out.println(map.getImageIcon());
	}

	public void prevButtonClicked(ActionEvent e) {
		System.out.println(e.getActionCommand());

		map.prevFloor();
    	background = new JLabel("", map.getImageIcon(), JLabel.CENTER);
		
    	drawGUI();
    	
    	System.out.println(map.getImageIcon());
	}
	
}
