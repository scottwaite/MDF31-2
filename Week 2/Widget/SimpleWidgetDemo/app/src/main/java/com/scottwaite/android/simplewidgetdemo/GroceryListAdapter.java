package com.scottwaite.android.simplewidgetdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by scottwaite on 5/27/15.
 */
public class GroceryListAdapter extends ArrayAdapter<GroceryItem> {

    private ArrayList<GroceryItem> items;
    private final Context context;

    public GroceryListAdapter(Activity context, ArrayList<GroceryItem> items) {
        super(context, -1, items);

        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_layout, parent, false);
        TextView tv_name = (TextView) row.findViewById(R.id.name);
        tv_name.setText(items.get(position).name);

        return row;
    }
}
