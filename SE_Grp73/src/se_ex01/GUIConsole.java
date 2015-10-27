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
		sc = new Scanner(System.in);
	}

	/**
	 * Prompt for entering a player name, after all names are entered the map
	 * dimension needs to be determined
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
	 * Prompt for entering a wall number to make a move
	 */
	public void move() {
		Player currentPlayer = engine.getCurrentPlayer();
		Integer id = engine.getCurrentPlayerID();

		engine.getGameStats();
		int input = getNumber("Player " + "(" + id + "): " + currentPlayer.getName() + " please enter a wall number");

		// The DotsNBoxesEngine calculates the Coords of the Arrayfield with the
		// input
		int[] coords = engine.getCoordinatesOfNumberInMap(input, map, width, height);
		int xCoord = coords[1];
		int yCoord = coords[0];

		// If the input is correct replace the field with the correct sign and
		// check if a box is complete
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
			} else {
				engine.increasePlayerIdByOne();
				displayMap(map);
			}
		} else
			// Else, enter a correct wall number
			move();
	}

	/**
	 * Prompt to enter the number of players for this gaming round
	 */
	public void enterNumberOfPlayers() {
		int input = -1;

		while (input < 2) {
			input = getNumber("Please enter a number (>= 2) of player");
		}
		engine.numberOfPlayers = input;
		enterPlayerName();
	}

	/**
	 * Prompt for entering a map dimension
	 */
	public void enterMapDimension() {
		width = -1;
		height = -1;
		int ArrayWidth, ArrayHeight;

		while (!engine.checkFieldDimension(width, height)) {
			System.out.println("\twidth * height <= 450");
			ArrayWidth = getNumber("Please enter the width ");
			ArrayHeight = getNumber("Please enter the height ");
			width = engine.calculateArrayWidth(ArrayWidth);
			height = engine.calculateArrayHeight(ArrayHeight);
		}
		map = new String[height][width];
		initializeMap();
	}

	/**
	 * Initializes an empty two dimensional string array with correct wall
	 * numbers, and *
	 * 
	 * @return The correct initialized map
	 */
	public String[][] initializeMap() {
		// enumerator for the wall numbers
		int enumerate = 1;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i % 2 == 0 && j % 2 == 0) { // even line and even column ->
												// *
					map[i][j] = "*";
				} else if (i % 2 == 0 && j % 2 != 0) { // even line and uneven
														// column -> wall
					map[i][j] = String.valueOf(enumerate);
					enumerate++;
				} else if (i % 2 != 0 && j % 2 == 0) { // uneven line and even
														// column -> wall
					map[i][j] = String.valueOf(enumerate);
					enumerate++;
				} else if (i % 2 != 0 && j % 2 != 0) { // uneven line and uneven
														// column -> space for
														// the player who closed
														// this field
					map[i][j] = " ";
				} else
					System.err.println("GUIConsole - Method: initializeMap()");
			}
		}
		displayMap(map);
		return map;
	}

	/**
	 * Prints the map (2D - Array) on the console
	 * 
	 * @param newMap
	 *            the new map that should be updated
	 */
	public void displayMap(String[][] newMap) {

		for (int height = 0; height < this.height; height++) {
			System.out.println();
			for (int width = 0; width < this.width; width++) {
				if (amountOfDigits(map[height][width]) == 1) { // format for
																// one-digit
																// number
					if (width < this.width - 1) { // one line
						System.out.print("  " + map[height][width] + "  ");
					} else // begin the next line
						System.out.println("  " + map[height][width] + "  ");

				} else if (amountOfDigits(map[height][width]) == 2) { // format
																		// for
																		// two-digit
																		// number
					if (width < this.width - 1) {
						System.out.print(" " + map[height][width] + "  ");
					} else
						System.out.println(" " + map[height][width] + "  ");

				} else if (amountOfDigits(map[height][width]) == 3) { // format
																		// for
																		// three-digit
																		// number
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
	 * Checks the amount of digits in any string
	 * 
	 * @param string
	 *            The string that should be checked
	 * @return the amount of digits in a string
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
	 * Prints the game statistics onto the console and the winner or winners
	 * with their score
	 */
	public void endOfGame() {
		System.out.println("GAME ENDED ------------------------------- GAME ENDED");
		LinkedList<Player> winners = engine.returnWinnerList();
		engine.getGameStats();

		if (engine.draw()) {
			engine.printDrawMessage();
		}

		else {
			for (Player p : winners) {
				System.out.println("\n" + "The Winner is " + p.getName() + " - Your Score: " + p.getScore() + "\n"
						+ "CONGRATUUUUUU....WAIT FOR IT.....UULATIONS!!!🎉🎉🎉");
			}
		}
	}

}
