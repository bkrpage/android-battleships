package uk.co.bkrpage.battleshipsi7709331;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BoardView extends View {

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public static final double SEPARATOR_RATIO = 0.025;

	Game bGame = new Game(Game.DEFAULT_COLUMNS, Game.DEFAULT_ROWS);

	private Paint bGridPaint;
	private Paint bPlayer1Paint;
	private Paint bPlayer2Paint;
	private Paint bBGPaint;

	// The calculations to find the best dimensions for the grid.
	float calcDiam(){
		float calcX =(float) Math.floor(getWidth()
				/ (Game.DEFAULT_COLUMNS + (Game.DEFAULT_COLUMNS + 1)
						* SEPARATOR_RATIO));
		float calcY =(float) Math.floor(getHeight()
			/ (Game.DEFAULT_ROWS + (Game.DEFAULT_ROWS + 1)
					* SEPARATOR_RATIO));
		float diameter = Math.min(calcX, calcY);
		return diameter;
	}

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

		
		float diameter = calcDiam();
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


}
