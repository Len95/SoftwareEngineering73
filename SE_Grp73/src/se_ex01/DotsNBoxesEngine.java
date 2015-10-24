package se_ex01;

public class DotsNBoxesEngine {

	
	int turnsPlayed = 0;
	int playerID = 1;
	int numberOfPlayers = 0;
	Player player = new Player(null, 0);

	public DotsNBoxesEngine() {

	}

	public Player currentPlayer() {
		return player.playerList.get(calculatePlayerID(playerID));
	}

	/**
	 * Calculates the playerID whose turn is to play
	 * 
	 * @param moveNumber
	 *            The total number of moves that are played
	 * @return playerID
	 */
	public int calculatePlayerID(int ID) {
		int currentPlayer = -1;
		if (playerID > numberOfPlayers) {
			playerID = 1;
		}
		for (int i = 1; i <= numberOfPlayers; i++) {
			if (ID % (numberOfPlayers + 1) == i) {
				currentPlayer = i;
			}
		}
		return currentPlayer;
	}

	/**
	 * Checks if the number entered by the player actually exists on the map.
	 * 
	 * @return True if it does. Else False.
	 */

	public boolean validMove(Player currentPlayer, int fieldNumber, String[][] map, int width, int height) {

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {

				if (map[j][i] == (Integer.toString(fieldNumber))) {
					
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * 
	 * @param currentPlayer
	 * @param fieldNumber
	 * @param map
	 * @param width
	 * @param height
	 * @return returns the coordinates of the Number in the Map as an array. The
	 *         Y coordinate is at the first position in the array while the X
	 *         coordinate is at the second position
	 */

	public int[] getCoordinatesOfNumberInMap(Player currentPlayer, int fieldNumber, String[][] map, int width,
			int height) {

		int[] coordinatesOfFieldNumber = new int[2];

		if (validMove(currentPlayer, fieldNumber, map, width, height)) {

			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {

					if (map[j][i] == (Integer.toString(fieldNumber))) {

						coordinatesOfFieldNumber[0] = j;
						coordinatesOfFieldNumber[1] = i;
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

		if (validMove(currentPlayer, fieldNumber, map, width, height)) {

			if ((fieldNumber % width) == 0) {
				map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "|";
				return true;
			}

			else if (map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber + 1] == "*") {
				map[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "-";
				return true;
			}

			else {
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
		if (!(width > 2 && width % 2 != 0 && height > 2 && height % 2 != 0)) {

			return false;
		} else
			return true;
	}

	public Player returnWinner() {
		Player bestPlayer = new Player(null, 0);

		for (int i = 0; i <= player.playerList.size(); i++) {

			Player currentP = player.playerList.get(i);

			if (bestPlayer.getScore() < currentP.getScore()) {

				bestPlayer = currentP;

			}

		}

		return bestPlayer;

	}
	

	public boolean gameEnded(int width, int height) {

		turnsPlayed++;
		
		if (turnsPlayed == ((width * height) / 2)) {

			return true;
		}

		return false;
	}
}
