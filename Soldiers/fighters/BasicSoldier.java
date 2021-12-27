package fighters;

import framework.BattleField;
import framework.Random131;

public class BasicSoldier {

	// Initial Attributes
	public final static int INITIAL_HEALTH = 25;
	public final static int ARMOR = 25;
	public final static int STRENGTH = 25;
	public final static int SKILL = 25;

	// Symbolic Constants
	public final static int UP = 0;
	public final static int RIGHT = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int UP_AND_RIGHT = 4;
	public final static int DOWN_AND_RIGHT = 5;
	public final static int DOWN_AND_LEFT = 6;
	public final static int UP_AND_LEFT = 7;
	public final static int NEUTRAL = -1;

	// Instance Variables
	public final BattleField grid;
	public int row, col;
	public int health;
	public final int team;

	// Constructor that initializes all the variables
	public BasicSoldier(BattleField gridIn, int teamIn,
						int rowIn, int colIn) {
		grid = gridIn;
		health = INITIAL_HEALTH;
		row = rowIn;
		col = colIn;
		team = teamIn;
	}

	// movement method that returns a boolean value and checks whether the 
	//soldier can move up
	public boolean moveUp() {

		if (grid.get(row + 1, col) == BattleField.OUT_OF_BOUNDS || 
				grid.get(row + 1, col) == BattleField.OBSTACLE
				|| grid.get(row + 1, col) == enemyTeam() 
				|| grid.get(row + 1, col) == team) {
			return false;
		} else {
			return true;
		}
	}

	// movement method that returns a boolean value and checks whether the 
	// soldier can move down
	public boolean moveDown() {

		if (grid.get(row - 1, col) == BattleField.OUT_OF_BOUNDS || 
				grid.get(row - 1, col) == BattleField.OBSTACLE
				|| grid.get(row - 1, col) == enemyTeam() 
				|| grid.get(row - 1, col) == team) {
			return false;
		} else {
			return true;
		}
	}

	// movement method that returns a boolean value and checks whether the 
	// soldier can move left
	public boolean moveLeft() {

		if (grid.get(row, col - 1) == BattleField.OUT_OF_BOUNDS || 
				grid.get(row, col - 1) == BattleField.OBSTACLE
				|| grid.get(row, col - 1) == enemyTeam() 
				|| grid.get(row, col - 1) == team) {
			return false;
		} else {
			return true;
		}
	}

	// movement method that returns a boolean value and checks whether the 
	// soldier can move right
	public boolean moveRight() {

		if (grid.get(row, col + 1) == BattleField.OUT_OF_BOUNDS || 
				grid.get(row, col + 1) == BattleField.OBSTACLE
				|| grid.get(row, col + 1) == enemyTeam() 
				|| grid.get(row, col + 1) == team) {
			return false;
		} else {
			return true;
		}
	}

	// Method returns true if it is possible for the soldier to move
	public boolean canMove() {
		int above = grid.get(row - 1, col); // moving up
		int below = grid.get(row + 1, col); // moving down
		int right = grid.get(row, col + 1); // moving right
		int left = grid.get(row, col - 1); // moving left
		if (above == BattleField.EMPTY || below == BattleField.EMPTY 
				|| right == BattleField.EMPTY || left == BattleField.EMPTY) {
			return true;
		} else {
			return false;
		}
	}

	// Method that identifies and returns enemy team
	public int enemyTeam() {
		int enemyTeam;
		if (team == BattleField.RED_TEAM) {
			enemyTeam = BattleField.BLUE_TEAM;
		} else {
			enemyTeam = BattleField.RED_TEAM;
		}
		return enemyTeam;
	}

	// Method that returns number of enemies remaining in the battlefield
	public int numberOfEnemiesRemaining() {
		int enemyRemaining = 0;
		// nested for loop that goes through the grid to check for enemy 
		// teammates
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				if (grid.get(i, j) == enemyTeam()) {
					// increments when enemy found on the grid
					enemyRemaining++; 
				}
			}
		}
		return enemyRemaining;
	}

	// Method that calculates and returns the moves it takes to 
	// reach a certain position on the grid
	public int getDistance(int destinationRow, int destinationCol) {
		int VERTICAL_MOVES = Math.abs(row - destinationRow);
		int HORIZONTAL_MOVES = Math.abs(col - destinationCol);
		int moves = VERTICAL_MOVES + HORIZONTAL_MOVES;
		return moves;
	}

	// Method evaluates the direction the soldier is required 
	// to go in order to reach the given destination
	public int getDirection(int destinationRow, int destinationCol) {
		if (row > destinationRow && col == destinationCol) {
			return UP;
		} else if (row < destinationRow && col == destinationCol) {
			return DOWN;
		} else if (row == destinationRow && col > destinationCol) {
			return LEFT;
		} else if (row == destinationRow && col < destinationCol) {
			return RIGHT;
		} else if (row > destinationRow && col < destinationCol) {
			return UP_AND_RIGHT;
		} else if (row < destinationRow && col < destinationCol) {
			return DOWN_AND_RIGHT;
		} else if (row > destinationRow && col > destinationCol) {
			return UP_AND_LEFT;
		} else if (row < destinationRow && col > destinationCol) {
			return DOWN_AND_LEFT;
		} else {
			return NEUTRAL;
		}
	}

	// Method returns the direction of the nearest teammate
	public int getDirectionOfNearestFriend() {
		int distance;
		int shortDist = grid.getRows() * grid.getCols();
		int nearFriend = NEUTRAL;
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				if (getDistance(i, j) > 0 && grid.get(i, j) == team) {
					distance = getDistance(i, j);
					if (distance <= shortDist) {
						shortDist = distance;
						nearFriend = getDirection(i, j);
					}
				}
			}

		}
		return nearFriend;
	}

	// Method that counts and returns the number of 
	// teammates within the given radius
	public int countNearbyFriends(int radius) {
		int counter = 0;
		int distance = 0;
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				if (getDistance(i, j) > 0 && grid.get(i, j) == team) {
					distance = getDistance(i, j);
					if (distance <= radius) {
						counter++;
					}
				}
			}

		}
		return counter;
	}

	// Method that counts and returns the number of enemies 
	// within the given radius
	public int getDirectionOfNearestEnemy(int radius) {
		int distance=0;
		int shortDist = grid.getRows() * grid.getCols();
		int nearEnemy=NEUTRAL;
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				if (getDistance(i, j) <= radius && 
						grid.get(i, j) == enemyTeam()) {
					distance = getDistance(i, j);
					if (distance <= shortDist) {
						shortDist = distance;
						nearEnemy = getDirection(i, j);
					}
				} 
				}

			}
		return nearEnemy;
	}

	// the heart of the program, the AI of the program. 
	// The programmed soldier will follow the set of 
	// instructions mentioned here
	public void performMyTurn() {
		if (grid.get(row - 1, col) == enemyTeam()) {
			grid.attack(row - 1, col);
		} else if (grid.get(row + 1, col) == enemyTeam()) {
			grid.attack(row + 1, col);
		} else if (grid.get(row, col - 1) == enemyTeam()) {
			grid.attack(row, col - 1);
		} else if (grid.get(row, col + 1) == enemyTeam()) {
			grid.attack(row, col + 1);
		} else if (grid.get(row + 1, col) == BattleField.OUT_OF_BOUNDS 
				|| grid.get(row + 1, col) == BattleField.OBSTACLE
				|| grid.get(row + 1, col) == enemyTeam() 
				|| grid.get(row + 1, col) == team) {
			row--;
		} else if (grid.get(row - 1, col) == BattleField.OUT_OF_BOUNDS 
				|| grid.get(row - 1, col) == BattleField.OBSTACLE
				|| grid.get(row - 1, col) == enemyTeam() 
				|| grid.get(row - 1, col) == team) {
			row++;
		} else if (grid.get(row, col - 1) == BattleField.OUT_OF_BOUNDS 
				|| grid.get(row, col - 1) == BattleField.OBSTACLE
				|| grid.get(row, col - 1) == enemyTeam() 
				|| grid.get(row, col - 1) == team) {
			col--;
		} else if (grid.get(row, col + 1) == BattleField.OUT_OF_BOUNDS 
				|| grid.get(row, col + 1) == BattleField.OBSTACLE
				|| grid.get(row, col + 1) == enemyTeam() 
				|| grid.get(row, col + 1) == team) {
			col++;
		}
	}
}
