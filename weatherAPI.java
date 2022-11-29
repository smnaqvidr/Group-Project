package jsonproject.jsonobject;

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

	public static void main(String[] args) throws IOException, ParseException {
		
		
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("/Users/dwm/Desktop/Eclipse/jsonobject/jsonFiles/weather.json"));
			JSONObject jsonObject = (JSONObject) obj;
			System.out.println(jsonObject);
			
			JSONArray weatherData = (JSONArray) jsonObject.get("weather");
            Iterator iterator = weatherData.iterator();
            while (iterator.hasNext()) {
            	
            	Iterator<Map.Entry> itr3 = ((Map) iterator.next()).entrySet().iterator();
                while (itr3.hasNext()) {
                	
                    Map.Entry pair = itr3.next();
                    if (pair.getKey().equals("description")) {
                    	System.out.println(pair.getValue());
                    }
           
                    //System.out.println(pair.getKey() + " : " + pair.getValue());
                }
            }
            
            System.out.println("----------------------------------");
            
            //fetched the main using .get
			Map MainObjects = (Map) jsonObject.get("main");
			//define iterator to loop through map
			Iterator<Map.Entry> itr2 = MainObjects.entrySet().iterator();
            while (itr2.hasNext()) {
                Map.Entry pair = itr2.next();
                if (pair.getKey().equals("temp")) {
                	System.out.println(pair.getValue());
                }
                //System.out.println(pair.getKey() + " : " + pair.getValue());
            }
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
		
		
		

	}

}

