package se_ex01;

public class Menu {
	private ControlInputs police = new ControlInputs();
	private DotsNBoxesEngine engine;

	public Menu(DotsNBoxesEngine engine) {
		this.engine = engine; 
	}

	public void promptForTheMenueSettings() {
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
