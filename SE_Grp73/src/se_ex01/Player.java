package se_ex01;

import java.util.HashMap;

public class Player {
	String name;
	int score;
//	HashMap<Integer, Player> playerList = new HashMap<Integer, Player>();
	
	/**
	 * Player who plays the game
	 * @param name the name of the player 
	 * @param score the score of the player during the game, initialize with 0
	 */
	public Player(String name, int score) {
		this.score = score;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void increaseScoreBy(int pointsToIncreaseBy){
		this.score += pointsToIncreaseBy;
		
	}
	
}
