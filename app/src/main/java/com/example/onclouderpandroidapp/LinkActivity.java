package com.example.onclouderpandroidapp;

//import android.support.v7.app.ActionBarActivity;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Html;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;
import android.widget.ImageView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class LinkActivity extends ActionBarActivity {

    public boolean a = false;
    public String UserIdPk, RoleIdPk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        Bundle b = getIntent().getExtras();
        String CompanyName = (String) b.getCharSequence("companyNameUnique");
        String RoleName = (String) b.getCharSequence("roleNameUnique");
        String userName = (String) b.getCharSequence("UserNameUnique");
        TextView tv1 = (TextView) findViewById(R.id.txtGreeting);
        String sourceString = "<b>" + "Company Name :" + "</b> " + CompanyName;
        String roleName = "<b>" + "Role Name :" + "</b> " + RoleName;
        String userNameUnique = "<b>" + "User Name :" + "</b> " + userName;
        UserIdPk = (String) b.getCharSequence("UserIdPk");
        RoleIdPk = (String) b.getCharSequence("RoleIdPk");
        tv1.setText(Html.fromHtml(sourceString));
        TextView tv2 = (TextView) findViewById(R.id.txtroleName);
        tv2.setText(Html.fromHtml(userNameUnique));
        String image = (String) b.getCharSequence("image");
        String imageURL = "https://mobileservices.onclouderp.com/Media/ImageService.svc/GetImage?imagePath=" + image;
        new DownloadImageTask((ImageView) findViewById(R.id.imageView1)) .execute(imageURL);
        /*Toast.makeText(getApplicationContext(), CompanyName + RoleName,
				Toast.LENGTH_LONG).show();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.link, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
		/*
		 * int id = item.getItemId(); if (id == R.id.action_settings) { return
		 * true; }
		 */
        switch (item.getItemId()) {

            case R.id.logout_menu:
                // Toast.makeText(getApplicationContext(),"Contact Us page",Toast.LENGTH_LONG).show();
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                // IsLogout = "true";
                                // session.setCheck("komathi");
                                Intent intent = new Intent(LinkActivity.this,
                                        HomeActivity.class);
                                // intent.putExtras(bsad);
                                Bundle b = new Bundle();
                                b.putString("CheckSignOut", "success");
                                intent.putExtras(b);
                                startActivity(intent);
                                finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                // No button clicked
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to Logout?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
			/*Intent intentHome = new Intent(ApprovalActivity.this,
					HomeActivity.class);
			startActivity(intentHome);*/
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
            a = true;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay =
                    urls[0];
            Bitmap mIcon11 = null;
            a = true;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 =
                        BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error",
                        e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            a = true;
        }
    }


    public void navigate(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnApproval:
                Bundle b = new Bundle();
                b.putString("RoleIdPk", RoleIdPk);
                b.putString("UserIdPk", UserIdPk);
                intent = new Intent(this, ApprovalActivity.class);
                intent.putExtras(b);
                break;
            case R.id.btnReport:
                intent = new Intent(this, ReportActivity.class);
                break;
        }
        startActivity(intent);
    }
}
