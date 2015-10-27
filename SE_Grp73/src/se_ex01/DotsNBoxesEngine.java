package se_ex01;

import java.util.HashMap;
import java.util.LinkedList;

public class DotsNBoxesEngine {

	int turnsPlayed = 0;
	int playerID = 1;
	int numberOfPlayers = 0;
	Player player = new Player("default", 0);
	HashMap<Integer, Player> playerList = new HashMap<Integer, Player>();

	/**
	 * constructor : might be of value later when the code is extended.
	 */
	public DotsNBoxesEngine() {

	}

	/**
	 * 
	 * @return returns the current player
	 */
	public Player getCurrentPlayer() {
		Player currentPlayer = playerList.get(getCurrentPlayerID());
		return currentPlayer;
	}

	/**
	 * increases the player ID by one to switch to the next player
	 */
	public void increasePlayerIdByOne() {
		playerID++;

	}

	/**
	 * @return returns the ID of the current player
	 */
	public Integer getCurrentPlayerID() {
		return calculatePlayerID(playerID);
	}

	/**
	 * 
	 * @param height:
	 *            the height of the map
	 * @return
	 */
	public int calculateArrayHeight(int height) {
		return 3 * height - (height - 1);
	}

	public int calculateArrayWidth(int width) {
		return 3 * width - (width - 1);
	}

	/**
	 * stores the player(s) in a hashmap
	 * 
	 * @param counter:
	 *            counter to assign a unique number to each player
	 * @param name:
	 *            name of the player
	 */
	public void storePlayerName(Integer counter, String name) {
		playerList.put(counter, new Player(name, 0));
	}

	/**
	 * Calculates the playerID of whose turn it is
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
		}

		else {
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
	 * @param fieldNumber:
	 *            number entered by the player
	 * @param map:
	 *            map of the game
	 * @param width:
	 *            width of the map
	 * @param height:
	 *            height of the map
	 * @return: True if the number entered by the player actually exists on the
	 *          map. Else false.
	 */

	private boolean validMove(int fieldNumber, String[][] map, int width, int height) {
		// two for-loops to iterate over the whole map
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
	 * @param fieldNumber:
	 *            number entered by the player
	 * @param map:
	 *            map of the game
	 * @param width:
	 *            width of the game
	 * @param height:
	 *            height of the game
	 * @return: returns the coordinates of the Number in the Map as an array.
	 *          The Y coordinate is at the first position in the array while the
	 *          X coordinate is at the second position
	 */

	public int[] getCoordinatesOfNumberInMap(int fieldNumber, String[][] map, int width, int height) {

		int[] coordinatesOfFieldNumber = new int[2];
		if (validMove(fieldNumber, map, width, height)) {
			// the entered number exists on the map
			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {
					if (map[j][i].equals(Integer.toString(fieldNumber))) {
						// when the entered number is found on the map save its
						// position in an array and return it
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
	 * depending on its position on the map.
	 * 
	 * @param currentPlayer:
	 *            player whose turn it currently is
	 * @param fieldNumber:
	 *            number entered by the player
	 * @param width:
	 *            width of the map
	 * @param height:
	 *            height of the map
	 * @param map:
	 *            map of the game
	 * @param yCoordinateOfFoundNumber:
	 *            xCoordinate of the position of the number entered by the
	 *            player on the map
	 * @param xCoordinateOfFoundNumber:
	 *            yCoordinate of the position of the number entered by the
	 *            player on the map
	 * @return: returns true if the map is changed at the given position. Else
	 *          false
	 */

	public boolean replaceNumber(Player currentPlayer, int fieldNumber, int width, int height, String[][] map,
			int yCoordinateOfFoundNumber, int xCoordinateOfFoundNumber) {

		if (validMove(fieldNumber, map, width, height)) {
			if ((fieldNumber % width) == 0) {
				// the number entered by the player is on the right edge of the
				// map --> replace with "|"
				map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "|";
				return true;
			} else if (map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber + 1] == "*") {
				// if the number entered by the player is followed by a "+" -->
				// replace with "-"
				map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "-";
				return true;
			} else {
				// the number entered by the player has to be on either the left
				// or the right side of an "empty box"
				// --> replace with "|"
				map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "|";
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param map:
	 *            map of the game
	 * @param width:
	 *            width of the map
	 * @param height:
	 *            height of the map
	 * @return: returns true if there is a box that was just completed (closed)
	 *          by a player. Else false.
	 */

	public boolean completedBox(String[][] map, int width, int height) {

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (map[j][i] == " ") {
					// empty field was found
					if ((map[j - 1][i] == "-") && (map[j + 1][i] == "-") && (map[j][i + 1] == "|")
							&& (map[j][i - 1] == "|")) {
						// check around the empty field..if there is no number
						// --> the field was just completed by a player
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param map:
	 *            map of the game
	 * @param currentPlayer:
	 *            player whose turn it currently is
	 * @param yCoordinateOfFoundNumber:
	 *            yCoordinate of the position of the number entered by the
	 *            player on the map
	 * @param xCoordinateOfFoundNumber:
	 *            xCoordinate of the position of the number entered by the
	 *            player on the map
	 * @param width:
	 *            width of the map
	 * @param height:
	 *            height of the map
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
						// if there is a completed box --> store its position on
						// the map in an array and return it
						coordinatesOfCompletedBox[0] = j;
						coordinatesOfCompletedBox[1] = i;
					}
				}
			}
		}
		return coordinatesOfCompletedBox;
	}

	/**
	 * Enters the ID of the current player in the completed Box. e.g. "p1"
	 * 
	 * @param map:
	 *            map of the game
	 * @param currentPlayer:
	 *            player whose turn it currently is
	 * @param yCoordinateOfCompletedBox:
	 *            yCoordinate of the position of the completed box on the map
	 * @param xCoordinateOfCompletedBox:
	 *            xCoordinate of the position of the completed box on the map
	 * @param width:
	 *            width of the map
	 * @param height:
	 *            height of the map
	 */

	public void updateBoxWithName(String[][] map, Player currentPlayer, int yCoordinateOfCompletedBox,
			int xCoordinateOfCompletedBox, int width, int height) {

		if (completedBox(map, width, height)) {
			map[yCoordinateOfCompletedBox][xCoordinateOfCompletedBox] = "p" + calculatePlayerID(playerID);
			// assign a "p" + the ID of the current player to the completed box
			// and increase the score of the current player by 1
			currentPlayer.increaseScoreBy(1);
		}
	}

	public boolean checkFieldDimension(int width, int height) {
		if (!(width > 2 && width % 2 != 0 && height > 2 && height % 2 != 0 && (width * height) <= 450)) {

			return false;
		} else
			return true;
	}

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

	/**
	 * prints the statistics of the game to the console.
	 */
	public void getGameStats() {

		System.out.println("This is round no. : " + turnsPlayed);

		for (int i = 1; i <= numberOfPlayers; i++) {

			Player currentP = playerList.get(i);
			System.out.println(currentP.getName() + ", your current score is: " + currentP.getScore());

		}

	}

	private LinkedList<Player> getBestPlayerList(Player bestPlayer) {
		LinkedList<Player> winnerList = new LinkedList<Player>();
		for (int i = 1; i <= numberOfPlayers; i++) {
			Player currentP = playerList.get(i);
			if (bestPlayer.getScore() == currentP.getScore()) {
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
