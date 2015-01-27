package com.colorcards;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GridView gridview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gridview = (GridView) findViewById(R.id.gridView);

		List<Colors> list = getColorList();
		if (list != null && list.size() > 0) {
			// display list
			displayGrid(list);
		} else {
			// add to DB and display
			setupFromDB();
			displayGrid(getColorList());
		}

	}

	private void displayGrid(List<Colors> list) {
		if (list != null && list.size() > 0) {
			gridview.setAdapter(new ColorsAdapter(getApplicationContext(), 0,
					list));

			gridview.setOnItemLongClickListener(new OnItemLongClickListener() {

				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// On long click to enable drag and drop listeners
					Toast.makeText(getApplicationContext(),
							"on long click" + position, Toast.LENGTH_LONG)
							.show();
					return false;
				}
			});

			gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// On click start a new activity using volley to download
					// data
					Intent intent = new Intent(getApplicationContext(),
							MyCardCLicked.class);
					startActivity(intent);
				}
			});
		}

	}

	private List<Colors> getColorList() {
		MyDataBase myDataBase = new MyDataBase(getApplicationContext());
		myDataBase.openDB();
		try {
			List<Colors> colors = myDataBase.getColortList();
			myDataBase.closeDB();
			return colors;
		} catch (Exception e) {
			return null;
		}

	}

	protected void setupFromDB() {
		MyDataBase myDataBase = new MyDataBase(getApplicationContext());
		myDataBase.openDB();
		myDataBase.insert("#ffff0000");// red
		myDataBase.insert("#FFFFBB33");// orange
		myDataBase.insert("#ffffff00");// yellow
		myDataBase.insert("#ff00ff00");// green
		myDataBase.insert("#ff0000ff");// blue
		myDataBase.insert("#FFAA66CC");// purple
		myDataBase.insert("#ffffffff");// white
		myDataBase.insert("#ff000000");// black
		myDataBase.closeDB();

	}

}
