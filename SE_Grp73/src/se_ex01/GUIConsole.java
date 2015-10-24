package se_ex01;

import java.util.Scanner;

public class GUIConsole {
	DotsNBoxesEngine engine = new DotsNBoxesEngine();
	Player player = new Player(null, 0);
	Scanner s = new Scanner(System.in);
	DotsNBoxesEngine gameEngine;
	Player winner;
	String wall;
	String[][] map;
	int width;
	int height;

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
		while (counter <= engine.numberOfPlayers) {
			System.out.print("Player " + counter + " please enter your Name: ");
			String name = s.next();
			player.playerList.put(counter, new Player(name, 0));
			
			counter++;
		}
		return true;
	}
	
	/**
	 * The player gives an integer input to remove a wall
	 * 
	 * @return The wall number
	 */
	public int move(Player player) {
//		numberOfMoves++;
//		System.out.print(player.playerList.get(calculatePlayerID(playerID)) + ": Please enter a number of a wall: ");
		int input = s.nextInt();

		return input;
	}
	

	/**
	 * Prompt to enter the number of players for this gaming round
	 * 
	 * @return True if the number of players are entered
	 */
	public boolean enterNumberOfPlayers() {
		System.out.print("Please enter a number of player: ");
		String input = s.next();
		engine.numberOfPlayers = Integer.parseInt(input);
		return true;
	}

	/**
	 * 
	 * @return true if the player entered
	 */
	public boolean mapDimension() {
		width = 1;
		height = 1;

		System.out.print("Please enter the width, uneven integer greater than 1: ");
		width = s.nextInt();
		System.out.print("Please enter the height, uneven integer greater than 1: ");
		height = s.nextInt();

		while (!engine.checkFieldDimension(width, height)) {
			System.out.print("Please enter the width, uneven integer greater than 1: ");
			width = s.nextInt();
			System.out.print("Please enter the height, uneven integer greater than 1: ");
			height = s.nextInt();
		}
<<<<<<< HEAD
		map = new String[height][width];
=======
		map = new String[height][width];
		initializeMap();
>>>>>>> branch 'master' of https://github.com/Len95/SoftwareEngineering73
		return true;
	}

	
	/**
	 * Initializes an empty two dimensional string array
	 * 
	 * @return The map with the correct entries
	 */

	// TODO: Kommentare schreiben
	public String[][] initializeMap() {
		int enumerate = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i % 2 == 0 && j % 2 == 0) {
					map[i][j] = "*";
				} else if (i % 2 == 0 && j % 2 != 0) {
					map[i][j] = String.valueOf(enumerate);
					enumerate++;
				} else if (i % 2 != 0 && j % 2 == 0) {
					map[i][j] = String.valueOf(enumerate);
					enumerate++;
				} else if (i % 2 != 0 && j % 2 != 0) {
					map[i][j] = " ";
				} else
					System.err.println("GUIConsole - Method: initializeMap()");
			}
		}
		updateMap(map);
		return map;
	}

	/**
	 * Update after the map was modified and print on console
	 * 
	 * @param newMap
	 *            The modified map from DotsNBoxesEngine if a move was
	 *            successful
	 */

	// TODO: Kommentare schreiben + else if verschönern
	public void updateMap(String[][] newMap) {
		for (int height = 0; height < this.height; height++) {
			System.out.println();
			for (int width = 0; width < this.width; width++) {
				if (amountOfDigits(map[height][width]) == 1) {
					if (width < this.width - 1) {
						System.out.print("  " + map[height][width] + "  ");
					} else
						System.out.println("  " + map[height][width] + "  ");

				} else if (amountOfDigits(map[height][width]) == 2) {
					if (width < this.width - 1) {
						System.out.print(" " + map[height][width] + "  ");
					} else
						System.out.println(" " + map[height][width] + "  ");

				} else if (amountOfDigits(map[height][width]) == 3) {
					if (width < this.width - 1) {
						System.out.print(" " + map[height][width] + " ");
					} else
						System.out.println(" " + map[height][width] + " ");
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
	private int amountOfDigits(String string) {
		int digits = 0;
		for (int i = 0; i < string.length(); i++) {
			digits++;
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
	}

}
