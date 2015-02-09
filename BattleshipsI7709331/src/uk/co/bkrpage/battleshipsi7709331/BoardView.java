package uk.co.bkrpage.battleshipsi7709331;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View {

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public static final double SEPARATOR_RATIO = 0.025;

	private Game bGame = new Game(Game.DEFAULT_COLUMNS, Game.DEFAULT_ROWS);

	private Paint bGridPaint;
	private Paint bPlayer1Paint;
	private Paint bPlayer2Paint;
	private Paint bBGPaint;

	// The calculations to find the best dimensions for the grid.

	private void init() {

		bGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bGridPaint.setStyle(Paint.Style.FILL);
		bGridPaint.setColor(Color.WHITE);

		bPlayer1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bPlayer1Paint.setStyle(Paint.Style.FILL);
		bPlayer1Paint.setColor(Color.RED);

		bPlayer2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bPlayer2Paint.setStyle(Paint.Style.FILL);
		bPlayer2Paint.setColor(Color.BLUE);

		bBGPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bBGPaint.setStyle(Paint.Style.FILL);
		bBGPaint.setColor(Color.GRAY);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		float diameterX = (float) Math.floor(getWidth()
				/ (Game.DEFAULT_COLUMNS + (Game.DEFAULT_COLUMNS + 1)
						* SEPARATOR_RATIO));
		float diameterY = (float) Math.floor(getHeight()
				/ (Game.DEFAULT_ROWS + (Game.DEFAULT_ROWS + 1) * SEPARATOR_RATIO));
		float diameter = Math.min(diameterX, diameterY);
		float separator = (float) (diameter * SEPARATOR_RATIO);

		int targetAtPos;

		for (int col = 0; col < bGame.getColumns(); col++) {
			for (int row = 0; row < bGame.getRows(); row++) {
				Paint paint;
				targetAtPos = bGame.getTarget(col, row);
				if (targetAtPos == 1) {
					paint = bPlayer1Paint;
				} else if (targetAtPos == 2) {
					paint = bPlayer2Paint;
				} else {
					paint = bBGPaint;
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

		private int currentPlayer = 1;

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {

			float diameterX = (float) Math.floor(getWidth()
					/ (Game.DEFAULT_COLUMNS + (Game.DEFAULT_COLUMNS + 1)
							* SEPARATOR_RATIO));
			float diameterY = (float) Math.floor(getHeight()
					/ (Game.DEFAULT_ROWS + (Game.DEFAULT_ROWS + 1) * SEPARATOR_RATIO));
			float diameter = Math.min(diameterX, diameterY);
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
				if (bGame.getTarget(touchedColumn, touchedRow) == 0){
					bGame.playTarget(touchedColumn, touchedRow, currentPlayer);
					
					currentPlayer = bGame.changePlayer(currentPlayer);
				}

			}

			// TODO Add Change of player method in which activity is changed

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
