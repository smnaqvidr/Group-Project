import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class MainGUI {
	private Map map;
	private static JFrame mainFrame;
	private static JLabel background;
	private static JLayeredPane layers;
	private static JButton prev, next;
	
	private ArrayList<String> favArray;
	private JMenuBar mb;
	private JMenu m1, m2, m3;
	private JMenuItem m11, m12, m13, m14, m31, m32, m33;
	
	public static void main(String args[]) {
		new MainGUI();
	}
	
	public MainGUI() {
		// Initializing variables and components
		map = new Map();
		mainFrame = new JFrame("Western Geographical Information System");
		background = new JLabel("", map.getImageIcon(), JLabel.CENTER);
		layers = new JLayeredPane();
		prev = new JButton("< Previous");
		next = new JButton("Next >");
		// Setting GUI frame defaults
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(map.getImageIcon().getIconWidth(), map.getImageIcon().getIconHeight());
		mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setVisible(true);
		
		drawGUI(); 
		
		// Navigation menu
		ArrayList<String> favArray = new ArrayList<String>(
    			Arrays.asList("MC_220", "MC_240", "HC_110")
    			
    			);
        
        //Creating the menu and adding components to the menu m2 and m3 are menus inside of menu m1
        mb = new JMenuBar();
        m1 = new JMenu("Menu");
        m2 = new JMenu("Favorites");
        m3 = new JMenu("Buildnings");
        
        mb.add(m1);
        
        //Create menu items for menu one
        m11 = new JMenuItem("Dispaly Options");
        m12 = new JMenuItem("Settings");
        m13 = new JMenuItem("Help");
        m14 = new JMenuItem("Logout");
        
        //Create menu items for menu m3 buildings
        m31 = new JMenuItem("Building 1");
        m32= new JMenuItem("Building 2");
        m33 = new JMenuItem("Building 3");
       
        //Adds items to menu one
        m1.add(m3);
        m1.add(m2);
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
        
        //Menu m2 loops through the array of favorites and displays them as options in menu 2
        for (String i : favArray) {
        	JMenuItem x = new JMenuItem(i);
        	m2.add(x);
        	x.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(i);
                }
            });
        	
        }
        
        //Adds items to menu three
        m3.add(m31);
        m3.add(m32);
        m3.add(m33);
        
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel();        
        JLabel label = new JLabel("Search");
        JTextField tf = new JTextField(20); // accepts up to 20 characters
        
        mb.add(label); 
        mb.add(tf);
        
        mainFrame.getContentPane().add(BorderLayout.NORTH, mb);
        mainFrame.setVisible(true);
        
        
        //Action listener for m1
        m11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Dispaly Options");
            }
        });
        
        m12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Settings");
            }
        });
        
        m14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logout");
            }
        });
        
        
        m13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	JDialog d = new JDialog(mainFrame, "User Guide");
                JLabel l = new JLabel("Text ...");
                d.add(l);
                d.setSize(200, 200);
                d.setVisible(true);
                
            }
        });
        
        //Action listener for m3
        m31.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Buildning 1");
            }
        });
        
        m32.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Buildning 2");
            }
        });
        
        m33.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Buildning 3");
            }
        });
        
        
        
        
		// Adding component listeners 
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
		
	}
	
	public void drawGUI() {
		layers.removeAll();
		mainFrame.remove(layers);
		
		// Background maps
		background.setIcon(map.getImageIcon());
		background.setBounds(0, 0, map.getImageIcon().getIconWidth(), map.getImageIcon().getIconHeight());
		background.setLocation(mainFrame.getWidth()/2 - map.getImageIcon().getIconWidth()/2, mainFrame.getHeight()/2 - map.getImageIcon().getIconHeight()/2);
		layers.setPreferredSize(new Dimension(mainFrame.getWidth(), mainFrame.getHeight()));
		layers.add(background, 1);
		
		// Buttons
		next.setBounds(mainFrame.getWidth() - 150, mainFrame.getHeight() - 100, 100, 25);
		layers.add(next, 0);
		prev.setBounds(25, mainFrame.getHeight() - 100, 100, 25);
    	layers.add(prev, 0);
        
    	mainFrame.add(layers);
		mainFrame.repaint();
	}
	
	
	public void scroll(MouseWheelEvent e) {
		try {
			//map.resize(e.getWheelRotation());
		}
		catch (Exception except) {
			except.printStackTrace();
		}
		drawGUI();
	}
	
	public void nextButtonClicked(ActionEvent e) {
		map.nextFloor();
    	background = new JLabel("", map.getImageIcon(), JLabel.CENTER);
    	background.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				scroll(e);
			}
        });
    	drawGUI();
	}

	public void prevButtonClicked(ActionEvent e) {
		map.prevFloor();
    	background = new JLabel("", map.getImageIcon(), JLabel.CENTER);
    	background.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				scroll(e);
			}
        });
    	drawGUI();   	
	}
	
}
