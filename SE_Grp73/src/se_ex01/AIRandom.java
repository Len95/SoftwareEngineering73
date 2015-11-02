package se_ex01;


public class AIRandom extends AI {

	

	public AIRandom(String name, int score, DotsNBoxesEngine engine) {
		super(name, score, engine);
	}

	
	public int chooseNumber() {

		int randomPosition = (int) (Math.random() * (remainingNumbers.size()-1));
		int chosenNumber = remainingNumbers.get(randomPosition);
		remainingNumbers.remove(chosenNumber);
		return chosenNumber;
		
	}


	@Override
	public int getNextMove() {
		int randomPosition = (int) (Math.random() * (remainingNumbers.size()-1));
		int chosenNumber = remainingNumbers.get(randomPosition);
		remainingNumbers.remove(chosenNumber);
		return chosenNumber;
	}

}
