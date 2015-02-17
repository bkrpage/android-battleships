package uk.co.bkrpage.battleshipsi7709331;

import java.util.Random;

import android.widget.Toast;

public class Game {
	public static final int DEFAULT_COLUMNS = 10;
	public static final int DEFAULT_ROWS = 10;
	public static final int PLAYERS = 2;
	
	public static final int ACTION_HIT = 3;
	public static final int ACTION_MISS = 2;
	public static final int ACTION_SHIP = 1;
	
	public static final boolean HORIZONTAL = true;
	public static final boolean VERTICAL = false;

	private final int bColumns;
	private final int bRows;
	private final int[][] player1Grid;
	private final int[][] player2Grid;
	
	private int shipCount[];
	
	private static int shipSize = 0;
	private static boolean shipOrientation = true;
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

//		player1ShipSet = new boolean[5];
//		player2ShipSet = new boolean[5];
//
//		for(int i = 0; i <= 5; i++){
//			player1ShipSet[i] = false;
//			player2ShipSet[i] = false;
//		}
		
		
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
	
	public void placeRandomShips(){
		Random rand = new Random();
		
		Toast toast = Toast.makeText(getContext(),
				"Ship Placed", Toast.LENGTH_SHORT);
		toast.show();
		
		for (int i = 0; i < 5; i++){
			int randCol = rand.nextInt(9);
			int randRow = rand.nextInt(9);
			boolean randBool = rand.nextBoolean();
			
			if (i > 1){
				if (setShip(randCol, randRow, i + 1, randBool, 2)){
					
				}
			} else {
				setShip(randCol, randRow, i + 2, randBool, 2);
			}
		}
	}
	
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
		
		// TODO move into own method isShipValid()
		if (size > 0){
			valid = true;
			
			valid = isShipValid(column, row, size, horiz, player);

			for (int i = 0; i <= size; i++) {
				if (player == 1) {
					if (horiz && valid) {
						player1Grid[column + i][row] = ACTION_SHIP;
					} else if (!horiz && valid) {
						player1Grid[column][row + i] = ACTION_SHIP;
					}
				} else if (player == 2){
					if (horiz && valid) {
						player2Grid[column + i][row] = ACTION_SHIP;
					} else if (!horiz && valid) {
						player2Grid[column][row + i] = ACTION_SHIP;
					}
				}
			}
		}
		return valid;
	}

	public boolean touchGrid(int column, int row, int action) {
		for (int iRow = 0; iRow < bRows; ++iRow) {
			for (int jCol = 0; jCol < bColumns; jCol++) {
				if (player2Grid[column][row] == 0 || player2Grid[column][row] == ACTION_SHIP) { // TODO Move the ACTION_SHIP side to somewhere else.
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
