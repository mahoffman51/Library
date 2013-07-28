package com.example.library.pages;

import java.util.HashMap;
import java.util.Map;

import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.library.LibraryActivity;
import com.example.library.R;
import com.example.library.engine.Book;

public class EditPage {

	private LibraryActivity mActivity;
	private Map<String, EditText> mEditResponse = new HashMap<String, EditText>();

	public EditPage(LibraryActivity activity) {
		mActivity = activity;
	}

	public void setUpEditMenu(Book b) {
		LinearLayout ll = (LinearLayout) mActivity
				.findViewById(R.id.main_edit_book);
		if (b == null) {
			setUpSingleField(ll, SearchPage.TITLE, "", "");
			setUpSingleField(ll, SearchPage.AUTHOR, "", "");
			setUpSingleField(ll, SearchPage.YEAR, "", "");
			setUpSingleField(ll, SearchPage.GENRE, "", "");
			setUpSingleField(ll, SearchPage.SUMMARY, "", "");
			setUpSingleField(ll, SearchPage.TAG, "", "");
			setUpSingleField(ll, SearchPage.HAVE_READ, "", "");
			setUpSingleField(ll, SearchPage.RATING, "", "");
		} else {
			setUpSingleField(ll, SearchPage.TITLE, "", b.getTitle());
			setUpSingleField(ll, SearchPage.AUTHOR, "", b.getAuthorsString());
			setUpSingleField(ll, SearchPage.YEAR, "", b.getYear());
			setUpSingleField(ll, SearchPage.GENRE, "", b.getGenresString());
			setUpSingleField(ll, SearchPage.SUMMARY, "", b.getSummary());
			setUpSingleField(ll, SearchPage.TAG, "", b.getTagsString());
			setUpSingleField(ll, SearchPage.HAVE_READ, "", b.getIsRead());
			setUpSingleField(ll, SearchPage.RATING, "", b.getRating());
		}
	}

	private void setUpSingleField(LinearLayout ll, String subject, String hint,
			String text) {
		LinearLayout singleLayout = new LinearLayout(
				mActivity.getApplicationContext());
		singleLayout.setPadding(0, 10, 0, 0);
		singleLayout.setOrientation(LinearLayout.HORIZONTAL);
		TextView search = new TextView(mActivity.getApplicationContext());
		search.setLayoutParams(new LayoutParams(250, LayoutParams.WRAP_CONTENT));
		search.setPadding(10, 0, 10, 0);
		search.setTextSize(16);
		search.setText(subject + ": ");
		EditText parameter = new EditText(mActivity.getApplicationContext());
		parameter.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		parameter.setHint(hint);
		parameter.setText(text);
		singleLayout.addView(search);
		mEditResponse.put(subject, parameter);
		singleLayout.addView(parameter);
		ll.addView(singleLayout);
	}

	public Book getUserInput() {
		// TODO: check to make sure required fields (title, author, etc.) are
		// entered and validate all entries
		String title = mEditResponse.get(SearchPage.TITLE).getText().toString();
		String author = mEditResponse.get(SearchPage.AUTHOR).getText()
				.toString();
		String[] authorArray = { author, "" };
		String year = mEditResponse.get(SearchPage.YEAR).getText().toString();
		String genre = mEditResponse.get(SearchPage.GENRE).getText().toString();
		String tag = mEditResponse.get(SearchPage.TAG).getText().toString();
		String summary = mEditResponse.get(SearchPage.SUMMARY).getText()
				.toString();
		String rating = mEditResponse.get(SearchPage.RATING).getText()
				.toString();
		String isRead = mEditResponse.get(SearchPage.HAVE_READ).getText()
				.toString();
		return new Book(title, authorArray, year, genre, tag, summary, rating,
				isRead);
	}
}
