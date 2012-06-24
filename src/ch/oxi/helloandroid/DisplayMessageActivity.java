package ch.oxi.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("onCreate DisplayMessageActivity");
		super.onCreate(savedInstanceState);

		// Get the message from the intent
		Intent intent = getIntent();
		String message = intent.getStringExtra(GetUserInputActivity.EXTRA_MESSAGE);

		// Create the text view
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(message);

		setContentView(textView);
	}
}