package se_ex01;

public class DotsNBoxesEngine {
	public String[][] map;

	/**
	 * Initialize the field size of the map
	 * 
	 * @param width
	 *            width of the field
	 * @param height
	 *            height of the field
	 */
	public DotsNBoxesEngine(int width, int height) {
		if (width >= 2 && width % 2 == 0 && height >= 2 && height % 2 == 0) {
			this.map = new String[width][height];
		} else
			System.err.println("Wrong scaling of the map");
	}
	
	
	// asdasd 
	// adfjofjao
	
}
