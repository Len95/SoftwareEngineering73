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
		canidate[0] = Integer.valueOf(map[startHeight][startWidth]); // Wall
																		// number
		canidate[1] = calculatePossiblePoints(startHeight, startWidth, map); // Possible
																				// points
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
		int points = 0;

		// Option one it is a position where we set |
		
		/*
		 * Definitely option one
		 * 1) width == 0
		 * 2) width == engine.width 
		 * 
		 * 3) Check if map[height][width--] and map[height][width++] equal " " then option one else option two
		 */
		
		// Option two it is a position where we set -
		
		/*
		 * Definitely option two
		 * 1) height == 0
		 * 2) height == engine.height
		 * 
		 * 3) Check if map[height][width--] and map[height][width++] equal " " then option one else option two
		 */
		
		// -> Hilfsmethode eine für option| (one) \\\/// die andere für option- (two)

		return points;
	}

}
