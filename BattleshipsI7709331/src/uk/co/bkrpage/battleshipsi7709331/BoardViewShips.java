package uk.co.bkrpage.battleshipsi7709331;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class BoardViewShips extends BoardView {

	public BoardViewShips(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		float diameter = calcDiam();
		float separator = (float) (diameter * SEPARATOR_RATIO);

		int actionAtPos;

		for (int col = 0; col < bGame.getColumns(); col++) {
			for (int row = 0; row < bGame.getRows(); row++) {
				Paint paint;
				actionAtPos = bGame.getPlayer1Grid(col, row);

				// TODO Convert this code into correct functions - basically
				// treating it as one player.
				if (actionAtPos == Game.ACTION_SHIP) {
					paint = getShipPaint();
				} else if (actionAtPos == Game.ACTION_MISS) {
					paint = getMissPaint();
				} else if (actionAtPos == Game.ACTION_HIT) {
					paint = getHitPaint();
				} else {
					paint = getBGPaint();
				}

				float ls = separator + (diameter + separator) * col; // left
																		// Coordinate
				float ts = separator + (diameter + separator) * row; // top
																		// coordinate
				float rs = separator + diameter + (diameter + separator) * col; // right
																				// coordinate
				float bs = separator + diameter + (diameter + separator) * row; // bottom
																				// coordinate

				canvas.drawRect(ls, ts, rs, bs, paint);
			}
		}

		//String strShipCount = Integer.toString(bGame.getShipCount()[0] + 1); 
		// TODO Make array position dependent on the current player.

		//canvas.drawText("Battleships to place: " + strShipCount, 15,
				//((separator + diameter) * 10) + 30, textPaint);
	}

	// TODO add ship Placement.

	class mListener extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

		//private int currentPlayer = 1;
		
		//private int[] action = {1,2,3};

		// TODO Move change player stuff to Game.java

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {

			float diameter = calcDiam();
			float separator = (float) (diameter * SEPARATOR_RATIO);

			int touchedColumn;
			int touchedRow;

			float touchX = e.getX();
			float touchY = e.getY();

			touchedColumn = (int) Math.floor(touchX
					/ ((separator + diameter) * Game.DEFAULT_COLUMNS) * 10);
			touchedRow = (int) Math.floor(touchY
					/ ((separator + diameter) * Game.DEFAULT_ROWS) * 10);
			

			if (touchedColumn <= 9 && touchedRow <= 9) { // checks if the player
															// is clicking
															// inside the grid
				if (bGame.getShipCount()[0] >= 0) {
					if (bGame.getPlayer1Grid(touchedColumn, touchedRow) != Game.ACTION_SHIP) {
						// This code below to be replaced with setship 
						//bGame.touchGrid(touchedColumn, touchedRow, Game.ACTION_SHIP);
												
						if (bGame.setShip(touchedColumn, touchedRow, bGame.getShipSize(), bGame.getShipOrientation())) {
							bGame.getShipCount()[0]--;

							Toast toast = Toast.makeText(getContext(),
									"Ship Placed", Toast.LENGTH_SHORT);
							toast.show();
						} else {
							Toast toast = Toast.makeText(getContext(),
									"Invalid ship placement", Toast.LENGTH_SHORT);
							toast.show();
						}
					}
				} else {
					if (bGame.getPlayer1Grid(touchedColumn, touchedRow) == Game.ACTION_SHIP){
						
						bGame.touchGrid(touchedColumn, touchedRow, Game.ACTION_HIT); //ship hit
						
						Toast toast = Toast.makeText(getContext(), "Ship Hit", Toast.LENGTH_SHORT);
						toast.show();
						
					} else if (bGame.getPlayer1Grid(touchedColumn, touchedRow) != Game.ACTION_MISS 
							&& bGame.getPlayer1Grid(touchedColumn, touchedRow) != Game.ACTION_HIT) {
						
								bGame.touchGrid(touchedColumn, touchedRow, Game.ACTION_MISS); //ship missed
					}
				}
				
			}
			
			

			// TODO Add Change of player method in which activity is changed
//			float ls = separator + (diameter + separator) * touchedColumn; // left
//			float ts = separator + (diameter + separator) * touchedRow; // top
//			float rs = separator + diameter + (diameter + separator)
//					* touchedColumn; // right
//			float bs = separator + diameter + (diameter + separator)
//					* touchedRow; // bottom
			
			// make it stop if count is at 0;
			
			invalidate();
			//invalidate((int) ls, (int) ts, (int) rs, (int) bs);
			return false;
		}
	}

	GestureDetector bDetector = new GestureDetector(this.getContext(),
			new mListener());

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		boolean result = bDetector.onTouchEvent(event);
		if (!result) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				result = true;
			}
		}
		return result;
	}

}
