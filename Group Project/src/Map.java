import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;

public class Map {
	private int floorNum;
	private final double scale = 0.05;
	private ImageIcon mapImage;
	private File mapFile;
	private String buildingName;
	
	public Map() {
		mapFile = new File("resources/maps/MC_1.jpg");
		buildingName = "MC";
		mapImage = new ImageIcon(mapFile.getPath());
		floorNum = 1;
	}
	
	public Map(String buildName, int fNum) {
		mapFile = new File("resources/maps/" + buildName + "_" + Integer.toString(fNum) + ".jpg");
		buildingName = buildName;
		mapImage = new ImageIcon(mapFile.getPath());
		floorNum = fNum;
	}
	
	public Map nextFloor() {
		mapFile = new File("resources/maps/" + buildingName + "_" + Integer.toString(floorNum + 1) + ".jpg");
		try {
			if (mapFile.exists()) {
				mapImage = new ImageIcon(mapFile.getPath());
				floorNum++;
			}
			return this;
		} catch (Exception e) {
			e.printStackTrace();
			return this;
		}
	}
	
	public Map prevFloor() {
		mapFile = new File("resources/maps/" + buildingName + "_" + Integer.toString(floorNum - 1) + ".jpg");
		try {
			if (mapFile.exists()) {
				mapImage = new ImageIcon(mapFile.getPath());
				floorNum--;
			}
			return this;
		} catch (Exception e) {
			e.printStackTrace();
			return this;
		}
	}
	
	public int getFloor() {
		return floorNum;
	}
	
	public String getBuilding() {
		return mapFile.getPath().split("\\\\")[1].split("_")[0];
	}
	
	public ImageIcon getImageIcon() {
		return mapImage;
	}
	
	/* Working on getting this function working 
	public void resize(int zoom) throws IOException {
		int newWidth = (int) (mapImage.getIconWidth() - zoom*mapImage.getIconWidth()*scale);
		int newHeight = (int) (mapImage.getIconHeight() - zoom*mapImage.getIconHeight()*scale);
		Image newimg = mapImage.getImage().getScaledInstance(newWidth, newHeight,  Image.SCALE_FAST);
		mapImage = new ImageIcon(newimg); 
	    
	    
	    
		
        Image newimg = mapImage.getImage().getScaledInstance((int) (mapImage.getIconWidth() - zoom*mapImage.getIconWidth()*scale), (int) (mapImage.getIconHeight() - zoom*mapImage.getIconHeight()*scale),  Image.SCALE_FAST);
		int newImgW = (int) (mapImage.getIconWidth() - zoom*mapImage.getIconWidth()*scale);
	    int newImgH = (int) (mapImage.getIconHeight() - zoom*mapImage.getIconHeight()*scale);
	    BufferedImage resized = new BufferedImage(newImgW, newImgH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D graphics2D = resized.createGraphics();
	    //graphics2D.drawImage(mapImage.getImage(), 0, 0, newImgW, newImgH, null);
	    mapImage = new ImageIcon(resized);
	    
	}
    */
	
}
