package uk.co.bkrpage.battleshipsi7709331;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;


public class BoardViewShips extends BoardView {

	public BoardViewShips(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		
		float diameter = calcDiam();
		float separator = (float) (diameter * SEPARATOR_RATIO);

		int shipAtPos;

		for (int col = 0; col < bGame.getColumns(); col++) {
			for (int row = 0; row < bGame.getRows(); row++) {
				Paint paint;
				shipAtPos = bGame.getPlayer1Target(col, row);
				if (shipAtPos == 1) {
					paint = getPlayer1Paint();
				} else if (shipAtPos == 2) {
					paint = getPlayer2Paint();
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
	}
	// TODO add ship Placement.
	
	class mListener extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

		private int currentPlayer = 1;
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

			
				if (touchedColumn <= 9 && touchedRow <= 9) { // checks if the player is clicking inside the grid - it crashes if not  here..
					if (bGame.getPlayer1Target(touchedColumn, touchedRow) == 0){
						bGame.shipPlace(touchedColumn, touchedRow, currentPlayer);
					}
				}

			// TODO Add Change of player method in which activity is changed
			
			float ls = separator + (diameter + separator) * touchedColumn; // left
			float ts = separator + (diameter + separator) * touchedRow; // top
			float rs = separator + diameter + (diameter + separator) * touchedColumn; // right
			float bs = separator + diameter + (diameter + separator) * touchedRow; // bottom

			invalidate((int)ls,(int)ts,(int)rs,(int)bs);
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
