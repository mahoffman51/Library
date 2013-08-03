package com.example.library.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String ID = "_id";

	public static final String TABLE_TITLE = "titles";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_AUTHOR = "author";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_GENRE = "genre";
	public static final String COLUMN_TAG = "tag";
	public static final String COLUMN_SUMMARY = "summary";
	public static final String COLUMN_RATING = "rating";
	public static final String COLUMN_IS_READ = "is_read";

	private static final String NOT_NULL = " text not null, ";
	private static final String TEXT = " text, ";
	public static final String DATABASE_NAME = "books.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table " + TABLE_TITLE
			+ "(" + ID + " integer primary key autoincrement, " + COLUMN_TITLE
			+ NOT_NULL + COLUMN_AUTHOR + NOT_NULL + COLUMN_YEAR + TEXT
			+ COLUMN_GENRE + TEXT + COLUMN_TAG + TEXT + COLUMN_SUMMARY + TEXT
			+ COLUMN_RATING + TEXT + COLUMN_IS_READ + " text " + ");";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Log.w(MySQLiteHelper.class.getName(),
		// "Upgrading database from version " + oldVersion + " to "
		// + newVersion + ", which will destroy all old data");
		// db.execSQL("DROP TABLE IF EXISTS " + TABLE_TITLE);
		// onCreate(db);
	}

}