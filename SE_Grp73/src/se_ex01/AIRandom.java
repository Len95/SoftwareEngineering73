package se_ex01;

import java.util.ArrayList;

public class AIRandom extends AI {

	

	public AIRandom(String name, int score, DotsNBoxesEngine engine) {
		super(name, score, engine);
	}

	


	@Override
	public int getNextMove() {
		ArrayList<Integer> openWalls = engine.getMap().getOpenWallnumbers();
		int randomPosition = (int) (Math.random() * (openWalls.size()-1));
		return openWalls.get(randomPosition);
	}

}
