package se_ex01;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GUIConsole {
	DotsNBoxesEngine engine = new DotsNBoxesEngine();
	String wall;
	String[][] map;
	int width;
	int height;
	Scanner sc;

	public GUIConsole() {
		// leave as default?!
		sc = new Scanner(System.in);
	}

	/**
	 * Prompt to enter the name of the player who is playing
	 * 
	 * @return True if the name is entered
	 */
	public void enterPlayerName() {
		Integer counter = 1;
		while (counter <= engine.numberOfPlayers) {
			String name = getString("Player " + counter + " please enter your Name");
			engine.storePlayerName(counter, name);
			counter++;
		}
		enterMapDimension();
	}

	/**
	 * The player gives an integer input to remove a wall
	 * 
	 * @return The wall number
	 */
	public void move() {
		Player currentPlayer = engine.getCurrentPlayer();
		Integer id = engine.getCurrentPlayerID();

		int input = getNumber("Player " + "(" + id + "): " + currentPlayer.getName() + " please enter a wall number");

		int[] coords = engine.getCoordinatesOfNumberInMap(input, map, width, height);
		int xCoord = coords[1];
		int yCoord = coords[0];

		if (engine.replaceNumber(currentPlayer, input, width, height, map, yCoord, xCoord)) {
			if (engine.completedBox(map, width, height)) {
				int[] coordsComplete = engine.getCoordinatesOfCompletedBox(map, width, height);
				int xComplete = coordsComplete[1];
				int yComplete = coordsComplete[0];
				engine.updateBoxWithName(map, currentPlayer, yComplete, xComplete, width, height);

				// check for another completed Box..since one move can complete
				// 2 boxes at the same time and update the map again

				if (engine.completedBox(map, width, height)) {
					int[] coordsComplete2 = engine.getCoordinatesOfCompletedBox(map, width, height);
					int xComplete2 = coordsComplete2[1];
					int yComplete2 = coordsComplete2[0];
					engine.updateBoxWithName(map, currentPlayer, yComplete2, xComplete2, width, height);
				}

				displayMap(map);
			} else
				engine.increasePlayerIdByOne();
			displayMap(map);
		} else
			move();
	}

	/**
	 * Prompt to enter the number of players for this gaming round
	 * 
	 * @return True if the number of players are entered
	 */
	// TODO: Inputs in der Engine checken und erst dann dem Attribut
	// numberOfPlayers übergeben
	public void enterNumberOfPlayers() {
		int input = -1;

		while (input < 2) {
			input = getNumber("Please enter a number (>= 2) of player");
		}
		engine.numberOfPlayers = input;
		enterPlayerName();
	}

	/**
	 * 
	 * @return true if the player entered
	 */
	// TODO Inputs in der Engine checken
	public void enterMapDimension() {
		width = -1;
		height = -1;
		int ArrayWidth, ArrayHeight;

		while (!engine.checkFieldDimension(width, height)) {
			ArrayWidth = getNumber("Please enter the width ");
			ArrayHeight = getNumber("Please enter the height ");
			width = engine.calculateArrayWidth(ArrayWidth);
			height = engine.calculateArrayHeight(ArrayHeight);
		}
		
		map = new String[height][width];
		initializeMap();
	}

	/**
	 * Initializes an empty two dimensional string array
	 * 
	 * @return The map with the correct entries
	 */

	// TODO: Kommentare schreiben
	public String[][] initializeMap() {
		int enumerate = 1;
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
		displayMap(map);
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
	public void displayMap(String[][] newMap) {

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
		if (engine.gameEnded(width, height)) {
			endOfGame();
		} else
			move();
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
	 * 
	 * Prompts the user with message to enter a integer
	 * 
	 * Stolen from
	 * http://codereview.stackexchange.com/questions/58800/making-sure-user-
	 * inputs-correct-type
	 * 
	 * @param prompt
	 *            The message the user is prompted with
	 * @return The number, the user entered
	 */
	public int getNumber(String prompt) {
		while (true) {
			String input = getString(prompt);
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException ne) {
				System.out.println("\tPlease enter a positive whole number.");
			}
		}
	}

	/**
	 * 
	 * Prompts the user with message to enter a string
	 * 
	 * Stolen from
	 * http://codereview.stackexchange.com/questions/58800/making-sure-user-
	 * inputs-correct-type
	 * 
	 * @param prompt
	 *            The message the user is prompted with
	 * @return The string, the user entered
	 */
	public String getString(String prompt) {

		String input = "";
		while (true) {

			System.out.print("\t" + prompt + ": ");

			if (sc.hasNextLine()) {
				input = sc.nextLine();
			}
			
			if (input != null && !input.isEmpty()) {
				return input;
			}
		}

	}

	/**
	 * Prints the game statistics onto the console, the winner's name and the
	 * winner's score
	 */
	public void endOfGame() {
		System.out.println("GAME ENDED ------------------------------- GAME ENDED");
		LinkedList<Player> winners = engine.returnWinnerList();
		
		for (Player p : winners) {
			System.out.println("Winner is " + p.getName());
		}

	}

}
