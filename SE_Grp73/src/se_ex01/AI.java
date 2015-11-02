package se_ex01;

import java.util.ArrayList;

public abstract class AI extends Player {

	

	protected DotsNBoxesEngine engine;

	protected ArrayList<Integer> remainingNumbers = new ArrayList<Integer>();

	/**
	 * Gets the number of the wall the KI wants to select
	 * 
	 * (Called by the GUI)
	 * 
	 * @return the number of the wall the KI wants to move
	 */

	public AI(String name, int score, DotsNBoxesEngine engine) {
		super(name, score);
		super.isAI = true;
		this.engine = engine;
	}

	public static boolean isNumber(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public void calculateRemainingNumbers(String[][] map, int width, int height) {

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				if (isNumber(map[i][j])) {
					remainingNumbers.add(Integer.valueOf(map[i][j]));
				}
			}
		}
	}

	public abstract int getNextMove();
	

}
