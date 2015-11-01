package se_ex01;

public class KIRandom extends KI {

	//ArrayList remainingNumbers = new ArrayList();

	public KIRandom(String name, int score, DotsNBoxesEngine engine) {
		super(name, score, engine);
		// TODO Auto-generated constructor stub
	}

	
	public int chooseNumber() {

		int randomPosition = (int) (Math.random() * (remainingNumbers.size()-1));
		int chosenNumber = remainingNumbers.get(randomPosition);
		remainingNumbers.remove(chosenNumber);
		return chosenNumber;
		
	}


	@Override
	public int getNextMove() {
		// TODO Auto-generated method stub
		return 0;
	}

}
