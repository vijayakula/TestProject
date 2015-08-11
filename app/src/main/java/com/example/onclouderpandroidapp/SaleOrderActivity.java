package com.example.onclouderpandroidapp;

//import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
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
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class SaleOrderActivity extends TabActivity implements
		OnTabChangeListener {

	TabHost tabHost;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_order);

		Resources ressources = getResources();
		tabHost = getTabHost();
		tabHost.setOnTabChangedListener(this);
		/*String saleOrderDetails = "<b>" + "Sales Order Details" + "</b> "; 
		// Sales Order Details
		Intent intentHome = new Intent(this, SaleOrderDetailsActivity.class);
		TabSpec tabSpecHome = tabHost
				.newTabSpec("Home")
				.setIndicator(Html.fromHtml(saleOrderDetails),
						ressources.getDrawable(R.drawable.icon_home_config))
				.setContent(intentHome);
		String saleOrderItemDetails = "<b>" + "Item Details" + "</b> "; 
		// Item Details
		Intent intentNotification = new Intent(this,
				SaleOrderItemDetailsActivity.class);
		TabSpec tabSpecNotification = tabHost
				.newTabSpec("Notification")
				.setIndicator(
						Html.fromHtml(saleOrderItemDetails),
						ressources
								.getDrawable(R.drawable.icon_notification_config))
				.setContent(intentNotification);

		String saleOrderTermsDetails = "<b>" + "Terms and Conditions" + "</b> "; 
		// Terms and Condition
		Intent intentSummary = new Intent(this,
				SaleOrderTermsAndConditionActivity.class);
		TabSpec tabSpecSummary = tabHost
				.newTabSpec("Summary")
				.setIndicator(Html.fromHtml(saleOrderTermsDetails),
						ressources.getDrawable(R.drawable.icon_summary_config))
				.setContent(intentSummary);
		// Status
		String status = "<b>" + "Status" + "</b> "; 
		Intent intentSetting = new Intent().setClass(this,
				SaleOrderStatusActivity.class);
		TabSpec tabSpecSetting = tabHost
				.newTabSpec("Setting")
				.setIndicator(Html.fromHtml(status),
						ressources.getDrawable(R.drawable.icon_setting_config))
				.setContent(intentSetting);

		// add all tabs
		tabHost.addTab(tabSpecHome);
		tabHost.addTab(tabSpecNotification);
		tabHost.addTab(tabSpecSummary);*/
		// tabHost.addTab(tabSpecSetting);
		
		
		
		// Tab for Item Details
				TabSpec salesOrder = tabHost.newTabSpec("Sales Order Details");
				// setting Title for the Tab
				String saleOrderDetails = "<b>" + "Sales Order Details" + "</b> ";
				salesOrder.setIndicator(Html.fromHtml(saleOrderDetails));
				Bundle bun = new Bundle();
				/*bun.putString("RoleIdPk", RoleIdPk);
				bun.putString("UserIdPk", UserIdPk);*/
				Intent salesIntent = new Intent(this, SaleOrderDetailsActivity.class);
				//salesIntent.putExtras(bun);
				// startActivity(salesIntent);
				salesOrder.setContent(salesIntent);

				String saleOrderItemDetails = "<b>" + "Item Details" + "</b> ";
				// Tab for Item Details
				TabSpec items = tabHost.newTabSpec("Item Details");
				items.setIndicator(Html.fromHtml(saleOrderItemDetails));
				Intent itemsIntent = new Intent(this, SaleOrderItemDetailsActivity.class);
				//itemsIntent.putExtras(bun);
				items.setContent(itemsIntent);

				String saleOrderTermsDetails = "<b>" + "Terms and Conditions" + "</b> "; 
				// Tab for Terms and Condition
				TabSpec terms = tabHost.newTabSpec("Terms and Condition");
				terms.setIndicator(Html.fromHtml(saleOrderTermsDetails));
				Intent termsIntent = new Intent(this, SaleOrderTermsAndConditionActivity.class);
				//termsIntent.putExtras(bun);
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
		getMenuInflater().inflate(R.menu.sale_order, menu);
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
                                Intent intent = new Intent(SaleOrderActivity.this,
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
            case R.id.back:
                this.finish();
                return(true);
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
