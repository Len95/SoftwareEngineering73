package se_ex01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class PlayerList {
	// FIFO-Queue - to archive the right sequence of players
	Queue<Player> queue;
	
	/**
	 * Compares players by score. score=10 > score=1
	 */
	private Comparator<Player> scoreComparator = new Comparator<Player>() {
		@Override
		public int compare(Player p1, Player p2) {
			if(p1.getScore() < p2.getScore()) {
				return 1;
			} else if (p1.getScore() > p2.getScore()) {
				return -1;
			} else {
				return 0;
			}
		}
	};

	/**
	 * Constructor
	 */
	public PlayerList() {
		queue = new LinkedList<Player>();
	}
	
	/**
	 * Adds a player p to list
	 * @param p The player to add
	 * @return true, if addition was successful
	 */
	public boolean addPlayer(Player p) {
		p.ID = queue.size() + 1;
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

	/**
	 * Returns the highscore
	 * 
	 * @return Sorted ArrayList
	 */
	public ArrayList<Player> getHighscore() {
		ArrayList<Player> highscore = new ArrayList<Player>(queue);
		
		highscore.sort(scoreComparator);
		
		return highscore;
	}
	
	/**
	 * Returns the playerlist as ArrayList
	 * @return the ArrayList
	 */
	public ArrayList<Player> asArraylist() {
		return new ArrayList<Player>(queue);
	}
}
