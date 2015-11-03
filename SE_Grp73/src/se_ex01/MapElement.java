package se_ex01;

public enum MapElement {
	POINT (-1), FILLED_WALL_HORIZONTAL (-2), FILLED_WALL_VERT (-3), OPEN_WALL_HORIZONTAL (-4), OPEN_WALL_VERITICAL (-5), FIELD (-6);
	
	public final int value;
	
	MapElement(int vl) {
		this.value = vl;
	}
	
	public int getValue() {
		return value;
	}
}
