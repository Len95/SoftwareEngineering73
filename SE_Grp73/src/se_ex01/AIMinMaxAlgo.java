package se_ex01;

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
	
	@Override
	public int getNextMove() {
		minMaxAlgo(0, 0, engine.getMap());
		int[] bestChoice = options.get(0);
		for (int[] option : options) {
			if (option[1] > bestChoice[1]) {
				bestChoice = option;
			}
		}
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
		// Got a: * (or) - (or) |
		if (map[startHeight][startWidth].equals("*") || map[startHeight][startWidth].equals("*")
				|| map[startHeight][startWidth].equals("*") || map[startHeight][startWidth].equals(" ")) {
			// Reached the right border of the field and it is not the last line
			if (startWidth >= (engine.getWidth() - 1) && !(startHeight >= (engine.getHeight() - 1))) {
				// Start at the beginning of the next row
				minMaxAlgo(startHeight++, 0, map);
			} else if (startHeight >= (engine.getHeight() - 1) && startWidth >= (engine.getWidth() - 1)) {
				// did we reach the bottom?
				return;
			} else
				// okay we can one more field right
				minMaxAlgo(startHeight, startWidth++, map);
		}

		// Found a wall number -> update options
		canidate[0] = Integer.valueOf(map[startHeight][startWidth]); // Wall-number
		canidate[1] = calculatePossiblePoints(startHeight, startWidth, map); // Possible-points
		options.add(canidate);

		// Recursive call
		// Reached the right border of the field and it is not the last line
		if (startWidth >= (engine.getWidth() - 1) && !(startHeight >= (engine.getHeight() - 1))) {
			// Start at the beginning of the next row
			minMaxAlgo(startHeight++, 0, map);
		} else if (startHeight >= (engine.getHeight() - 1) && startWidth >= (engine.getWidth() - 1)) {
			// did we reach the bottom?
			return;
		} else
			// okay we can one more field right
			minMaxAlgo(startHeight, startWidth++, map);
	}

	private int calculatePossiblePoints(int height, int width, String[][] map) {
		int points = -1;

		// Option one it is a position where we set |
		if (width == 0 || width == engine.getWidth()) {
			return points = calculateOption1(height, width, map);
		}
		if (map[height][width--].equals(" ")) {
			return points = calculateOption2(height, width, map);
		}

		// Option two it is a position where we set -
		if (height == 0 || height == engine.getHeight()) {
			return points = calculateOption2(height, width, map);
		}
		if (map[height--][width].equals(" ")) {
			return points = calculateOption2(height, width, map);
		}

		System.err.println("Non of the if cases in AiMinMaxAlgo.calculatePossiblePoints was used, returned -1 ");
		return points;
	}

	/**
	 * Set |
	 * 
	 * @param height
	 * @param width
	 * @param map
	 * @return
	 */
	private int calculateOption1(int height, int width, String[][] map) {
		int points = -1;

		// Check right field on right border
		if (width == 0) {
			if (map[height--][width++].equals("-") && map[height][width += 2].equals("|")
					&& map[height++][width++].equals("-")) {
				return points = 1;
			} else
				return points = 0;
		}

		// Check left field on left border
		if (width == engine.getWidth()) {
			if (map[height--][width--].equals("-") && map[height][width -= 2].equals("|")
					&& map[height++][width--].equals("-")) {
				return points = 1;
			} else
				return points = 0;
		}

		// Check if we can gain 2 points if we set |
		if (map[height--][width++].equals("-") && map[height][width += 2].equals("|")
				&& map[height++][width++].equals("-") && map[height--][width--].equals("-")
				&& map[height][width -= 2].equals("|") && map[height++][width--].equals("-")) {
			return points = 2;
		}

		if (width != 0 && width != engine.getWidth()) {
			// Check right field
			if (map[height--][width++].equals("-") && map[height][width += 2].equals("|")
					&& map[height++][width++].equals("-")) {
				return points = 1;
			} else if (map[height--][width--].equals("-") && map[height][width -= 2].equals("|")
					&& map[height++][width--].equals("-")) {
				// Check left field
				return points = 1;
			} else
				return points = 0;
		}

		System.err.println("Non of the if cases in AiMinMaxAlgo.calculateOption1 was used, returned -1 ");
		return points;
	}

	/**
	 * Set -
	 * 
	 * @param height
	 * @param width
	 * @param map
	 * @return
	 */
	private int calculateOption2(int height, int width, String[][] map) {
		int points = -1;

		// Check top on upper border
		if (height == 0) {
			if (map[height++][width--].equals("|") && map[height += 2][width].equals("-")
					&& map[height++][width++].equals("|")) {
				return points = 1;
			} else
				return points = 0;
		}

		// Check bottom on lower border
		if (height == engine.getHeight()) {
			if (map[height--][width--].equals("|") && map[height -= 2][width].equals("-")
					&& map[height--][width++].equals("|")) {
				return points = 1;
			} else
				return points = 0;
		}

		// Check if we can gain 2 points if we set -
		if (map[height++][width--].equals("|") && map[height += 2][width].equals("-")
				&& map[height++][width++].equals("|") && map[height--][width--].equals("|")
				&& map[height -= 2][width].equals("-") && map[height--][width++].equals("|")) {
			return points = 2;
		}

		if (width != 0 && width != engine.getWidth()) {
			// Check lower field
			if (map[height++][width--].equals("|") && map[height += 2][width].equals("-")
					&& map[height++][width++].equals("|")) {
				return points = 1;
			} else if (map[height--][width--].equals("|") && map[height -= 2][width].equals("-")
					&& map[height--][width++].equals("|")) {
				// Check upper field
				return points = 1;
			} else
				return points = 0;
		}

		System.err.println("Non of the if cases in AiMinMaxAlgo.calculateOption2 was used, returned -1 ");
		return points;
	}

}
