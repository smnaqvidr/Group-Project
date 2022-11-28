package jsonproject.jsonobject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class readDataFromJSONFile {

	public static void main(String[] args) throws IOException, ParseException {
		
		JSONParser jsonparser = new JSONParser();
		FileReader reader = new FileReader("/Users/dwm/Desktop/Eclipse/jsonobject/jsonFiles/weather.json");
		
		Object obj = jsonparser.parse(reader);
		
		JSONObject empjsonobj = (JSONObject)obj;
		
		JSONArray array = (JSONArray)empjsonobj.get("weather");
		
		for (int i = 0; i < array.size(); i ++) {
			JSONObject weather = (JSONObject)array.get(i);
			
			Long id = (Long) weather.get("id");
			String main = (String) weather.get("main");
			String description = (String) weather.get("description");
			
			System.out.println("Main: " + main);
			System.out.println("ID: " + id);
			System.out.println("Description: " + description);
			
			
			

		}
		
		
		
		
		
		System.out.println();
		
		
		

	}

}


