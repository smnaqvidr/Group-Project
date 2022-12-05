import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class POITest {

	@Test
	void testPOI() {
		
		//fail("Not yet implemented");
	}

	@Test
	void testGetXCoor() {
		System.out.println("testGetBuilding");
		POI m = new POI("MC220", "2ndfloor", 200, 300, "classroom", "MC_2.png");
		int expResult=200; 
		int result=m.getXCoor();
		assertEquals(expResult,result);
	}

	@Test
	void testGetYCoor() {
		System.out.println("testGetBuilding");
		POI m = new POI("MC220", "2ndfloor", 200, 300, "classroom", "MC_2.png");
		int expResult=300; 
		int result=m.getYCoor();
		assertEquals(expResult,result);
		//fail("Not yet implemented");
	}

	@Test
	void testSetXCoor() {
		POI m = new POI("MC220", "2ndfloor", 200, 300, "classroom", "MC_2.png");
		int expResult=400; 
		m.setXCoor(400);
		assertEquals(expResult,m.getXCoor());
		//fail("Not yet implemented");
	}

	@Test
	void testSetYCoor() {
		POI m = new POI("MC220", "2ndfloor", 200, 300, "classroom", "MC_2.png");
		int expResult=600; 
		m.setYCoor(600);
		assertEquals(expResult,m.getYCoor());
		//fail("Not yet implemented");
	}

	@Test
	void testGetName() {
		POI m = new POI("MC220", "2ndfloor", 200, 300, "classroom", "MC_2.png");
		String expResult="MC220"; 
		String result=m.getName();
		assertEquals(expResult,result);
		//fail("Not yet implemented");
	}

}
