package se_ex01;

import java.util.Scanner;

public class GUIConsole {

	Menu menu;
	ControlInputs police = new ControlInputs();
	DotsNBoxesEngine engine;

	String wall;
	int width;
	int height;
	Scanner sc;

	private Map map;

	public GUIConsole() {
		sc = new Scanner(System.in);
		menu = new Menu();
	}

	private void displayGameStatistics() {
		System.out.println("This is round no. : " + engine.turnsPlayed);
		for (Player p : engine.playerlist.asArraylist()) {
			System.out.println(p);
		}
	}

	public void launch() {
		PlayingMode mode = menu.promptForTheMenueSettings();

		enterMapDimension();

		engine = new DotsNBoxesEngine(width, height);
		engine.mode = mode;
		map = engine.getMap();

		int numberOfPlayers = 0;

		if (mode == PlayingMode.AgainstAIMinMax || mode == PlayingMode.AgainstAIRandom) {
			numberOfPlayers = 1;
		} else if (mode == PlayingMode.AIMinMaxSupport || mode == PlayingMode.AIRandomSupport) {
			numberOfPlayers = 2;
		} else {
			numberOfPlayers = enterNumberOfPlayers();
		}
		enterPlayerName(numberOfPlayers);

		System.out.println("Nun while Schleife");

		while (engine.getMap().isAWallOpen()) {

			displayMap();
			System.out.println("Nun move");
			move();
		}
		System.out.println("Ende while");
		endOfGame();
	}

	/**
	 * Prompt for entering a player name, after all names are entered the map
	 * dimension needs to be determined
	 */
	public void enterPlayerName(int numberOfPlayers) {
		Integer counter = 0;

		if (engine.mode == PlayingMode.AgainstAIRandom) {
			String name = police.getString(" Please enter your name ");
			Player playerOne = new Player(name, 0);
			AIRandom artificialIntelligence = new AIRandom("AIRandom", 0, engine);

			engine.playerlist.addPlayer(playerOne);
			engine.playerlist.addPlayer(artificialIntelligence);

		}

		else if (engine.mode == PlayingMode.AgainstAIMinMax) {
			String name = police.getString(" Please enter your Name ");
			Player playerOne = new Player(name, 0);
			AIMinMaxAlgo artificialIntelligence = new AIMinMaxAlgo("AIMinMax", 0, engine);

			engine.playerlist.addPlayer(playerOne);
			engine.playerlist.addPlayer(artificialIntelligence);

		} else if (engine.mode == PlayingMode.AIMinMaxSupport) {
			String nameOfFirstPlayer = police.getString("Player 1: Please enter your name");
			Player playerOne = new Player(nameOfFirstPlayer, 0);
			engine.playerlist.addPlayer(playerOne);

			String nameOfSecondPlayer = police.getString("Player 2: Please enter your name");
			Player playerTwo = new Player(nameOfSecondPlayer, 0);
			engine.playerlist.addPlayer(playerTwo);

		} else if (engine.mode == PlayingMode.AIRandomSupport) {

			String nameOfFirstPlayer = police.getString("Player 1: Please enter your name");
			Player playerOne = new Player(nameOfFirstPlayer, 0);
			engine.playerlist.addPlayer(playerOne);

			String nameOfSecondPlayer = police.getString("Player 2: Please enter your name");
			Player playerTwo = new Player(nameOfSecondPlayer, 0);
			engine.playerlist.addPlayer(playerTwo);

		} else {
			for (int i = 1; i <= numberOfPlayers; i++) {
				System.out.println("Number of players: " + numberOfPlayers);
				String name = police.getString("Player " + i + " please enter your name");
				Player currentP = new Player(name, 0);
				engine.playerlist.addPlayer(currentP);
			}
		}
	}

	/**
	 * Prompt for entering a wall number to make a move
	 */
	public void move() {
		Player currentPlayer = engine.playerlist.getCurrentPlayer();
		int wallnumber = -1;
		String needHelp = "Empty";

		// engine.getGameStats();

		displayGameStatistics();

		if (currentPlayer.isAI) {
			AI currentAI = (AI) currentPlayer;
			currentAI.getRemainingNumbers();
			wallnumber = currentAI.getNextMove();
		} else {
			wallnumber = police.getNumber("Player: " + currentPlayer.getName() + " please enter a wall number",
					"\tPlease enter a positive whole number.");
			/*
			 * if (engine.playerlist.size() == 2 && engine.mode ==
			 * PlayingMode.AgainstHumans) { while
			 * (!needHelp.toUpperCase().equals("YES") &&
			 * !needHelp.toUpperCase().equals("NO")) { needHelp =
			 * police.getString(
			 * "Would you like to receive help from the AI? (Type 'YES' or 'NO')"
			 * ); if (!needHelp.toUpperCase().equals("YES") &&
			 * !needHelp.toUpperCase().equals("NO")) { System.out.println(
			 * "Please enter a valid command : 'YES' if you would like to receive help and 'NO' if you don't."
			 * ); } } if (needHelp.toUpperCase().equals("YES")) {
			 * 
			 * currentPlayer.supportingAI = new AIMinMaxAlgo(currentPlayer.name,
			 * currentPlayer.getScore(), engine);
			 * 
			 * engine.playerlist.getCurrentPlayer().supportingAI = new
			 * AIMinMaxAlgo( engine.playerlist.getCurrentPlayer().getName(),
			 * engine.playerlist.getCurrentPlayer().getScore(), engine);
			 * 
			 * System.out.println(engine.playerlist.getCurrentPlayer().getName()
			 * + ", the MinMaxAI advises you to play the number " +
			 * engine.playerlist.getCurrentPlayer().supportingAI.getNextMove() +
			 * "\n"); }
			 * 
			 * } else if (engine.mode == PlayingMode.AIRandomSupport) {
			 * 
			 * currentPlayer.supportingAI = new AIRandom(currentPlayer.name,
			 * currentPlayer.getScore(), engine);
			 * 
			 * currentPlayer.supportingAI.getRemainingNumbers();
			 * System.out.println(currentPlayer.getName() +
			 * ", the RandomAI advises you to play the number " +
			 * currentPlayer.supportingAI.getNextMove() + "\n"); } else if
			 * (engine.mode == PlayingMode.AIMinMaxSupport) {
			 * currentPlayer.supportingAI = new AIMinMaxAlgo(currentPlayer.name,
			 * currentPlayer.getScore(), engine);
			 * 
			 * System.out.println(currentPlayer.getName() +
			 * ", the MinMaxAI advises you to play the number " +
			 * currentPlayer.supportingAI.getNextMove() + "\n");
			 * 
			 * } else // if !currentPlayer.isAI --> input =
			 * currentPlayer.getNextMove; wallnumber =
			 * police.getNumber("Player: " + currentPlayer.getName() +
			 * " please enter a wall number",
			 * "\tPlease enter a positive whole number.");
			 */
		}

		map.takeWall(currentPlayer, wallnumber);

		if (!map.justClosedAField) {
			engine.playerlist.nextPlayer();
		}
	}

	/**
	 * Prompt to enter the number of players for this gaming round
	 */
	public int enterNumberOfPlayers() {
		int input = -1;

		while (input < 2) {
			input = police.getNumber("Please enter a number (>= 2) of player",
					"\tPlease enter a positive whole number.");
		}
		return input;
	}

	/**
	 * Prompt for entering a map dimension
	 */
	public void enterMapDimension() {
		width = -1;
		height = -1;

		while (!Map.isMapDimensionOkay(width, height)) {
			System.out.println("\twidth * height <= 450");
			width = police.getNumber("Please enter the width ", "\tPlease enter a positive whole number.");
			height = police.getNumber("Please enter the height ", "\tPlease enter a positive whole number.");
		}
	}

	/**
	 * Prints the map (2D - Array) on the console
	 * 
	 * @param newMap
	 *            the new map that should be updated
	 */
	public void displayMap() {
		int mapArray[][] = map.getMapAsIntArray();

		int[] maxSizes = new int[map.arrayWidth];
		for (int i = 0; i < map.arrayWidth; i++) {
			maxSizes[i] = getMaxDisplayWidthOfColumn(i);
		}
		StringBuilder sb = new StringBuilder();
		String value;
		String number;
		for (int x = 0; x < map.arrayWidth; x++) {
			for (int y = 0; y < map.arrayHeight; y++) {
				int me = mapArray[x][y];

				if (me == MapElement.FIELD.getValue()) {
					// empty field
					sb.append(repeatString(" ", maxSizes[x]));
				} else if (me > 0) {
					number = String.valueOf(me);
					value = fillStringUpToSize(number, maxSizes[x], " ");
					sb.append(value);
				} else if (me == MapElement.FILLED_WALL_HORIZONTAL.getValue()) {
					sb.append(repeatString("=", maxSizes[x]));
				} else if (me == MapElement.FILLED_WALL_VERT.getValue()) {
					sb.append(repeatString("|", maxSizes[x]));
				} else if (me == MapElement.OPEN_WALL_HORIZONTAL.getValue()
						|| me == MapElement.OPEN_WALL_VERITICAL.getValue()) {
					number = String.valueOf(map.xyToWallNumber(x, y));
					value = fillStringUpToSize(number, maxSizes[x], " ");
					sb.append(value);
				} else if (me == MapElement.POINT.getValue()) {
					sb.append(repeatString("*", maxSizes[x]));
				}
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
		/*
		 * for (int height = 0; height < this.height; height++) {
		 * System.out.println(); for (int width = 0; width < this.width;
		 * width++) { if (amountOfDigits((mapArray[height][width])) == 1) { //
		 * format // _for_one-digit_number if (width < this.width - 1) { // one
		 * line System.out.print("  " + mapArray[height][width] + "  "); } else
		 * // begin the next line System.out.println("  " +
		 * mapArray[height][width] + "  ");
		 * 
		 * } else if (amountOfDigits(mapArray[height][width]) == 2) { //
		 * format_for_two-digit_number if (width < this.width - 1) {
		 * System.out.print(" " + mapArray[height][width] + "  "); } else
		 * System.out.println(" " + mapArray[height][width] + "  ");
		 * 
		 * } else if (amountOfDigits(mapArray[height][width]) == 3) { //
		 * format_for_three-digit_number if (width < this.width - 1) {
		 * System.out.print(" " + mapArray[height][width] + " "); } else
		 * System.out.println(" " + mapArray[height][width] + " "); } } }
		 */
	}

	private String fillStringUpToSize(String s, int max, String filler) {
		String result = s;
		while (result.length() < max) {
			result = filler + result;
		}
		return result;
	}

	private int getMaxDisplayWidthOfColumn(int col) {
		String[][] array = map.getMapAsStringArray();

		int max = 0;
		for (int y = 0; y < map.arrayHeight; y++) {
			if (array[col][y].length() > max) {
				max = array[col][y].length();
			}
		}
		return max;
	}

	/**
	 * Repeats n times the string s
	 * 
	 * @param s
	 *            the string to repeat
	 * @param n
	 *            the times to repeat
	 * @return the repeated string
	 */
	public String repeatString(String s, int n) {
		return String.format(String.format("%%%ds", n), " ").replace(" ", s);
	}

	/**
	 * Prints the game statistics onto the console and the winner or winners
	 * with their score
	 */
	public void endOfGame() {
		System.out.println("GAME ENDED ------------------------------- GAME ENDED");
		Player winner = engine.playerlist.getBestPlayer();
		displayGameStatistics();

		if (engine.draw()) {
			engine.printDrawMessage();
		}

		else {
			System.out.println("\n" + "The Winner is " + winner.getName() + " - Your Score: " + winner.getScore() + "\n"
					+ "CONGRATUUUUUU....WAIT FOR IT.....UULATIONS!!! :)");

		}
	}

}
