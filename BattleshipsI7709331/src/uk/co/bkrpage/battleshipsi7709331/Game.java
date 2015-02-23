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
	
	private int shipCount[];
	private int shipBlocksSunk;
	
	private static int shipSize = 0;
	private static boolean shipOrientation = true;
	
	private int gameScore;
	private static boolean singlePlayer = false;
	private String strCurrentPlayer;
	private int currentPlayer;
	
	private boolean[] shipsSet = new boolean[2];
	
	public Game(int columns, int rows, int players) {
		setColumns = columns;
		setRows = rows;
		player1Grid = new int[columns][rows];
		player2Grid = new int[columns][rows];
		gameScore = 0;
		
		shipsSet[PLAYER_ONE] = false;
		shipsSet[PLAYER_TWO] = false;
		
		shipBlocksSunk = 0;
		
		strCurrentPlayer = "Player 1";
		currentPlayer = PLAYER_ONE;
	}
	
	public void resetGame(){
		for (int i = 0; i < setColumns; i++){
			for (int j = 0; j < setRows; j++){
				player1Grid[i][j] = 0;
				player2Grid[i][j] = 0;
			}
		}

		gameScore = 0;
		shipBlocksSunk = 0;
		
		shipsSet[PLAYER_ONE] = false;
		shipsSet[PLAYER_TWO] = false;
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
			if (player == PLAYER_ONE) 
			{
				if ((horiz && column + i >= 10)
						|| (horiz && (player1Grid[column + i][row] == ACTION_SHIP))) {
					valid = false;
				} else if ((!horiz && row + i >= 10)
						|| (!horiz && (player1Grid[column][row + i] == ACTION_SHIP))) {
					valid = false;
				}
			} 
			else if (player == PLAYER_TWO)
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
		
		while (!setShip(randCol, randRow, size, randBool, player)){
			randCol = rand.nextInt(10);
			randRow = rand.nextInt(10);
			randBool = rand.nextBoolean();
		}
			
	}
		
	/**
	 * Places 5 ships - 5, 4, 3, 3, 2 - on the specified game board.
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
	
	public void sinkShipBlock(){
		this.shipBlocksSunk++;
	}
	
	public void addToGameScore(int addition){
		this.gameScore += addition;
	}
	
	public void changePlayerFrom(int player){
		
		if (player == PLAYER_ONE) {
			currentPlayer = PLAYER_TWO;
			if (singlePlayer){
				strCurrentPlayer = "Computer";
			} else {
				strCurrentPlayer = "Player 2";
			}
		} else {
			currentPlayer = PLAYER_ONE;
			strCurrentPlayer = "Player 1";
		}
	}
	
	public void setPlayer(int player){
		currentPlayer = player;
		
		// Set the string values
		if (player == PLAYER_ONE){
			strCurrentPlayer = "Player 1";
		} else if (player == PLAYER_TWO) {
			// Proper distinction between computer and human player.
			if (singlePlayer){
				strCurrentPlayer = "Computer";
			} else {
				strCurrentPlayer = "Player 2";
			}
		}
	}
	
	public  int getPlayer(){
		return currentPlayer;
	}
	
	public int getColumns() {
		return setColumns;
	}

	public int getRows() {
		return setRows;
	}

	public int getPlayer1Grid(int column, int row) {
		return player1Grid[column][row];
	}

	public int getPlayer2Grid(int column, int row) {
		return player2Grid[column][row];
	}
	
	public int getShipCount(int player){
		return shipCount[player];
	}
	
	public int getShipCountLength(){
		return getShipCount().length;
	}

	public int[] getShipCount() {
		return shipCount;
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

	public int getShipBlocksSunk() {
		return shipBlocksSunk;
	}

	public boolean getShipsSet(int player) {
		return shipsSet[player];
	}

	public boolean isSinglePlayer() {
		return singlePlayer;
	}

	public String getStrCurrentPlayer() {
		return strCurrentPlayer;
	}

	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}

	public void setShipCount(int shipCount[]) {
		this.shipCount = shipCount;
	}

	public void setShipBlocksSunk(int shipBlocksSunk) {
		this.shipBlocksSunk = shipBlocksSunk;
	}
	public void setShipsSet(boolean shipsSet, int player) {
		this.shipsSet[player] = shipsSet;
	}

	public static void setSinglePlayer(boolean bool) {
		singlePlayer = bool;
	}

	public void setStrCurrentPlayer(String strCurrentPlayer) {
		this.strCurrentPlayer = strCurrentPlayer;
	}
	
	
}
