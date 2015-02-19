package uk.co.bkrpage.battleshipsi7709331;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
//import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class BoardView extends View {

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}

	public static final double SEPARATOR_RATIO = 0.025;

	protected static Game bGame;

	private Paint gridPaint;
	private Paint shipPaint;
	private Paint missPaint;
	private Paint hitPaint;
	
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

	public void init(){
		
		if(bGame == null){
			 bGame = new Game(Game.DEFAULT_COLUMNS, Game.DEFAULT_ROWS, Game.PLAYERS);
		}

		gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		gridPaint.setStyle(Paint.Style.FILL);
		gridPaint.setColor(Color.WHITE);
		
		shipPaint = new Paint();
		shipPaint.setStyle(Paint.Style.FILL);
		shipPaint.setColor(Color.DKGRAY);
		
		missPaint = new Paint();
		missPaint.setStyle(Paint.Style.FILL);
		missPaint.setColor(0xFF008EBA);
		
		hitPaint = new Paint();
		hitPaint.setStyle(Paint.Style.FILL);
		hitPaint.setColor(Color.GREEN);

		bGPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bGPaint.setStyle(Paint.Style.FILL);
		bGPaint.setColor(0xFF00C3FF);

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

	public Paint getShipPaint() {
		return shipPaint;
	}

	public void setShipPaint(Paint shipPaint) {
		this.shipPaint = shipPaint;
	}

	public Paint getMissPaint() {
		return missPaint;
	}

	public void setMissPaint(Paint missPaint) {
		this.missPaint = missPaint;
	}

	public Paint getHitPaint() {
		return hitPaint;
	}

	public void setHitPaint(Paint hitPaint) {
		this.hitPaint = hitPaint;
	}

	public Paint getbGPaint() {
		return bGPaint;
	}

	public void setbGPaint(Paint bGPaint) {
		this.bGPaint = bGPaint;
	}


}
