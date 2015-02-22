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
	
	public void init(){
		super.init(); 
		
		if (game.getSinglePlayer()){
			game.placeAllShips(Game.PLAYER_ONE);
		} else {
			game.placeAllShips(Game.PLAYER_ONE);
			game.placeAllShips(Game.PLAYER_TWO);
		}
		
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		float diameter = calcDiam();
		float separator = (float) (diameter * SEPARATOR_RATIO);

		int actionAtPos;

		for (int col = 0; col < game.getColumns(); col++) {
			for (int row = 0; row < game.getRows(); row++) {
				Paint paint;
				actionAtPos = game.getPlayer1Grid(col, row);

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

	}

}
