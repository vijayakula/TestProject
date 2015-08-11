package com.example.onclouderpandroidapp;

//import android.support.v7.app.ActionBarActivity;
//import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		//actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*
		 * int id = item.getItemId(); if (id == R.id.action_settings) { return
		 * true; } return super.onOptionsItemSelected(item);
		 */
		switch (item.getItemId()) {
		case R.id.about_menu:
			// Toast.makeText(getApplicationContext(),"Contact Us page",Toast.LENGTH_LONG).show();
			Intent intent1 = new Intent(HomeActivity.this,
					AboutUsActivity.class);
			startActivity(intent1);
			return true;
		case R.id.contact_menu:
			// Toast.makeText(getApplicationContext(),"Contact Us page",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(HomeActivity.this,
					ContactUsActivity.class);
			startActivity(intent);
			return true;
		case R.id.login_menu:
			// Toast.makeText(getApplicationContext(),"Contact Us page",Toast.LENGTH_LONG).show();
			Intent intentHome = new Intent(HomeActivity.this,
					LoginActivity.class);
			startActivity(intentHome);
			return true;
		case android.R.id.home:
			this.finish();
			return (true);
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void click(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.about_us:
			intent = new Intent(this, AboutUsActivity.class);      
			break;
		case R.id.contactDetails:
			intent = new Intent(this, ContactUsActivity.class);
			break;
		case R.id.login:
			Bundle b = new Bundle();
			b.putString("CheckSignOut", "false");
			intent = new Intent(this, LoginActivity.class);
			intent.putExtras(b);
			break;
		case R.id.websiteHome:
			Uri uriUrl = Uri.parse("http://www.onclouderp.com/");
			intent = new Intent(Intent.ACTION_VIEW, uriUrl);
		//	intent = new Intent(HomePage.this, AboutUs.class);
		//	break;
		}
		startActivity(intent);
	}
}
