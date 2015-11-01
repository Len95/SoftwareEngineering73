package se_ex01;

import java.util.ArrayList;

public abstract class KI extends Player {

	protected ArrayList remainingNumbers = new ArrayList();

	public KI(String name, int score) {
		super(name, score);
		// TODO Auto-generated constructor stub
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
