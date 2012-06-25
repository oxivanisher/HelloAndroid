package ch.oxi.helloandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

public class LoadingScreenActivity extends Activity {
	private Intent userInputIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		this.userInputIntent = new Intent(this, GetUserInputActivity.class);

		ProgressDialog MyDialog = ProgressDialog.show(
				LoadingScreenActivity.this, "", " Connecting... ", true);

		MyDialog.show();

		System.out.println("onCreate LoadingScreenActivity");

		super.onCreate(savedInstanceState);
		try {

			// FIXME buuh -.-
			// new RetreiveJsonTask(this).execute("http://android.oxi.ch/");
			MyDialog.setMessage("Recieving...");
			startActivity(userInputIntent);
		} catch (Exception e) {
			System.out
					.println("ERROR try to start background process to recieve data: "
							+ e);
			MyDialog.cancel();
		}

		startActivity(userInputIntent);

	}
}