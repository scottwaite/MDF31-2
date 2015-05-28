/*
Created By: Scott Waite
Course: MDF III
Instructor: Michael Celey
Assignment: Building a Widget
Date: May 22, 2015
*/

package com.scottwaite.android.simplewidgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class SampleWidgetProvider extends AppWidgetProvider {
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		
		for(int i = 0; i < appWidgetIds.length; i++) {
			
			int widgetId = appWidgetIds[i];
			
			Intent intent = new Intent(context, com.scottwaite.android.simplewidgetdemo.ConfigActivity.class);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
			PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			
			RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			widgetView.setOnClickPendingIntent(R.id.widget_image, pIntent);
			
			appWidgetManager.updateAppWidget(widgetId, widgetView);
		}
	}
}