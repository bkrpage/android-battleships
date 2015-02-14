package uk.co.bkrpage.battleshipsi7709331;

public class Game {
	public static final int DEFAULT_COLUMNS = 10;
	public static final int DEFAULT_ROWS = 10;
	public static final int PLAYERS = 2;
	
	public static final int ACTION_HIT = 3;
	public static final int ACTION_MISS = 2;
	public static final int ACTION_SHIP = 1;

	private final int bColumns;
	private final int bRows;
	private final int[][] player1Grid;
	private final int[][] player2Grid;
	
	private int shipCount[];
	
	//private Ship[] ship;
	
	//private int currentPlayer;
	//TODO Cannibalise this and make into different classes i.e. ShipPlace.java shots.java
	
	public Game(int columns, int rows, int players) {
		bColumns = columns;
		bRows = rows;
		// TODO Remove this 
		//currentPlayer = 1;
		player1Grid = new int[columns][rows];
		player2Grid = new int[columns][rows];
		setShipCount(new int[players]);
		
		for (int i = 0; i < getShipCount().length; i++){
			getShipCount()[i] = 4;
		}
	}
	
	public int getColumns() {
		return bColumns;
	}

	public int getRows() {
		return bRows;
	}

	public int getPlayer1Grid(int column, int row) {
		return player1Grid[column][row];
	}

	public int getPlayer2Grid(int column, int row) {
		return player2Grid[column][row];
	}
	
	/**
	 * @param pos	The array position of the ship
	 * @param column		The x grid co-ordinate of the ship
	 * @param row		The y grid co-ordinate of the ship
	 * @param size	The size (in blocks) of the ship
	 * @param horiz Is the ship Horizontal? x and y co-ords are the top left of the ship at all time.
	 */
	
	public boolean setShip(int column, int row, int size, boolean horiz ){
		boolean valid = true;
		
		// TODO move into own method isShipValid()
		for (int i = 0; i <= size; i++){
			if (horiz && (player1Grid[column + i][row] == ACTION_SHIP)){
				valid = false;
			} else if (!horiz && (player1Grid[column][row + i] == ACTION_SHIP)){
				valid = false;
			}
		}
		
		for (int i = 0; i <= size; i++) {
			if (horiz && valid) {
				player1Grid[column + i][row] = ACTION_SHIP;
			} else if (!horiz && valid) {
				player1Grid[column][row + i] = ACTION_SHIP;
			}
		}
		return valid;
	}

	public boolean touchGrid(int column, int row, int action) {
		for (int iRow = 0; iRow < bRows; ++iRow) {
			for (int jCol = 0; jCol < bColumns; jCol++) {
				if (player1Grid[column][row] == 0 || player1Grid[column][row] == ACTION_SHIP) { // TODO Move the ACTION_SHIP side to somewhere else.
					player1Grid[column][row] = action;
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
//	public boolean shipPlace(int column, int row, int action) {
//		for (int iRow = 0; iRow < bRows; ++iRow) {
//			for (int jCol = 0; jCol < bColumns; jCol++) {
//				if (player1Grid[column][row] == 0) {
//					player1Grid[column][row] = action;
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
//	public void changePlayerFrom(int player){
//		
//		if (player == 1) {
//			player++;
//		} else {
//			player = 1;
//		}
//		
//		//setPlayer(player);
//	}
	
//	public void setPlayer(int player){
//		currentPlayer = player;
//	}
//	public static int getPlayer(){
//		return currentPlayer;
//	};
	
	public int getShipCount(int player){
		return shipCount[player];
	}
	
	public int getShipCountLength(){
		return getShipCount().length;
	}
//	
//	public void setShipCount(int i){
//		shipCount = i;
//	}

	public int[] getShipCount() {
		return shipCount;
	}

	public void setShipCount(int shipCount[]) {
		this.shipCount = shipCount;
	}
	
	//public void setShipPos(int row, int col){
	//	ship[shipCount] = player; 
	//}
	
	
}
