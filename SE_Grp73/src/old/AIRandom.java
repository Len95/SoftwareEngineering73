package old;

public class AIRandom extends AI {

	/**
	 * 
	 * @param name:
	 *            the name of the "player" (AI in this case)
	 * @param score:
	 *            the score of the "player" (AI in this case)
	 * @param engine:
	 *            the engine of the game
	 */
	public AIRandom(String name, int score, DotsNBoxesEngine engine) {
		super(name, score, engine);
	}

	/**
	 * calculates the next move of the AI
	 * 
	 * return: returns an int which represents the "input"(move) of the AI
	 */
	@Override
	public int getNextMove() {
		int randomPosition = (int) (Math.random() * (remainingNumbers.size() - 1));
		int chosenNumber = remainingNumbers.get(randomPosition);
		return chosenNumber;
	}

}
