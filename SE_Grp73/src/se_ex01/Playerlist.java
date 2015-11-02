package se_ex01;

import java.util.LinkedList;
import java.util.Queue;

public class Playerlist {
	// FIFO-Queue - to archive the right sequence of players
	Queue<Player> queue;

	/**
	 * Constructor
	 */
	public Playerlist() {
		queue = new LinkedList<Player>();
	}
	
	/**
	 * Adds a player p to list
	 * @param p The player to add
	 * @return true, if addition was successful
	 */
	public boolean addPlayer(Player p) {
		return queue.add(p);
	}
	
	/**
	 * Get current player
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return queue.peek();
	}
	
	/**
	 * Adjusts the list to point to the next player in sequence
	 * 
	 * @return true, if successful
	 */
	public boolean nextPlayer() {
		return queue.add(queue.poll());
	}
	
	/**
	 * Returns the number of players
	 * 
	 * @return 
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * Returns the best player by score
	 * @return
	 */
	public Player getBestPlayer() {
		// TODO
		return null;
	}

}
