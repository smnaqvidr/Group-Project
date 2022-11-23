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
			mainFrame = new JFrame("Western Geographical Information System");
    		Map map = new Map();
    		JLabel background = new JLabel("", map.getImage(), JLabel.CENTER);
    		
    		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    		mainFrame.setState(JFrame.MAXIMIZED_BOTH);
    		mainFrame.setLocation(0, 0);
    		mainFrame.setVisible(true);
    		
    		
    		background.setBounds(0, 0, map.getImage().getIconWidth(), map.getImage().getIconHeight());
    		background.addMouseWheelListener(new GUI());
    		
    		JLayeredPane layers = new JLayeredPane();
    		layers.setPreferredSize(new Dimension(100, 100));
    		layers.add(background);

    		mainFrame.add(layers);
 
        	JPanel panel = new JPanel();
        	panel.setLayout(null);
        	//mainFrame.add(panel);
        	
        	JButton next = new JButton("Next");
        	JButton prev = new JButton("Previous");
        	next.setBounds(10, 10, 100, 25);
        	panel.add(next);
        	//prev.setBounds(0, mainFrame.getHeight() - 25, 100, 25);
        	//mainFrame.add(next);
        	//mainFrame.add(prev);
            next.addActionListener((ActionEvent e) -> { 
            	map.nextFloor();
            	System.out.println(e);
            	System.out.println("Button clicked");});
        	
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	

}
