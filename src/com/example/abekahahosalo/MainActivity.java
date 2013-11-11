package com.example.abekahahosalo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import adapter.TabsPagerAdapter;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;
import android.content.Intent;


public class MainActivity extends FragmentActivity  implements ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter tabsPagerAdapter;
	private ActionBar actionBar;
	private ProgressBar pb;

	//Tabs Names
	private String tabs[] = { "Create Team", "Join Team", "About" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		// Initialization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		tabsPagerAdapter = new TabsPagerAdapter( getSupportFragmentManager() );

		viewPager.setAdapter(tabsPagerAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		//Adding tab names
		for( String tab_name : tabs){
			actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
		}
		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());

	}


	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub


	}

	public void sendMessage(View view){

		//ActionBar actionBar = getActionBar();
		//actionBar.hide();

		boolean flag;
		if(view.getId() == R.id.btn_reg)
			flag = getValuesReg();
		else
			flag = getValuesLogin();
		
		if(flag){
		Intent intent = new Intent(this, TeamCreated.class);

		startActivity(intent);
		}


	}

	private boolean getValuesReg(){

		// extracting user name
		EditText editText = (EditText) findViewById(R.id.user_name);
		String user_name = editText.getText().toString();

		// extracting team name
		editText = (EditText) findViewById(R.id.team_name);
		String team_name = editText.getText().toString();

		// extracting password
		editText = ( EditText ) findViewById(R.id.password);
		String password = editText.getText().toString();

		if( user_name.isEmpty() || team_name.isEmpty() || password.isEmpty()){
			Toast.makeText(this, "please enter all the details", Toast.LENGTH_LONG).show();
			return false;
		}
		else{
			pb=(ProgressBar)findViewById(R.id.progressBar1);
			pb.setVisibility(View.VISIBLE);
			new MyAsyncTask().execute(user_name, team_name, password);	
			return true;
		}


	}

	private boolean getValuesLogin(){

		// extracting user name
		EditText editText = (EditText) findViewById(R.id.user_name_log);
		String user_name = editText.getText().toString();

		// extracting team name
		editText = (EditText) findViewById(R.id.team_name_log);
		String team_name = editText.getText().toString();

		// extracting password
		editText = ( EditText ) findViewById(R.id.password_log);
		String password = editText.getText().toString();

		if( user_name.isEmpty() || team_name.isEmpty() || password.isEmpty()){
			Toast.makeText(this, "please enter all the details", Toast.LENGTH_LONG).show();
			return false;
		}
		else{
			pb=(ProgressBar)findViewById(R.id.progressBar1);
			pb.setVisibility(View.VISIBLE);
			new MyAsyncTask().execute(user_name, team_name, password);	
			return true;
		}


	}


	private class MyAsyncTask extends AsyncTask<String, Integer, Double>{

		@Override
		protected Double doInBackground(String... params) {
			// TODO Auto-generated method stub
			postData(params[0], params[1], params[2]);
			return null;
		}

		protected void onPostExecute(Double result){
			pb.setVisibility(View.GONE);
			Toast.makeText(getApplicationContext(), "command sent", Toast.LENGTH_LONG).show();
		}
		protected void onProgressUpdate(Integer... progress){
			pb.setProgress(progress[0]);
		}

		public void postData(String user_name, String team_name, String password) {
			// Create a new HttpClient and Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://jacknjill.byethost14.com/htdocs/abe_kaha_ho_salo/add_team.php");

			try {
				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("user_name", user_name));
				nameValuePairs.add(new BasicNameValuePair("team_name", team_name));
				nameValuePairs.add(new BasicNameValuePair("password_name", password));
				
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}

	}

}
