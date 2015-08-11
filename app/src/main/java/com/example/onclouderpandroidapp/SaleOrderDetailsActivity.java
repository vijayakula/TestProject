package com.example.onclouderpandroidapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class SaleOrderDetailsActivity extends Activity {

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
		setContentView(R.layout.activity_sale_order_details);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		try {

			String sampleUrl = "https://mobileservices.onclouderp.com/Approval/ApprovalService.svc/SalesOrder?userId=2&roleId=3&salesOrderId=336&recordNavigatorId=0&createdDate=&formId=frmSalesOrders&authkey=a0af7b9518e14358ad77d6738959cd12&token=e689ebf36a894a72ae9cc4ef3ff2d83f&companyId=1";
			URL newURL;
			newURL = new URL(sampleUrl);
			URLConnection yc = newURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String saleOrderDetails = in.readLine();

			JSONObject json = new JSONObject(saleOrderDetails);
			JSONObject response = json.getJSONObject("GetSaleOrderDetails");

			//int n = json.length();
			// String length = String.valueOf(n);

			TextView documentNo = (TextView) findViewById(R.id.txtDocumentNo);
			documentNo.setText(response.getString("SalesOrderCodeUnique"));

			TextView customerName = (TextView) findViewById(R.id.txtCustomer);
			customerName.setText(response
					.getString("BusinessPartnerNameUnique"));

			TextView customerAddress = (TextView) findViewById(R.id.txtCustomerAddress);
			customerAddress.setText(response.getString("Address"));

			TextView currency = (TextView) findViewById(R.id.txtCurrency);
			currency.setText(response.getString("CurrencyNameUnique"));

			TextView salesPerson = (TextView) findViewById(R.id.txtSalesPerson);
			salesPerson.setText(response.getString("UserNameUnique"));

			String date = response.getString("SalesOrderDate").split("\\(")[1]
					.split("\\+")[0];
			Date entryDate = new Date(Long.parseLong(date));
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String saleOrderDate = dateFormat.format(entryDate);

			TextView orderEntryDate = (TextView) findViewById(R.id.txtEntryDate);
			orderEntryDate.setText(saleOrderDate);

			TextView customerPONumber = (TextView) findViewById(R.id.txtCustomerPONumber);
			customerPONumber.setText(response
					.getString("PartnerPurchaseOrderNumber"));

			TextView shippingAddress = (TextView) findViewById(R.id.txtShippingAddress);
			shippingAddress.setText(response.getString("ShippingAddress"));

			TextView currencyValue = (TextView) findViewById(R.id.txtCurrencyValue);
			currencyValue.setText(response.getString("CurrencyValue"));

			TextView priceList = (TextView) findViewById(R.id.txtPriceList);
			priceList.setText(response.getString("PriceList"));

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sale_order_details, menu);
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
                                Intent intent = new Intent(SaleOrderDetailsActivity.this,
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
