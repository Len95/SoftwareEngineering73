package se_ex01;

import java.util.ArrayList;

public class Map {

	private int[][] map;
	private int numberOfBoxesX, numberOfBoxesY;
	private int arrayWidth, arrayHeight;
	private DotsNBoxesEngine engine;
	private PlayerList playerlist;
	public final int lastChooseableWall;

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
		for (int y = 0; y < arrayHeight; y++) {
			for (int x = 0; x < arrayWidth; x++) {

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
		System.out
				.println("New Wall:\tx=" + x + "\ty=" + y + "\twallnumber=" + numberOfWalls + "\tsize=" + walls.length);
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
		return walls[xyToWallNumber(x, y)].isOpen;
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
			if (wall.y != arrayHeight) {
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
			if (wall.y != arrayWidth) {
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

		fieldClosed = leftWallClosed && rightWallClosed && upperWallClosed && lowerWallClosed;

		if (fieldClosed) {
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
		for (int i = 0; i < walls.length; i++) {
			if (walls[i] != null && walls[i].x == x_Wall && walls[i].y == y_Wall) {
				System.out.println("Found wall (" + x_Wall + "|" + y_Wall + "):\t" + walls[i]);
				System.out.println("Return " + walls[i].wallNumber);
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
}
