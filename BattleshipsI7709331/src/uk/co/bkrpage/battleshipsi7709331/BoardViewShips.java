package uk.co.bkrpage.battleshipsi7709331;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class BoardViewShips extends BoardView {

	public BoardViewShips(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	// I need to add on the ship placement so that it only starts in 
	// this view only and not for every subclass of BoardView
	@Override
	public void init(){
		super.init(); 

		game.placeAllShips(Game.PLAYER_ONE);
		game.placeAllShips(Game.PLAYER_TWO);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		float diameter = calcDiam();
		float separator = (float) (diameter * SEPARATOR_RATIO);

		int actionAtPos;

		for (int col = 0; col < game.getColumns(); col++) {
			for (int row = 0; row < game.getRows(); row++) {
				Paint paint;
				actionAtPos = game.getPlayer1Grid(col, row);

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
				float ts = separator + (diameter + separator) * row; // top
				float rs = separator + diameter + (diameter + separator) * col; // right
				float bs = separator + diameter + (diameter + separator) * row; // bottom

				canvas.drawRect(ls, ts, rs, bs, paint);
			}
		}

	}

}
