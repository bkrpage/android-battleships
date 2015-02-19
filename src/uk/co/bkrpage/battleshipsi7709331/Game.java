package uk.co.bkrpage.battleshipsi7709331;

import java.util.Random;

public class Game {
	public static final int DEFAULT_COLUMNS = 10, DEFAULT_ROWS = 10, PLAYERS = 2;
	
	public static final int ACTION_HIT = 3, ACTION_MISS = 2, ACTION_SHIP = 1;
	
	public static final boolean HORIZONTAL = true, VERTICAL = false;
	
	public static final int SCORE_HIT = 50, SCORE_MISS = -5;

	private final int bColumns;
	private final int bRows;
	private final int[][] player1Grid;
	private final int[][] player2Grid;
	
	private int shipCount[];
	private int[] shipBlocksSunk;
	
	private static int shipSize = 0;
	private static boolean shipOrientation = true;
	
	private int gameScore;
//
//	private static boolean[] player1ShipSet;
//	private static boolean[] player2ShipSet;
		
	//private int currentPlayer;
	//TODO Cannibalise this and make into different classes i.e. ShipPlace.java shots.java
	
	public Game(int columns, int rows, int players) {
		bColumns = columns;
		bRows = rows;
		// TODO Remove this 
		//currentPlayer = 1;
		player1Grid = new int[columns][rows];
		player2Grid = new int[columns][rows];
		shipCount = new int[players];
		gameScore = 0;
		
		
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
	
	
	public void placeRandomShip(int size){
		Random rand = new Random();
		
		int randCol = rand.nextInt(9);
		int randRow = rand.nextInt(9);
		boolean randBool = rand.nextBoolean();		
		
		while (!setShip(randCol, randRow, size, randBool, 2)){
			randCol = rand.nextInt(9);
			randRow = rand.nextInt(9);
			randBool = rand.nextBoolean();
		}
			
	}
	
	/**
	 * @param column	The x grid co-ordinate of the ship
	 * @param row		The y grid co-ordinate of the ship
	 * @param size		The size (in blocks) of the ship
	 * @param horiz 	Is the ship Horizontal? x and y co-ords are the top left of the ship at all time.
	 * @param player	The player of the board that is being checked.
	 */
	public boolean isShipValid(int column, int row, int size, boolean horiz, int player){
		boolean valid = true;
		
		for (int i = 0; i <= size; i++) {
			if (player == 1) 
			{
				if ((horiz && column + i >= 10)
						|| (horiz && (player1Grid[column + i][row] == ACTION_SHIP))) {
					valid = false;
				} else if ((!horiz && row + i >= 10)
						|| (!horiz && (player1Grid[column][row + i] == ACTION_SHIP))) {
					valid = false;
				}
			} 
			else if (player == 2)
			{
				if ((horiz && column + i >= 10)
						|| (horiz && (player2Grid[column + i][row] == ACTION_SHIP))) 
				{
					valid = false;
				} 
				else if ((!horiz && row + i >= 10)
						|| (!horiz && (player2Grid[column][row + i] == ACTION_SHIP))) 
				{
					valid = false;
				}
			}
		}
		
		return valid;
	}
	
	public boolean setShip(int column, int row, int size, boolean horiz, int player){
		boolean valid = false;
		
		valid = isShipValid(column, row, size, horiz, player);

		for (int i = 0; i <= size; i++) {
			if (player == 1) {
				if (horiz && valid) {
					player1Grid[column + i][row] = ACTION_SHIP;
				} else if (!horiz && valid) {
					player1Grid[column][row + i] = ACTION_SHIP;
				}
			} else if (player == 2) {
				if (horiz && valid) {
					player2Grid[column + i][row] = ACTION_SHIP;
				} else if (!horiz && valid) {
					player2Grid[column][row + i] = ACTION_SHIP;
				}
			}
		}
		return valid;
	}

	public boolean touchGrid(int column, int row, int action) {
		for (int iRow = 0; iRow < bRows; ++iRow) {
			for (int jCol = 0; jCol < bColumns; jCol++) {
				if (player2Grid[column][row] == 0 || player2Grid[column][row] == ACTION_SHIP) { 
					player2Grid[column][row] = action;
					return true;
				}
			}
		}
		return false;
	}
	
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

	public int[] getShipCount() {
		return shipCount;
	}

	public void setShipCount(int shipCount[]) {
		this.shipCount = shipCount;
	}

	public int getShipSize() {
		return shipSize;
	}

	public static void setShipSize(int size) {
		shipSize = size;
	}

	public boolean getShipOrientation() {
		return shipOrientation;
	}

	public static void setShipOrientation(boolean orient) {
		shipOrientation= orient;
	}

	public int getGameScore() {
		return gameScore;
	}

	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}
	
	public void addToGameScore(int addition){
		this.gameScore += addition;
	}

//	public boolean[] getPlayer1ShipSet() {
//		return player1ShipSet;
//	}
//
//	public static void setPlayer1ShipSet(int pos, boolean set) {
//		player1ShipSet[pos] = set;
//	}
//
//	public boolean[] getPlayer2ShipSet() {
//		return player2ShipSet;
//	}
//
//	public static void setPlayer2ShipSet(int pos, boolean set) {
//		player2ShipSet[pos] = set;
//	}
	
	
}
