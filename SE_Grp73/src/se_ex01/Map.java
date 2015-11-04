package se_ex01;

import java.util.ArrayList;

public class Map {

	private int[][] map;
	private int numberOfBoxesX, numberOfBoxesY;
	public int arrayWidth, arrayHeight;
	private DotsNBoxesEngine engine;
	private PlayerList playerlist;
	public final int lastChooseableWall;
	public boolean justClosedAField = false;;

	private class Wall {
		public int wallNumber;
		public int x;
		public int y;
		public int type;
		public boolean isOpen = true;

		public Wall(int x, int y, int wallNumber, int type) {
			this.x = x;
			this.y = y;
			this.wallNumber = wallNumber;
			this.type = type;
		}

		public String toString() {
			return "Wall Nr. " + wallNumber + ":\tx=" + x + "\ty=" + y + "\ttype=" + type + "\tisOpen=" + isOpen;
		}
	}

	private Wall[] walls;
	private int numberOfWalls = 0;

	/**
	 * Creates a new map with effective width and height
	 * 
	 * width = 2, means, that there are two boxes
	 * 
	 * @param numberOfBoxesX
	 *            the width of the map
	 * @param numberOfBoxesY
	 *            the height of the map
	 */
	public Map(int numberOfBoxesX, int numberOfBoxesY, DotsNBoxesEngine engine, PlayerList playerlist) {
		this.engine = engine;
		this.playerlist = playerlist;

		this.arrayWidth = 2 * numberOfBoxesX + 1;
		this.arrayHeight = 2 * numberOfBoxesY + 1;
		this.numberOfBoxesX = numberOfBoxesX;
		this.numberOfBoxesY = numberOfBoxesY;

		this.lastChooseableWall = numberOfBoxesX * (numberOfBoxesY + 1) + numberOfBoxesY * (numberOfBoxesX + 1);
		walls = new Wall[this.lastChooseableWall + 1];

		map = new int[arrayWidth][arrayHeight];
		initializeMap();
	}

	/**
	 * Returns the map as int[][] with raw values
	 * 
	 * Points, walls and everything included
	 * 
	 * @return
	 */
	public int[][] getMapAsIntArray() {
		return map;
	}

	private void initializeMap() {
		for (int x = 0; x < arrayWidth; x++) {
			for (int y = 0; y < arrayHeight; y++) {

				// Node?
				if (x % 2 == 0 && y % 2 == 0) {
					
					map[x][y] = MapElement.POINT.getValue();
					// Horizontal Wall?
				} else if ((x + 1) % 2 == 0 && y % 2 == 0) {
					map[x][y] = MapElement.OPEN_WALL_HORIZONTAL.getValue();
					addNewWall(x, y, MapElement.OPEN_WALL_HORIZONTAL.getValue());
				} else if (x % 2 == 0 && (y + 1) % 2 == 0) {
					map[x][y] = MapElement.OPEN_WALL_VERITICAL.getValue();
					addNewWall(x, y, MapElement.OPEN_WALL_VERITICAL.getValue());

				} else {
					map[x][y] = MapElement.FIELD.getValue();
				}
			}
		}
		/*
		 * for (int i = 0; i < walls.length; i++) {
		 * System.out.println(walls[i]); }
		 */
	}

	/**
	 * Creates a new wall at position with type
	 * 
	 * @param x
	 *            The x coordinate of the wall
	 * @param y
	 *            The y coordinate of the wall
	 * @param type
	 *            Value of enum MapElement of the wall
	 */
	private void addNewWall(int x, int y, int type) {
		numberOfWalls++;
		// System.out
		// .println("New Wall:\tx=" + x + "\ty=" + y + "\twallnumber=" +
		// numberOfWalls + "\tsize=" + walls.length);
		walls[numberOfWalls] = new Wall(x, y, numberOfWalls, type);
	}

	/**
	 * Checks whether the wall is open
	 * 
	 * @param wall
	 *            The number of the wall
	 * @return Whether the wall with this number is still open
	 */
	public boolean isWallOpen(int wall) {
		if (isWallOnTheMap(wall)) {
			int x = wallNumberToXCoordinate(wall);
			int y = wallNumberToYCoordinate(wall);
			int me = map[x][y];
			if (me == MapElement.OPEN_WALL_HORIZONTAL.getValue() || me == MapElement.OPEN_WALL_VERITICAL.getValue()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * Checks whether wall at x|y is open
	 * 
	 * @param x
	 * @param y
	 * @return true if wall is open open
	 */
	public boolean isWallOpen(int x, int y) {
		int wallnumber = xyToWallNumber(x, y);
		if(wallnumber < 0) {
			return false;
		}
		return walls[wallnumber].isOpen;
	}

	/**
	 * Checks whether the field size in boxes is okay (both values > 1)
	 * 
	 * @param widthInBoxes
	 *            The width in boxes
	 * @param heightInBoxes
	 *            The height in boxes
	 * @return true if okay, false if not
	 */
	public static boolean isMapDimensionOkay(int widthInBoxes, int heightInBoxes) {
		if (widthInBoxes > 1 && heightInBoxes > 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Is the wall with this number on the map?
	 * 
	 * @param wall
	 * @return true if wall is on the map
	 */
	private boolean isWallOnTheMap(int wall) {
		if (wall >= 1 && wall <= lastChooseableWall) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Let player take a wall
	 * 
	 * @param p
	 *            The player who takes a wall
	 * @param wallnumber
	 *            the number of the wall to take
	 * @return true if wall had successfully take, false if note
	 */
	public boolean takeWall(Player p, int wallnumber) {
		if (isWallOpen(wallnumber)) {
			this.justClosedAField = false;
			closeWall(wallnumber);
			processClosedWall(p, wallnumber);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks whether the just CLOSED wall closed a field
	 * 
	 * THE WALL IS ALREADY CLOSED IF THIS METHOD IS CALLED
	 * 
	 * @param wallnumber
	 *            the number of the just CLOSED wall
	 */
	private void processClosedWall(Player p, int wallnumber) {
		Wall wall = walls[wallnumber];
		// Horizontal wall
		if (wall.type == MapElement.FILLED_WALL_HORIZONTAL.getValue()) {
			// Upper field (if existing)
			if (wall.y != 0) {
				hasWallClosedField(p, wall.x, wall.y - 1);
			}
			// Field below (if existing)
			if (wall.y != arrayHeight - 1) {
				hasWallClosedField(p, wall.x, wall.y + 1);
			}
		}
		// Vertical wall
		else {
			// Left field (if existing)
			if (wall.x != 0) {
				hasWallClosedField(p, wall.x - 1, wall.y);
			}
			// Right field (if existing)
			if (wall.x != arrayWidth - 1) {
				hasWallClosedField(p, wall.x + 1, wall.y);
			}
		}
	}

	/**
	 * Checks whether the field at x|y just is closed by player
	 * 
	 * @param p
	 *            The player who could have closed a field
	 * @param x
	 * @param y
	 */
	private void hasWallClosedField(Player p, int x, int y) {
		boolean leftWallClosed, rightWallClosed, upperWallClosed, lowerWallClosed, fieldClosed;

		leftWallClosed = !isWallOpen(x - 1, y);
		rightWallClosed = !isWallOpen(x + 1, y);
		upperWallClosed = !isWallOpen(x, y - 1);
		lowerWallClosed = !isWallOpen(x, y + 1);
		//System.out.println("Checking field at " + x + " | " + y );
		fieldClosed = leftWallClosed && rightWallClosed && upperWallClosed && lowerWallClosed;

		if (fieldClosed) {
			this.justClosedAField = true;
			map[x][y] = p.ID;
			p.increaseScoreBy(1);
		}

	}

	/**
	 * Closes a wall with number
	 * 
	 * @param wallnumber
	 */
	private void closeWall(int wallnumber) {
		if (isWallOpen(wallnumber)) {
			Wall wall = walls[wallnumber];
			// Get new type of wall
			int type = wall.type;
			int newType;
			if (type == MapElement.OPEN_WALL_HORIZONTAL.getValue()) {
				newType = MapElement.FILLED_WALL_HORIZONTAL.getValue();
			} else {
				newType = MapElement.FILLED_WALL_VERT.getValue();
			}

			// Change in Map
			int x = wallNumberToXCoordinate(wallnumber);
			int y = wallNumberToYCoordinate(wallnumber);
			map[x][y] = newType;

			// Change in Walls
			walls[wallnumber].type = newType;
			walls[wallnumber].isOpen = false;
		}
	}

	/**
	 * Is map[x][y] a coordinate of a field?
	 * 
	 * @param x
	 * @param y
	 * @return true if field
	 */
	private boolean isAFieldCoordinate(int x, int y) {
		if (x % 2 == 0 && y % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int xyToWallNumber(int x_Wall, int y_Wall) {
		//System.out.println("Looking for wall at x=" + x_Wall + "\ty=" + y_Wall);
		//System.out.println("Walls size=" + walls.length);
		for (int i = 1; i < walls.length; i++) {
			//System.out.println("i=" + i + "\t" + walls[i]);
			if (walls[i] != null && walls[i].x == x_Wall && walls[i].y == y_Wall) {
				// System.out.println("Found wall (" + x_Wall + "|" + y_Wall +
				// "):\t" + walls[i]);
				// System.out.println("Return " + walls[i].wallNumber);
				return walls[i].wallNumber;
			}
		}
		return -1;
	}

	private int wallNumberToXCoordinate(int wallnumber) {
		return walls[wallnumber].x;
	}

	private int wallNumberToYCoordinate(int wallnumber) {
		return walls[wallnumber].y;
	}

	/**
	 * Returns the numbers of all open walls
	 * 
	 * @return Open wall numbers
	 */
	public ArrayList<Integer> getOpenWallnumbers() {
		ArrayList<Integer> openWalls = new ArrayList<Integer>();

		for (Wall w : walls) {
			if (w != null && w.isOpen) {
				openWalls.add(w.wallNumber);
			}
		}

		return openWalls;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < arrayWidth; x++) {
			for (int y = 0; y < arrayHeight; y++) {
				int e = map[x][y];
				if (e == MapElement.FIELD.getValue()) {
					sb.append('O');
				} else if (e == MapElement.FILLED_WALL_HORIZONTAL.getValue()) {
					sb.append('-');
				} else if (e == MapElement.FILLED_WALL_VERT.getValue()) {
					sb.append('|');
				} else if (e == MapElement.OPEN_WALL_HORIZONTAL.getValue()
						|| e == MapElement.OPEN_WALL_VERITICAL.getValue()) {
					sb.append(xyToWallNumber(x, y));
				} else if (isAFieldCoordinate(x, y) && e > 0) {
					sb.append(playerlist.getPlayerByID(e).getShortName());
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Checks whether at least one wall is still open
	 * 
	 * @return true, if a wall is still open
	 */
	public boolean isAWallOpen() {
		ArrayList<Integer> openWalls = getOpenWallnumbers();
		return openWalls.size() > 0;
	}

	public int getLargestWallNumber() {
		return walls.length - 1;
	}

	public String[][] getMapAsStringArray() {
		String[][] stringArray = new String[arrayWidth][arrayHeight];
		int[][] intArray = getMapAsIntArray();
		for (int x = 0; x < arrayWidth; x++) {
			for (int y = 0; y < arrayHeight; y++) {
				stringArray[x][y] = String.valueOf(intArray[x][y]);
			}
		}
		return stringArray;
	}
}
