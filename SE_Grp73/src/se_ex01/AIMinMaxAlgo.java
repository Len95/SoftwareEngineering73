package se_ex01;

import java.util.ArrayList;
import java.util.LinkedList;

public class AIMinMaxAlgo extends AI {

	/**
	 * A LinkedList with all options for the next move, int[0] contains the wall
	 * number and int[1] contains the maximal points by choosing this wall
	 * number for the next move
	 */
	private LinkedList<int[]> options = new LinkedList<int[]>();
	private Map map;
	private int /* height, */ width, dNL, dNR, dNV;
	private LinkedList<Integer> horizontalNo = new LinkedList<Integer>();
	private LinkedList<Integer> verticalNo = new LinkedList<Integer>();
	LinkedList<Integer> rightCollum = new LinkedList<Integer>();
	LinkedList<Integer> leftCollum = new LinkedList<Integer>();

	public AIMinMaxAlgo(String name, int score, DotsNBoxesEngine engine) {
		super(name, score, engine);
		map = engine.getMap();
		// height = map.arrayHeight;
		width = map.arrayWidth;
	}

	@Override
	public int getNextMove() {
		// minMaxAlgo(0, 0, map.getMapAsStringArray());
		minMaxAlgo();
		int[] bestChoice = options.get(0);
		for (int[] option : options) {
			System.out.println("option[0] = wall:   " + option[0]);
			System.out.println("option[1] = points: " + option[1]);
			if (option[1] >= bestChoice[1]) {
				bestChoice = option;
			}
		}
		options.clear();
		return bestChoice[0];
	}

	private void minMaxAlgo() {
		setDimensionNumber();
		criticalWallsLeft();
		criticalWallsRight();
		filterWallsHorizontalVertical();
		// int[] canidate = new int[2];
		ArrayList<Integer> walls = map.getOpenWallnumbers();
		for (Integer wall : walls) {
			System.out.println("for(..) - WallNo. " + wall);
			if (leftCollum.contains(wall)) {
				if (!map.isWallOpen(wall - dNR) && !map.isWallOpen(wall + 1) && !map.isWallOpen(wall + dNL)
						&& map.isWallOnTheMap(wall - dNR) && map.isWallOnTheMap(wall + 1)
						&& map.isWallOnTheMap(wall + dNL)) {
					int[] canidate = new int[2];
					canidate[0] = wall;
					canidate[1] = 1;
					options.add(canidate);
					// continue;
				}
			} else if (rightCollum.contains(wall)) {
				if (!map.isWallOpen(wall - 1) && !map.isWallOpen(wall + dNR) && !map.isWallOpen(wall - dNL)) {
					int[] canidate = new int[2];
					canidate[0] = wall;
					canidate[1] = 1;
					options.add(canidate);
					// continue;
				}
			} else {
				int points = 0;
				int[] canidate = new int[2];
				if (verticalNo.contains(wall)) {
					// left field
					System.err.println("----------------------");
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
					// upper field
					if (!map.isWallOpen(wall - dNV) && !map.isWallOpen(wall - dNL) && !map.isWallOpen(wall - dNR)
							&& map.isWallOnTheMap(wall - dNR) && map.isWallOnTheMap(wall - dNV)
							&& map.isWallOnTheMap(wall - dNL)) {
						canidate[0] = wall;
						points = points + 1;
						canidate[1] = points;
					}

					// lower field
					if (!map.isWallOpen(wall + dNV) && !map.isWallOpen(wall + dNR) && !map.isWallOpen(wall + dNL)
							&& map.isWallOnTheMap(wall + dNR) && map.isWallOnTheMap(wall + dNV)
							&& map.isWallOnTheMap(wall - dNL)) {
						canidate[0] = wall;
						points = points + 1;
						canidate[1] = points;
					}
					options.add(canidate);
				}
			}
		}
	}

	private void filterWallsHorizontalVertical() {
		String[][] field = map.getMapAsStringArray();
		for (int h = 0; h < map.arrayHeight; h++) {
			for (int w = 0; w < map.arrayWidth; w++) {
				if (h % 2 == 0 && w % 2 != 0 && super.police.isNumeric(field[w][h])) {
					verticalNo.add(Integer.valueOf(field[w][h]));
				} else if (h % 2 != 0 && w % 2 == 0 && super.police.isNumeric(field[w][h])) {
					horizontalNo.add(Integer.valueOf(field[w][h]));
				}
			}
		}
		System.out.println(horizontalNo.toString());
		System.out.println(verticalNo.toString());
	}

	private void setDimensionNumber() {
		int[][] field = map.getMapAsIntArray();
		dNL = field[0][1];
		dNR = field[width - 2][0];
		dNV = field[width - 1][1];
	}

	private LinkedList<Integer> criticalWallsLeft() {
		String[][] field = map.getMapAsStringArray();
		for (int i = 0; i < map.arrayWidth; i++) {
			if (i % 2 != 0) {
				if (super.police.isNumeric(field[i][0])) {
					leftCollum.add(Integer.valueOf(field[i][0]));
				}
			}
		}
		System.out.println("LeftC" + leftCollum.toString());
		return leftCollum;
	}

	// TODO Map wird nicht richtig geupdated
	private LinkedList<Integer> criticalWallsRight() {
		String[][] field = map.getMapAsStringArray();
		for (int i = 0; i < map.arrayWidth; i++) {
			if (i % 2 != 0) {
				if (super.police.isNumeric(field[i][map.arrayHeight - 1])) {
					rightCollum.add(Integer.valueOf(field[i][map.arrayHeight - 1]));
				}
			}
		}
		System.out.println("RightC" + rightCollum.toString());
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
