package se_ex01;

public class Menu {
	private ControlUserInputs police = new ControlUserInputs();
	private DotsNBoxesEngine engine = new DotsNBoxesEngine();

	public void promptForTheMenueSettings() {
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("------------------->MENUE<-------------------");
		System.out.println("---------------------------------------------");
		System.out.println();
		System.out.println("Plese choose between: ");
		System.out.println("\tMode 1: Game against at least one human");
		System.out.println("\tMode 2: Game against one human and an AI support");
		System.out.println("\tMode 3: Game against AI");
		System.out.println();
		int input = police.getNumber("Please enter a number between 1 and 3 to choose your mode",
				"Please enter a number between 1 - 3");

		while (!(1 <= input && input <= 3)) {
			input = police.getNumber("Please enter a number between 1 and 3 to choose your mode",
					"Please enter a number between 1 - 3");
		}
		switch (input) {
		case 1:
			engine.mode = PlayingMode.AgainstHumans;
			break;
		case 2:
			engine.mode = PlayingMode.AISupport;
			break;
		case 3:
			engine.mode = PlayingMode.AgainstAI;
			break;
		}
	}
}
