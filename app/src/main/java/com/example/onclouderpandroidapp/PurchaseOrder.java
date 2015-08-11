package com.example.onclouderpandroidapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;


public class PurchaseOrder extends TabActivity implements OnTabChangeListener {

    public String UserIdPk, RoleIdPk;
	TabHost tabHost;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_order);
		Resources ressources = getResources();
		tabHost = getTabHost();
		tabHost.setOnTabChangedListener(this);

        Bundle b = getIntent().getExtras();
        UserIdPk = (String) b.getCharSequence("UserIdPk");
        RoleIdPk = (String) b.getCharSequence("RoleIdPk");
        Toast.makeText(getApplicationContext(),RoleIdPk + UserIdPk,Toast.LENGTH_LONG).show();
		String purchaseOrderDetails = "<b>" + "Purchse Order Details" + "</b> ";
		// Tab for Item Details
		TabSpec salesOrder = tabHost.newTabSpec("Purchase Order Details");
		// setting Title for the Tab
		salesOrder.setIndicator(Html.fromHtml(purchaseOrderDetails));
		Bundle bun = new Bundle();
		bun.putString("RoleIdPk", RoleIdPk);
		bun.putString("UserIdPk", UserIdPk);
		Intent salesIntent = new Intent(this,
				PurchaseOrderDetails.class);
		salesIntent.putExtras(bun);
		salesOrder.setContent(salesIntent);

		String purchseOrderItemDetails = "<b>" + "Item Details" + "</b> ";
		// Tab for Item Details
		TabSpec items = tabHost.newTabSpec("Item Details");
		items.setIndicator(Html.fromHtml(purchseOrderItemDetails));
		Intent itemsIntent = new Intent(this,
				PurchaseOrderItemDetails.class);
		itemsIntent.putExtras(bun);
		items.setContent(itemsIntent);

		String purchaseOrderTermsDetails = "<b>" + "Terms and Conditions"+ "</b> ";
		// Tab for Terms and Condition
		TabSpec terms = tabHost.newTabSpec("Terms and Condition");
		terms.setIndicator(Html.fromHtml(purchaseOrderTermsDetails));
		Intent termsIntent = new Intent(this,
				PurchaseOrderTermsAndConditions.class);
		termsIntent.putExtras(bun);
		terms.setContent(termsIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(salesOrder);
		tabHost.addTab(items);
		tabHost.addTab(terms);

		tabHost.getTabWidget()
				.getChildAt(0)
				.setBackgroundDrawable(
						new ColorDrawable(Color.parseColor("#CE93D8")));

		tabHost.getTabWidget()
				.getChildAt(1)
				.setBackgroundDrawable(
						new ColorDrawable(Color.parseColor("#9FA8DA")));

		tabHost.getTabWidget()
				.getChildAt(2)
				.setBackgroundDrawable(
						new ColorDrawable(Color.parseColor("#80CBC4")));

		tabHost.getTabWidget().setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.purchase_order, menu);
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
                                Intent intent = new Intent(PurchaseOrder.this,
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

	@SuppressWarnings("deprecation")
	@Override
	public void onTabChanged(String tabId) {

		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			if (i == 0)
				tabHost.getTabWidget()
						.getChildAt(i)
						.setBackgroundDrawable(
								new ColorDrawable(Color.parseColor("#CE93D8")));
			else if (i == 1)
				tabHost.getTabWidget()
						.getChildAt(i)
						.setBackgroundDrawable(
								new ColorDrawable(Color.parseColor("#9FA8DA")));
			else if (i == 2)
				tabHost.getTabWidget()
						.getChildAt(i)
						.setBackgroundDrawable(
								new ColorDrawable(Color.parseColor("#80CBC4")));

		}

		if (tabHost.getCurrentTab() == 0)
			tabHost.getTabWidget()
					.getChildAt(tabHost.getCurrentTab())
					.setBackgroundDrawable(
							new ColorDrawable(Color.parseColor("#CE93D8")));
		else if (tabHost.getCurrentTab() == 1)
			tabHost.getTabWidget()
					.getChildAt(tabHost.getCurrentTab())
					.setBackgroundDrawable(
							new ColorDrawable(Color.parseColor("#9FA8DA")));
		else if (tabHost.getCurrentTab() == 2)
			tabHost.getTabWidget()
					.getChildAt(tabHost.getCurrentTab())
					.setBackgroundDrawable(
							new ColorDrawable(Color.parseColor("#80CBC4")));

	}
}
