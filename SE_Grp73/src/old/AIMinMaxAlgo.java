package old;

import java.util.LinkedList;

public class AIMinMaxAlgo extends AI {

	/**
	 * A LinkedList with all options for the next move, int[0] contains the wall
	 * number and int[1] contains the maximal points by choosing this wall
	 * number for the next move
	 */
	private LinkedList<int[]> options = new LinkedList<int[]>();

	public AIMinMaxAlgo(String name, int score, DotsNBoxesEngine engine) {
		super(name, score, engine);
	}

	/**
	 * calculates the next move of the AI
	 *
	 * return: returns an int which represents the "input"(move) of the AI
	 */
	@Override
	public int getNextMove() {
		minMaxAlgo(0, 0, engine.getMap());
		int[] bestChoice;
		try {
			bestChoice = options.get(0);
		} catch (IndexOutOfBoundsException ioobe) {
			return -1;
		}

		for (int[] option : options) {
			if (option[1] >= bestChoice[1]) {
				bestChoice = option;
			}
		}
		options.clear();
		return bestChoice[0];
	}

	/**
	 * Fills up options recursively
	 *
	 * @param startWidth
	 * @param startHeight
	 * @param map
	 */
	private void minMaxAlgo(int startHeight, int startWidth, String[][] map) {
		int[] canidate = new int[2];
		// Break condition we reached the bottom
		if (startHeight >= (engine.getHeight() - 1) && startWidth >= (engine.getWidth() - 1)) {
			return;
		}

		if (super.police.isNumeric(map[startHeight][startWidth])) {
			// Found a wall number -> update options
			canidate[0] = Integer.valueOf(map[startHeight][startWidth]); // Wall-number
			canidate[1] = calculatePossiblePoints(startHeight, startWidth, map); // Possible-points
			options.add(canidate);
			if (startWidth % 2 == 0 && startWidth == (engine.getWidth() - 1)) {
				minMaxAlgo(startHeight + 1, 0, map);
			} else
				minMaxAlgo(startHeight, startWidth + 1, map);
		} else if (startWidth >= (engine.getWidth() - 1) && !(startHeight >= (engine.getHeight() - 1))) {
			// Start at the beginning of the next row
			minMaxAlgo(startHeight + 1, 0, map);
		} else {
			// okay we can go one more field right
			minMaxAlgo(startHeight, startWidth + 1, map);
		}
	}

	private int calculatePossiblePoints(int height, int width, String[][] map) {
		// Option one it is a position where we set |
		if ((width % 2) == 0) {
			return calculatePointsOption1(height, width, map);
		} else // Option two it is a position where we set -
			return calculatePointsOption2(height, width, map);
	}

	/**
	 * Set |
	 *
	 * @param height:
	 *            height of the map
	 * @param width:
	 *            width of the map
	 * @param map:
	 *            map of the game
	 * @return
	 */
	private int calculatePointsOption1(int height, int width, String[][] map) {
		int points = 0;

		// we can check left field
		if (width != 0) {
			if (map[height - 1][width - 1].equals("-") && map[height][width - 2].equals("|")
					&& map[height + 1][width - 1].equals("-")) {
				points += 1;
			} else
				points += 0;
		}

		// we can check right field
		if (width != (engine.getWidth() - 1)) {
			if (map[height - 1][width + 1].equals("-") && map[height][width + 2].equals("|")
					&& map[height + 1][width + 1].equals("-")) {
				points += 1;
			} else
				points += 0;
		}
		return points;
	}

	/**
	 * Set -
	 *
	 * @param height:
	 *            height of the map
	 * @param width:
	 *            width of the map
	 * @param map:
	 *            map of the game
	 * @return
	 */
	private int calculatePointsOption2(int height, int width, String[][] map) {
		int points = 0;

		// we can check upper field
		if (height != 0) {
			if (map[height - 1][width - 1].equals("|") && map[height - 2][width].equals("-")
					&& map[height - 1][width + 1].equals("|")) {
				points += 1;
			} else
				points += 0;
		}

		// we can check lower field
		if (height != (engine.getHeight() - 1)) {
			if (map[height + 1][width - 1].equals("|") && map[height + 2][width].equals("-")
					&& map[height + 1][width + 1].equals("|")) {
				points += 1;
			} else
				points += 0;
		}
		return points;
	}

}
