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

	private Game mGame = new Game(Game.DEFAULT_COLUMNS, Game.DEFAULT_ROWS);

	private Paint mGridPaint;
	private Paint mPlayer1Paint;
	private Paint mPlayer2Paint;
	private Paint mBGPaint;

	// The calculations to find the best dimensions for the grid.
	private float diameterX = (float) Math.floor(getWidth()
			/ (Game.DEFAULT_COLUMNS + (Game.DEFAULT_COLUMNS + 1)
					* SEPARATOR_RATIO));
	private float diameterY = (float) Math.floor(getHeight()
			/ (Game.DEFAULT_ROWS + (Game.DEFAULT_ROWS + 1) * SEPARATOR_RATIO));
	private float diameter = Math.min(diameterX, diameterY);
	private float separator = (float) (diameter * SEPARATOR_RATIO);

	private void init() {

		mGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mGridPaint.setStyle(Paint.Style.FILL);
		mGridPaint.setColor(Color.WHITE);

		mPlayer1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPlayer1Paint.setStyle(Paint.Style.FILL);
		mPlayer1Paint.setColor(Color.RED);

		mPlayer2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPlayer2Paint.setStyle(Paint.Style.FILL);
		mPlayer2Paint.setColor(Color.BLUE);

		mBGPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBGPaint.setStyle(Paint.Style.FILL);
		mBGPaint.setColor(Color.GRAY);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int targetAtPos;

		for (int col = 0; col < mGame.getColumns(); col++) {
			for (int row = 0; row < mGame.getRows(); row++) {
				Paint paint;
				targetAtPos = mGame.getTarget(col, row);
				if (targetAtPos == 1) {
					paint = mPlayer1Paint;
				} else if (targetAtPos == 2) {
					paint = mPlayer2Paint;
				} else {
					paint = mBGPaint;
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

			int touchedColumn;
			int touchedRow;

			float touchX = e.getX();
			float touchY = e.getY();

			touchedColumn = (int) Math.floor(touchX
					/ ((separator + diameter) * Game.DEFAULT_COLUMNS) * 10);
			touchedRow = (int) Math.floor(touchY
					/ ((separator + diameter) * Game.DEFAULT_ROWS) * 10);

			if (touchedColumn <= 9 && touchedRow <= 9) { // checks if the player is clicking inside the grid - it crashes if not  here..
				if (mGame.getTarget(touchedColumn, touchedRow) == 0){
					mGame.playTarget(touchedColumn, touchedRow, currentPlayer);

					if (currentPlayer == 1) {
						currentPlayer++;
					} else {
						currentPlayer = 1;
					}
				}

			}

			// TODO Add Change of player method in which activity is changed

			invalidate();
			return false;
		}
	}

	GestureDetector mDetector = new GestureDetector(this.getContext(),
			new mListener());

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		boolean result = mDetector.onTouchEvent(event);
		if (!result) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				result = true;
			}
		}

		// int tokenAtPos = mGame.getToken(column, row);
		return result;
	}

}
