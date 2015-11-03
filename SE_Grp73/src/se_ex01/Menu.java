package se_ex01;

public class Menu {
	private ControlInputs police = new ControlInputs();

	public Menu() {
	}

	public PlayingMode promptForTheMenueSettings() {
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("------------------->MENU<-------------------");
		System.out.println("---------------------------------------------");
		System.out.println();
		System.out.println("Please choose between: ");
		System.out.println();
		System.out.println("Hint: the AIMinMax is more difficult than AIRandom. ");
		System.out.println();
		System.out.println("\tMode 1: Game against at least one human (With optional KIMinMaxSupport)");
		System.out.println("\tMode 2: Player vs Player with AIRandom support");
		System.out.println("\tMode 3: Player vs Player with AIMinMax support");
		System.out.println("\tMode 4: Player vs AIRandom (Easy Mode)");
		System.out.println("\tMode 5: Player vs AIMinMax (Hard Mode)");
		System.out.println();
		int input = -1;

		while (!(1 <= input && input <= 5)) {
			input = police.getNumber("Please enter a number between 1 and 5 to choose your mode",
					"Please enter a number between 1 - 5");
		}
		switch (input) {
		case 1:return PlayingMode.AgainstHumans;
		case 2:return PlayingMode.AIRandomSupport;
		case 3:return PlayingMode.AIMinMaxSupport;
		case 4:return PlayingMode.AgainstAIRandom;
		case 5:return PlayingMode.AgainstAIMinMax;
		}
		// Kann eigentlich nicht passieren, da Wert oben geprueft wird
		return PlayingMode.AgainstHumans;
	}
}
