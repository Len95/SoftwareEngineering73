package se_ex01;

import java.util.LinkedList;
import java.util.Scanner;

public class GUIConsole {

	Menu menu;
	ControlUserInputs police = new ControlUserInputs();
	DotsNBoxesEngine engine;

	String wall;
	int width;
	int height;
	Scanner sc;

	public GUIConsole() {
		sc = new Scanner(System.in);
		engine = new DotsNBoxesEngine();
		menu = new Menu(engine);
	}

	public void launch() {
		menu.promptForTheMenueSettings();
		if (engine.mode == PlayingMode.AgainstHumans) {
			enterNumberOfPlayers();
		} else if (engine.mode == PlayingMode.AgainstAIMinMax) {
			enterPlayerName();
		} else if (engine.mode == PlayingMode.AgainstAIRandom) {
			enterPlayerName();
		} else if (engine.mode == PlayingMode.AIMinMaxSupport) {
			System.out.println("AIMinMax support feature not yet implementet");
		} else if (engine.mode == PlayingMode.AIRandomSupport) {
			System.out.println("AIRandom support feature not yet implementet");
		} else {
			System.err.println("GUIConsole line 33 - State of mode in engiene: " + engine.mode);
			System.out.println("  Game runs on default (Abgabe 1)");
			enterNumberOfPlayers();
		}
	}

	/**
	 * Prompt for entering a player name, after all names are entered the map
	 * dimension needs to be determined
	 */
	public void enterPlayerName() {
		Integer counter = 1;

		if (engine.mode == PlayingMode.AgainstAIRandom) {
			String name = police.getString(" Please enter your Name ");
			Player playerOne = new Player(name, 0);
			AIRandom artificialIntelligence = new AIRandom("AIRandom", 0, engine);
			engine.setNumberOfPlayers(2);
			engine.storePlayerName(1, playerOne);
			engine.storePlayerName(2, artificialIntelligence);
			
		} else if (engine.mode == PlayingMode.AgainstAIMinMax) {
			String name = police.getString(" Please enter your Name ");
			Player playerOne = new Player(name, 0);
			AIMinMaxAlgo artificialIntelligence = new AIMinMaxAlgo("AIMinMax", 0, engine);
			engine.setNumberOfPlayers(2);
			engine.storePlayerName(1, playerOne);
			engine.storePlayerName(2, artificialIntelligence);
			
		} else {
			while (counter <= engine.numberOfPlayers) {
				String name = police.getString("Player " + counter + " please enter your Name");
				Player currentP = new Player(name, 0);
				engine.storePlayerName(counter, currentP);
				counter++;
			}
		}
		enterMapDimension();
	}

	/**
	 * Prompt for entering a wall number to make a move
	 */
	public void move() {
		Player currentPlayer = engine.getCurrentPlayer();
		Integer id = engine.getCurrentPlayerID();
		int input = -1;
		engine.getGameStats();
		if (currentPlayer.isAI) {
			AI currentAI = (AI) currentPlayer;
			input = currentAI.getNextMove();
		} else {
			// if !currentPlayer.isAI --> input = currentPlayer.getNextMove;
			input = police.getNumber(
					"Player " + "(" + id + "): " + currentPlayer.getName() + " please enter a wall number",
					"\tPlease enter a positive whole number.");
		}
		// The DotsNBoxesEngine calculates the Coords of the Arrayfield with the
		// input
		int[] coords = engine.getCoordinatesOfNumberInMap(input, width, height);
		int xCoord = coords[1];
		int yCoord = coords[0];

		// If the input is correct replace the field with the correct sign and
		// check if a box is complete
		if (engine.replaceNumber(currentPlayer, input, yCoord, xCoord)) {
			if (engine.completedBox()) {
				int[] coordsComplete = engine.getCoordinatesOfCompletedBox();
				int xComplete = coordsComplete[1];
				int yComplete = coordsComplete[0];
				engine.updateBoxWithName(currentPlayer, yComplete, xComplete);

				// check for another completed Box..since one move can complete
				// 2 boxes at the same time and update the map again

				if (engine.completedBox()) {
					int[] coordsComplete2 = engine.getCoordinatesOfCompletedBox();
					int xComplete2 = coordsComplete2[1];
					int yComplete2 = coordsComplete2[0];
					engine.updateBoxWithName(currentPlayer, yComplete2, xComplete2);
				}

				displayMap();
			} else {
				engine.increasePlayerIdByOne();
				displayMap();
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
			input = police.getNumber("Please enter a number (>= 2) of player",
					"\tPlease enter a positive whole number.");
		}
		engine.setNumberOfPlayers(input);
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
			ArrayWidth = police.getNumber("Please enter the width ", "\tPlease enter a positive whole number.");
			ArrayHeight = police.getNumber("Please enter the height ", "\tPlease enter a positive whole number.");
			width = engine.calculateArrayWidth(ArrayWidth);
			height = engine.calculateArrayHeight(ArrayHeight);
		}
		engine.setHeight(height);
		engine.setWidth(width);
		engine.initializeMap();
		displayMap();
	}

	/**
	 * Prints the map (2D - Array) on the console
	 * 
	 * @param newMap
	 *            the new map that should be updated
	 */
	public void displayMap() {
		String map[][] = engine.getMap();
		for (int height = 0; height < this.height; height++) {
			System.out.println();
			for (int width = 0; width < this.width; width++) {
				if (amountOfDigits(map[height][width]) == 1) { // format
																// _for_one-digit_number
					if (width < this.width - 1) { // one line
						System.out.print("  " + map[height][width] + "  ");
					} else // begin the next line
						System.out.println("  " + map[height][width] + "  ");

				} else if (amountOfDigits(map[height][width]) == 2) { // format_for_two-digit_number
					if (width < this.width - 1) {
						System.out.print(" " + map[height][width] + "  ");
					} else
						System.out.println(" " + map[height][width] + "  ");

				} else if (amountOfDigits(map[height][width]) == 3) { // format_for_three-digit_number
					if (width < this.width - 1) {
						System.out.print(" " + map[height][width] + " ");
					} else
						System.out.println(" " + map[height][width] + " ");
				}
			}
		}
		// width, height
		if (engine.gameEnded()) {
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
						+ "CONGRATUUUUUU....WAIT FOR IT.....UULATIONS!!! :)");
			}
		}
	}

}
