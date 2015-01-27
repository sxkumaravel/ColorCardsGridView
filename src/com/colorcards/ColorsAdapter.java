package com.colorcards;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class ColorsAdapter extends ArrayAdapter<Colors> {

	private List<Colors> list;
	private LayoutInflater inflater;
	private int width;
	private int height;

	public ColorsAdapter(Context context, int resource, List<Colors> objects) {
		super(context, resource, objects);
		list = objects;
		inflater = LayoutInflater.from(context);
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		width = metrics.widthPixels;
		width = (width / 2) - 10;
		height = width + 100;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Colors getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getPosition(Colors item) {
		return list.indexOf(item);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Colors color = getItem(position);

		View view = inflater.inflate(R.layout.row_item_grid, null);
		TextView textView = (TextView) view.findViewById(R.id.color_view);
		textView.setBackgroundColor(Color.parseColor(color.getColorCode()));
		LayoutParams layoutParams = new LayoutParams(width, height);
		textView.setLayoutParams(layoutParams);
		return view;
	}

}
