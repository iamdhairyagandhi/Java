package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Game;
import model.BoardCell;
import model.ClearCellGame;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {

	/* Remove the following test and add your tests */
	@Test
	public void constructorCheck() {
		int maxRows = 4, maxCols = 5, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
		boolean checker = false;
		if (ccGame.getBoardCell(1, 2) == BoardCell.EMPTY) {
			checker = true;
		}
		assertTrue(checker);
	}

	@Test
	public void processCellCheck() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.processCell(2, 5);
		boolean checker = false;
		if (ccGame.getBoardCell(2, 7) == BoardCell.EMPTY) {
			checker = true;
		}
		assertFalse(checker);

	}

	@Test
	public void getScoreCheck() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.processCell(2, 5);
		int score = ccGame.getScore();
		boolean checker = (score == 18) ? true : false;
		if (ccGame.getBoardCell(2, 7) == BoardCell.EMPTY) {
			checker = true;
		}
		assertTrue(checker);

	}

	@Test
	public void nextAnimationCheck() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.processCell(2, 5);
		ccGame.nextAnimationStep();
		boolean checker = false;
		if (ccGame.getBoardCell(6, 1) == BoardCell.EMPTY) {
			checker = true;
		}
		assertTrue(checker);

	}

	@Test
	public void isGameOver() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.processCell(2, 5);
		ccGame.nextAnimationStep();
		boolean checker = false;
		if (ccGame.getBoardCell(7, 1) == BoardCell.EMPTY) {
			checker = true;
		}
		assertTrue(checker);
		if (ccGame.getBoardCell(7, 2) != BoardCell.EMPTY) {
			checker = true;
		}
		assertTrue(checker);
	}

	@Test
	public void setBoardColorCheck() {
		int maxRows = 7, maxCols = 8, strategy = 2;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
		ccGame.setBoardWithColor(BoardCell.BLUE);
		boolean checker = false;
		if (ccGame.getBoardCell(0, 0) == BoardCell.BLUE) {
			checker = true;
		}
		assertTrue(checker);
	
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		boolean checker2 = false;
		if (ccGame.getBoardCell(1, 0) == BoardCell.YELLOW) {
			checker2 = true;

		}
		assertTrue(checker2);

		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);

		boolean checker3 = false;
		if (ccGame.getBoardCell(1, maxCols - 1) == BoardCell.RED) {
			checker = true;
		}

		assertTrue(checker);
	}

	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
}
