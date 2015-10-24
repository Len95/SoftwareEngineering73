package se_ex01;

public class DotsNBoxesEngine {

//	GUIConsole console = new GUIConsole();
//	String[][] currentMap = console.map;
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


	public boolean validMove(Player currentPlayer, int fieldNumber, String[][] map, int width, int height) {

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {

				if (map[j][i] == (Integer.toString(fieldNumber))) {

					xCoordinateOfFoundNumber = i;
					yCoordinateOfFoundNumber = j;
					turnsPlayed++;
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * replaces the Number entered by the player with either "-" or "|"
	 * depending on its position.
	 */
	
	public boolean replaceNumber(Player currentPlayer, int fieldNumber, int width, int height, String[][] map) {

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

	/**
	 * checks for all possible completed Box around the Field that was just
	 * changed by the player. assigns the coordinates of the empty field inside
	 * of the completed Box to xCoordinateOfEmptyBox and yCoordinateOfEmptyBox.
	 * 
	 * @return True if a completed Box was found, else False.
	 */

	// parameter
//	public boolean completedBox() {
//		if (currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] == "-") {
//			if (yCoordinateOfFoundNumber < 1) {
//				if ((currentMap[yCoordinateOfFoundNumber + 2][xCoordinateOfFoundNumber] == "-")
//						&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber + 1] == "|")
//						&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber - 1] == "|")) {
//
//					yCoordinateOfEmptyBox = xCoordinateOfFoundNumber;
//					xCoordinateOfEmptyBox = yCoordinateOfFoundNumber + 1;
//					return true;
//				} else if (yCoordinateOfFoundNumber == console.height - 1) {
//					if ((currentMap[yCoordinateOfFoundNumber - 2][xCoordinateOfFoundNumber] == "-")
//							&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber + 1] == "|")
//							&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber - 1] == "|")) {
//
//						yCoordinateOfEmptyBox = xCoordinateOfFoundNumber;
//						xCoordinateOfEmptyBox = yCoordinateOfFoundNumber - 1;
//					}
//				} else {
//					if ((currentMap[yCoordinateOfFoundNumber - 2][xCoordinateOfFoundNumber] == "-")
//							&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber + 1] == "|")
//							&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber - 1] == "|")) {
//
//						yCoordinateOfEmptyBox = xCoordinateOfFoundNumber;
//						xCoordinateOfEmptyBox = yCoordinateOfFoundNumber - 1;
//						return true;
//					} else if ((currentMap[yCoordinateOfFoundNumber + 2][xCoordinateOfFoundNumber] == "-")
//							&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber + 1] == "|")
//							&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber - 1] == "|")) {
//
//						yCoordinateOfEmptyBox = xCoordinateOfFoundNumber;
//						xCoordinateOfEmptyBox = yCoordinateOfFoundNumber + 1;
//						return true;
//					}
//				}
//			}
//		} else if (currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] == "|") {
//
//			if ((currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber - 2] == "|")
//					&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber - 1] == "-")
//					&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber - 1] == "-")) {
//
//				yCoordinateOfEmptyBox = xCoordinateOfFoundNumber - 1;
//				xCoordinateOfEmptyBox = yCoordinateOfFoundNumber;
//				return true;
//			} else if ((currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber + 2] == "|")
//					&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber + 1] == "-")
//					&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber + 1] == "-")) {
//
//				yCoordinateOfEmptyBox = xCoordinateOfFoundNumber + 1;
//				xCoordinateOfEmptyBox = yCoordinateOfFoundNumber;
//				return true;
//			}
//		}
//		return false;
//	}
	
	

	/**
	 * assigns the name of the current player to the completed Box
	 * 
	 */

//	public void updateBoxWithName() {
//
//		// not the whole name just p1 or p2
//
//		if (completedBox()) {
//			console.player.increaseScoreBy(1);
//			currentMap[xCoordinateOfEmptyBox][yCoordinateOfEmptyBox] = console.player.getName();
//		}
//	}
//
//	/**
//	 * checks if the game ended using a counter.
//	 * 
//	 * @return True if the game ended. Else False
//	 */
//
//	public boolean gameEnded() {
//		if (turnsPlayed == ((console.width * console.height) / 2)) {
//			return true;
//		} else {
//
//			return false;
//		}
//	}
//
//	/**
//	 * execute the actual turn using all methods above
//	 * 
//	 */
//
//	public void executeTurn() {
//
////		replaceNumber();
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
