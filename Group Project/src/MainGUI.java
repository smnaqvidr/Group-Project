import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainGUI implements ActionListener, MouseWheelListener, ComponentListener {
	private static JFrame mainFrame;
	private static JLabel background;
	private static JLayeredPane layers;
	private static JButton prev;
	private static JButton next;
	private Map map;
	
	public static void main(String args[]) {
		new MainGUI();
	}
	
	public MainGUI() {
		map = new Map();
		
		mainFrame = new JFrame("Western Geographical Information System");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(map.getImage().getIconWidth(), map.getImage().getIconHeight());
		mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setVisible(true);
		mainFrame.addComponentListener(this);
		
		/*JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Menu");
        JMenu m2 = new JMenu("Favorites");
        JMenu m3 = new JMenu("Buildnings");*/
		
		background = new JLabel("", map.getImage(), JLabel.CENTER);
		background.setBounds(0, 0, map.getImage().getIconWidth(), map.getImage().getIconHeight());
		background.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - map.getImage().getIconWidth()/2, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - map.getImage().getIconHeight()/2);
		background.addMouseWheelListener(this);
		
		layers = new JLayeredPane();
		layers.setPreferredSize(new Dimension(mainFrame.getWidth(), mainFrame.getHeight()));
		layers.add(background, 1);
		mainFrame.add(layers);
		
		next = new JButton("Next >");
		next.setBounds(mainFrame.getWidth() - 150, mainFrame.getHeight() - 75, 100, 25);
		next.addActionListener(this);
		layers.add(next, 0);
		
		prev = new JButton("< Previous");
		prev.setBounds(25, mainFrame.getHeight() - 75, 100, 25);
    	prev.addActionListener(this);
    	layers.add(prev, 0);
    	
    	 //mainFrame.getContentPane().add(BorderLayout.SOUTH, panel);
         //mainFrame.getContentPane().add(BorderLayout.NORTH, mb);
         
    	
	}
	
	public void updateBackground() {
		mainFrame.repaint();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println(e.getWheelRotation());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		
		if (e.getActionCommand().equals("< Previous")) {
			map.prevFloor();
		}
		else if (e.getActionCommand().equals("Next >")) {
			map.nextFloor();
		}
		
    	layers.remove(background);
    	background = new JLabel("", map.getImage(), JLabel.CENTER);
    	background.setBounds(0, 0, map.getImage().getIconWidth(), map.getImage().getIconHeight());
		background.setLocation(0, 0);
		background.addMouseWheelListener(this);
    	layers.add(background, 1);
    	System.out.println(map.getImage());
    	
    	mainFrame.repaint();

		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
