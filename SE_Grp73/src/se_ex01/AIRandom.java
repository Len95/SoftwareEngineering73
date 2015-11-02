package se_ex01;


public class AIRandom extends AI {

	

	public AIRandom(String name, int score, DotsNBoxesEngine engine) {
		super(name, score, engine);
		// TODO Auto-generated constructor stub
	}

	


	@Override
	public int getNextMove() {
		int randomPosition = (int) (Math.random() * (remainingNumbers.size()-1));
		int chosenNumber = remainingNumbers.get(randomPosition);
		remainingNumbers.remove(chosenNumber);
		return chosenNumber;
	}

}
