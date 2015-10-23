package se_ex01;

import java.util.Scanner;

public class GUIConsole {
	Player player = new Player(0);
	Scanner s = new Scanner(System.in);
	DotsNBoxesEngine map;
	String winner;
	int numberOfMoves = 0;

	public GUIConsole() {
		// leave as default?!
	}

	/**
	 * Prompt to enter the name of the player who is playing
	 * 
	 * @return True if the name is entered
	 */
	public boolean enterPlayerName() {
		String name = s.next();
		player.setName(name);
		return true;
	}

	/**
	 * Prompt to enter the number of players for this gaming round
	 * 
	 * @return True if the number of players are entered
	 */
	public boolean enterNumberOfPlayers() {
		String numberOfPlayers = s.next();
		Integer input = Integer.parseInt(numberOfPlayers);
		while (!(input instanceof Integer)) {
			System.out.println("0 is invalid, please enter an integer greater than 0");
			numberOfPlayers = s.next();
		}
		return true;
	}

	public boolean mapDimension() {
		int width = 1;
		int height = 1;

		while (!(width >= 2 && width % 2 == 0 && height >= 2 && height % 2 == 0)) {
			System.out.print("Please enter the width, even integer greater than 1: ");
			width = s.nextInt();
			System.out.print("Please enter the height, even integer greater than 1: ");
			height = s.nextInt();
		}
		map = new DotsNBoxesEngine(width, height);
		return true;
	}

	/**
	 * 
	 * @param conditon
	 * @param player
	 * @param wall
	 * @return
	 */
	public boolean move(boolean conditon, Player player, int wall) {
		//TODO Len's task
		return false;
	}

}
