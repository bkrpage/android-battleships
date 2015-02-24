package uk.co.bkrpage.battleshipsi7709331;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ScoreView extends View{
	
	HighScoreDB dbScores = new HighScoreDB(getContext());

	ArrayList<Integer> names = dbScores.getScores();
	
	public ScoreView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private Paint textPaint;
	
	public void init(){
		
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(0xFF000000);
		textPaint.setTextSize(25);
	}
	
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
//		int entryToDisplay;
//		
//		if (names.size() < 9){
//			entryToDisplay = names.size();
//		} else {
//			entryToDisplay = 9;
//		}
		
		int count = 1;
		for(Integer name : names){
			String number = Integer.toString(count + 1);
			
			canvas.drawText(number + ". " + name, 50, 50 * count, textPaint);
		}
		
//		for (int i = 0; i <= names.size(); i++){
//			String number = Integer.toString(i + 1);
//			
//			canvas.drawText(number + ". " + names.get(i), 50, 50 * i, textPaint);
//		}
	}

}
