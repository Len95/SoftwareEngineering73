package se_ex01;

import java.util.HashMap;
import java.util.LinkedList;

public class DotsNBoxesEngine {

	int turnsPlayed = 0;
	int playerID = 1;
	int numberOfPlayers = 0;
	Player player = new Player("default", 0);
	HashMap<Integer, Player> playerList = new HashMap<Integer, Player>();

	public DotsNBoxesEngine() {

	}

	/**
	 * Returns the current player
	 * 
	 * @return Current player
	 */
	public Player getCurrentPlayer() {
		Player currentPlayer = playerList.get(getCurrentPlayerID());
		return currentPlayer;
	}

	/**
	 * Increases the player ID by one
	 */
	public void increasePlayerIdByOne() {
		playerID++;

	}

	/**
	 * Calculates the current player ID
	 * 
	 * @return current player ID
	 */
	public Integer getCurrentPlayerID() {
		return calculatePlayerID(playerID);
	}

	/**
	 * Calculates the right array height for the given input of field in a
	 * column
	 * 
	 * @param height
	 *            Columns
	 * @return Right array dimension for the array height
	 */
	public int calculateArrayHeight(int height) {
		return 3 * height - (height - 1);
	}

	/**
	 * Calculates the right array width for the given input of field in a row
	 * 
	 * @param width
	 *            Rows
	 * @return Right array dimension for the array width
	 */
	public int calculateArrayWidth(int width) {
		return 3 * width - (width - 1);
	}

	/**
	 * Stores the player names in a hashMap playerList<Integer, Player>
	 * 
	 * @param counter
	 *            Is the equivalent for the player ID
	 * @param name
	 *            The name of the player
	 */
	// TODO Do the checking in the Engine and add a boolean return statement so
	// the GUIConsole can work with this method
	public void storePlayerName(Integer counter, String name) {
		playerList.put(counter, new Player(name, 0));
	}

	/**
	 * Calculates the playerID whose turn is to play
	 * 
	 * @param moveNumber
	 *            The total number of moves that are played
	 * @return playerID
	 */
	protected Integer calculatePlayerID(int ID) {
		Integer currentPlayer = -1;
		if (playerID > numberOfPlayers) {
			playerID = 1;
			currentPlayer = playerID;
			return currentPlayer;
		} else {
			for (int i = 1; i <= numberOfPlayers; i++) {
				if ((ID % (numberOfPlayers + 1)) == i) {
					currentPlayer = Integer.valueOf(i);
					break;
				} else
					continue;
			}
		}
		return currentPlayer;
	}

	/**
	 * Checks if the number entered by the player actually exists on the map.
	 * 
	 * @return True if it does. Else False.
	 */
	private boolean validMove(int fieldNumber, String[][] map, int width, int height) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (map[i][j].equals(Integer.toString(fieldNumber))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * 
	 * @param fieldNumber
	 * @param map
	 * @param width
	 * @param height
	 * @return returns the coordinates of the Number in the Map as an array. The
	 *         Y coordinate is at the first position in the array while the X
	 *         coordinate is at the second position
	 */
	public int[] getCoordinatesOfNumberInMap(int fieldNumber, String[][] map, int width, int height) {

		int[] coordinatesOfFieldNumber = new int[2];
		if (validMove(fieldNumber, map, width, height)) {
			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {
					if (map[j][i].equals(Integer.toString(fieldNumber))) {

						coordinatesOfFieldNumber[0] = j;
						coordinatesOfFieldNumber[1] = i;
						return coordinatesOfFieldNumber;
					}
				}
			}
		}
		return coordinatesOfFieldNumber;
	}

	/**
	 * replaces the Number entered by the player with either "-" or "|"
	 * depending on its position.
	 */
	public boolean replaceNumber(Player currentPlayer, int fieldNumber, int width, int height, String[][] map,
			int yCoordinateOfFoundNumber, int xCoordinateOfFoundNumber) {
		if (validMove(fieldNumber, map, width, height)) {
			if ((fieldNumber % width) == 0) {
				map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "|";
				return true;
			} else if (map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber + 1] == "*") {
				map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "-";
				return true;
			} else {
				map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "|";
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Checks if the field dimension is correct
	 * 
	 * @param width
	 *            Width of the field
	 * @param height
	 *            Height of the field
	 * @param map
	 * @return True a box was just completed
	 */
	public boolean completedBox(String[][] map, int width, int height) {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (map[j][i] == " ") {
					if ((map[j - 1][i] == "-") && (map[j + 1][i] == "-") && (map[j][i + 1] == "|")
							&& (map[j][i - 1] == "|")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param map
	 * @param currentPlayer
	 * @param yCoordinateOfFoundNumber
	 * @param xCoordinateOfFoundNumber
	 * @param width
	 * @param height
	 * @return : returns the coordinates of the completed Box(if it exists) as
	 *         an Integer Array. The first position in the array is the
	 *         Y-Coordinate the second position is the X-Coordinate.
	 */
	public int[] getCoordinatesOfCompletedBox(String[][] map, int width, int height) {
		int[] coordinatesOfCompletedBox = new int[2];
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (map[j][i] == " ") {
					if ((map[j - 1][i] == "-") && (map[j + 1][i] == "-") && (map[j][i + 1] == "|")
							&& (map[j][i - 1] == "|")) {
						coordinatesOfCompletedBox[0] = j;
						coordinatesOfCompletedBox[1] = i;
					}
				}
			}
		}
		return coordinatesOfCompletedBox;
	}

	/**
	 * Enters the Name of the current player in the completed Box.
	 * 
	 * @param map
	 * @param currentPlayer
	 * @param yCoordinateOfCompletedBox
	 * @param xCoordinateOfCompletedBox
	 * @param width
	 * @param height
	 */
	public void updateBoxWithName(String[][] map, Player currentPlayer, int yCoordinateOfCompletedBox,
			int xCoordinateOfCompletedBox, int width, int height) {
		if (completedBox(map, width, height)) {
			map[yCoordinateOfCompletedBox][xCoordinateOfCompletedBox] = "p" + calculatePlayerID(playerID);
			currentPlayer.increaseScoreBy(1);
		}
	}

	/**
	 * checks if the game ended using a counter.
	 * 
	 * @return True if the game ended. Else False
	 */
	public boolean checkFieldDimension(int width, int height) {
		if (!(width > 2 && width % 2 != 0 && height > 2 && height % 2 != 0 && (width * height) <= 450)) {
			return false;
		} else
			return true;
	}

	/**
	 * Returns the winner list with the best players
	 * 
	 * @return The list with the best players (winner list)
	 */
	public LinkedList<Player> returnWinnerList() {
		Player bestPlayer = new Player("default", 0);
		for (int i = 1; i <= numberOfPlayers; i++) {
			Player currentP = playerList.get(i);
			if (bestPlayer.getScore() < currentP.getScore()) {
				bestPlayer = currentP;
			}
		}
		return getBestPlayerList(bestPlayer);
	}

	public void getGameStats() {
		System.out.println("This is round no. : " + turnsPlayed);
		for (int i = 1; i <= numberOfPlayers; i++) {
			Player currentP = playerList.get(i);
			System.out.println(currentP.getName() + ", your current score is: " + currentP.getScore());
		}
	}

	/**
	 * Puts the best player in a list, the list contains at least one player
	 * 
	 * @param player
	 *            A random player of this game
	 * @return The winner list with the best players
	 */
	private LinkedList<Player> getBestPlayerList(Player player) {
		LinkedList<Player> winnerList = new LinkedList<Player>();
		for (int i = 1; i <= numberOfPlayers; i++) {
			Player currentP = playerList.get(i);
			if (player.getScore() == currentP.getScore()) {
				winnerList.add(currentP);
			}
		}
		return winnerList;
	}

	public boolean gameEnded(int width, int height) {
		if (turnsPlayed == ((width * height) / 2)) {
			return true;
		}
		turnsPlayed++;
		return false;
	}
}
