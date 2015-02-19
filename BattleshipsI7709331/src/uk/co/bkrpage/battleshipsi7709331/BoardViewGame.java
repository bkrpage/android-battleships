package uk.co.bkrpage.battleshipsi7709331;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BoardViewGame extends BoardView {
	
	private boolean shipsSet = false;

	public BoardViewGame(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	
	public void init(){
		super.init();
		
		if (!shipsSet){
			for (int i = 0; i <= 4 ; i++ ){
				if (i <= 1){
					bGame.placeRandomShip(i + 1);
				} else {
					bGame.placeRandomShip(i);
				}
			}
			shipsSet = true;
		}
	}
	
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		
		float diameter = calcDiam();
		float separator = (float) (diameter * SEPARATOR_RATIO);

		int actionAtPos;

		for (int col = 0; col < bGame.getColumns(); col++) {
			for (int row = 0; row < bGame.getRows(); row++) {
				Paint paint;
				actionAtPos = bGame.getPlayer2Grid(col, row);

				// TODO Convert this code into correct functions - basically
				// treating it as one player.
				if (actionAtPos == Game.ACTION_MISS) {
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
	}

	class mListener extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			
			float diameter = calcDiam();
			float separator = (float) (diameter * SEPARATOR_RATIO);

			int touchedColumn;
			int touchedRow;

			float touchX = e.getX();
			float touchY = e.getY();
			
			TextView score = (TextView) findViewById(R.id.score);
			
			touchedColumn = (int) Math.floor(touchX
					/ ((separator + diameter) * Game.DEFAULT_COLUMNS) * 10);
			touchedRow = (int) Math.floor(touchY
					/ ((separator + diameter) * Game.DEFAULT_ROWS) * 10);
			

			if (touchedColumn <= 9 && touchedRow <= 9) { // checks if the player
															// is clicking
															// inside the grid
				if (bGame.getPlayer2Grid(touchedColumn, touchedRow) == Game.ACTION_SHIP) {

					bGame.touchGrid(touchedColumn, touchedRow, Game.ACTION_HIT); // ship
																					// hit
					bGame.addToGameScore(Game.SCORE_HIT);
					score.setText(bGame.getGameScore());

					Toast toast = Toast.makeText(getContext(), "Ship Hit",
							Toast.LENGTH_SHORT);
					toast.show();

				} else if (bGame.getPlayer2Grid(touchedColumn, touchedRow) != Game.ACTION_MISS
						&& bGame.getPlayer2Grid(touchedColumn, touchedRow) != Game.ACTION_HIT) {

					bGame.touchGrid(touchedColumn, touchedRow, Game.ACTION_MISS); // ship

					bGame.addToGameScore(Game.SCORE_MISS);
					score.setText(bGame.getGameScore());
				}
			}

			invalidate();
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
