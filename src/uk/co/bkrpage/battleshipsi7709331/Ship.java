package uk.co.bkrpage.battleshipsi7709331;

public class Ship {
	private int column, row, size;
	private boolean isHoriz;


	/**
	 * Checks if a placement of ship is valid
	 * @param player	The player of the board that is being checked - global static variables set for player values
	 * @return If the ships placement is valid
	 */
	public boolean isValid(int player){
		boolean isValid = true;
		
		for (int i = 0; i <= size; i++) {
			if (player == Game.PLAYER_ONE){ //Check which player grid to compare
				// Check the next position in grid depending on if ship is horizontal or not.
				if ((this.isHoriz && column + i >= 10)
						|| (this.isHoriz && (Game.getPlayer1Grid(column + i,row) == Game.ACTION_SHIP))) {
					
					isValid = false;
				} else if ((!this.isHoriz && row + i >= 10)
						|| (!this.isHoriz && (Game.getPlayer1Grid(column,row + i) == Game.ACTION_SHIP))) {
					isValid = false;
				}
			} 
			else if (player == Game.PLAYER_TWO){
				if ((this.isHoriz && column + i >= 10)
						|| (this.isHoriz && (Game.getPlayer2Grid(column + 1, row) == Game.ACTION_SHIP))) {
					isValid = false;
				} else if ((!this.isHoriz && row + i >= 10)
						|| (!this.isHoriz && (Game.getPlayer2Grid(column, row + 1) == Game.ACTION_SHIP))) {
					isValid = false;
				}
			}
		}
		return isValid;
	}
	
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isHoriz() {
		return isHoriz;
	}
	public void setHoriz(boolean isHoriz) {
		this.isHoriz = isHoriz;
	}
	
	
}
