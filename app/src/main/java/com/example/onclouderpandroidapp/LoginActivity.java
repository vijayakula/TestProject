package com.example.onclouderpandroidapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.support.v7.app.ActionBar;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity implements OnClickListener {

	public String email;
	public String password;
	public static String loginCheck, Checking;
	public String inputLine, name, passWord;
	private EditText editTextUsername, editTextPassword;
	private CheckBox saveLoginCheckBox;
	public static SharedPreferences loginPreferences;
	public static SharedPreferences.Editor loginPrefsEditor;
	private Boolean saveLogin;
	private Button ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ok = (Button) findViewById(R.id.btnSignIn);
		ok.setOnClickListener(this);
		editTextUsername = (EditText) findViewById(R.id.User_Name);
		editTextPassword = (EditText) findViewById(R.id.Password);
		saveLoginCheckBox = (CheckBox) findViewById(R.id.chkRememberMe);
		loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
		loginPrefsEditor = loginPreferences.edit();
		loginCheck = loginPreferences.getString("Check", "");
		saveLogin = loginPreferences.getBoolean("saveLogin", false);
		if (saveLogin == true) {
			editTextUsername
					.setText(loginPreferences.getString("username", ""));
			editTextPassword
					.setText(loginPreferences.getString("password", ""));
			saveLoginCheckBox.setChecked(true);	
		}
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
			Intent intent1 = new Intent(LoginActivity.this,
					AboutUsActivity.class);
			startActivity(intent1);
			return true;
		case R.id.contact_menu:
			// Toast.makeText(getApplicationContext(),"Contact Us page",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(LoginActivity.this,
					ContactUsActivity.class);
			startActivity(intent);
			return true;
		case R.id.homePage_menu:
			// Toast.makeText(getApplicationContext(),"Contact Us page",Toast.LENGTH_LONG).show();
			Intent intentHome = new Intent(LoginActivity.this,
					HomeActivity.class);
			startActivity(intentHome);
			return true;
		case android.R.id.home:
			this.finish();
			return (true);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		if(loginCheck.contains("true") == false){
			loginPrefsEditor.putString("Check", "true");
			loginPrefsEditor.commit();
		}
		try {
			/*
			 * if (editTextUserName.getText().toString() == "" &&
			 * editTextPassword.getText().toString() == "") { //testFlag =
			 * false; } else { if (editTextUserName.getText().length() != 0 &&
			 * editTextUserName.getText().toString() != "") { if
			 * (editTextPassword.getText().length() != 0 &&
			 * editTextPassword.getText().toString() != "") { name =
			 * editTextUserName.getText().toString().trim(); if
			 * (name.matches(emailPattern)) { testFlag = true; passWord =
			 * editTextPassword.getText().toString(); } else {
			 * AlertDialog.Builder emailValidDialog = new AlertDialog.Builder(
			 * this); emailValidDialog.setTitle("");
			 * emailValidDialog.setMessage("Invalid User name");
			 * emailValidDialog.setPositiveButton("OK", new
			 * DialogInterface.OnClickListener() {
			 * 
			 * @Override public void onClick( DialogInterface dialog, int which)
			 * { // TODO Auto-generated method stub
			 * 
			 * } }); AlertDialog alertDialog = emailValidDialog.create();
			 * alertDialog.show(); } } } }
			 */
			// if (testFlag == true) {
			if (editTextUsername != null) {
				email = editTextUsername.getText().toString();
				email = email.trim();
				password = editTextPassword.getText().toString();
			} else {
				EditText editText = (EditText) findViewById(R.id.User_Name);
				email = editText.getText().toString();
				email = email.trim();
				EditText editText1 = (EditText) findViewById(R.id.Password);
				password = editText1.getText().toString();
			}
			if (email.trim().isEmpty()) {
				AlertDialog.Builder userNameDialog = new AlertDialog.Builder(
						this);
				userNameDialog.setTitle("");
				userNameDialog.setMessage("Enter User Name");
				userNameDialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				AlertDialog alertDialog = userNameDialog.create();
				alertDialog.show();
			} else if (password.trim().isEmpty()) {
				AlertDialog.Builder userNameDialog = new AlertDialog.Builder(
						this);
				userNameDialog.setTitle("");
				userNameDialog.setMessage("Enter Password");
				userNameDialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				AlertDialog alertDialog = userNameDialog.create();
				alertDialog.show();
				EditText editText1 = (EditText) findViewById(R.id.Password);
				editText1.requestFocus(editText1.length());
			} else {
                String sampleUrl = "https://mobileservices.onclouderp.com/Approval/ApprovalService.svc/GetTenantDetails?Email="
                        + email
                        + "&Password="
                        + password
                        + "&IsTrail=0&authkey=a0af7b9518e14358ad77d6738959cd12&token=e689ebf36a894a72ae9cc4ef3ff2d83f";
                URL newURL = new URL(sampleUrl);
                URLConnection yc = newURL.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        yc.getInputStream()));
                inputLine = in.readLine();
                String[] responseMessage = inputLine.split("ResponseMessage");
                String[] responseCode = responseMessage[1].split(",");
                String finalresponseCode = responseCode[0];
                finalresponseCode = finalresponseCode.substring(0, finalresponseCode.length() - 1);
                finalresponseCode = finalresponseCode.substring(3);
                if (finalresponseCode.contains("8000") == true) {
                    String RoleIdPk = null;
                    String UserIdPk = null;
                    String companyNameUnique = null;
                    String roleNameUnique = null;
                    String UserName = null;
                    String[] code = inputLine.split("RoleIdPk");
                    if (code[1] != "" && code[1] != null) {
                        String[] orderCode = code[1].split(",");
                        // String finalCode = orderCode[0];
                        // finalCode = finalCode.substring(0, finalCode.length() -
                        // 1);
                        RoleIdPk = orderCode[0].substring(2);
                    }
                    String[] companyName = inputLine.split("CompanyName");
                    if (companyName[1] != "" && companyName[1] != null) {
                        String[] resultcompanyName = companyName[1].split(",");
                        companyNameUnique = resultcompanyName[0].substring(3);
                        companyNameUnique = companyNameUnique.substring(0, companyNameUnique.length() - 1);
                    }

                    String[] roleName = inputLine.split("RoleIdPk");
                    if (roleName[1] != "" && roleName[1] != null) {
                        String[] resultroleName = roleName[1].split(",");
                        String finalroleName = resultroleName[1];
                        String[] finalroleNameUnique = finalroleName.split("RoleName");
                        roleNameUnique = finalroleNameUnique[1].substring(3);
                        roleNameUnique = roleNameUnique.substring(0, roleNameUnique.length() - 1);
                    }
                    String[] User = inputLine.split("UserID");
                    if (User[1] != "" && User[1] != null) {
                        String[] orderCode = User[1].split(",");
                        String finalCode = orderCode[0];
                       // finalCode = finalCode.substring(0, finalCode.length() - 1);
                        UserIdPk = finalCode.substring(2);
                    }

                    String[] splitAfter = inputLine.split("ResponseMessage");
                    String[] UserNameUnique = splitAfter[1].split("UserNameUnique");
                    if (UserNameUnique[1] != "" && UserNameUnique[1] != null) {
                        String[] UserNameUniqueCode = UserNameUnique[1].split(",");
                        String finalUserNameUnique = UserNameUniqueCode[0];
                        finalUserNameUnique = finalUserNameUnique.substring(0, finalUserNameUnique.length() - 2);
                        UserName = finalUserNameUnique.substring(3);
                    }

                    if (inputLine != null) {
                        if (saveLoginCheckBox.isChecked()) {
                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("username", email);
                            loginPrefsEditor.putString("password", password);
                            loginPrefsEditor.commit();
                        } else if (!saveLoginCheckBox.isChecked()) {
                            loginPrefsEditor.putBoolean("saveLogin", false);
                            loginPrefsEditor.putString("username", "");
                            loginPrefsEditor.putString("password", "");
                            loginPrefsEditor.commit();
                        }
                        Bundle b = new Bundle();
                        b.putString("RoleIdPk", RoleIdPk);
                        b.putString("UserIdPk", UserIdPk);
                        b.putString("companyNameUnique", companyNameUnique);
                        b.putString("roleNameUnique", roleNameUnique);
                        b.putString("UserNameUnique", UserName);
                        Button mBtn1 = (Button) findViewById(R.id.btnSignIn);
                        mBtn1.setText("Loading..");

                        Intent i = new Intent(LoginActivity.this,
                                LinkActivity.class);
                        i.putExtras(b);
                        startActivity(i);
                        finish();
                    }

                } else if (finalresponseCode.contains("9006") == true)  {
                    AlertDialog.Builder userNameDialog = new AlertDialog.Builder(
                            this);
                    userNameDialog.setTitle("");
                    userNameDialog.setMessage("Invalid User Name");
                    userNameDialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            });
                    AlertDialog alertDialog = userNameDialog.create();
                    alertDialog.show();
                }
                else if (finalresponseCode.contains("9007") == true)  {
                    AlertDialog.Builder userNameDialog = new AlertDialog.Builder(
                            this);
                    userNameDialog.setTitle("");
                    userNameDialog.setMessage("Invalid Password");
                    userNameDialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            });
                    AlertDialog alertDialog = userNameDialog.create();
                    alertDialog.show();
                }
            }

		} catch (ConnectException | UnknownHostException ex) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
			alertDialogBuilder
					.setMessage("Please check your internet connection...");

			alertDialogBuilder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {

						}
					});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();

		} catch (Exception ex) {
			String error = ex.toString();
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
			alertDialogBuilder.setMessage(error);

			alertDialogBuilder.setPositiveButton("YES",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {

						}
					});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		}
	}
}
