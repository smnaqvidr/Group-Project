import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.JOptionPane;
import org.json.simple.JSONArray;

import java.io.FileReader;
import java.io.FileWriter;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI {
	public static JSONArray jrr = new JSONArray();
	private static JLabel userLabel;
	private static JLabel userdevLabel;
	private static JTextField userdevText;
	private static JTextField userText;
	private static JLabel passLabel;
	private static JPasswordField passText;
	private static JButton button;
	private static JLabel success;
	private static JButton button2;
	private static JButton button3;
	private static JButton button4;
	
	public GUI() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame("Login");
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);
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
        
        userdevLabel = new JLabel("User/Dev");
        userdevLabel.setBounds(10, 80, 80, 25);
        panel.add(userdevLabel);
        userdevText = new JTextField();
        userdevText.setBounds(100, 80, 165, 25);
        panel.add(userdevText);
        
        
        
        button = new JButton("Login");
        button.setBounds(10, 110, 80, 25);
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    		JSONArray jrr = new JSONArray();
    		Object ob = null;
    		JSONParser Jp = new JSONParser();
    		try {
    			FileReader file=new FileReader("UserData.json");
    			ob=Jp.parse(file);
    			jrr=(JSONArray) ob;
    			file.close();
    			
    		}catch(Exception ex){
        	JOptionPane.showMessageDialog(null,"Error Occured while fetching");
        
        }
                
    		    JSONObject obj = new JSONObject();
                int size = jrr.size();
  			obj.put("Username", userText.getText());
  			obj.put("Password", passText.getText());
  			obj.put("User/Dev", userdevText.getText());
  			for(int i=0;i<size;i++){ 
  				if(obj.equals(jrr.get(i))){
                    JOptionPane.showMessageDialog(null, "Password matched");
                    frame.setVisible(false);
                    frame.dispose();
                    new MainGUI();
                    break;
                }else if(i==size-1){
                JOptionPane.showMessageDialog(null, "Incorrect User/Password");
                }
             
        }
    	}
        }
		);
        panel.add(button);
        
        button2 = new JButton("Sign Up");
        button2.setBounds(100, 110, 90, 25);
        button2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			JSONObject obj = new JSONObject();
    			JSONArray jrr = new JSONArray();
    			JSONParser jp= new JSONParser();
    			try {
    				FileReader file = new FileReader("UserData.json");
    				jrr=(JSONArray)jp.parse(file);
    			}catch(Exception ex) {
    				JOptionPane.showMessageDialog(null, "Error occured");
    			}
    			
    			obj.put("Username", userText.getText());
    			obj.put("Password", passText.getText());
    			obj.put("User/Dev", userdevText.getText());
    			jrr.add(obj);
    			try {
    				FileWriter file = new FileWriter("UserData.json");
    				file.write(jrr.toJSONString());
    				file.close();
    			}catch(Exception ex) {
    				JOptionPane.showMessageDialog(null, "Error occured");
    			}
    			JOptionPane.showMessageDialog(null, jrr);
    		// TODO Auto-generated method stub
    		
    	}
        });
        panel.add(button2);
        
        button3 = new JButton("Delete");
        button3.setBounds(200, 110, 90, 25);
        button3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JSONArray jrr = new JSONArray();
        		Object ob = null;
        		JSONParser Jp = new JSONParser();
        		try {
        			FileReader file=new FileReader("UserData.json");
        			ob=Jp.parse(file);
        			jrr=(JSONArray) ob;
        			file.close();
        			
        		}catch(Exception ex){
            	JOptionPane.showMessageDialog(null,"Error Occured while fetching");
            
            }
                
        		JSONObject obj = new JSONObject();
                int size = jrr.size();
  			obj.put("Username", userText.getText());
  			obj.put("Password", passText.getText());
  			obj.put("User/Dev", userdevText.getText());
  			for(int i=0;i<size;i++){
                if(obj.equals(jrr.get(i))){
                	try {
        				FileWriter file = new FileWriter("UserData.json");
        				jrr.remove(i);
        				file.write(jrr.toJSONString());
        				file.close();
        			}catch(Exception ex) {
        				JOptionPane.showMessageDialog(null, "Error occured");
        			}
                	JOptionPane.showMessageDialog(null, "Successfully Deleted");
                    break;
                }else if(i==size-1){
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
             }
        }
    	}	
        }
        );
        panel.add(button3);
        
        
        success = new JLabel(""); 
        success.setBounds(10, 110, 300, 25);
        frame.setVisible(true);
        panel.add(success);
        
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}