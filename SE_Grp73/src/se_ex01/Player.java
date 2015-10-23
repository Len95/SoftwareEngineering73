package se_ex01;

public class Player {
	String name;
	int score;
	
	/**
	 * Player who plays the game
	 * @param name the name of the player 
	 * @param score the score of the player during the game, initialize with 0
	 */
	public Player(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void increasePointsBy(int pointsToIncreaseBy){
		this.score += pointsToIncreaseBy;
		
	}
	
}
