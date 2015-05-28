/*
Created By: Scott Waite
Course: MDF III
Instructor: Michael Celey
Assignment: Building a Widget
Date: May 22, 2015
*/

package com.scottwaite.android.simplewidgetdemo;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RemoteViews;

public class ConfigActivity extends Activity implements OnClickListener {
	
	private int mWidgetId;

	GroceryListAdapter adapter;
	EditText et_item, et_qty, et_aisle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);
		
		Intent launcherIntent = getIntent();
		Bundle extras = launcherIntent.getExtras();
		
		mWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
		
		if(extras != null) {
			mWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		if(mWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
			setResult(RESULT_CANCELED);
			finish();
		}

		GroceryListManager manager = GroceryListManager.getInstanceWithContext(getApplicationContext());

		ListView list = (ListView)findViewById(R.id.listView);
		adapter = new GroceryListAdapter(this, manager.getGroceryList());
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO: go to detail view and send in "manager.getGroceryList().get(position)" <-- GroceryItem object
			}
		});

		et_item = (EditText)findViewById(R.id.et_item);
		et_qty = (EditText)findViewById(R.id.et_qty);
		et_aisle = (EditText)findViewById(R.id.et_aisle);

		findViewById(R.id.update_button).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		updateWidget();
		close();
	}
	
	private void updateWidget() {
		// get grocery list manager singleton
		GroceryListManager manager = GroceryListManager.getInstanceWithContext(getApplicationContext());

		// create new grocery item to add to list
		GroceryItem newItem = new GroceryItem(et_item.getText().toString(), Integer.parseInt(et_qty.getText().toString()), Integer.parseInt(et_aisle.getText().toString()));
		// add new item to grocery list
		manager.addNewItem(newItem);
		// update listview dataset
		adapter.notifyDataSetChanged();

		// clear textfields
		et_item.setText("");
		et_qty.setText("");
		et_aisle.setText("");

		if(mWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
			Intent intent = new Intent(this, ConfigActivity.class);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mWidgetId);
			PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			
			RemoteViews widgetView = new RemoteViews(getPackageName(), R.layout.widget_layout);
			widgetView.setOnClickPendingIntent(R.id.widget_image, pIntent);
			
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
			appWidgetManager.updateAppWidget(mWidgetId, widgetView);
		}
	}
	
	private void close() {
		Intent result = new Intent();
		result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mWidgetId);
		setResult(RESULT_OK, result);
		finish();
	}
}