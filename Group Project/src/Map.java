import javax.swing.*;

import java.awt.Image;
import java.io.*;

public class Map {
	private int floorNum;
	private final int scale = 20;
	private ImageIcon mapImage;
	private File mapFile;
	//private POI[] mapPOI;
	
	public Map() {
		mapFile = new File("resources/MC_1.jpg");
		mapImage = new ImageIcon(mapFile.getPath());
		floorNum = 1;
	}
	
	public Map(String buildingName, int fNum) {
		mapFile = new File("resources/" + buildingName + "_" + Integer.toString(fNum) + ".jpg");
		mapImage = new ImageIcon(mapFile.getPath());
		floorNum = fNum;
	}
	
	public Map nextFloor() {
		mapFile = new File("resources/MC_" + Integer.toString(floorNum + 1) + ".jpg");
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
		mapFile = new File("resources/MC_" + Integer.toString(floorNum - 1) + ".jpg");
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
	
	public void resize(int rotation) {
		ImageIcon newMap = new ImageIcon(mapImage.getImage().getScaledInstance(mapImage.getIconWidth() - rotation*scale, mapImage.getIconHeight() - rotation*scale, Image.SCALE_SMOOTH));
		mapImage = newMap;
	}
	
}
