package old;

public class Main {
	GUIConsole guiConsole = new GUIConsole();

	public void newGame() {
		System.out.println("---------------------------------------------------------");
		System.out.println("           A NEW GAME STARTS: HAVE FUN! :)");
		System.out.println("---------------------------------------------------------");
		// Default start - the game begins with a prompt that sets the number of
		// players in this game
		guiConsole.launch();
	}

	public static void main(String[] args) {
		Main launch = new Main();
		launch.newGame();
	}
}

