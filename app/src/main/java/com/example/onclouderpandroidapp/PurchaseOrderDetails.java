package com.example.onclouderpandroidapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PurchaseOrderDetails extends Activity {

    public String UserIdPk, RoleIdPk;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_order_details);
        Bundle b = getIntent().getExtras();
        UserIdPk = (String) b.getCharSequence("UserIdPk");
        RoleIdPk = (String) b.getCharSequence("RoleIdPk");
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		try {

			String sampleUrl = "https://mobileservices.onclouderp.com/Approval/ApprovalService.svc/PurchaseOrder?userId=" + UserIdPk +"&roleId=" + RoleIdPk + "&purchaseOrderId=277&recordNavigatorId=0&createdDate=08/03/2015&formId=frmPurchaseOrders&authkey=a0af7b9518e14358ad77d6738959cd12&token=e689ebf36a894a72ae9cc4ef3ff2d83f&companyId=1";
			URL newURL;
			newURL = new URL(sampleUrl);
			URLConnection yc = newURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String saleOrderDetails = in.readLine();

			JSONObject json = new JSONObject(saleOrderDetails);
			JSONObject response = json.getJSONObject("GetPurchaseOrderDetails");

			// int n = json.length();
			// String length = String.valueOf(n);

			TextView documentNo = (TextView) findViewById(R.id.txtDocumentNo);
			documentNo.setText(response.getString("PurchaseOrderCodeUnique"));

			TextView customerName = (TextView) findViewById(R.id.txtVendor);
			customerName.setText(response
					.getString("BusinessPartnerNameUnique"));

			TextView customerAddress = (TextView) findViewById(R.id.txtVendorContact);
			customerAddress.setText(response.getString("BusinessPartnerAddress"));

			TextView currency = (TextView) findViewById(R.id.txtReferenceNumber);
			currency.setText(response.getString("OurReference"));

			TextView salesPerson = (TextView) findViewById(R.id.txtCurrency);
			salesPerson.setText(response.getString("CurrencyNameUnique"));

			String date = response.getString("PurchaseOrderDate").split("\\(")[1]
					.split("\\+")[0];
			Date entryDate = new Date(Long.parseLong(date));
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String saleOrderDate = dateFormat.format(entryDate);

			TextView orderEntryDate = (TextView) findViewById(R.id.txtEntryDate);
			orderEntryDate.setText(saleOrderDate);

			TextView customerPONumber = (TextView) findViewById(R.id.txtVendorAddress);
			customerPONumber.setText(response
					.getString("BusinessPartnerAddress"));

			TextView shippingAddress = (TextView) findViewById(R.id.txtRequestedDelieveryDate);
			shippingAddress.setText(response.getString("RequestedDeliveryDate"));

			TextView currencyValue = (TextView) findViewById(R.id.txtPricelist);
			currencyValue.setText(response.getString("PriceList"));

			TextView priceList = (TextView) findViewById(R.id.txtBaseCurrencyValue);
			priceList.setText(response.getString("CurrencyValue"));

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
		getMenuInflater().inflate(R.menu.purchase_order_details, menu);
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
                                Intent intent = new Intent(PurchaseOrderDetails.this,
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
