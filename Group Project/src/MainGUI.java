import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class MainGUI implements ActionListener, MouseWheelListener {
	private static JFrame mainFrame;
	private static JLabel background;
	
	public MainGUI() {
		try {
			Map map = new Map();
			mainFrame = new JFrame("Western Geographical Information System");
    		background = new JLabel("", map.getImage(), JLabel.CENTER);
    		
    		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    		mainFrame.setSize(map.getImage().getIconWidth(), map.getImage().getIconHeight());
    		mainFrame.setMinimumSize(mainFrame.getSize());
    		mainFrame.setVisible(true);
    		
    		background.setBounds(0, 0, map.getImage().getIconWidth(), map.getImage().getIconHeight());
    		background.setLocation(0, 0);
    		background.addMouseWheelListener(new GUI());
    		
    		JLayeredPane layers = new JLayeredPane();
    		layers.setPreferredSize(new Dimension(mainFrame.getWidth(), mainFrame.getHeight()));
    		layers.add(background, 1);
    		mainFrame.add(layers);
 
    		
        	JButton next = new JButton("Next >");
        	JButton prev = new JButton("< Previous");
        	next.setBounds(mainFrame.getWidth() - 125, mainFrame.getHeight() - 75, 100, 25);
        	prev.setBounds(25, mainFrame.getHeight() - 75, 100, 25);
        	layers.add(next, 0);
        	layers.add(prev, 0);
            next.addActionListener((ActionEvent e) -> { 
            	map.nextFloor();
            	layers.remove(background);
            	background = new JLabel("", map.getImage(), JLabel.CENTER);
            	background.setBounds(0, 0, map.getImage().getIconWidth(), map.getImage().getIconHeight());
        		background.setLocation(0, 0);
        		background.addMouseWheelListener(new GUI());
            	layers.add(background, 1);
            	System.out.println(map.getImage());});
            prev.addActionListener((ActionEvent e) -> { 
            	map.prevFloor();
            	layers.remove(background);
            	background = new JLabel("", map.getImage(), JLabel.CENTER);
            	background.setBounds(0, 0, map.getImage().getIconWidth(), map.getImage().getIconHeight());
        		background.setLocation(0, 0);
        		background.addMouseWheelListener(new GUI());
            	layers.add(background, 1);
            	System.out.println(map.getImage());});
        	
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		String message;
		System.out.println(e);
	       int notches = e.getWheelRotation();
	       if (notches < 0) {
	           message = "Mouse wheel moved UP "
	                        + -notches + " notch(es)\n";
	       } else {
	           message = "Mouse wheel moved DOWN "
	                        + notches + " notch(es)\n";
	       }
	       System.out.println(message);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
