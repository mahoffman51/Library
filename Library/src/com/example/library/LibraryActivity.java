package com.example.library;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class LibraryActivity extends Activity {

	private PageManager mPageManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library);
		initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.library, menu);
		return true;
	}

	private void initialize() {
		mPageManager = new PageManager(this);
	}

	public PageManager getPageManager() {
		return mPageManager;
	}

	public void searchEntries(View view) {
		mPageManager.searchEntry();
	}

	public void saveEntry(View view) {
		mPageManager.saveBook();
	}

	public void deleteEntry(View view) {
		mPageManager.deleteBook();
	}

	public void editExistingBook(View view) {
		mPageManager.editExistingBook();
	}
}
