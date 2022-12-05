import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

class MapTest {

	@Test
	void testMap() {
		
		//fail("Not yet implemented");
	}

	@Test
	void testMapStringInt() { 
		//fail("Not yet implemented");
	}

	@Test
	void testNextFloor() {
		String mapFile = new File("resources/maps/MC_2.png").toString();
        ///ImageIcon mapImage = new ImageIcon(mapFile.getPath());
        
        Map m = new Map();
        Map icon = m.nextFloor();

      
        ImageIcon icon1= icon.getImageIcon();
        String result =icon1.toString();
        

        
        assertEquals(mapFile,result);
		
		//fail("Not yet implemented");
	}
	
	@Test
	void testNextFloorNotNull() {
		
        Map m = new Map();
        Map icon = m.nextFloor();

        assertNotNull(icon);
		
	}

	@Test
	void testPrevFloor() {
		String mapFile = new File("resources/maps/MC_1.png").toString();
        ///ImageIcon mapImage = new ImageIcon(mapFile.getPath());
        
        Map m = new Map();
        Map icon = m.prevFloor();
        
        System.out.println("File is " + icon);

      
        ImageIcon icon1= icon.getImageIcon();
        String result =icon1.toString();
        
        System.out.println("File is " + icon1);
        System.out.println("File is " + mapFile);

        
        assertEquals(mapFile,result);
		
		//fail("Not yet implemented");
	}
	
	@Test
	void testPrevFloorNotNull() {
		
        Map m = new Map();
        Map icon = m.nextFloor();

        assertNotNull(icon);
		
	}


	@Test
	void testGetFloor() {
		System.out.println("testGetBuilding");
		Map m = new Map();
		int expResult=1;
		int result=m.getFloor();
		assertEquals(expResult,result);
//		fail("Not yet implemented");
	}

	@Test
	void testGetBuilding() {
		System.out.println("testGetBuilding");
		Map m = new Map();
		String expResult="MC";
		String result=m.getBuilding();
		assertEquals(expResult,result);
				
//		fail("Not yet implemented");
	}

	@Test
	void testGetImageIcon() {
		String mapFile = new File("resources/maps/MC_1.png").toString();
        ///ImageIcon mapImage = new ImageIcon(mapFile.getPath());
        
        Map m = new Map();
        ImageIcon icon = m.getImageIcon();
        
        String file = icon.toString();
        
                
        assertEquals(mapFile,file);
        
	
//		fail("Not yet implemented");
	}

	@Test
	void testResize() {
		//fail("Not yet implemented");
	}

}
