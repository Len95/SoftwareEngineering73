package se_ex01;

public class DotsNBoxesEngine {

	GUIConsole console = new GUIConsole();
	String[][] currentMap = console.map;
	int xCoordinateOfFoundNumber = -1;
	int yCoordinateOfFoundNumber = -1;
	int xCoordinateOfEmptyBox = -1;
	int yCoordinateOfEmptyBox = -1;
	int turnsPlayed = 0;

	public DotsNBoxesEngine() {

	}

	/**
	 * Checks if the number entered by the player actually exists on the map.
	 * 
	 * @return True if it does. Else False.
	 */
	
	public boolean validMove() {

		for (int i = 0; i < console.width; i++) {
			for (int j = 0; j < console.height; j++) {
				if (currentMap[i][j].equals(console.move(console.player))) {

					xCoordinateOfFoundNumber = j;
					yCoordinateOfFoundNumber = i;
					turnsPlayed++;
					return true;

				}

			}

		}

		return false;

	}

	/**
	 * 
	 * replaces the Number entered by the player with either "-" or "|" depending on its position. 
	 */
	
	public void replaceNumber() {

		if (validMove()) {

			if ((console.move(console.player) % console.width) == 0) {
				currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "|";

			}

			else {

				if (currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber + 1] == "*") {

					currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "-";
				}

				else {

					currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] = "|";
				}

			}

		}
		
		// else error Message : invalid move!!

	}

	/**
	 * 
	 * checks for all possible completed Box around the Field that was just changed by the player.
	 * assigns the coordinates of the empty field inside of the completed Box to xCoordinateOfEmptyBox and
	 * yCoordinateOfEmptyBox.
	 * 
	 * @return True if a completed Box was found, else False.
	 */
	
	public boolean completedBox() {

		if (currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] == "-") {

			if (yCoordinateOfFoundNumber < 1) {

				if ((currentMap[yCoordinateOfFoundNumber + 2][xCoordinateOfFoundNumber] == "-")
						&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber + 1] == "|")
						&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber - 1] == "|")) {

					xCoordinateOfEmptyBox = xCoordinateOfFoundNumber;
					yCoordinateOfEmptyBox = yCoordinateOfFoundNumber + 1;

					return true;
				}

				else if (yCoordinateOfFoundNumber == console.height - 1) {
					if ((currentMap[yCoordinateOfFoundNumber - 2][xCoordinateOfFoundNumber] == "-")
							&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber + 1] == "|")
							&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber - 1] == "|")) {

						xCoordinateOfEmptyBox = xCoordinateOfFoundNumber;
						yCoordinateOfEmptyBox = yCoordinateOfFoundNumber - 1;

					}

				}

				else {

					if ((currentMap[yCoordinateOfFoundNumber - 2][xCoordinateOfFoundNumber] == "-")
							&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber + 1] == "|")
							&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber - 1] == "|")) {

						xCoordinateOfEmptyBox = xCoordinateOfFoundNumber;
						yCoordinateOfEmptyBox = yCoordinateOfFoundNumber - 1;

						return true;
					}

					else if ((currentMap[yCoordinateOfFoundNumber + 2][xCoordinateOfFoundNumber] == "-")
							&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber + 1] == "|")
							&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber - 1] == "|")) {

						xCoordinateOfEmptyBox = xCoordinateOfFoundNumber;
						yCoordinateOfEmptyBox = yCoordinateOfFoundNumber + 1;

						return true;
					}

				}

			}
		}

		else if (currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber] == "|") {

			if ((currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber - 2] == "|")
					&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber - 1] == "-")
					&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber - 1] == "-")) {

				xCoordinateOfEmptyBox = xCoordinateOfFoundNumber - 1;
				yCoordinateOfEmptyBox = yCoordinateOfFoundNumber;

				return true;
			}

			else if ((currentMap[yCoordinateOfFoundNumber][xCoordinateOfFoundNumber + 2] == "|")
					&& (currentMap[yCoordinateOfFoundNumber + 1][xCoordinateOfFoundNumber + 1] == "-")
					&& (currentMap[yCoordinateOfFoundNumber - 1][xCoordinateOfFoundNumber + 1] == "-")) {

				xCoordinateOfEmptyBox = xCoordinateOfFoundNumber + 1;
				yCoordinateOfEmptyBox = yCoordinateOfFoundNumber;

				return true;
			}

		}

		return false;

	}

	/**
	 * assigns the name of the current player to the completed Box
	 * 
	 */
	
	public void updateBoxWithName() {

		if (completedBox()) {
			console.player.increaseScoreBy(1);
			currentMap[yCoordinateOfEmptyBox][xCoordinateOfEmptyBox] = console.player.getName();

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
		}

		else {

			return false;
		}

	}
	
	/**
	 * execute the actual turn using all methods above
	 * 
	 */
	
	public void executeTurn(){
		
		replaceNumber();
		updateBoxWithName();
		
		if(gameEnded()){
			
			// victory Message
		}
		
		else if (!completedBox()){
			
			// switch player  ,, else do nothing
			
		}
		
		
	}
	
	

}
