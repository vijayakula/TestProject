package com.example.onclouderpandroidapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class PurchaseOrderItemDetails extends Activity {

    public String UserIdPk, RoleIdPk;
	DisplayMetrics metrics;
	int width;

	private ExpandableListView expListView;
	private ListAdapterExpandible adapter;

	// declare array List for all headers in list
	ArrayList<String> headersArrayList = new ArrayList<String>();

	// Declare Hash map for all headers and their corresponding values
	HashMap<String, ArrayList<String>> childArrayList = new HashMap<String, ArrayList<String>>();

	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_order_item_details);
        Bundle b = getIntent().getExtras();
        UserIdPk = (String) b.getCharSequence("UserIdPk");
        RoleIdPk = (String) b.getCharSequence("RoleIdPk");
		try {

			String sampleUrl = "https://mobileservices.onclouderp.com/Approval/ApprovalService.svc/PurchaseOrder?userId=" + UserIdPk +"&roleId=" + RoleIdPk + "&purchaseOrderId=277&recordNavigatorId=0&createdDate=08/03/2015&formId=frmPurchaseOrders&authkey=a0af7b9518e14358ad77d6738959cd12&token=e689ebf36a894a72ae9cc4ef3ff2d83f&companyId=1";

			URL newURL = new URL(sampleUrl);
			URLConnection yc = newURL.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));

			String itemDetails = in.readLine();

			JSONObject json = new JSONObject(itemDetails);
			JSONObject response = json.getJSONObject("GetPurchaseOrderDetails");
			JSONArray saleOrderItemDetails = response
					.getJSONArray("purchaseOrderDetails");

			int n = saleOrderItemDetails.length();

			for (int i = 0; i < saleOrderItemDetails.length(); i++) {

				String output = saleOrderItemDetails.getJSONObject(i)
						.getString("ItemNameUnique");
				String uom = saleOrderItemDetails.getJSONObject(i).getString(
						"UnitOfMeasurementNameUnique");
				float quantity = saleOrderItemDetails.getJSONObject(i).getLong(
						"OrderedQuantity");
				float unitPrice = saleOrderItemDetails.getJSONObject(i)
						.getLong("UnitPrice");
				float baseCurrency = saleOrderItemDetails.getJSONObject(i)
						.getLong("CompanyCurrencyAmount");

				// parentHeaderInformation.add(output);
				expListView = (ExpandableListView) findViewById(R.id.expListView);

				// add headers values
				headersArrayList.add(output);

				ArrayList<String> itemChild = new ArrayList<String>();
				itemChild.add("Unit of Measurement : " + " " + uom);
				itemChild.add("Quantity : " + " " + quantity);
				itemChild.add("Unit Price : " + " " + unitPrice);
				itemChild.add("Base Currency Value : " + " " + baseCurrency);

				childArrayList.put(output, itemChild);

			}

			if (n != 0) {

				adapter = new ListAdapterExpandible(this, headersArrayList,
						childArrayList);

				expListView.setAdapter(adapter);

				metrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(metrics);
				width = metrics.widthPixels;

				if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
					expListView.setIndicatorBounds(
							width - GetDipsFromPixel(85), width
									- GetDipsFromPixel(10));
				} else {
					expListView.setIndicatorBoundsRelative(width
							- GetDipsFromPixel(85), width
							- GetDipsFromPixel(10));
				}

				expListView.setOnChildClickListener(new OnChildClickListener() {

					@Override
					public boolean onChildClick(ExpandableListView parent,
							View v, int groupPosition, int childPosition,
							long id) {
						// TODO Auto-generated method stub
						return false;
					}
				});

				expListView.setOnGroupClickListener(new OnGroupClickListener() {

					@Override
					public boolean onGroupClick(ExpandableListView parent,
							View v, int groupPosition, long id) {
						// TODO: Do your stuff
						/*
						 * Toast.makeText(getApplicationContext(),
						 * "Group is Clicked", Toast.LENGTH_LONG).show();
						 */
						return false;
					}
				});
				expListView
						.setOnGroupCollapseListener(new OnGroupCollapseListener() {

							@Override
							public void onGroupCollapse(int groupPosition) {
								// TODO: Do your stuff
								/*
								 * Toast.makeText(getApplicationContext(),
								 * "Child is Collapsed", Toast.LENGTH_LONG)
								 * .show();
								 */
							}
						});

				expListView
						.setOnGroupExpandListener(new OnGroupExpandListener() {

							@Override
							public void onGroupExpand(int groupPosition) {
								// TODO: Do your stuff

								/*
								 * Toast.makeText(getApplicationContext(),
								 * "Child is Expanded", Toast.LENGTH_LONG)
								 * .show();
								 */
							}
						});
			}

		} catch (ConnectException | UnknownHostException ex) {
			AlertDialog Dialog = new AlertDialog.Builder(this).create();
			Dialog.setMessage("Please check your internet connection..");
			Dialog.show();
		} catch (Exception ex) {
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setMessage("Catch Error");
			alertDialog.show();
		}
	}

	public int GetDipsFromPixel(float pixels) {
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.purchase_order_item_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
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
                                Intent intent = new Intent(PurchaseOrderItemDetails.this,
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

                            case R.id.back:
                                finish();
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
}
