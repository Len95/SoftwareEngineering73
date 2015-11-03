package se_ex01;

import java.util.ArrayList;

public abstract class AI extends Player {

	

	protected DotsNBoxesEngine engine;
	protected ControlInputs police = new ControlInputs();
	protected ArrayList<Integer> remainingNumbers = new ArrayList<Integer>();

	/**
	 * Gets the number of the wall the KI wants to select
	 * 
	 * (Called by the GUI)
	 * 
	 * @return the number of the wall the KI wants to move
	 */

	public AI(String name, int score, DotsNBoxesEngine engine) {
		super(name, score);
		super.isAI = true;
		this.engine = engine;
	}

	public void getRemainingNumbers() {
		remainingNumbers = engine.getMap().getOpenWallnumbers();
	}

	public abstract int getNextMove();
	

}
