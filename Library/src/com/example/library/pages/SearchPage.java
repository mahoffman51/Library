package com.example.library.pages;

import java.util.HashMap;
import java.util.Map;

import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.library.LibraryActivity;
import com.example.library.R;
import com.example.library.engine.Book;

public class SearchPage {

	public static final String AUTHOR = "Author";
	public static final String TITLE = "Title";
	public static final String YEAR = "Year";
	public static final String GENRE = "Genre";
	public static final String TAG = "Tag";
	public static final String SUMMARY = "Summary";
	public static final String HAVE_READ = "Have read";
	public static final String RATING = "Rating";

	private LibraryActivity mActivity;
	private Map<String, EditText> mSearchResponse = new HashMap<String, EditText>();

	public SearchPage(LibraryActivity activity) {
		mActivity = activity;
	}

	public void setUpSearchMenu() {
		LinearLayout ll = (LinearLayout) mActivity
				.findViewById(R.id.main_search_books);
		ll.setOrientation(LinearLayout.VERTICAL);
		addExplanation(ll);
		setUpSingleField(ll, AUTHOR, "Last Name");
		setUpSingleField(ll, TITLE, "All or part of title");
		setUpSingleField(ll, YEAR, "");
		setUpSingleField(ll, GENRE, "");
		setUpSingleField(ll, TAG, "");
	}

	private void addExplanation(LinearLayout ll) {
		// TODO: Change this to an explanation of search instead of buffer
		// Stuff to include: using partial words, separate searches by comma
		// AND/OR checkmark: either all things must be in each book or any of
		// them
		TextView buffer = new TextView(mActivity.getApplicationContext());
		buffer.setHeight(100);
		ll.addView(buffer);
	}

	private void setUpSingleField(LinearLayout ll, String subject, String hint) {
		LinearLayout singleLayout = new LinearLayout(
				mActivity.getApplicationContext());
		singleLayout.setPadding(0, 10, 0, 0);
		singleLayout.setOrientation(LinearLayout.HORIZONTAL);
		TextView search = new TextView(mActivity.getApplicationContext());
		search.setLayoutParams(new LayoutParams(200, LayoutParams.WRAP_CONTENT));
		search.setPadding(10, 0, 10, 0);
		search.setTextSize(16);
		search.setText(subject + ": ");
		EditText parameter = new EditText(mActivity.getApplicationContext());
		parameter.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		parameter.setHint(hint);
		singleLayout.addView(search);
		singleLayout.addView(parameter);
		mSearchResponse.put(subject, parameter);
		ll.addView(singleLayout);
	}

	public Book parseSearchEntries() {
		return new Book(Book.USER_ID_FLAG, parseEntry(TITLE), parseEntry(AUTHOR), parseEntry(YEAR),
				parseEntry(GENRE), parseEntry(TAG), null, null, null);
	}

	private String parseEntry(String label) {
		return mSearchResponse.get(label).getText().toString();
	}

	public boolean getToggleState() {
		Switch s = (Switch) mActivity.findViewById(R.id.search_and_or_toggle);
		return s.isChecked();
	}
}
