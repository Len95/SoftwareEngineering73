package se_ex01;

import java.util.Scanner;

public class ControlInputs {
	Scanner sc;

	public ControlInputs() {
		sc = new Scanner(System.in);
	}

	/**
	 * 
	 * Prompts the user with message to enter a integer
	 * 
	 * Stolen from
	 * http://codereview.stackexchange.com/questions/58800/making-sure-user-
	 * inputs-correct-type
	 * 
	 * @param prompt
	 *            The message the user is prompted with
	 * @return The number, the user entered
	 */
	public int getNumber(String prompt, String errorMessage) {
		while (true) {
			String input = getString(prompt);
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException ne) {
				System.out.println(errorMessage);
			}
		}
	}

	/**
	 * 
	 * Prompts the user with message to enter a string
	 * 
	 * Stolen from
	 * http://codereview.stackexchange.com/questions/58800/making-sure-user-
	 * inputs-correct-type
	 * 
	 * @param prompt
	 *            The message the user is prompted with
	 * @return The string, the user entered
	 */
	public String getString(String prompt) {
		String input = "";
		while (true) {
			System.out.print("\t" + prompt + ": ");
			if (sc.hasNextLine()) {
				input = sc.nextLine();
			}
			if (input != null && !input.isEmpty()) {
				return input;
			}
		}
	}

	/**
	 * http://stackoverflow.com/questions/14206768/how-to-check-if-a-string-is-
	 * numeric
	 * 
	 * @param string
	 * @return
	 */
	public boolean isNumeric(String string) {
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
