package com.example.onclouderpandroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.StrictMode;
import android.webkit.WebView;
import android.widget.Toast;

public class ChartsItemVsSales extends Activity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charts_item_vs_sales);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		String chartDatas = "";

		try {
			String ItemVsSalesUrl = "https://demo.onclouderp.com/OncloudErpRestfulServices/Purchase/Dashboard/DashboardService.svc/GetItemVsSalesDatas?fromDate=04/01/2015%20&toDate=03/31/%202016&type=1&userConnectionString=JoH9FCh6o8L1gE/HhdCG++UbHo6zHC+tRyTiicSqQOrHUtW7yoORhWn6ZD4AkH37MVWefERoGvUpLuPsWFwv7Cbkbs9P39eA4cNc+PFG+rYcREZmZyPPnxx4850Ho02GfkuKaxeISCioHU8EAnqYWLVg1ZmdmXMD5WUHoZgdGnEZqkJJkooC5QfkGYbgFqWa&oauth_consumer_key=2&oauth_nonce=tvBWYLqh4qIUfO0c7mvo87KLc96dUBgA&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1438591960&oauth_version=1.0&oauth_signature=aL7EToq8yB60AJiEm%2FnC75Dv%2F7Q%3D&_=1438591960173";
			URL newURL = new URL(ItemVsSalesUrl);
			URLConnection yc = newURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String userData = in.readLine();
			JSONArray ja = new JSONArray(userData);

			Toast.makeText(getApplicationContext(), "Column2D",
					Toast.LENGTH_LONG).show();

			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				String ItemNameUnique = jo.getString("ItemNameUnique");
				float Amount = jo.getLong("Amount");
				chartDatas += "<set label='" + ItemNameUnique + "' value='"
						+ Amount + "' />";
			}

			webView = (WebView) findViewById(R.id.webView1);
			webView.getSettings().setJavaScriptEnabled(true);

			String summary = "<html>"
					+ "<head>"
					+ "<hr>"
					+ "Items Vs Sales"
					+ "<hr>"
					+ " <title>Items Vs Sales XT - Using JavaScript</title>"
					+ "<script type=\"text/javascript\" src=\"file:///android_asset/fusioncharts.js\"></script>"
					+ "<script type=\"text/javascript\" src=\"file:///android_asset/fusioncharts.charts.js\"></script>"

					+ ""
					+ "<style>#chartContainerParent {position: relative;height: 300px;width: 400px;border: 1px dashed #efeffe;}#chartContainerParent #floader {position: absolute;top: 35%;left: 35%;width: 100px;height: 100px;background:url('file:///android_asset/loader.gif') no-repeat;border: 1px solid #efdfea;display:none;}</style>"
					+ "</head> "
					+ "<body >"
					+ "  <div id=\"chartContainerParent\"><div id=\"chartContainer\">FusionCharts XT will load here!</div><div id=\"floader\"></div></div>"
					+ "<script type=\"text/javascript\">"
					+

					"FusionCharts.addEventListener( \"initialized\", function(e,a) {if (e.sender.id==\"myChartId\") {(\"#chartContainerParent #floader\").show(); }});"
					+ " var myChart = new FusionCharts( \"Column2D\",\"myChartId\", \"300\",\"300\", \"0\", \"1\" );"
					+

					"myChart.addEventListener( \"Rendered\", function() {$(\"#chartContainerParent #floader\").hide();});"
					+

					"myChart.setXMLData(\"<chart caption='Items Vs Sales' xAxisName='Item' yAxisName='Amount' numberPrefix='$'>"
					+ chartDatas + "</chart>\");"
					+ "myChart.render(\"chartContainer\");" + "</script>" +

					"</body>" +

					"</html>";
			webView.loadDataWithBaseURL(null, summary, "text/html", "utf8",
					null);

		} catch (Exception ex) {
			String error = ex.toString();
			Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.charts_item_vs_sales, menu);
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
                                Intent intent = new Intent(ChartsItemVsSales.this,
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
}
