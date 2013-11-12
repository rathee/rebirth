package com.example.abekahahosalo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class TeamCreated extends Activity {

	private ProgressDialog pDialog;
	private String user_name, team_name, password;

	private static String url_create_team = "http://jacknjill.byethost14.com/abe_kaha_ho_salo/add_team.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";


	JSONParser jsonParser = new JSONParser();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle bundle = getIntent().getExtras();
		user_name = bundle.getString("user_name");
		team_name = bundle.getString("team_name");
		password = bundle.getString("password");
		new TeamCreatedAsync().execute();

	}


	class TeamCreatedAsync extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(TeamCreated.this);
			pDialog.setMessage("Creating Product..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("user_name", user_name));
			params.add(new BasicNameValuePair("team_name", team_name));
			params.add(new BasicNameValuePair("password", password));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_team,
					"POST", params);

			// check log cat fro response
			/*
			if (BuildConfig.DEBUG) {
				Log.d("Create Response", json.toString());
			}
			 */
			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);
				System.out.println(success);


				if (success == 1) {
					// successfully created product


					// closing this screen
					//finish();
				} else {
					if(success == 0){
						Toast.makeText(getApplicationContext(), "Oops! An error occurred", Toast.LENGTH_LONG).show();
					}
					else{
						Toast.makeText(getApplicationContext(), "Team Already exist! Try some other", Toast.LENGTH_LONG).show();
					}
					// failed to create product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}
	}

}
