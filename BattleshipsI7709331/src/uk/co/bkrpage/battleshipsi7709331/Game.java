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
	private static int[][] player1Grid;
	private static int[][] player2Grid;
	
	private Ship[][] ships;
	private int shipBlocksSunk[] = {PLAYER_ONE, PLAYER_TWO};
	
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
		
		ships = new Ship[2][5];
		
		for(int i = 0; i < ships.length; i++){
			for (int j = 0; j < ships[i].length ; j++){
				ships[i][j] = new Ship(0,0,0,true); // sets initial values for ship object.		
			}
		}
		
		shipBlocksSunk[PLAYER_ONE] = 0;
		shipBlocksSunk[PLAYER_TWO] = 0;
		
		strCurrentPlayer = "Player 1";
		strOppositePlayer = "Player 2";
		
		currentPlayer = PLAYER_ONE;
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
	public boolean placeShip(Ship ship, int player){
		boolean valid = false;
		
		valid = ship.isValid(player);

		for (int i = 0; i <= ship.getSize(); i++) {
			if (player == PLAYER_ONE) {
				if (ship.isHoriz() && valid) {
					player1Grid[ship.getColumn() + i][ship.getRow()] = ACTION_SHIP;
				} else if (!ship.isHoriz() && valid) {
					player1Grid[ship.getColumn()][ship.getRow() + i] = ACTION_SHIP;
				}
			} else if (player == PLAYER_TWO) {
				if (ship.isHoriz() && valid) {
					player2Grid[ship.getColumn() + i][ship.getRow()] = ACTION_SHIP;
				} else if (!ship.isHoriz() && valid) {
					player2Grid[ship.getColumn()][ship.getRow() + i] = ACTION_SHIP;
				}
			}
		}
		return valid;
	}
	
	/**
	 * Sets the column and row of the specified ship that should already have a 
	 * size set.
	 * @param ship The ship that is to be given a random position
	 * @param player The player of the board to be set on.
	 */
	public void placeRandomShip(Ship ship, int player){
		Random rand = new Random();

		ship.setColumn(rand.nextInt(10));
		ship.setRow(rand.nextInt(10));
		ship.setHoriz(rand.nextBoolean());		
		
		while (!placeShip(ship, player)){ // Re-tries if the placement isn't valid.
			ship.setColumn(rand.nextInt(10));
			ship.setRow(rand.nextInt(10));
			ship.setHoriz(rand.nextBoolean());	
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
				if (i <= 1) { // this will set 0 and 1 to ship sizes 2 and 3
					ships[player][i].setSize(i + 1);;
					placeRandomShip(ships[player][i], player);
				} else { // this sets ship sizes 3 4 5
					ships[player][i].setSize(i);;
					placeRandomShip(ships[player][i], player);
				}
			}
			shipsSet[player] = true;
		}
	}

	/**
	 * To be called on a single play - will only hit empty or ship-taken blocks.
	 * @param column Column co-ordinate of hit
	 * @param row Row co-ordinate of hit
	 * @param action Game.ACTION_HIT or Game.ACTION_MISS
	 * @param player the player of the grid to be hit.
	 * @return Returns true if grid is hit - will only hit empty or ship-taken blocks.
	 */
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
	/**
	 * Plays the computers go - only to be used in single player games.
	 * Will place a shot if valid and only once.
	 */
	public void computerPlay(){
		Random rand = new Random();
		
		int column; 
		int row;
		
		column = rand.nextInt(10);
		row = rand.nextInt(10);
		boolean shot = false;
		
		while(!shot){ // To make sure it  doesn't hit twice
			
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
	
	/**
	 * Adds one to amount of blocks sunk for the specified player
	 * @param player The player to add score to.
	 */
	public void sinkShipBlock(int player){
		this.shipBlocksSunk[player]++;
	}
	
	public void addToGameScore(int addition, int player){
		this.gameScore[player] += addition;
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
	
	public static int getPlayer1Grid(int column, int row) {
		return player1Grid[column][row];
	}

	public static int getPlayer2Grid(int column, int row) {
		return player2Grid[column][row];
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
	
	public void setShipsSet(boolean shipsSet, int player) {
		this.shipsSet[player] = shipsSet;
	}

	public void setStrCurrentPlayer(String strCurrentPlayer) {
		this.strCurrentPlayer = strCurrentPlayer;
	}
	
	
}
