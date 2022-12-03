import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;

public class Map {
	private int floorNum;
	private final double zoomFactor = 0.10;
	private ImageIcon mapImage;
	private File mapFile;
	private String buildingName;
	
	public Map() {
		mapFile = new File("resources/maps/MC_1.png");
		buildingName = "MC";
		mapImage = new ImageIcon(mapFile.getPath());
		floorNum = 1;
		resize();
	}
	
	public Map(String buildName, int fNum) {
		mapFile = new File("resources/maps/" + buildName + "_" + Integer.toString(fNum) + ".png");
		buildingName = buildName;
		mapImage = new ImageIcon(mapFile.getPath());
		floorNum = fNum;
		resize();
	}
	
	public Map nextFloor() {
		mapFile = new File("resources/maps/" + buildingName + "_" + Integer.toString(floorNum + 1) + ".png");
		if (mapFile.exists()) {
			mapImage = new ImageIcon(mapFile.getPath());
			floorNum++;
		}
		resize();
		return this;
	}
	
	public Map prevFloor() {
		mapFile = new File("resources/maps/" + buildingName + "_" + Integer.toString(floorNum - 1) + ".png");
		if (mapFile.exists()) {
				mapImage = new ImageIcon(mapFile.getPath());
				floorNum--;
			}
		resize();
		return this;
	}
	
	public int getFloor() {
		return floorNum;
	}
	
	public String getBuilding() {
		return buildingName;
	}
	
	public ImageIcon getImageIcon() {
		return mapImage;
	}
	
	
	public void resize() {
		double w = mapImage.getIconWidth() / Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double h = mapImage.getIconHeight() / Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		Double min = Math.min(w, h);
		Image newimg = mapImage.getImage().getScaledInstance((int) (mapImage.getIconWidth() / min * 0.8), (int) (mapImage.getIconHeight() / min * 0.8),  Image.SCALE_FAST);
		mapImage = new ImageIcon(newimg);   
	}
   
	/* Working on scaling clearly 
	public void zoom(int zoom) {
		int newImgW = (int) (mapImage.getIconWidth() - zoom*mapImage.getIconWidth()*zoomFactor);
	    int newImgH = (int) (mapImage.getIconHeight() - zoom*mapImage.getIconHeight()*zoomFactor);
	    BufferedImage resized = new BufferedImage(newImgW, newImgH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D graphics2D = resized.createGraphics();
	    graphics2D.drawImage(resized, 0, 0, newImgW, newImgH, null);
	    mapImage = new ImageIcon(resized);
	    
	    
		Image newimg = mapImage.getImage().getScaledInstance((int) (mapImage.getIconWidth() - zoom*mapImage.getIconWidth()*zoomFactor), (int) (mapImage.getIconHeight() - zoom*mapImage.getIconHeight()*zoomFactor),  Image.SCALE_DEFAULT);
		mapImage = new ImageIcon(newimg);   
		
	}
	*/
}
