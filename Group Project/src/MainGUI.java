import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
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
	private JMenuItem m11, m12, m13, m14;
	
	private ArrayList<String> favArray;
	private ArrayList<String> buildingArray;
	public static ArrayList<String> fav_menu_arr = new ArrayList<String>();
	
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
		
		ImageIcon iconLogo = new ImageIcon("pin.png");
		
		
		JLabel TestTest = new JLabel();
		TestTest.setOpaque(true);
		//TestTest.setBackground(Color.blue);
		TestTest.setBounds(150, 150, 500, 500);
		TestTest.setIcon(iconLogo);
		

		
		
		
		
		
		int xC = 200;
    	int yC = 200;
    	
    	JLabel contentPane = new JLabel() {
            public void paintComponent(Graphics g) {
                                  Image img = Toolkit.getDefaultToolkit().getImage(
                                  "pin.png");
                                  g.drawImage(img, xC, yC, 40, 50, this);
                                 }
           };
		// Update background maps and add to layers
		background.setIcon(map.getImageIcon());
		background.setBounds(0, 0, map.getImageIcon().getIconWidth(), map.getImageIcon().getIconHeight());
		background.setLocation(mainFrame.getWidth()/2 - map.getImageIcon().getIconWidth()/2, mainFrame.getHeight()/2 - map.getImageIcon().getIconHeight()/2);
		layers.setPreferredSize(new Dimension(mainFrame.getWidth(), mainFrame.getHeight()));
		layers.add(background, 1);
		
		// Update buttons and add to layers
		next.setBounds(mainFrame.getWidth() - 150, mainFrame.getHeight() - 100, 100, 25);
		layers.add(next, 0);
		prev.setBounds(25, mainFrame.getHeight() - 100, 100, 25);
    	layers.add(prev, 0);

        
    	// Add Navigation menu to layers
    	layers.add(container, 0);
    	layers.add(TestTest, Integer.valueOf(1));
    	layers.add(contentPane, Integer.valueOf(2));
    	
    	
    	
    	
    	
          // System.out.println("Hello");
        //layers.add(contentPane, Integer.valueOf(3));
        
    	// Add layers back to mainFrame and repaint
    	mainFrame.add(layers);

		mainFrame.repaint();
		mainFrame.setVisible(true);
		
		//addPOI();
		
	}
	
	public void generateNav() {
		// Initializing navigation bar components
		container = new JPanel();
		menubar = new JMenuBar();
		menu = new JMenu("Menu");
        favourites = new JMenu("Favorites");
        buildings = new JMenu("Buildnings");
        
        // Create menu items for menu
        m11 = new JMenuItem("Dispaly Options");
        m12 = new JMenuItem("Settings");
        m13 = new JMenuItem("Help");
        m14 = new JMenuItem("Logout");
        
        // Create menu items for buildings menu
        buildingArray = new ArrayList<String>();
        for (File f : new File("resources/maps/").listFiles()) {
        	if (!buildingArray.contains(f.getName().split("_")[0])) {
        		buildingArray.add(f.getName().split("_")[0]);
        	}
        }


        
		// Setting navigation bar defaults 
		container.setLayout(new BorderLayout());
		container.setBounds(15, 15, 400, 40);
		container.add(menubar);
		menubar.add(menu);
    	menu.add(buildings);
    	menu.add(favourites);
    	menu.add(m11);
    	menu.add(m12);
    	menu.add(m13);
    	menu.add(m14);
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
        
        search = new JTextField(20); // accepts up to 20 characters
        search.setBounds(90, 20, 250, 30);
        menubar.add(search);
        
        //new
        search.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String searchWord;
				searchWord = search.getText();
				JSONParser parser = new JSONParser();
		    	
				//Change JSON file!!!!!!!!
		    	try(FileReader reader = new FileReader("UserData.json") ) 
		    	{
		    		Object obj = parser.parse(reader);
		    		JSONArray empList = (JSONArray) obj;

		    		empList.forEach(emp -> parseEmpObj((JSONObject)emp, searchWord));
		    		
		    	} catch(Exception err) 
		    	{
		    		err.printStackTrace();
		    	}
			}	
        });
        
      ///Displays menu for favourites in the top menu
        printFavMenu();
        
          
        //Action listeners for menu
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
            	// Need to switch to login GUI here (fix: in GUI class use extends JFrame)
            	//mainFrame.dispose();
            	//GUI login = new GUI();
            	//login.setVisible(true);
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
	
	//Change so it founds the right attribute!!!
	public static void parseEmpObj(JSONObject emp, String word) {
		
    	//JSONObject empObj = (JSONObject) emp.get("Username");
    	//String room = (String) empObj.get("room_nr");
		
		JSONObject jsonObject = (JSONObject) emp;
    	String room = (String) jsonObject.get("Username");
    	
    	if((room.startsWith(word))) {
    		System.out.println("Room is: " + room);
		}
    }
	
	public static void printFavMenu() {
		
		int xC = 50;
		int yC = 60;
		JSONParser parser = new JSONParser();
        try {
        	
        	Object obj = parser.parse(new FileReader("UserData.json"));
        	JSONArray empList = (JSONArray) obj;
        	 
        	empList.forEach(emp -> parseEmpObjUser_fav((JSONObject)emp));

        	
        } catch(Exception e1) {
        	e1.printStackTrace();
        }
        
        System.out.println("test_arr: " + fav_menu_arr);
        
		for (String i : fav_menu_arr) {
		        	
		        	JMenuItem x = new JMenuItem(i);
		        	favourites.add(x);
		        	x.addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                    //System.out.println(i);
		                	display_POI(xC,yC);

		                }
		 });}
	}
	
public static void parseEmpObjUser_fav(JSONObject emp) {
		
		JSONObject jsonObject = (JSONObject) emp;
    	String username = (String) jsonObject.get("Username");    	
    	if(username.equals("mike")) {
    		JSONArray test_arr_fav = (JSONArray) emp.get("Fav_POI");
        	test_arr_fav.forEach(fav -> print_menu_two_room((JSONObject)fav));	

    	}

    	
    	
    }
	
	public static void print_menu_two_room(JSONObject emp) {

			JSONObject jsonObject = (JSONObject) emp;
	    	String name = (String) jsonObject.get("name");
	    	fav_menu_arr.add(name);
		}
	
	public static void display_POI(int x, int y) {
		
		JPanel contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                                  Image img = Toolkit.getDefaultToolkit().getImage(
                                  "pin.png");
                                  g.drawImage(img, x, y, 25, 40, this);
                                 }
           };
           System.out.println("Hello");
           layers.add(contentPane,0);
	}
	
	
	
}
