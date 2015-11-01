package se_ex01;



public class KIRandom extends KI {

	

	public KIRandom(String name, int score) {
		super(name, score);
		// TODO Auto-generated constructor stub
	}

	
	public int chooseNumber() {

		int randomPosition = (int) (Math.random() * (remainingNumbers.size()-1));
		int chosenNumber = remainingNumbers.get(randomPosition);
		remainingNumbers.remove(chosenNumber);
		return chosenNumber;
		
	}

}
