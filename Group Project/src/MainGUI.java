import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MainGUI {
	private Map map;
	private static JFrame mainFrame;
	private static JLabel background;
	private static JLayeredPane layers;
	private static JButton prev, next;
	
	private static JPanel container;
	private static JMenuBar menubar;
	private static JTextField search;
	private static JMenu menu, favourites, buildings;
	private JMenuItem display, settings, help, logout;
	
	private ArrayList<String> favArray;
	private ArrayList<String> buildingArray;
	
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
		
		generateNav();
		
		drawGUI();
		
		addToFavourites();
		
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
		background.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				scroll(e);
			}
        });
		background.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
               addPOI();
            }

            //get location when press
            public void mousePressed(MouseEvent e) {
                background.setText("Pressed at[" + e.getX() + "," + e.getY() + "]");
            }

            //put image when release
            public void mouseReleased(MouseEvent e) {
                background.setText("Released at[" + e.getX() + "," + e.getY() + "]");
            }

            //enter
            public void mouseEntered(MouseEvent e) {
                //System.out.println("Entered at[" + e.getX() + "," + e.getY() + "]");
            }

            //exit
            public void mouseExited(MouseEvent e) {
                background.setText("window");
            }

            //dragged status
            public void mouseDragged(MouseEvent e) {
                background.setText("Dragged at[" + e.getX() + "," + e.getY() + "]");
            }

            //moving status
            public void mouseMoved(MouseEvent e) {
                background.setText("Moved at[" + e.getX() + "," + e.getY() + "]");
            }
        });
		
	}
	
	public void drawGUI() {
		// Clear all from layers
		layers.removeAll();
		mainFrame.remove(layers);
		
		// Update background maps and add to layers
		background.setIcon(map.getImageIcon());
		background.setBounds(0, 0, map.getImageIcon().getIconWidth(), map.getImageIcon().getIconHeight());
		background.setLocation(mainFrame.getWidth()/2 - map.getImageIcon().getIconWidth()/2, 0); //mainFrame.getHeight()/2 - map.getImageIcon().getIconHeight()/2);
		layers.setPreferredSize(new Dimension(mainFrame.getWidth(), mainFrame.getHeight()));
		layers.add(background, 1);
		
		// Update buttons and add to layers
		next.setBounds(mainFrame.getWidth() - 150, mainFrame.getHeight() - 100, 100, 25);
		layers.add(next, 0);
		prev.setBounds(25, mainFrame.getHeight() - 100, 100, 25);
    	layers.add(prev, 0);
        
    	// Add Navigation menu to layers
    	layers.add(container, 0);
    	
    	// Add layers back to mainFrame and repaint
    	mainFrame.add(layers);
		mainFrame.repaint();
		mainFrame.setVisible(true);
		
	}
	
	public void generateNav() {
		// Initializing navigation bar components
		container = new JPanel();
		menubar = new JMenuBar();
		menu = new JMenu("Menu");
        favourites = new JMenu("Favorites");
        buildings = new JMenu("Buildnings");
        // Create menu items for menu
        display = new JMenuItem("Dispaly Options");
        settings = new JMenuItem("Settings");
        help = new JMenuItem("Help");
        logout = new JMenuItem("Logout");
        // Create menu items for buildings menu
        buildingArray = new ArrayList<String>();
        for (File f : new File("resources/maps/").listFiles()) {
        	if (!buildingArray.contains(f.getName().split("_")[0])) {
        		buildingArray.add(f.getName().split("_")[0]);
        	}
        }
     		
        // Create menu items for favourites menu
        favArray = new ArrayList<String>(
				Arrays.asList("MC_220", "MC_240", "MC_110")
    			);
        
		// Setting navigation bar defaults 
		container.setLayout(new BorderLayout());
		container.setBounds(15, 15, 400, 40);
		container.add(menubar);
		menubar.add(menu);
    	menu.add(buildings);
    	menu.add(favourites);
    	menu.add(display);
    	menu.add(settings);
    	menu.add(help);
    	menu.add(logout);
    	// Loops through the array of buildings and displays them as options in buildings menu       
    	String buildName = null;
        for (String i : buildingArray) {
        	switch (i) {
	    		case "MC":
	    			buildName = "Middlesex College";
	    			break;
	    		case "AH":
	    			buildName = "Alumni Hall";
	    			break;
	    		case "VAC":
	    			buildName = "Visual Arts Center";
	    			break;
			}
        	JMenuItem x = new JMenuItem(buildName);
        	buildings.add(x);
        	x.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    map = new Map(i, 1);
                    drawGUI();
                }
            });
        	
        }
        // Loops through the array of favorites and displays them as options in favourites menu
        for (String i : favArray) {
        	JMenuItem x = new JMenuItem(i);
        	favourites.add(x);
        	x.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(i);
                }
            });
        	
        }
        
        search = new JTextField(20); // accepts up to 20 characters
        search.setBounds(90, 20, 250, 30);
        menubar.add(search);
                
        //Action listeners for menu
        display.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Dispaly Options");
            }
        });
        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Settings");
            }
        });
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mainFrame.setVisible(false);
            	mainFrame.dispose();
            	new GUI();
            }
        });      
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JDialog d = new JDialog(mainFrame, "User Guide");
                JLabel l = new JLabel("Text ...");
                d.add(l);
                d.setSize(200, 200);
                d.setVisible(true); 
            }
        });
        
	}
	
	public void addToFavourites() {
		// add to favorites functionality
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("favData.json"));
			JSONArray arr = (JSONArray)obj;
			System.out.println(arr);
			
		
			JSONObject favPOI = new JSONObject();
			favPOI.put("Username", "mike");
			favPOI.put("favList", new ArrayList<String>(Arrays.asList("MC_220", "MC_240", "MC_110")));
			//favPOI.put("favList", new ArrayList<String>(Arrays.asList("POI1", "POI2", "POI3")));
			
			// Overwrite object if user already exists to eliminate duplicate entries (e.g. one entry per user)
			boolean contains = false;
			for (Object o : arr) {
				JSONObject jobj = (JSONObject)o;
				if (jobj.get("Username").equals(favPOI.get("Username"))) {
					contains = true;
				}
			}
			if (contains) {
				arr.add(0, favPOI);;
			}
			else {
				arr.add(favPOI);
			}
			
			System.out.println(arr);
			
			FileWriter file = new FileWriter("favData.json");
			file.write(arr.toJSONString());
			file.flush();
			file.close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void addPOI() {
		background.addMouseListener(new MouseListener() {
			
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked at[" + e.getX() + "," + e.getY() + "]");
                JLabel jLabel = new JLabel();
                jLabel.setBounds(e.getX(), e.getY(), 30, 30);
                Image img;
				try {
					img = ImageIO.read(new File("resources/poi.png"));
					img.getScaledInstance(10, 10, Image.SCALE_AREA_AVERAGING);
	                ImageIcon icon = new ImageIcon(img);
	                jLabel.setIcon(icon);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                layers.add(jLabel, 0);

            }

            //get location when press
            public void mousePressed(MouseEvent e) {
                background.setText("Pressed at[" + e.getX() + "," + e.getY() + "]");
            }

            //put image when release
            public void mouseReleased(MouseEvent e) {
                background.setText("Released at[" + e.getX() + "," + e.getY() + "]");
            }

            //enter
            public void mouseEntered(MouseEvent e) {
                background.setText("Entered at[" + e.getX() + "," + e.getY() + "]");
            }

            //exit
            public void mouseExited(MouseEvent e) {
                background.setText("window");
            }

            //dragged status
            public void mouseDragged(MouseEvent e) {
                background.setText("Dragged at[" + e.getX() + "," + e.getY() + "]");
            }

            //moving status
            public void mouseMoved(MouseEvent e) {
                background.setText("Moved at[" + e.getX() + "," + e.getY() + "]");
            }
        });
	}
	
	
	public void scroll(MouseWheelEvent e) {
		//map.zoom(e.getWheelRotation());
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
