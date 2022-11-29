/**
*
* @author Hashim Cheema
*/

package jsonproject.jsonobject;

/**
 * Import all these.
 */
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

public class weatherAPI {
	
	 /**
	* This is a program for displaying the current temperature in London Ontario 
	* using the JSON file provided by OpenWeatherAPI.
	    * @param args
	*/
	public static void main(String[] args)  {
		
		/**
	     * This is the main method 
	     * which is very important for 
	     * execution for a java program.
	     */
		
		/**
	     * Declared  variables parser, jsonObject,
	     * weatherData, iterator
	     * And taking input from the JSON file 
	     * by using JSONParser class. 
	     *
	     */
		JSONParser parser = new JSONParser();
		
		System.out.println("*  *  *  *  *  *  The current weather forecast for London, Ontario is *  *  *  *  *  * ");
		System.out.println("---------------------------------------------------------------------------------------");
		
		try {
			Object obj = parser.parse(new FileReader("/Users/dwm/Desktop/Eclipse/jsonobject/jsonFiles/weather.json"));
			JSONObject jsonObject = (JSONObject) obj;

			/**
		     * Storing the array in weatherData  
		     * and looping through it, checking for
		     * key values
		     */
			JSONArray weatherData = (JSONArray) jsonObject.get("weather");
            Iterator iterator = weatherData.iterator();
            while (iterator.hasNext()) {
            	Iterator<Map.Entry> itr3 = ((Map) iterator.next()).entrySet().iterator();
                while (itr3.hasNext()) {	
                    Map.Entry pair = itr3.next();
                    if (pair.getKey().equals("description")) {
                    	System.out.print(pair.getValue() + " with a temperature of ");
                    }
                }
            }
        
            /**
		     * Fetched the main objects using get method  
		     * and looping through it, checking for
		     * key values
		     */
			Map MainObjects = (Map) jsonObject.get("main");
			
			/**
		     * Define iterator to loop through map
		     */
			Iterator<Map.Entry> itr2 = MainObjects.entrySet().iterator();
            while (itr2.hasNext()) {
                Map.Entry pair = itr2.next();
                /**
    		     * Output temperature
    		     */
                if (pair.getKey().equals("temp")) {
                	System.out.print(pair.getValue() + " degrees celsius ");
                }
            }
            
            /**
		     * Fetched the wind obejcts using get method  
		     * and looping through it, checking for
		     * key values
		     */
            Map windObjects = (Map) jsonObject.get("wind");

			/**
		     * Define iterator to loop through map
		     */
			Iterator<Map.Entry> itr3 = windObjects.entrySet().iterator();
            while (itr3.hasNext()) {
                Map.Entry pair = itr3.next();
                if (pair.getKey().equals("speed")) {
                	/**
        		     * Output wind speeds
        		     */
                	System.out.println("and wind speeds of " + pair.getValue() + " km/h.");
                }
            }	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

