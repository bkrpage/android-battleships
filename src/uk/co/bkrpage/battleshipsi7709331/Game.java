package uk.co.bkrpage.battleshipsi7709331;

public class Game {
	public static final int DEFAULT_COLUMNS = 10;
	public static final int DEFAULT_ROWS = 10;

	private final int bColumns;
	private final int bRows;
	private final int[][] bData;
	
	public Game(int columns, int rows) {
		bColumns = columns;
		bRows = rows;
		bData = new int[columns][rows];
	}
	
	public int getColumns() {
		return bColumns;
	}

	public int getRows() {
		return bRows;
	}

	public int getTarget(int column, int row) {
		return bData[column][row];
	}

	public boolean playTarget(int column, int row, int player) {
		if (player <= 0) {
			throw new IllegalArgumentException("Player Numbers start with 1");
		}
		for (int iRow = 0; iRow < bRows; ++iRow) {
			for (int jCol = 0; jCol < bColumns; jCol++) {
				if (bData[column][row] == 0) {
					bData[column][row] = player;
					return true;
				}
			}
		}
		return false;
	}
}
