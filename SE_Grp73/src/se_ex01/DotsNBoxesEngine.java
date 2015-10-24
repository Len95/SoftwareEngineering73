package se_ex01;

public class DotsNBoxesEngine {

	int xCoordinateOfFoundNumber = -1;
	int yCoordinateOfFoundNumber = -1;
	int yCoordinateOfEmptyBox = -1;
	int xCoordinateOfEmptyBox = -1;
	int turnsPlayed = 0;
	int playerID = 1;
	int numberOfPlayers = 0;
	Player player = new Player(null, 0);
	
	
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
	 * @return returns the coordinates of the Number in the Map as an array. The Y coordinate is at the first position
	 * in the array while the X coordinate is at the second position
	 */
	
	public int[] getCoordinatesOfNumberInMap(Player currentPlayer, int fieldNumber, String[][] map, int width, int height) {

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
	 * Checks if the field dimension is correct
	 * @param width Width of the field
	 * @param height Height of the field
	 * @return True if width and height are correct else false
	 */
	public boolean checkFieldDimension(int width, int height) {
		if (!(width > 2 && width % 2 != 0 && height > 2 && height % 2 != 0)) {
			return false;
		} else
			return true;
	}
	
	public Player currentPlayer() {
		return null;
	}
	
	/**
	 * Calculates the playerID whose turn is to play
	 * @param moveNumber The total number of moves that are played
	 * @return playerID
	 */
	private int calculatePlayerID(int ID) {
		int currentPlayer = -1;
		if (playerID > numberOfPlayers) {
			playerID = 1;
		}
		for (int i = 1; i <= numberOfPlayers; i++) {
			if ( ID % (numberOfPlayers + 1) == i) {
				currentPlayer = i;
			}
		}
		return currentPlayer;
	}

	
//	public boolean gameEnded() {
//		if (turnsPlayed == ((console.width * console.height) / 2)) {
//			return true;
//		} else {
//
//			return false;
//		}
//	}

	/**
	 * execute the actual turn using all methods above
	 * 
	 */

//	public void executeTurn() {
//
//		// replaceNumber();
//		updateBoxWithName();
//		if (gameEnded()) {
//
//			// victory Message
//		}
//
//		else if (!completedBox()) {
//
//			// switch player ,, else do nothing
//		}
//	}
}
