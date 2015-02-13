package uk.co.bkrpage.battleshipsi7709331;

public class Ship {
	
	private int row;
	private int col;
	private int size = 1;
	private boolean horiz = true;
	
	// These are final variables for the ship orientation.
	public static final boolean HORIZONTAL = true;
	public static final boolean VERTICAL = false;

	public Ship(int x, int y, int size , boolean horiz){
		x = this.row;
		y = this.col;
		size = this.size;
		horiz = this.horiz;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isHoriz() {
		return horiz;
	}

	public void setHoriz(boolean horiz) {
		this.horiz = horiz;
	}
	
	
	
}
