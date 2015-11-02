package se_ex01_Tests;

import se_ex01.Player;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import se_ex01.PlayerList;

public class Playerlist_Test {
	private PlayerList plist;
	private Player p1, p2;
	
	@Before
	public void init() {
		plist = new PlayerList();
		p1 = new Player("p1", 0);
		p2 = new Player("p2", 100);
	}
	
	@Test
	public void HighscoreTest() {
		plist.addPlayer(p1);
		plist.addPlayer(p2);
		ArrayList<Player> apl = plist.getHighscore();
		assertEquals(2, apl.size());
		assertEquals(p2, apl.get(0));
		assertEquals(p1, apl.get(1));
	}
	
	@Test
	public void FIFOQueueTest() {
		plist.addPlayer(p1);
		plist.addPlayer(p2);
		assertEquals(p1, plist.getCurrentPlayer());
		assertTrue(plist.nextPlayer());
		assertEquals(p2, plist.getCurrentPlayer());
		assertTrue(plist.nextPlayer());
		assertEquals(p1, plist.getCurrentPlayer());
	}
	
	@Test
	public void addPlayerTest() {
		assertEquals(0, plist.size());
		plist.addPlayer(p1);
		assertEquals(1, plist.size());
		plist.addPlayer(p2);
		assertEquals(2, plist.size());
	}


}
