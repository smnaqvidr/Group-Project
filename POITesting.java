

import org.junit.Test;

import jsonproject.jsonobject.weatherAPI;

public class POITesting {
	
	   @Test
	    public void testMain() {
		   
	            POI poi = new POI();  
	            
	            poi.POI("MC220", 8, 9);
	          
	            poi.displayPOI(poi, poi.getXCoor(), poi.getYCoor());
	    }
}

