package old;

public class Player {
	public String name;
	public int score;
	public int ID;
	public boolean isAI = false;
	public AI supportingAI;

	/**
	 * Player who plays the game
	 * 
	 * @param name
	 *            the name of the player
	 * @param score
	 *            the score of the player during the game, initialize with 0
	 */
	public Player(String name, int score) {
		this.score = score;
		this.name = name;
	}

	/**
	 * returns the players' name
	 * 
	 * @return: returns a string with the name of the player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * sets the name of the player
	 * 
	 * @param name:
	 *            the name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * returns the score of the player
	 * 
	 * @return an int with the value of the score of the player
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * sets the score of the player
	 * 
	 * @param score:
	 *            the score of the player
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Increases the score of a player by any integer value that is passed
	 * 
	 * @param pointsToIncreaseBy
	 *            The points that the player gained by his strategically move
	 */
	public void increaseScoreBy(int pointsToIncreaseBy) {
		this.score += pointsToIncreaseBy;
	}

	/**
	 * returns the name and score of the player
	 * 
	 * return: a string with the name and an int with the value of the score of the player
	 */
	public String toString() {
		return "Name: " + this.name + "\tScore: " + this.score + "\n";
	}

}
