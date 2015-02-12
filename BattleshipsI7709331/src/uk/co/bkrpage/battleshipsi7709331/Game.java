package uk.co.bkrpage.battleshipsi7709331;

public class Game {
	public static final int DEFAULT_COLUMNS = 10;
	public static final int DEFAULT_ROWS = 10;
	public static final int PLAYERS = 2;

	private final int bColumns;
	private final int bRows;
	private final int[][] player1Grid;
	private final int[][] player2Grid;
	
	int shipCount[];
	
	private int currentPlayer;
	//TODO Cannibalise this and make into different classes i.e. ShipPlace.java shots.java
	
	public Game(int columns, int rows, int players) {
		bColumns = columns;
		bRows = rows;
		// TODO Remove this 
		currentPlayer = 1;
		player1Grid = new int[columns][rows];
		player2Grid = new int[columns][rows];
		shipCount = new int[players];
	}
	
	public int getColumns() {
		return bColumns;
	}

	public int getRows() {
		return bRows;
	}
	
	public void initPlayers(){
		for (int i = 0; i < shipCount.length; i++){
			shipCount[i] = 4;
		}
	};

	public int getPlayer1Target(int column, int row) {
		return player1Grid[column][row];
	}

	public int getPlayer2Target(int column, int row) {
		return player2Grid[column][row];
	}

	public boolean playTarget(int column, int row, int player) {
		if (player <= 0) {
			throw new IllegalArgumentException("Player Numbers start with 1");
		}
		for (int iRow = 0; iRow < bRows; ++iRow) {
			for (int jCol = 0; jCol < bColumns; jCol++) {
				if (player1Grid[column][row] == 0) {
					player1Grid[column][row] = player;
					return true;
				}
			}
		}
		return false;
	}

//	public int getShip(int column, int row) {
//		return bShips[column][row];
//	}
//	
	public boolean shipPlace(int column, int row, int player) {
		if (player <= 0) {
			throw new IllegalArgumentException("Player Numbers start with 1");
		}
		for (int iRow = 0; iRow < bRows; ++iRow) {
			for (int jCol = 0; jCol < bColumns; jCol++) {
				if (player1Grid[column][row] == 0) {
					player1Grid[column][row] = player;
					return true;
				}
			}
		}
		return false;
	}
	
	public void changePlayerFrom(int player){
		
		if (player == 1) {
			player++;
		} else {
			player = 1;
		}
		
		setPlayer(player);
	}
	
	public void setPlayer(int player){
		currentPlayer = player;
	}
	public int getPlayer(){
		return currentPlayer;
	};
	
//	public int getShipCount(int i){
//		return shipCount;
//	}
//	
//	public void setShipCount(int i){
//		shipCount = i;
//	}
	
	//public void setShipPos(int row, int col){
	//	ship[shipCount] = player; 
	//}
}
