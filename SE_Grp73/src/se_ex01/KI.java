package se_ex01;

import java.util.ArrayList;

public abstract class KI extends Player {

	public final boolean isKI = false;
	
<<<<<<< HEAD

=======
	private DotsNBoxesEngine engine;
	
>>>>>>> branch 'master' of https://github.com/Len95/SoftwareEngineering73.git
	protected ArrayList<Integer> remainingNumbers = new ArrayList<Integer>();
<<<<<<< HEAD

=======
	
	public abstract int getNextMove();
>>>>>>> branch 'master' of https://github.com/Len95/SoftwareEngineering73.git

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
