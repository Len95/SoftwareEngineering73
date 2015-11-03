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
	//	System.out.println("options.size: " + options.size());
		for (int[] option : options) {
		//	System.out.println("Optioin[0]: " + option[0] + " Option[1]: " + option[1]);

			if (option[1] >= bestChoice[1]) {
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

		if (super.police.isNumeric(map[startHeight][startWidth])) {
			// Found a wall number -> update options
		//	System.out.println("WallNo.: " + map[startHeight][startWidth] + " h|w " + startHeight + "|"+ startWidth);
			canidate[0] = Integer.valueOf(map[startHeight][startWidth]); // Wall-number
			canidate[1] = calculatePossiblePoints(startHeight, startWidth, map); // Possible-points
			options.add(canidate);
			if (startWidth % 2 == 0 && startWidth == (engine.getWidth() - 1)) {
				minMaxAlgo(++startHeight, 0, map);
			} else
				minMaxAlgo(startHeight, ++startWidth, map);
		} else if (startWidth >= (engine.getWidth() - 1) && !(startHeight >= (engine.getHeight() - 1))) {
			// Start at the beginning of the next row
			minMaxAlgo(++startHeight, 0, map);
		} else if (startHeight >= (engine.getHeight() - 1) && startWidth >= (engine.getWidth() - 1)) {
			// did we reach the bottom?
			return;
		} else {
			// okay we can go one more field right
			minMaxAlgo(startHeight, ++startWidth, map);
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
	 * @param height
	 * @param width
	 * @param map
	 * @return
	 */
	private int calculatePointsOption1(int height, int width, String[][] map) {
		int points = 0;

		// we can check left field
		if (width != 0) {
			
		//	System.out.println("Height LeftField: " + height);
		//	System.out.println("Width LeftField: " + width);
			
			if (map[height--][width--].equals("-") && map[height][width -= 2].equals("|")
					&& map[height++][width--].equals("-")) {
				points += 1;
			} else
				points += 0;
		}
		
		// we can check right field
		if (width != (engine.getWidth() - 1)) {
			
		//	System.out.println("Height RightField: " + height);
		//	System.out.println("Width RightField: " + width);
			
			if (map[height--][width++].equals("-") && map[height][width += 2].equals("|")
					&& map[height++][width++].equals("-")) {
				points += 1;
			} else
				points += 0;
		}
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
	private int calculatePointsOption2(int height, int width, String[][] map) {
		int points = 0;

		// we can check upper field
		if (height != 0) {
			if (map[height--][width--].equals("|") && map[height -= 2][width].equals("-")
					&& map[height--][width++].equals("|")) {
				points += 1;
			} else
				points += 0;
		}

		// we can check lower field
		if (height != (engine.getHeight() - 1)) {
			if (map[height++][width--].equals("|") && map[height += 2][width].equals("-")
					&& map[height++][width++].equals("|")) {
				points += 1;
			} else
				points += 0;
		}
		return points;
	}

}
