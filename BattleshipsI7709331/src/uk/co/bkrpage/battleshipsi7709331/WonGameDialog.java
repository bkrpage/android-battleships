package uk.co.bkrpage.battleshipsi7709331;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class WonGameDialog extends DialogFragment{
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setMessage(R.string.dialog_won_game);
		
		builder.setPositiveButton(R.string.restart,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(getActivity(),
								Ship_Placement.class);
						
						startActivity(intent);
					}
				});


		//AlertDialog dialog = builder.create();
		
		return builder.create();

	}
}
