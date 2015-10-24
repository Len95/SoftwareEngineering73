package se_ex01;

public class Main {
	GUIConsole guiConsole = new GUIConsole();
	
	public void newGame() {
		System.out.println("---------------------------------------------------------");
		System.out.println("A NEW GAME STARTS: HAVE FUN! :)");
		guiConsole.enterNumberOfPlayers();
		guiConsole.enterPlayerName();
		guiConsole.mapDimension();
		guiConsole.updateMap(guiConsole.initializeMap());
		
		// jetzt müsste noch ein move kommen oder ähnliches, damit gespielt werden kann 
	}
	
	public static void main(String[] args) {
		Main launch = new Main();
		launch.newGame();
	}
}
