import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI implements ActionListener, MouseWheelListener{
	
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passLabel;
	private static JPasswordField passText;
	private static JButton button;
	private static JLabel success;
	
	private static JLabel map;
	

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
			JFrame frame = new JFrame("Western Geographical Information System");
    		ImageIcon img = new ImageIcon("resources/MC_1.jpg");
    		JLabel map = new JLabel("", img, JLabel.CENTER);
        	
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setSize(img.getIconWidth()+15, img.getIconHeight() - 60);
        	frame.setMinimumSize(frame.getSize());
        	frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - img.getIconWidth()/2, 0);

        	map.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        	frame.add(map);
        	
        	frame.setVisible(true);
        	
        	
        	map.addMouseWheelListener(new GUI());
            
        	/* Unworking previous and next floor buttons
        	JButton next = new JButton("Next");
        	JButton prev = new JButton("Previous");
        	next.setBounds(frame.getWidth() - 110, 10, 100, 25);
        	prev.setBounds(10, frame.getHeight() - 10, 100, 25);
        	frame.add(next);
            frame.add(prev);
            next.addActionListener((ActionEvent e) -> { 
            	ImageIcon newImg = nextFloor(img.getDescription());
        		JLabel bkrgnd = new JLabel("", newImg, JLabel.CENTER);
        		bkrgnd.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        		frame.add(bkrgnd);});
            
            */
        	
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	

}
