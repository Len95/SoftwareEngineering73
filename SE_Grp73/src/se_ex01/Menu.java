package se_ex01;

public class Menu {
	private ControlUserInputs police = new ControlUserInputs();
	private DotsNBoxesEngine engine;

	public Menu(DotsNBoxesEngine engine) {
		this.engine = engine; 
	}

	public void promptForTheMenueSettings() {
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("------------------->MENUE<-------------------");
		System.out.println("---------------------------------------------");
		System.out.println();
		System.out.println("Plese choose between: ");
		System.out.println();
		System.out.println("Hint: the AIMinMax is more difficult than AIRandom. ");
		System.out.println();
		System.out.println("\tMode 1: Game against at least one human");
		System.out.println("\tMode 2: Game against one human and an AIRandom support");
		System.out.println("\tMode 3: Game against AIMinMax support");
		System.out.println("\tMode 4: Game against AIRandom");
		System.out.println("\tMode 5: Game against AIMinMax");
		System.out.println();
		int input = -1;

		while (!(1 <= input && input <= 5)) {
			input = police.getNumber("Please enter a number between 1 and 3 to choose your mode",
					"Please enter a number between 1 - 3");
		}
		switch (input) {
		case 1:
			engine.mode = PlayingMode.AgainstHumans;
			break;
		case 2:
			engine.mode = PlayingMode.AIRandomSupport;
			break;
		case 3:
			engine.mode = PlayingMode.AIMinMaxSupport;
			break;
		case 4: 
			engine.mode = PlayingMode.AgainstAIRandom;
			break;
		case 5:
			engine.mode = PlayingMode.AgainstAIMinMax;
			break;
		}
	}
}
