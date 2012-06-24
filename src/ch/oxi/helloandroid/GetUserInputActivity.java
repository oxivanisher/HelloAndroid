package ch.oxi.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class GetUserInputActivity extends Activity {
	/** Called when the activity is first created. */
	public final static String EXTRA_MESSAGE = "ch.oxi.helloandroid.MESSAGE";

	private Intent textviewIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		System.out.println("onCreate MyFirstActivity");
	}

	/** Called when the user selects the Send button */
	public void sendMessage(View view) {
		System.out.println("-> sendMessage");
		this.textviewIntent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		try {
			new RetreiveJsonTask(this).execute("http://android.oxi.ch/");

			System.out.println("started background process to recieve data...");
			message = "Connecting...";

		} catch (Exception e) {
			message = "ERROR try to start background process to recieve data: "
					+ e;
			System.out.println(message);
		}

		textviewIntent.putExtra(EXTRA_MESSAGE, message);
		startActivity(textviewIntent);
	}

	public Intent getTextviewIntent() {
		return textviewIntent;
	}

	public void setTextviewIntent(Intent textviewIntent) {
		this.textviewIntent = textviewIntent;
	}
}
