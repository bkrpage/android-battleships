package uk.co.bkrpage.battleshipsi7709331;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartMenu extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_menu);

		final Button btnPlayComputer = (Button) findViewById(R.id.btnPlayComputer);
		
		btnPlayComputer.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				Intent intent = new Intent(getApplicationContext(), ShipPlacement.class);
				
				startActivity(intent);
			}
		});
	}
	
	
	
	
}
