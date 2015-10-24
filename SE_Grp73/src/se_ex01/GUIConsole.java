package se_ex01;

import java.util.Scanner;

public class GUIConsole {
	Player player = new Player(0);
	Scanner s = new Scanner(System.in);
	DotsNBoxesEngine gameEngine;
	Player winner;
	int numberOfMoves = 0;
	String wall;
	String[][] map;
	int width;
	int height;
	int numberOfPlayers;

	public GUIConsole() {
		// leave as default?!
	}

	/**
	 * Prompt to enter the name of the player who is playing
	 * 
	 * @return True if the name is entered
	 */
	public boolean enterPlayerName() {
		int counter = 1;
		while (counter <= numberOfPlayers) {
			System.out.print("Plyer " + counter + " please enter your Name: ");
			String name = s.next();
			player.setName(name);
			counter++;
		}
		return true;
	}

	/**
	 * Prompt to enter the number of players for this gaming round
	 * 
	 * @return True if the number of players are entered
	 */
	public boolean enterNumberOfPlayers() {
		System.out.print("Please enter a number of player: " );
		String input = s.next();
		numberOfPlayers = Integer.parseInt(input);
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public boolean mapDimension() {
		width = 1;
		height = 1;

		while (!(width > 2 && width % 2 != 0 && height > 2 && height % 2 != 0)) {
			System.out.print("Please enter the width, uneven integer greater than 1: ");
			width = s.nextInt();
			System.out.print("Please enter the height, uneven integer greater than 1: ");
			height = s.nextInt();
		}
		map = new String[height][width];
		return true;
	}

	/**
	 * The player gives an integer input to remove a wall
	 * 
	 * @return The wall number
	 */
	public int move(Player player) {
		System.out.print(player.getName() + ": Please enter a number of a wall: ");
		int input = s.nextInt();

		return input;
	}

	/**
	 * Initializes an empty two dimensional string array
	 * 
	 * @return The map with the correct entries
	 */
	public String[][] initializeMap() {
		int enumerate = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i % 2 == 0 && j % 2 == 0) {
					map[i][j] = "*";
				} else {
					if (i % 2 == 0 && j % 2 != 0) {
						map[i][j] = String.valueOf(enumerate);
						enumerate++;
					} else {
						if (i % 2 != 0 && j % 2 == 0) {
							map[i][j] = String.valueOf(enumerate);
							enumerate++;
						} else {
							if (i % 2 != 0 && j % 2 != 0) {
								map[i][j] = " ";
							} else {
								System.err.println("GUIConsole - Method: initializeMap()");
								System.err.println("This two messages shouldn't appear!!");
							}
						}
					}
				}
			}
		}
		return map;
	}

	/**
	 * Update after the map was modified and print on console
	 * 
	 * @param newMap
	 *            The modified map from DotsNBoxesEngine if a move was
	 *            successful
	 */
	public void updateMap(String[][] newMap) {
		for (int height = 0; height < this.height; height++) {
			System.out.println();
			for (int width = 0; width < this.width; width++) {
				if (amountOfIntegerDigits(map[height][width]) == -1 || amountOfIntegerDigits(map[height][width]) == 1) {
					if (width < this.width - 1) {
						System.out.print("  " + map[height][width] + "  ");
					} else {
						System.out.println("  " + map[height][width] + "  ");
					}
				} else {
					if (amountOfIntegerDigits(map[height][width]) == 2) {
						if (width < this.width - 1) {
							System.out.print(" " + map[height][width] + "  ");
						} else {
							System.out.println(" " + map[height][width] + "  ");
						} 
					} else {
						if (amountOfIntegerDigits(map[height][width]) == 3) {
							if (width < this.width - 1) {
								System.out.print(" " + map[height][width] + " ");
							} else {
								System.out.println(" " + map[height][width] + " ");
							}
						}
					}
				} 
			}
		}
	}

	/**
	 * Checks if an array field contains an integer and returns the amount of
	 * digits
	 * 
	 * @param string
	 *            The array field to check if it contains an integer and get the
	 *            amount of digits
	 * @return -1 if it is not an integer otherwise it returns the amount of
	 *         digits
	 */
	private int amountOfIntegerDigits(String string) {
		int digits = 0;
		if (string.equals("*") || string.equals("|") || string.equals("-") || string.equals(" ")) {
			return -1;
		} else {
			for (int i = 0; i < string.length(); i++) {
				digits++;
			}
		}
		return digits;
	}

	/**
	 * Prints the game statistics onto the console, the winner's name and the
	 * winner's score
	 */
	public void endOfGame() {
		System.out.println("The WINNER is: " + winner.getName());
		System.out.println("The score of the WINNER is: " + winner.getScore());
		System.out.println("The total amount of player rounds " + this.numberOfMoves);
	}

}
