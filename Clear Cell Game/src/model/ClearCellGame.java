package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {
	private Random random;
	private int strategy;
	private int rows;
	private int cols;
	private int score;

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		super.setBoardWithColor(BoardCell.EMPTY);
		this.random = random;
		this.strategy = strategy;
		rows = maxRows;
		cols = maxCols;
		score = 0;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		int flag = 0;
		for (int k = 0; k < board[board.length - 1].length; k++) {
			if (board[board.length - 1][k] == BoardCell.EMPTY) {
				flag++;
			}
		}

		return !(flag == board[board.length - 1].length);
	}

	public int getScore() {
		return score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {
		if (!this.isGameOver()) {

			BoardCell[][] newboard = new BoardCell[rows][cols];
			for (int n = 0; n < board[0].length; n++) {
				newboard[0][n] = BoardCell.getNonEmptyRandomBoardCell(random);
			}
			for (int i = 0; i < board.length - 1; i++) {
				for (int j = 0; j < board[i].length; j++) {

					newboard[i + 1][j] = super.board[i][j];
				}
			}
			super.board = newboard;

		}
		;
	}

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {
		if (!this.isGameOver()) {
			BoardCell cellChecker = board[rowIndex][colIndex];
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			// checks the negative half of the ith column
			for (int i = colIndex - 1; i >= 0; --i) {
				if (board[rowIndex][i] == cellChecker) {
					board[rowIndex][i] = BoardCell.EMPTY;
					score++;
				} else {
					break;
				}
			}
			// checks positive half of the ith column
			for (int i = colIndex + 1; i <= board[rowIndex].length - 1; i++) {
				if (board[rowIndex][i] == cellChecker) {
					board[rowIndex][i] = BoardCell.EMPTY;
					score++;
				} else {
					i = board[rowIndex].length;
				}
			}
			// checks negative half of the ith row
			for (int i = rowIndex - 1; i >= 0; --i) {
				if (board[i][colIndex] == cellChecker) {
					board[i][colIndex] = BoardCell.EMPTY;
					score++;
				} else {
					break;
				}
			}
			// checks positive half of the ith row
			for (int i = rowIndex + 1; i <= board.length - 1; i++) {
				if (board[i][colIndex] == cellChecker) {
					board[i][colIndex] = BoardCell.EMPTY;
					score++;
				} else {
					break;
				}
			}
			// checks for top left cell
			int i1 = rowIndex;
			int j1 = colIndex;
			while (i1 > 0 && j1 > 0) {
				if (board[--i1][--j1] == cellChecker) {
					board[i1][j1] = BoardCell.EMPTY;
					score++;
				} else {
					i1 = 0;
					j1 = 0;
				}
			}
			// checks for the bottom right cell
			int i2 = rowIndex;
			int j2 = colIndex;
			while (i2 < board.length - 1 && j2 < board[rowIndex].length - 1) {
				if (board[++i2][++j2] == cellChecker) {
					board[i2][j2] = BoardCell.EMPTY;
					score++;
				} else {
					i2 = board.length;
				}
			}
			// checks for the top right cell
			int i3 = rowIndex;
			int j3 = colIndex;
			while (i3 < board.length - 1 && j3 > 0) {
				if (board[++i3][--j3] == cellChecker) {
					board[i3][j3] = BoardCell.EMPTY;
					score++;
				} else {
					j3 = 0;
					i3 = board.length;
				}
			}
			// checks for the bottom left cell
			int i4 = rowIndex;
			int j4 = colIndex;
			while (i4 > 0 && j4 < board[rowIndex].length - 1) {
				if (board[--i4][++j4] == cellChecker) {
					board[i4][j4] = BoardCell.EMPTY;
					score++;
				} else {
					i4 = 0;
					j4 = board[rowIndex].length;
				}
			}
			cellCollapser();
		}

	}

	public BoardCell[][] cellCollapser() {
		BoardCell[][] newboard = new BoardCell[getMaxRows()][getMaxCols()];
		int i = 0;
		while (i < board.length - 1) {
			boolean isPresent = true;
			for (int k = 0; k < board[i].length; k++) {
				if (board[i][k] != BoardCell.EMPTY) {
					isPresent = false;
				}
			}
			if (isPresent) {
				//checks for empty row
				for (int row = 0; row < i; row++) {
					for (int col = 0; col < board[i].length; col++) {
						newboard[row][col] = board[row][col];
					}
				}
				//empties the empty cells
				for (int rowEdit = i; rowEdit < board.length; rowEdit++) {
					for (int colEdit = 0; colEdit < board[i].length; colEdit++) {
						newboard[rowEdit][colEdit] = BoardCell.EMPTY;
					}
				}
				// adds the next row to the empty row
				for (int newRow = i; newRow < board.length - 1; newRow++) {
					for (int newCol = 0; newCol < board[i].length; newCol++) {
						newboard[newRow][newCol] = board[newRow + 1][newCol];
					}
				}
				board = newboard;

			}
			i++;

		}
		return board;

	}

}
