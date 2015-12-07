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

	public void closeWalls(int[] wallNumbers, Player current) {
		int x, y;
		int[] coords;
		for (int i = 0; i < wallNumbers.length; i++) {
			coords = engine.getCoordinatesOfNumberInMap(wallNumbers[i], engine.calculateArrayWidth(3),
					engine.calculateArrayHeight(3));
			y = coords[1];
			x = coords[0];
			engine.replaceNumber(current, wallNumbers[i], x, y);
		}
	}

	@Before
	public void initMap() {
		engine.setHeight(engine.calculateArrayHeight(3));
		engine.setWidth(engine.calculateArrayWidth(3));
		engine.initializeMap();
	}

	@Test
	public void closeFirstWall() {
		assertEquals(24, ai.getNextMove());
	}

	@Test
	public void generate1PointTest() {
		int[] walls = { 1, 4, 5 };
		closeWalls(walls, player);
		assertEquals(8, ai.getNextMove());
	}

	@Test
	public void generate2PpointTest() {
		int[] walls = { 1, 4, 5, 11, 12, 15 };
		closeWalls(walls, player);
		assertEquals(8, ai.getNextMove());
	}

	@Test
	public void choose1or2Points() {
		int[] walls = { 1, 4, 5, 11, 12, 15, 9, 16 };
		closeWalls(walls, player);
		assertEquals(8, ai.getNextMove());
	}

	@Test
	public void closeLastWall() {
		int[] walls = new int[21];
		int c = 1;
		for (int i = 0; i < 21; i++) {
			walls[i] = c;
			c++;
		}
		closeWalls(walls, player);
		assertEquals(24, ai.getNextMove());
	}

	@Test
	public void closeLastRandomWall() {
		int[] walls = new int[24];
		int c = 1;
		// 1. Store all wall numbers
		for (int i = 0; i < walls.length; i++) {
			walls[i] = c;
			c++;
		}
		// 2. Remove random wall number
		double r = Math.random();
		r *= 100;
		while (r > 21) {
			r = Math.random();
			r *= 100;
		}
		int k = (int) r;
		walls[k] = -1;
		closeWalls(walls, player);
		assertEquals(k + 1, ai.getNextMove());
	}

	@Test
	public void checkRightBorder() {
		int[] walls = new int[24];
		int c = 1;
		// 1. Store all wall numbers
		for (int i = 0; i < walls.length; i++) {
			walls[i] = c;
			c++;
		}
		// 2. Open right wall
		walls[13] = -1;
		closeWalls(walls, player);
		assertEquals(14, ai.getNextMove());
	}

	@Test
	public void checkLeftBorder() {
		int[] walls = new int[24];
		int c = 1;
		// 1. Store all wall numbers
		for (int i = 0; i < walls.length; i++) {
			walls[i] = c;
			c++;
		}
		// 2. Open right wall
		walls[10] = -1;
		closeWalls(walls, player);
		assertEquals(11, ai.getNextMove());
	}

	@Test
	public void checkTopRightBorder() {
		int[] walls = new int[24];
		int c = 4;
		// 1. Store all wall numbers
		for (int i = 0; i < walls.length; i++) {
			walls[i] = c;
			c++;
		}
		closeWalls(walls, player);
		assertEquals(3, ai.getNextMove());
	}

	@Test
	public void checkBottomLeftBorder() {
		int[] walls = new int[24];
		int c = 1;
		// 1. Store all wall numbers
		for (int i = 0; i < walls.length; i++) {
			walls[i] = c;
			c++;
		}
		// 2. Open bottom left wall
		walls[21] = -1;

		closeWalls(walls, player);
		assertEquals(22, ai.getNextMove());
	}

	@Test
	public void closeFieldInTheMiddle() {
		int[] walls = new int[24];
		int c = 1;
		// 1. Store all wall numbers
		for (int i = 0; i < walls.length; i++) {
			walls[i] = c;
			c++;
		}
		// 2. Open bottom left wall
		walls[21] = -1;

		closeWalls(walls, player);
		assertEquals(22, ai.getNextMove());
	}

	@Test
	public void allFieldsClosed() {
		int[] walls = new int[24];
		int c = 1;
		// 1. Store all wall numbers
		for (int i = 0; i < walls.length; i++) {
			walls[i] = c;
			c++;
		}
		closeWalls(walls, player);
		assertEquals(-1, ai.getNextMove());
	}

	@Test
	public void foo() {
		int[] walls = new int[24];
		int c = 1;
		// 1. Store all wall numbers
		for (int i = 0; i < walls.length; i++) {
			walls[i] = c;
			c++;
		}
		walls[10] = -1;
		closeWalls(walls, player);
		assertEquals(11, ai.getNextMove());
	}

}
