package com.example.onclouderpandroidapp;


import android.annotation.SuppressLint;
//import android.app.ActionBar;
import android.support.v7.app.ActionBar;
//import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutUsActivity extends ActionBarActivity {

	private TextView textView, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9,
			tv10, tv11, tv12;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		Typeface type = Typeface.createFromAsset(getAssets(),
				"fonts/Calibri.ttf");
		textView = (TextView) findViewById(R.id.nameText);
		textView.setTypeface(type, Typeface.BOLD);
		tv1 = (TextView) findViewById(R.id.nameText1);
		tv1.setTypeface(type);
		tv2 = (TextView) findViewById(R.id.nameText2);
		tv2.setTypeface(type);
		tv3 = (TextView) findViewById(R.id.nameText3);
		tv3.setTypeface(type);
		tv4 = (TextView) findViewById(R.id.nameText4);
		tv4.setTypeface(type);
		tv5 = (TextView) findViewById(R.id.nameText5);
		tv5.setTypeface(type);
		tv6 = (TextView) findViewById(R.id.nameText6);
		tv6.setTypeface(type);
		tv7 = (TextView) findViewById(R.id.nameText7);
		tv7.setTypeface(type);
		tv8 = (TextView) findViewById(R.id.nameText8);
		tv8.setTypeface(type);
		tv9 = (TextView) findViewById(R.id.nameText9);
		tv9.setTypeface(type);
		tv10 = (TextView) findViewById(R.id.nameText10);
		tv10.setTypeface(type);
		tv11 = (TextView) findViewById(R.id.nameText11);
		tv11.setTypeface(type);
		tv12 = (TextView) findViewById(R.id.nameContent);
		tv12.setTypeface(type);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		case R.id.contact_menu:
			// Toast.makeText(getApplicationContext(),"Contact Us page",Toast.LENGTH_LONG).show();
			Intent intent1 = new Intent(AboutUsActivity.this, ContactUsActivity.class);
			startActivity(intent1);
			return true;
		case R.id.login_menu:
			// Toast.makeText(getApplicationContext(),"Contact Us page",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(AboutUsActivity.this, LoginActivity.class);
			startActivity(intent);
			return true;
		case R.id.homePage_menu:
			// Toast.makeText(getApplicationContext(),"Contact Us page",Toast.LENGTH_LONG).show();
			Intent intentHome = new Intent(AboutUsActivity.this, HomeActivity.class);
			startActivity(intentHome);
			return true;
		case android.R.id.home:
		    this.finish();
	        return(true);
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
