package uk.co.bkrpage.battleshipsi7709331;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		BoardView.bGame.placeRandomShips();
	}
}
