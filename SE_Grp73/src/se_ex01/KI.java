package se_ex01;

import java.util.ArrayList;

public abstract class KI extends Player {

	public final boolean isKI = false;

	
	protected DotsNBoxesEngine engine;


	protected ArrayList<Integer> remainingNumbers = new ArrayList<Integer>();
	
	/**
	 * Gets the number of the wall the KI wants to select
	 * 
	 * (Called by the GUI)
	 * 
	 * @return the number of the wall the KI wants to move
	 */
	public abstract int getNextMove();


	public KI(String name, int score, DotsNBoxesEngine engine) {
		super(name, score);
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

}
