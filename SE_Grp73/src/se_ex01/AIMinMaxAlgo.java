package se_ex01;

import java.util.LinkedList;

public class AIMinMaxAlgo extends AI {

	/**
	 * A LinkedList with all options for the next move, int[0] contains the wall
	 * number and int[1] contains the maximal points by choosing this wall
	 * number for the next move
	 */
	private LinkedList<int[]> options = new LinkedList<int[]>();
	private Map map;
	private int /*height,*/ width, dNL, dNR, dNV;
	private LinkedList<Integer> horizontalNo = new LinkedList<Integer>();
	private LinkedList<Integer> verticalNo = new LinkedList<Integer>();

	public AIMinMaxAlgo(String name, int score, DotsNBoxesEngine engine) {
		super(name, score, engine);
		map = engine.getMap();
//		height = map.arrayHeight;
		width = map.arrayWidth;
	}

	@Override
	public int getNextMove() {
		// minMaxAlgo(0, 0, map.getMapAsStringArray());
		minMaxAlgo();
		int[] bestChoice = options.get(0);
		for (int[] option : options) {
			if (option[1] >= bestChoice[1]) {
				bestChoice = option;
			}
		}
		options.clear();
		return bestChoice[0];
	}

	private void minMaxAlgo() {
		setDimensionNumber();
		filterWallsHorizontalVertical();
		int[] canidate = new int[2];
		for (Integer wall : map.getOpenWallnumbers()) {
			if (criticalWallsLeft().contains(wall)) {
				if (!map.isWallOpen(wall - dNR) && !map.isWallOpen(wall + 1) && !map.isWallOpen(wall + dNL)) {
					canidate[0] = wall;
					canidate[1] = 1;
					options.add(canidate);
					continue;
				}
			} else if (criticalWallsRight().contains(wall)) {
				if (!map.isWallOpen(wall - 1) && !map.isWallOpen(wall + dNR) && !map.isWallOpen(wall - dNL)) {
					canidate[0] = wall;
					canidate[1] = 1;
					options.add(canidate);
					continue;
				}
			} else {
				int points = 0;
				if (verticalNo.contains(wall)) {
					// left field
					if (!map.isWallOpen(wall - 1) && !map.isWallOpen(wall - dNL) && !map.isWallOpen(wall + dNR)) {
						canidate[0] = wall;
						points = points + 1;
						canidate[1] = points;
					}
					// right field
					if (!map.isWallOpen(wall + 1) && !map.isWallOpen(wall - dNR) && !map.isWallOpen(wall + dNL)) {
						canidate[0] = wall;
						points = points + 1;
						canidate[1] = points;
					}
					options.add(canidate);
				} else {
					int score = 0;
					// upper field
					if (!map.isWallOpen(wall - dNV) && !map.isWallOpen(wall - dNL) && !map.isWallOpen(wall - dNR)) {
						canidate[0] = wall;
						score = score + 1;
						canidate[1] = score;
					}

					// lower field
					if (!map.isWallOpen(wall + dNV) && !map.isWallOpen(wall + dNR) && !map.isWallOpen(wall + dNL)) {
						canidate[0] = wall;
						score = score + 1;
						canidate[1] = score;
					}
					options.add(canidate);
				}
			}
		}
	}

	private void filterWallsHorizontalVertical() {
		int[][] field = map.getMapAsIntArray();
		for (int h = 0; h < map.arrayHeight; h++) {
			for (int w = 0; w < map.arrayWidth; w++) {
				if (h % 2 == 0) {
					horizontalNo.add(field[w][h]);
				} else
					verticalNo.add(field[w][h]);
			}
		}
	}

	private void setDimensionNumber() {
		int[][] field = map.getMapAsIntArray();
		dNL = field[0][1];
		dNR = field[width - 2][0];
		dNV = field[width - 1][1];
	}

	private LinkedList<Integer> criticalWallsLeft() {
		int[][] field = map.getMapAsIntArray();
		LinkedList<Integer> leftCollum = new LinkedList<Integer>();
		for (int i = 0; i < map.arrayHeight; i++) {
			if (i % 2 != 0) {
				leftCollum.add(field[0][i]);
			}
		}
		return leftCollum;
	}

	private LinkedList<Integer> criticalWallsRight() {
		int[][] field = map.getMapAsIntArray();
		LinkedList<Integer> rightCollum = new LinkedList<Integer>();
		for (int i = 0; i < map.arrayHeight; i++) {
			if (i % 2 != 0) {
				rightCollum.add(field[map.arrayWidth - 1][i]);
			}
		}
		return rightCollum;
	}

	// /**
	// * Fills up options recursively
	// *
	// * @param startWidth
	// * @param startHeight
	// * @param map
	// */
	// private void minMaxAlgo(int startHeight, int startWidth, String[][] map)
	// {
	// int[] canidate = new int[2];
	// // Break condition we reached the bottom
	// if (startHeight > height && startWidth > width) {
	// return;
	// }
	// System.out.println("Before if.isNumeric()" +
	// map[startHeight][startWidth]);
	// if (super.police.isNumeric(map[startWidth][startHeight])) {
	// // Found a wall number -> update options
	// canidate[0] = Integer.valueOf(map[startWidth][startHeight]); //
	// Wall-number
	// canidate[1] = calculatePossiblePoints(startWidth, startHeight, map); //
	// Possible-points
	// options.add(canidate);
	// if (startWidth % 2 == 0 && startWidth == (width - 1)) {
	// minMaxAlgo(startHeight + 1, 0, map);
	// } else
	// minMaxAlgo(startHeight, startWidth + 1, map);
	// } else if (startWidth > width && !(startHeight > height)) {
	// // Start at the beginning of the next row
	// minMaxAlgo(startHeight + 1, 0, map);
	// } else if (startHeight > height && startWidth > width) {
	// // did we reach the bottom?
	// return;
	// } else {
	// // okay we can go one more field right
	// minMaxAlgo(startHeight, startWidth + 1, map);
	// }
	// }
	//
	// private int calculatePossiblePoints(int height, int width, String[][]
	// map) {
	// // Option one it is a position where we set |
	// if ((height % 2) == 0) {
	// return calculatePointsOption1(height, width, map);
	// } else // Option two it is a position where we set -
	// return calculatePointsOption2(height, width, map);
	// }
	//
	// /**
	// * Set |
	// *
	// * @param height
	// * @param width
	// * @param map
	// * @return
	// */
	// private int calculatePointsOption1(int height, int width, String[][] map)
	// {
	// int points = 0;
	//
	// // we can check left field
	// if (height != 0) {
	// if (map[width - 1][height - 1].equals("-") && map[width][height -
	// 2].equals("|")
	// && map[width + 1][height - 1].equals("-")) {
	// points += 1;
	// } else
	// points += 0;
	// }
	//
	// // we can check right field
	// if (height != (this.height - 1)) {
	// if (map[width - 1][height + 1].equals("-") && map[width][height +
	// 2].equals("|")
	// && map[width + 1][height + 1].equals("-")) {
	// points += 1;
	// } else
	// points += 0;
	// }
	// return points;
	// }
	//
	// /**
	// * Set -
	// *
	// * @param height
	// * @param width
	// * @param map
	// * @return
	// */
	// private int calculatePointsOption2(int height, int width, String[][] map)
	// {
	// int points = 0;
	//
	// // we can check upper field
	// if (width != 0) {
	// if (map[width - 1][height - 1].equals("|") && map[width -
	// 2][height].equals("-")
	// && map[width - 1][height + 1].equals("|")) {
	// points += 1;
	// } else
	// points += 0;
	// }
	//
	// // we can check lower field
	// if (width != (this.width - 1)) {
	// if (map[width + 1][height - 1].equals("|") && map[width +
	// 2][height].equals("-")
	// && map[width + 1][height + 1].equals("|")) {
	// points += 1;
	// } else
	// points += 0;
	// }
	// return points;
	// }

}
