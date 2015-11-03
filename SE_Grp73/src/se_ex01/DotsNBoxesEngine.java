package se_ex01;

import java.util.ArrayList;

public class DotsNBoxesEngine {

	private int height;
	private int width;
	private Map map;
	

	PlayingMode mode;
	int turnsPlayed = 0;
	int playerID = 0;
	
	public PlayerList playerlist = new PlayerList();


	public DotsNBoxesEngine(int width, int height) {
		map = new Map(width, height, this, playerlist);
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
	/*
	public int[] getCoordinatesOfNumberInMap(int fieldNumber, int width, int height) {

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

*/

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
	/*
	public int[] getCoordinatesOfCompletedBox() {

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
	*/

	/**
	 * Determines if the entered width and height are correct values to open up
	 * a game map
	 * 
	 * @param width
	 *            The width of the array
	 * @param height
	 *            The height of the array
	 * @return True if the width and height are correct, else false
	 */
	public boolean checkFieldDimension(int width, int height) {
		if (!(width > 2 && width % 2 != 0 && height > 2 && height % 2 != 0 && (width * height) <= 450)) {

			return false;
		} else
			return true;
	}

	/**
	 * checks if there is a draw between the players or not
	 * 
	 * @return True if there is a draw. Else false.
	 */
	public boolean draw() {
		System.out.println("length of highscore list : " + playerlist.getHighscore().size());
		return playerlist.getHighscore().size() > 1;
	}

	/**
	 * prints a message to the console when there is a draw
	 */
	public void printDrawMessage() {
		ArrayList<Player> drawedPlayers = playerlist.getHighscore();

		System.out.println("\n" + "There is a draw between the following players: ");
		for (Player currentP : playerlist.getHighscore()) {
			System.out.println(currentP.getName());
		}

		System.out.println("with each of them having a score of " + drawedPlayers.get(0).getScore() + "!");
	}

	/**
	 * Returns true if there are no numeric walls
	 * 
	 * @param width
	 *            The width of the 2D array
	 * @param height
	 *            The height of the 2D array
	 * @return True if there are no numeric walls -> game ended |// else false
	 */
	public boolean gameEnded() {
		if (turnsPlayed == ((width * height) / 2)) {
			return true;
		}
		turnsPlayed++;
		return false;
	}

	public Map getMap() {
		return map;
	}


	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


	public void setMap(Map map) {
		this.map = map;
	}

}
