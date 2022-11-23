import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class GUI implements ActionListener, MouseWheelListener{
	
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passLabel;
	private static JPasswordField passText;
	private static JButton button;
	private static JLabel success;
	
	private static JFrame mainFrame;
	private static JLabel background;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);
        userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);
        
        passLabel = new JLabel("Password");
        passLabel.setBounds(10, 50, 80, 25);
        panel.add(passLabel);
        passText = new JPasswordField();
        passText.setBounds(100, 50, 165, 25);
        panel.add(passText);
        
        button = new JButton("Login");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new GUI());
        panel.add(button);
        
        success = new JLabel(""); 
        success.setBounds(10, 110, 300, 25);
        frame.setVisible(true);
        panel.add(success);
        
        displayMain(); // Temporary function call
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String user = userText.getText();
		String password = passText.getText();
		
		if(user.equals("syed") && (password.equals("syed"))){
			success.setText("Login Successful");
			
			// Main GUI
	        displayMain();
		}
		// TODO Auto-generated method stub
		
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
	
	public static void displayMain() {
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
	

}
