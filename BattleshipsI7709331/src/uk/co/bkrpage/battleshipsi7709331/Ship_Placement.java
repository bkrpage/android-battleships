package uk.co.bkrpage.battleshipsi7709331;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Ship_Placement extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ship_placement);

		final Button btnPlayGame = (Button) findViewById(R.id.btnPlayGame);
//		final Button btnShip1 = (Button) findViewById(R.id.btnShip1);
//		final Button btnShip2 = (Button) findViewById(R.id.btnShip2);
//		final Button btnShip3 = (Button) findViewById(R.id.btnShip3);
//		final Button btnShip4 = (Button) findViewById(R.id.btnShip4);
//		final Button btnShip5 = (Button) findViewById(R.id.btnShip5);
//
//		final Button btnHoriz = (Button) findViewById(R.id.btnHoriz);
//		final Button btnVert = (Button) findViewById(R.id.btnVert);
//		
		btnPlayGame.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(getApplicationContext(), GameActivity.class);
				startActivity(intent);
			}
		});
		
//		btnShip1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Game.setShipSize(4);
//                //Game.setPlayer1ShipSet(0, true);
//            }
//        });
//		
//		btnShip2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Game.setShipSize(3);
//                //Game.setPlayer1ShipSet(1, true);
//            }
//        });
//		
//		btnShip3.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Game.setShipSize(2);
//                //Game.setPlayer1ShipSet(2, true);
//            }
//        });
//		
//		btnShip4.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Game.setShipSize(2);
//                //Game.setPlayer1ShipSet(3, true);
//            }
//        });
//		
//		btnShip5.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Game.setShipSize(1);
//                //Game.setPlayer1ShipSet(4, true);
//            }
//        });
//		
//		btnHoriz.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Game.setShipOrientation(Game.HORIZONTAL);
//            }
//        });
//		
//		btnVert.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Game.setShipOrientation(Game.VERTICAL);
//            }
//        });

	}

}
