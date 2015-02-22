package uk.co.bkrpage.battleshipsi7709331;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class BoardViewGame extends BoardView{
	
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
					game.placeRandomShip(i + 1, 2);
				} else {
					game.placeRandomShip(i, 2);
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

		for (int col = 0; col < game.getColumns(); col++) {
			for (int row = 0; row < game.getRows(); row++) {
				Paint paint;
				actionAtPos = game.getPlayer2Grid(col, row);

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
		
		canvas.drawText("Your score is: " + game.getGameScore(), 15, (separator+diameter)* 10 + 25, getTextPaint());
		
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
			
			touchedColumn = (int) Math.floor(touchX
					/ ((separator + diameter) * Game.DEFAULT_COLUMNS) * 10);
			touchedRow = (int) Math.floor(touchY
					/ ((separator + diameter) * Game.DEFAULT_ROWS) * 10);
			

			if (touchedColumn <= 9 && touchedRow <= 9) { // checks if the player
															// is clicking
															// inside the grid
				if (game.getPlayer2Grid(touchedColumn, touchedRow) == Game.ACTION_SHIP) {

					game.touchGridOf(touchedColumn, touchedRow, Game.ACTION_HIT, Game.PLAYER_TWO); // ship
																					// hit
					game.addToGameScore(Game.SCORE_HIT);
					game.sinkShipBlock();

					if (game.getShipBlocksSunk() == 17) {
						Toast toast = Toast.makeText(getContext(), "You have won this game with a score of " + game.getGameScore(), Toast.LENGTH_LONG);
						toast.show();
						
						game.resetGame();
						
						showRestart();
						
					} else {
						Toast toast = Toast.makeText(getContext(), "Ship Hit", Toast.LENGTH_SHORT);
						toast.show();
					}

				} else if (game.getPlayer2Grid(touchedColumn, touchedRow) != Game.ACTION_MISS
						&& game.getPlayer2Grid(touchedColumn, touchedRow) != Game.ACTION_HIT) {

					game.touchGridOf(touchedColumn, touchedRow, Game.ACTION_MISS, Game.PLAYER_TWO); // ship

					game.addToGameScore(Game.SCORE_MISS);
				}
			}

			invalidate();
			return false;
		}
	}
	

	public void showRestart(){

		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setMessage("You won!").setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	Intent intent = new Intent(getContext(),
						ShipPlacement.class);
				
	            getContext().startActivity(intent);
            }
        });

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
