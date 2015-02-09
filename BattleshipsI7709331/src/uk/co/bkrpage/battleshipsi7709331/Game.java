package uk.co.bkrpage.battleshipsi7709331;

public class Game {
	public static final int DEFAULT_COLUMNS = 10;
	public static final int DEFAULT_ROWS = 10;

	private final int bColumns;
	private final int bRows;
	private final int[][] bShips;
	private final int[][] bShots;
	
	//TODO Cannibalise this and make into different classes i.e. ShipPlace.java shots.java
	
	public Game(int columns, int rows) {
		bColumns = columns;
		bRows = rows;
		bShips = new int[columns][rows];
		bShots = new int[columns][rows];
	}
	
	public int getColumns() {
		return bColumns;
	}

	public int getRows() {
		return bRows;
	}

	public int getTarget(int column, int row) {
		return bShots[column][row];
	}

	public boolean playTarget(int column, int row, int player) {
		if (player <= 0) {
			throw new IllegalArgumentException("Player Numbers start with 1");
		}
		for (int iRow = 0; iRow < bRows; ++iRow) {
			for (int jCol = 0; jCol < bColumns; jCol++) {
				if (bShots[column][row] == 0) {
					bShots[column][row] = player;
					return true;
				}
			}
		}
		return false;
	}

	public int getShip(int column, int row) {
		return bShips[column][row];
	}
	
	public boolean shipPlace(int column, int row, int player) {
		if (player <= 0) {
			throw new IllegalArgumentException("Player Numbers start with 1");
		}
		for (int iRow = 0; iRow < bRows; ++iRow) {
			for (int jCol = 0; jCol < bColumns; jCol++) {
				if (bShips[column][row] == 0) {
					bShips[column][row] = player;
					return true;
				}
			}
		}
		return false;
	}
	
	public int changePlayer(int currentPlayer){
		
		if (currentPlayer == 1) {
			currentPlayer++;
		} else {
			currentPlayer = 1;
		}
		
		return currentPlayer;
	}
}
