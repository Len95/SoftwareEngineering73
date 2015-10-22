package se_ex01;

public class Player {
	String name;
	int score;
	
	/**
	 * Player who plays the game
	 * @param name the name of the player 
	 * @param score the score of the player during the game, initialize with 0
	 */
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	/*
	 * Brauchen wir eine initScore(int score) Methode o.ä. um die Punkte um Wert x zu erhöhen 
	 * wie es im solution_73 PDF für die Klasse Player festgehalten wurde? 
	 * 
	 * Meiner Meinung nach nicht, da setScore vollkommen ausreichend ist
	 */
	
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
	
}
