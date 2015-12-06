package old_JUnitTestSuites;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import old.AIMinMaxAlgo;
import old.DotsNBoxesEngine;
import old.Player;



public class AIMinMaxTests {

	DotsNBoxesEngine engine = new DotsNBoxesEngine();
	AIMinMaxAlgo ai = new AIMinMaxAlgo("Test Ai", 0, this.engine);
	Player player = new Player("Human Player", 0);

	@Before
	public void initMap() {
		engine.setHeight(engine.calculateArrayHeight(3));
		engine.setWidth(engine.calculateArrayWidth(3));
		engine.initializeMap();
	}

	@Test
	public void closeWallTest() {
//		int[] coords = engine.getCoordinatesOfNumberInMap(24, this.engine.getWidth(), this.engine.getHeight());
//		int x = coords[1];
//		int y = coords[0];
		assertEquals(24, ai.getNextMove());

	}

}
