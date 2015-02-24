package uk.co.bkrpage.battleshipsi7709331;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);

		final TextView[] txtName = new TextView[10];
		final TextView[] txtScore = new TextView[10];
		final Button btnReset = (Button) findViewById(R.id.btnResetScores);
		
		final HighScoreDB db = new HighScoreDB(this);
		
		ArrayList<Integer> scores = db.getScores();
		ArrayList<String> names = db.getScoreNames();
		
		txtName[0] = (TextView) findViewById(R.id.txtName1);
		txtName[1] = (TextView) findViewById(R.id.txtName2);
		txtName[2] = (TextView) findViewById(R.id.txtName3);
		txtName[3] = (TextView) findViewById(R.id.txtName4);
		txtName[4] = (TextView) findViewById(R.id.txtName5);
		txtName[5] = (TextView) findViewById(R.id.txtName6);
		txtName[6] = (TextView) findViewById(R.id.txtName7);
		txtName[7] = (TextView) findViewById(R.id.txtName8);
		txtName[8] = (TextView) findViewById(R.id.txtName9);
		txtName[9] = (TextView) findViewById(R.id.txtName10);

		txtScore[0] = (TextView) findViewById(R.id.txtScore1);
		txtScore[1] = (TextView) findViewById(R.id.txtScore2);
		txtScore[2] = (TextView) findViewById(R.id.txtScore3);
		txtScore[3] = (TextView) findViewById(R.id.txtScore4);
		txtScore[4] = (TextView) findViewById(R.id.txtScore5);
		txtScore[5] = (TextView) findViewById(R.id.txtScore6);
		txtScore[6] = (TextView) findViewById(R.id.txtScore7);
		txtScore[7] = (TextView) findViewById(R.id.txtScore8);
		txtScore[8] = (TextView) findViewById(R.id.txtScore9);
		txtScore[9] = (TextView) findViewById(R.id.txtScore10);
		
		int positions = 0;
		
		if (txtScore.length >= scores.size()){
			positions = scores.size();
		} else {
			positions = txtScore.length;
		}
		
		for (int i = 0; i < positions; i++){
			txtScore[i].setText(Integer.toString(scores.get(i)));
			txtName[i].setText(names.get(i));
		}
		
		
		btnReset.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				db.resetScores();
				
				Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
				finish();
				startActivity(intent);
			}
		});
	}
	
}
