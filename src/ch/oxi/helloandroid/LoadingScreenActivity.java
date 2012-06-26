package ch.oxi.helloandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

public class LoadingScreenActivity extends Activity {
	private Intent userInputIntent;
	private static boolean done = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (!done) {
			this.userInputIntent = new Intent(this, LoadingScreenActivity.class);

			ProgressDialog MyDialog = ProgressDialog.show(
					LoadingScreenActivity.this, "", " Connecting... ", true);

			MyDialog.show();

			System.out.println("onCreate LoadingScreenActivity");

			try {

				// FIXME buuh -.-
				new RetreiveJsonTask(this).execute("http://android.oxi.ch/");
				MyDialog.setMessage("Recieving...");
				// this.startActivity(this.userInputIntent);
			} catch (Exception e) {
				System.out
						.println("ERROR try to start background process to recieve data: "
								+ e);
			}

			MyDialog.cancel();
			LoadingScreenActivity.done = true;
			this.startActivity(this.userInputIntent);
			// try {
			// Thread.sleep(2500);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }

		}
	}
}