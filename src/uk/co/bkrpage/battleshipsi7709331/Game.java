package uk.co.bkrpage.battleshipsi7709331;

import java.util.Random;


public class Game {
	public static final int DEFAULT_COLUMNS = 10, DEFAULT_ROWS = 10, PLAYERS = 2;
	public static final int ACTION_HIT = 3, ACTION_MISS = 2, ACTION_SHIP = 1;
	public static final int SCORE_HIT = 50, SCORE_MISS = -5;
	public static final int PLAYER_ONE = 0, PLAYER_TWO = 1;
	public static final boolean HORIZONTAL = true, VERTICAL = false;

	private final int setColumns;
	private final int setRows;
	private final int[][] player1Grid;
	private final int[][] player2Grid;
	
	private int shipBlocksSunk[] = new int[2];
	
	private static int shipSize = 0;
	private static boolean shipOrientation = true;
	
	private int[] gameScore = new int[2];
	private String strCurrentPlayer;
	private String strOppositePlayer;
	private int currentPlayer;
	
	private boolean[] shipsSet = new boolean[2];
	
	/**
	 * Creates an empty game object.
	 * @param columns Amount of columns in the game
	 * @param rows Amount of rows in the game.
	 */
	public Game(int columns, int rows) {
		setColumns = columns;
		setRows = rows;
		player1Grid = new int[columns][rows];
		player2Grid = new int[columns][rows];
		gameScore[PLAYER_ONE] = 0;
		gameScore[PLAYER_TWO] = 0;
		
		shipsSet[PLAYER_ONE] = false;
		shipsSet[PLAYER_TWO] = false;
		
		shipBlocksSunk[PLAYER_ONE] = 0;
		shipBlocksSunk[PLAYER_TWO] = 0;
		
		strCurrentPlayer = "Player 1";
		strOppositePlayer = "Player 2";
		
		currentPlayer = PLAYER_ONE;
	}
	
	/**
	 * Checks if a ship placement is valid.
	 * @param column	The x grid co-ordinate of the ship
	 * @param row		The y grid co-ordinate of the ship
	 * @param size		The size (in blocks) of the ship
	 * @param horiz 	Is the ship Horizontal? x and y co-ords are the top left of the ship at all time.
	 * @param player	The player of the board that is being checked.
	 * @return If the ship specified is valid.
	 */
	public boolean isShipValid(int column, int row, int size, boolean horiz, int player){
		boolean valid = true;
		
		for (int i = 0; i <= size; i++) {
			if (player == PLAYER_ONE){ 
				if ((horiz && column + i >= 10)
						|| (horiz && (player1Grid[column + i][row] == ACTION_SHIP))) {
					valid = false;
				} else if ((!horiz && row + i >= 10)
						|| (!horiz && (player1Grid[column][row + i] == ACTION_SHIP))) {
					valid = false;
				}
			} 
			else if (player == PLAYER_TWO){
				if ((horiz && column + i >= 10)
						|| (horiz && (player2Grid[column + i][row] == ACTION_SHIP))) {
					valid = false;
				} else if ((!horiz && row + i >= 10)
						|| (!horiz && (player2Grid[column][row + i] == ACTION_SHIP))) {
					valid = false;
				}
			}
		}
		
		return valid;
	}
	
	/**
	 * Places a ship at the specified position. Uses isShipValid() to check before placement.
	 * @param column	The x grid co-ordinate of the ship
	 * @param row		The y grid co-ordinate of the ship
	 * @param size		The size (in blocks) of the ship
	 * @param horiz 	Is the ship Horizontal? x and y co-ords are the top left of the ship at all time.
	 * @param player	The player of the board that is being checked.
	 * @return If the ship specified is placed.
	 */
	public boolean placeShip(int column, int row, int size, boolean horiz, int player){
		boolean valid = false;
		
		valid = isShipValid(column, row, size, horiz, player);

		for (int i = 0; i <= size; i++) {
			if (player == PLAYER_ONE) {
				if (horiz && valid) {
					player1Grid[column + i][row] = ACTION_SHIP;
				} else if (!horiz && valid) {
					player1Grid[column][row + i] = ACTION_SHIP;
				}
			} else if (player == PLAYER_TWO) {
				if (horiz && valid) {
					player2Grid[column + i][row] = ACTION_SHIP;
				} else if (!horiz && valid) {
					player2Grid[column][row + i] = ACTION_SHIP;
				}
			}
		}
		return valid;
	}
	
	public void placeRandomShip(int size, int player){
		Random rand = new Random();
		
		int randCol = rand.nextInt(10);
		int randRow = rand.nextInt(10);
		boolean randBool = rand.nextBoolean();		
		
		while (!placeShip(randCol, randRow, size, randBool, player)){
			randCol = rand.nextInt(10);
			randRow = rand.nextInt(10);
			randBool = rand.nextBoolean();
		}
			
	}
		
	/**
	 * Places 5 ships of sizes 5, 4, 3, 3, 2  on the specified game board.
	 * 
	 * @param player Should be game variable Game.PLAYER_ONE or Game.PLAYER_TWO This is the Board thatt he ships will be placed on.
	 */
	public void placeAllShips(int player){
		if (!shipsSet[player]) {
			for (int i = 0; i <= 4; i++) {
				if (i <= 1) {
					placeRandomShip(i + 1, player);
				} else {
					placeRandomShip(i, player);
				}
			}
			shipsSet[player] = true;
		}
	}

	// TODO Javadocs
	public boolean touchGridOf(int column, int row, int action, int player) {
		for (int iRow = 0; iRow < setRows; ++iRow) {
			for (int jCol = 0; jCol < setColumns; jCol++) {
				if (player == PLAYER_ONE){
					if (player1Grid[column][row] == 0 || player1Grid[column][row] == ACTION_SHIP) { 
						player1Grid[column][row] = action;
						return true;
					}
				} else if (player == PLAYER_TWO){
					if (player2Grid[column][row] == 0 || player2Grid[column][row] == ACTION_SHIP) { 
						player2Grid[column][row] = action;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void computerPlay(){
		Random rand = new Random();
		
		int column; 
		int row;
		
		column = rand.nextInt(10);
		row = rand.nextInt(10);
		boolean shot = false;
		
		while(!shot){
			
			if (getCurrentPlayerGrid(column, row) == ACTION_SHIP) {
	
				touchGridOf(column, row, ACTION_HIT, getPlayer()); 													
				addToGameScore(SCORE_HIT, getOppositePlayer());
				
				sinkShipBlock(getPlayer());
				shot = true;
	
			} else if (getCurrentPlayerGrid(column, row) != Game.ACTION_MISS
					&& getCurrentPlayerGrid(column, row) != Game.ACTION_HIT) {
	
				touchGridOf(column, row, ACTION_MISS, getPlayer());
				
				addToGameScore(SCORE_MISS, getOppositePlayer());
				shot = true;
			}
			column = rand.nextInt(10);
			row = rand.nextInt(10);
		}
	}
	
	public void sinkShipBlock(int player){
		this.shipBlocksSunk[player]++;
	}
	
	public void addToGameScore(int addition, int player){
		this.gameScore[player] += addition;
	}
	
	public void changePlayerFrom(int player) {

		if (player == PLAYER_ONE) {
			currentPlayer = PLAYER_TWO;
			strOppositePlayer = "Player 1";
			strCurrentPlayer = "Player 2";
		} else {
			currentPlayer = PLAYER_ONE;
			strCurrentPlayer = "Player 1";
			strOppositePlayer = "Player 2";
		}
	}
	
	public void setPlayer(int player){
		currentPlayer = player;
		
		// Set the string values
		if (player == PLAYER_ONE){
			strCurrentPlayer = "Player 1";
		} else if (player == PLAYER_TWO) {
			strCurrentPlayer = "Player 2";
		}
	}
	
	public int getPlayer(){
		return currentPlayer;
	}
	
	public int getOppositePlayer(){
		int oppositePlayer = 0;
		
		if (currentPlayer == PLAYER_ONE){
			oppositePlayer = PLAYER_TWO;
		} else if (currentPlayer == PLAYER_TWO){
			oppositePlayer = PLAYER_ONE;
		}
		
		return oppositePlayer;
	}
	
	public int getColumns() {
		return setColumns;
	}

	public int getRows() {
		return setRows;
	}

	public int getOppositePlayerGrid(int column, int row){
		int grid = 0;
		
		if (currentPlayer == PLAYER_ONE){
			grid = player2Grid[column][row];
		} else if (currentPlayer == PLAYER_TWO){
			grid = player1Grid[column][row];
		}
		
		return grid;
	}
	
	public int getCurrentPlayerGrid(int column, int row){
		int grid = 0;
		
		if (currentPlayer == PLAYER_ONE){
			grid = player1Grid[column][row];
		} else if (currentPlayer == PLAYER_TWO){
			grid = player2Grid[column][row];
		}
		
		return grid;
	}
	
	public int getPlayer1Grid(int column, int row) {
		return player1Grid[column][row];
	}

	public int getPlayer2Grid(int column, int row) {
		return player2Grid[column][row];
	}
	
//	public int getShipCount(int player){
//		return shipCount[player];
//	}
//	
//	public int getShipCountLength(){
//		return getShipCount().length;
//	}
//
//	public int[] getShipCount() {
//		return shipCount;
//	}

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

	public int getGameScore(int player) {
		return gameScore[player];
	}

	public int getShipBlocksSunk(int player) {
		return shipBlocksSunk[player];
	}

	public boolean getShipsSet(int player) {
		return shipsSet[player];
	}

	public String getStrCurrentPlayer() {
		return strCurrentPlayer;
	}
	
	public String getStrOppositePlayer(){
		return strOppositePlayer;
	}

	public void setGameScore(int gameScore, int player) {
		this.gameScore[player] = gameScore;
	}

//	public void setShipCount(int shipCount[]) {
//		this.shipCount = shipCount;
//	}
	
	public void setShipsSet(boolean shipsSet, int player) {
		this.shipsSet[player] = shipsSet;
	}

	public void setStrCurrentPlayer(String strCurrentPlayer) {
		this.strCurrentPlayer = strCurrentPlayer;
	}
	
	
}
