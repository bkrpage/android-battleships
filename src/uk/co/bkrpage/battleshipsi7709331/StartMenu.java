package uk.co.bkrpage.battleshipsi7709331;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.graphics.PorterDuff;

public class StartMenu extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_menu);

		final Button btnPlayComputer = (Button) findViewById(R.id.btnPlayComputer);
		final Button btnLeaderboards = (Button) findViewById(R.id.btnLeaderboards);
		//final Button btnHelp = (Button) findViewById(R.id.btnHelp); TODO
		final Button btnClose = (Button) findViewById(R.id.btnClose);
		
		btnPlayComputer.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
		btnPlayComputer.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				
				Intent intent = new Intent(getApplicationContext(), ShipPlacement.class);
				
				startActivity(intent);
			}
		});

		btnLeaderboards.getBackground().setColorFilter(0xFF0000FF, PorterDuff.Mode.MULTIPLY);
		btnLeaderboards.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				
				Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
				
				startActivity(intent);
			}
		});

		btnClose.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
		btnClose.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				System.exit(0);
			}
		});
	}
	
	
	
	
}
