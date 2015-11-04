package se_ex01_Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import se_ex01.DotsNBoxesEngine;
import se_ex01.Map;
import se_ex01.MapElement;
import se_ex01.Player;
import se_ex01.PlayerList;

public class Map_Test {
	public Map map;
	public DotsNBoxesEngine engine;
	public PlayerList playerlist;
	public Player p1, p2;
	
	@Before
	public void init() {
		engine = new DotsNBoxesEngine(2, 2);
		playerlist = engine.playerlist;
		p1 = new Player("Steffen", 0);
		p2 = new Player("Gott", 0);
		playerlist.addPlayer(p1);
		playerlist.addPlayer(p2);
		
		map = engine.getMap();
	}

	@Test
	public void testMap() {
		assertEquals(12, map.lastChooseableWall);
	}
	
	@Test
	public void xyToWallNumberTest() {
		int expected = 1;
		int actual = map.xyToWallNumber(1, 0);
		System.out.println("Actual: " + actual);
		assertEquals(expected, actual);
		assertEquals(2, map.xyToWallNumber(3, 0));
		
		assertEquals(3, map.xyToWallNumber(0, 1));
		assertEquals(4, map.xyToWallNumber(2, 1));
		assertEquals(5, map.xyToWallNumber(4, 1));
		
		assertEquals(6, map.xyToWallNumber(1, 2));
		assertEquals(7, map.xyToWallNumber(3, 2));
		
		assertEquals(8, map.xyToWallNumber(0, 3));
		assertEquals(9, map.xyToWallNumber(2, 3));
		assertEquals(10, map.xyToWallNumber(4, 3));
		
		assertEquals(11, map.xyToWallNumber(1, 4));
		assertEquals(12, map.xyToWallNumber(3, 4));
		
	}

	@Test
	public void testGetMapAsIntArray() {
		int[][] mapArray = map.getMapAsIntArray();
		
		assertEquals(5, mapArray.length);
		
		assertEquals(MapElement.POINT.getValue(), mapArray[0][0]);
		assertEquals(MapElement.OPEN_WALL_HORIZONTAL.getValue(), mapArray[1][0]);
		assertEquals(MapElement.POINT.getValue(), mapArray[2][0]);
		assertEquals(MapElement.OPEN_WALL_HORIZONTAL.getValue(), mapArray[3][0]);
		assertEquals(MapElement.POINT.getValue(), mapArray[4][0]);
		
		assertEquals(MapElement.OPEN_WALL_VERITICAL.getValue(), mapArray[0][1]);
		assertEquals(MapElement.FIELD.getValue(), mapArray[1][1]);
		assertEquals(MapElement.OPEN_WALL_VERITICAL.getValue(), mapArray[2][1]);
		assertEquals(MapElement.FIELD.getValue(), mapArray[3][1]);
		assertEquals(MapElement.OPEN_WALL_VERITICAL.getValue(), mapArray[4][1]);
		
		assertEquals(MapElement.POINT.getValue(), mapArray[0][2]);
		assertEquals(MapElement.OPEN_WALL_HORIZONTAL.getValue(), mapArray[1][2]);
		assertEquals(MapElement.POINT.getValue(), mapArray[2][2]);
		assertEquals(MapElement.OPEN_WALL_HORIZONTAL.getValue(), mapArray[3][2]);
		assertEquals(MapElement.POINT.getValue(), mapArray[4][2]);
		
		assertEquals(MapElement.OPEN_WALL_VERITICAL.getValue(), mapArray[0][3]);
		assertEquals(MapElement.FIELD.getValue(), mapArray[1][3]);
		assertEquals(MapElement.OPEN_WALL_VERITICAL.getValue(), mapArray[2][3]);
		assertEquals(MapElement.FIELD.getValue(), mapArray[3][3]);
		assertEquals(MapElement.OPEN_WALL_VERITICAL.getValue(), mapArray[4][3]);
		
		assertEquals(MapElement.POINT.getValue(), mapArray[0][4]);
		assertEquals(MapElement.OPEN_WALL_HORIZONTAL.getValue(), mapArray[1][4]);
		assertEquals(MapElement.POINT.getValue(), mapArray[2][4]);
		assertEquals(MapElement.OPEN_WALL_HORIZONTAL.getValue(), mapArray[3][4]);
		assertEquals(MapElement.POINT.getValue(), mapArray[4][4]);
		
	}

	@Test
	public void testIsWallOpenInt() {
		assertTrue(map.isWallOpen(1));
		assertTrue(map.takeWall(p1, 1));
		assertFalse(map.isWallOpen(1));
		
		assertTrue(map.isWallOpen(3));
		assertTrue(map.takeWall(p1, 3));
		assertFalse(map.isWallOpen(3));

		assertTrue(map.isWallOpen(4));
		assertTrue(map.takeWall(p1, 4));
		assertFalse(map.isWallOpen(4));

		assertTrue(map.isWallOpen(6));
		assertTrue(map.takeWall(p1, 6));
		assertFalse(map.isWallOpen(6));
		
		assertEquals(1, p1.score);
	}

	@Test
	public void testGetOpenWallnumbers() {
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			expected.add(i);
		}
		assertEquals(expected, map.getOpenWallnumbers());
		
		assertTrue(map.takeWall(p1, 1));
		expected = new ArrayList<Integer>();
		for (int i = 2; i <= 12; i++) {
			expected.add(i);
		}
		assertEquals(expected, map.getOpenWallnumbers());
		
	}

	@Test
	public void testToString() {
	}
	
	@Test
	public void isAWallOpenTest() {
		assertTrue(map.isAWallOpen());
		for (int i = 1; i <= 12; i++) {
			assertTrue(map.takeWall(p1, i));
		}
		assertFalse(map.isAWallOpen());
	}

}
