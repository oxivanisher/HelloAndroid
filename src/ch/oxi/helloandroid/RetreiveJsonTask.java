package ch.oxi.helloandroid;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

class RetreiveJsonTask extends AsyncTask<String, Void, String> {

	private final GetUserInputActivity getUserInputActivity;

	public RetreiveJsonTask(GetUserInputActivity getUserInputActivity) {
		this.getUserInputActivity = getUserInputActivity;
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			System.out.println("starting async task!");

			URL url = new URL(params[0]);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			try {
				urlConnection.setDoOutput(true);
				urlConnection.setChunkedStreamingMode(0);
				urlConnection.setRequestMethod("POST");

				/*
				 * Generate request json array
				 */
				System.out.println("Create Json object");
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("method", "ping");
					jsonObject.put("score", new Integer(200));
					jsonObject.put("current", new Double(152.32));
					jsonObject.put("nickname", "Hacker");
				} catch (JSONException e) {
					System.out.println("ERROR: can not create json object: "
							+ e);
					e.printStackTrace();
				}

				/*
				 * Generate output (request) stream
				 */
				OutputStream out = null;
				try {
					out = new BufferedOutputStream(
							urlConnection.getOutputStream());
					out.write("method=ping".getBytes());
					out.write("&arguments=".getBytes());
					out.write(jsonObject.toString().getBytes());

				} finally {
					if (out != null) {
						out.close();
					}
				}

				/*
				 * Get Status of the HTTP Connection
				 * http://www.w3.org/Protocols/HTTP/HTRESP.html
				 */
				int status = urlConnection.getResponseCode();
				System.out.println("HTTP Connection status: " + status);

				/*
				 * Fetch return
				 */
				InputStream in = new BufferedInputStream(
						urlConnection.getInputStream());

				/*
				 * Process Return into a string
				 */
				StringBuilder returnString = new StringBuilder();
				String line;
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(in, "UTF-8"));
					while ((line = reader.readLine()) != null) {
						returnString.append(line);
					}
				} finally {
					in.close();
				}

				/*
				 * Some logging
				 */
				System.out.println("download of " + params[0] + " finished!");
				System.out.println("found: " + returnString.toString());

				/*
				 * Draw output
				 */
				getUserInputActivity.getTextviewIntent().putExtra(
						GetUserInputActivity.EXTRA_MESSAGE,
						returnString.toString());
				getUserInputActivity.startActivity(getUserInputActivity
						.getTextviewIntent());

				return in.toString();
			} finally {
				urlConnection.disconnect();
			}

			// return builder.toString();

		} catch (Exception e) {
			System.out.println("failed to download " + params[0] + ":\n" + e);
			return null;
		}
	}
}