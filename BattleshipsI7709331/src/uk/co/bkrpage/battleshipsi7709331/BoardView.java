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

	private Paint gridPaint;
	private Paint player1Paint;
	private Paint player2Paint;
	private Paint bGPaint;

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

		gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		gridPaint.setStyle(Paint.Style.FILL);
		gridPaint.setColor(Color.WHITE);

		player1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		player1Paint.setStyle(Paint.Style.FILL);
		player1Paint.setColor(Color.RED);

		player2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		player2Paint.setStyle(Paint.Style.FILL);
		player2Paint.setColor(Color.BLUE);

		bGPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bGPaint.setStyle(Paint.Style.FILL);
		bGPaint.setColor(Color.GRAY);

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
					paint = player1Paint;
				} else if (targetAtPos == 2) {
					paint = player2Paint;
				} else {
					paint = bGPaint;
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

	public Paint getGridPaint() {
		return gridPaint;
	}

	public void setGridPaint(Paint gridPaint) {
		this.gridPaint = gridPaint;
	}

	public Paint getPlayer1Paint() {
		return player1Paint;
	}

	public void setPlayer1Paint(Paint player1Paint) {
		this.player1Paint = player1Paint;
	}

	public Paint getPlayer2Paint() {
		return player2Paint;
	}

	public void setPlayer2Paint(Paint player2Paint) {
		this.player2Paint = player2Paint;
	}

	public Paint getBGPaint() {
		return bGPaint;
	}

	public void setBGPaint(Paint bGPaint) {
		this.bGPaint = bGPaint;
	}


}
