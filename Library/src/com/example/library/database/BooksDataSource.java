package com.example.library.database;

import java.util.ArrayList;
import java.util.List;

import com.example.library.engine.Book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BooksDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.ID,
			MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_AUTHOR,
			MySQLiteHelper.COLUMN_YEAR, MySQLiteHelper.COLUMN_GENRE,
			MySQLiteHelper.COLUMN_TAG, MySQLiteHelper.COLUMN_SUMMARY,
			MySQLiteHelper.COLUMN_RATING, MySQLiteHelper.COLUMN_IS_READ };

	public BooksDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Book createBook(String title, String author, String year,
			String genre, String tags, String summary, String rating,
			String isRead) {
		// TODO: parse these string for actual content (i.e. split author string
		// to first and last name
		Log.v("Author Test", "createBook, BooksDataSource  " + author);
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_TITLE, title);
		values.put(MySQLiteHelper.COLUMN_AUTHOR, author);
		values.put(MySQLiteHelper.COLUMN_GENRE, genre);
		values.put(MySQLiteHelper.COLUMN_IS_READ, isRead);
		values.put(MySQLiteHelper.COLUMN_RATING, rating);
		values.put(MySQLiteHelper.COLUMN_SUMMARY, summary);
		values.put(MySQLiteHelper.COLUMN_TAG, tags);
		values.put(MySQLiteHelper.COLUMN_YEAR, year);
		open();
		long insertId = database.insert(MySQLiteHelper.TABLE_TITLE, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_TITLE, allColumns,
				MySQLiteHelper.ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Book newBook = cursorToBook(cursor);
		cursor.close();
		return newBook;
	}

	public void deleteBook(Book b) {
		long id = b.getId();
		open();
		database.delete(MySQLiteHelper.TABLE_TITLE, MySQLiteHelper.ID + "='"
				+ id + "'", null);
		close();
	}

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_TITLE, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Book b = cursorToBook(cursor);
			books.add(b);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return books;
	}

	private Book cursorToBook(Cursor cursor) {
		return new Book(cursor.getLong(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7),
				cursor.getString(8));
	}
}