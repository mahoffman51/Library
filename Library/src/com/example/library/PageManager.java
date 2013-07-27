package com.example.library;

import android.view.View;
import android.widget.ImageView;

import com.example.library.engine.Book;
import com.example.library.pages.EditPage;
import com.example.library.pages.LibraryPage;
import com.example.library.pages.SearchPage;

public class PageManager {

	private LibraryActivity mActivity;
	private LibraryPage mLibraryPage;
	private SearchPage mSearchPage;

	public PageManager(LibraryActivity activity) {
		mActivity = activity;
		initializePages();
		setUpListeners();
	}

	public void searchEntry() {
		Book b = mSearchPage.parseSearchEntries();
		boolean toggle = mSearchPage.getToggleState();
		mActivity.setContentView(R.layout.activity_library);
		mLibraryPage.searchLibrary(b, toggle);
		mLibraryPage.setUpSpinner();
		setUpListeners();
	}

	private void initializePages() {
		mLibraryPage = new LibraryPage(mActivity);
		mSearchPage = new SearchPage(mActivity);
		new EditPage(mActivity);
	}

	public void setUpListeners() {
		ImageView mainNavBarHome = (ImageView) mActivity
				.findViewById(R.id.main_nav_home_imageview);
		mainNavBarHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO: Make color of icon change on click
				mActivity.setContentView(R.layout.activity_library);
				setUpListeners();
				mLibraryPage.resetLibrary();
				mLibraryPage.setUpSpinner();
			}
		});

		ImageView mainNavBarSearch = (ImageView) mActivity
				.findViewById(R.id.main_nav_search_imageview);
		mainNavBarSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO: Make color of icon change on click
				mActivity.setContentView(R.layout.search_book);
				setUpListeners();
				mSearchPage.setUpSearchMenu();
			}
		});

		ImageView mainNavBarEdit = (ImageView) mActivity
				.findViewById(R.id.main_nav_edit);
		mainNavBarEdit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO: Make color of icon change on click
				mActivity.setContentView(R.layout.edit_book);
				setUpListeners();
			}
		});
	}

}
