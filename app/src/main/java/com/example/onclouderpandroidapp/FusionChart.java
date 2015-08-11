package com.example.onclouderpandroidapp;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FusionChart extends Activity {

	protected static final boolean FirstLoad = false;

	private Button barChart, ColumnChart;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fusion_chart);

		final Context context = this;

		barChart = (Button) findViewById(R.id.btnBarChart);

		barChart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Toast.makeText(getApplicationContext(), "Bar Chart Rendering",
						Toast.LENGTH_LONG).show();

				Intent intent = new Intent(context,
						ChartsItemVsSales.class);
				startActivity(intent);
			}
		});

		ColumnChart = (Button) findViewById(R.id.btnColumnChart);

		ColumnChart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(),
						"Column Chart Rendering", Toast.LENGTH_LONG).show();

				Intent intent1 = new Intent(context,
						PieChartItemVsSalesActivity.class);
				startActivity(intent1);
			}

		});

	}
}
