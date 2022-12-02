/**
 * This class represents the POI of the maps. 
 * @author Hashim Cheema
 *
 */

/**
 * Import all these.
 */
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class POI {
	
	/**
	 * buildingName name of the building
	 */
	public String buildingName;
	
	/**
	 * xCoor x coordinate of the building
	 */
	private int xCoor;
	
	/**
	 * yCoor y coordinate of the building
	 */
	private int yCoor;
	
	/**
	 * Constructor for the class which initializes the buildingName, xCoor, and yCoor
	 * @param buildingName name of the building
	 * @param xCoor x coordinate of the building
	 * @param yCoor y coordinate of the building
	 */
	public void POI(String buildingName, int xCoor, int yCoor) {
		this.buildingName = buildingName;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
	}
	
	/**
	 * This function gets the x coordinate
	 * @return xCoor
	 */
	public int getXCoor() {
		return xCoor;
	}
	
	/**
	 * This function gets the y coordinate
	 * @return yCoor
	 */
	public int getYCoor() {
		return yCoor;
	}
	
	/**
	 * This function sets the x coordinate
	 * @param x x coordinate you want to set
	 */
	public void setXCoor(int x) {
		 this.xCoor = x;
	}
	
	/**
	 * This function sets the y coordinate
	 * @param y y coordinate you want to set
	 */
	public void setYCoor(int y) {
		 this.yCoor = y;	
	}
	
	/**
	 * This function returns the buildingn name
	 * @return buildingName
	 */
	public String getBuildingName() {
		return this.buildingName;	
	}
	
	/**
	 * This function retrieves the POI specified by the xCoor and yCoor
	 * @param buildingObject
	 * @param xCoor x-coordinate of POI
	 * @param yCoor y-coordinate of POI
	 * @return buildingObject buildingObject associated with xCoor and yCoor
	 */
	public building getPOI(building buildingObject,int xCoor, int yCoor) {
		
		if (buildingObject.getXCoor() == xCoor && buildingObject.getYCoor() == yCoor ) {
			return buildingObject;
		}
		return buildingObject;
	}
	
	/**
	 * This function removes the POI specified by the xCoor and yCoor
	 * @param buildingObject buildingObject that you want to remove
	 * @param xCoor x coordinate of buiildingObject that you want to remove
	 * @param yCoor y coordinate of buiildingObject that you want to remove
	 */
	public void removePOI(building buildingObject,int xCoor, int yCoor) {
		if (buildingObject.getXCoor() == xCoor && buildingObject.getYCoor() == yCoor ) {
			buildingObject = null;
		}
	}
	
	/**
	 * This function displays the POI specified by the xCoor and yCoor
	 * @param buildingObject builidingObject that you want to remove
	 * @param xCoor xCoor x coordinate of buildingObject you want to display
	 * @param yCoor yCoor y coordinate of buildingObject you want to display
	 */
	public void displayPOI(building buildingObject, int xCoor, int yCoor) {
		
		if (buildingObject.getXCoor() == xCoor && buildingObject.getYCoor() == yCoor ) {
			System.out.println("This is building " + buildingObject.getBuildingName());
		}
	}
	
	/**
	 * This function modifies the POI you want to change with the specified xCoor and yCoor
	 * @param buildingObject buildingObject that you want to modify
	 * @param xCoor xCoor x coordinate of buildingObject you want to modify
	 * @param yCoor yCoor y coordinate of buildingObject you want to modify
	 * @param newxCoor newxCoor new x coordinate of buildingObject you want to display
	 * @param newYcoor newyCoor new y coordinate of buildingObject you want to display
	 */
	public void modifyPOI(building buildingObject, int xCoor, int yCoor, int newxCoor, int newyCoor) {
		if (buildingObject.getXCoor() == xCoor && buildingObject.getYCoor() == yCoor ) {
			buildingObject.setXCoor(newxCoor);
			buildingObject.setYCoor(newyCoor);
		}
	}
	
	public static void main(String[] args)  {
		
		/**
	     * This is the main method 
	     * which is very important for 
	     * execution for a java program.
	     */
		
		/**
	     * Declared  variables parser, jsonObject,
	     * poiData, iterator
	     * And taking input from the JSON file 
	     * by using JSONParser class. 
	     *
	     */
		JSONParser parser = new JSONParser();
	
		try {
			
			Object obj = parser.parse(new FileReader("/Users/dwm/Desktop/Eclipse/jsonobject/src/test/java/POI.json"));
			JSONObject jsonObject = (JSONObject) obj;

			/**
		     * Storing the array in poiData  
		     * and looping through it, checking for
		     * key values
		     */
			JSONArray poiData = (JSONArray) jsonObject.get("POI");
            Iterator iterator = poiData.iterator();
            while (iterator.hasNext()) {
            	Iterator<Map.Entry> itr3 = ((Map) iterator.next()).entrySet().iterator();
                while (itr3.hasNext()) {	
                	/**
                	 * Output the respective values.
                	 */
                    Map.Entry pair = itr3.next();
                    if (pair.getKey().equals("room_nr")) {
                    	System.out.print("Room Number: " + pair.getValue());
                    }
                    
                    if (pair.getKey().equals("coordinate")) {
                    	System.out.print("Coordinate: " + pair.getValue());
                    }
                    
                    if (pair.getKey().equals("building")) {
                    	System.out.print("Building: " + pair.getValue());
                    }
                }
            }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

