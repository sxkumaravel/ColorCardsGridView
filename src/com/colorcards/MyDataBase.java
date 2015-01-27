package com.colorcards;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase {

	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "color_database";
	private static final String TABLE_NAME = "mytable";

	private static final String COLOR_ID = "id";
	private static final String COLOR_NAME = "name";

	private Context context;
	private MyDatabaseHelper databaseHelper;
	private SQLiteDatabase database;
	private static final String TABLE_NAME_CREATE = "CREATE TABLE "
			+ TABLE_NAME + "(" + COLOR_ID
			+ " INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL," + COLOR_NAME
			+ " text " + ");";

	public MyDataBase(Context context) {
		this.context = context;
	}

	public void openDB() {
		databaseHelper = new MyDatabaseHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
		database = databaseHelper.getWritableDatabase();
	}

	public void closeDB() {
		database.close();
	}

	public void insert(String name) {
		ContentValues cv = new ContentValues();
		cv.put(COLOR_NAME, name);
		database.insert(TABLE_NAME, null, cv);
	}

	public void update(int Id, String color) {
		ContentValues cv = new ContentValues();
		cv.put(COLOR_NAME, color);
		database.update(TABLE_NAME, cv, " where " + COLOR_ID + "=?",
				new String[] { String.valueOf(Id) });
	}

	public void delete(int dbId) {
		database.delete(TABLE_NAME, COLOR_ID + " =?",
				new String[] { String.valueOf(dbId) });
	}

	public class MyDatabaseHelper extends SQLiteOpenHelper {

		public MyDatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(TABLE_NAME_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(" DROP TABLE IF EXIXT " + TABLE_NAME_CREATE);
		}
	}

	public List<Colors> getColortList() throws Exception {
		String statement = "SELECT * FROM " + TABLE_NAME;
		Cursor cursor = database.rawQuery(statement, null);
		List<Colors> colors = new ArrayList<Colors>();

		if (cursor == null || cursor.getCount() < -1) {
			cursor.close();
			return colors;
		}

		if (cursor != null && cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String name = cursor.getString(1);
				Colors color = new Colors(id, name);
				// Log.e("", "color code 1 "+name);
				colors.add(color);
			} while (cursor.moveToNext());
			cursor.close();
		}
		return colors;
	}
}
