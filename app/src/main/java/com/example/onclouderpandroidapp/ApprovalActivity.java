package com.example.onclouderpandroidapp;

//import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ApprovalActivity extends ActionBarActivity {

    public String UserIdPk, RoleIdPk;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approval);
        Bundle b = getIntent().getExtras();
        UserIdPk = (String) b.getCharSequence("UserIdPk");
        RoleIdPk = (String) b.getCharSequence("RoleIdPk");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approval, menu);
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
                                Intent intent = new Intent(ApprovalActivity.this,
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
	
	public void navigate(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnSaleOrder:
            Bundle b = new Bundle();
            b.putString("RoleIdPk", RoleIdPk);
            b.putString("UserIdPk", UserIdPk);
			intent = new Intent(this, SaleOrderActivity.class);
            intent.putExtras(b);
			break;
		case R.id.btnPurchaseOrder:
            Bundle bun = new Bundle();
            bun.putString("RoleIdPk", RoleIdPk);
            bun.putString("UserIdPk", UserIdPk);
			intent = new Intent(this, PurchaseOrder.class);
            intent.putExtras(bun);
            break;
		}
		startActivity(intent);
	}
}
