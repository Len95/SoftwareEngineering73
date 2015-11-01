package se_ex01;

import java.util.ArrayList;

public class KIRandom extends KI {

	//ArrayList remainingNumbers = new ArrayList();

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
