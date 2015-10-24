package se_ex01;

public class Main {
	GUIConsole guiConsole = new GUIConsole();
	
	public void newGame() {

		System.out.println("---------------------------------------------------------");
		System.out.println("A NEW GAME STARTS: HAVE FUN! :)");
		System.out.println("---------------------------------------------------------");
		guiConsole.enterNumberOfPlayers();
	}
	
	public static void main(String[] args) {
		Main launch = new Main();
		launch.newGame();
	}
}
