package se_ex01;

public class DotsNBoxesEngine {

	GUIConsole console = new GUIConsole();
	String[][] currentMap = console.map;
	// change x and y

	int xCoordinateOfFoundNumber = -1;
	int yCoordinateOfFoundNumber = -1;
	int yCoordinateOfEmptyBox = -1;
	int xCoordinateOfEmptyBox = -1;
	int turnsPlayed = 0;

	public DotsNBoxesEngine() {

	}

	/**
	 * Checks if the number entered by the player actually exists on the map.
	 * 
	 * @return True if it does. Else False.
	 */

	// turnsPlayed has to be in a method that only gets called once
	public boolean validMove(Player currentPlayer, int fieldNumber, String[][] map, int width, int height) {

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {

				if (map[j][i] == (Integer.toString(fieldNumber))) {

					turnsPlayed++;
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
	 * @param map
	 * @param yCoordinateOfFoundNumber
	 * @param xCoordinateOfFoundNumber
	 * @param width
	 * @param height
	 * @return True if there is a Box that was just completed.
	 */

	public boolean completedBoxNew(String[][] map, int width, int height) {

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

		if (completedBoxNew(map, width, height)) {
		//	map[yCoordinateOfCompletedBox][xCoordinateOfCompletedBox] = 

		}
	}

	

	/**
	 * checks if the game ended using a counter.
	 * 
	 * @return True if the game ended. Else False
	 */

	public boolean gameEnded() {
		if (turnsPlayed == ((console.width * console.height) / 2)) {
			return true;
		} else {

			return false;
		}
	}

	/**
	 * execute the actual turn using all methods above
	 * 
	 */

	public void executeTurn() {

		// replaceNumber();
		updateBoxWithName();
		if (gameEnded()) {

			// victory Message
		}

		else if (!completedBox()) {

			// switch player ,, else do nothing
		}
	}
}
