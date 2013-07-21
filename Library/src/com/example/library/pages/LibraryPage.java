package com.example.library.pages;

import java.util.List;

import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.library.LibraryActivity;
import com.example.library.R;
import com.example.library.engine.Book;
import com.example.library.engine.Library;

public class LibraryPage {

	private LibraryActivity mActivity;
	private Library mLibrary;
	private List<Book> mBookList;

	public LibraryPage(LibraryActivity activity) {
		mActivity = activity;
		setUpSpinner();
		setUpLibrary();
	}

	private void setUpLibrary() {
		mLibrary = new Library();
	}

	public void setUpSpinner() {
		Spinner spinner = (Spinner) mActivity.findViewById(R.id.main_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				mActivity.getApplicationContext(), R.array.selections_array,
				R.layout.spinner_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new MainOnItemSelectedListener());
	}

	public class MainOnItemSelectedListener implements OnItemSelectedListener {

		// TODO: Clean this class up, it is really ugly right now

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int pos,
				long row) {

			String position = parent.getItemAtPosition(pos).toString();

			mBookList = mLibrary.alphabetizeBy(position);

			LinearLayout ll = (LinearLayout) mActivity
					.findViewById(R.id.main_view_books);
			ll.removeAllViews();
			for (final Book book : mBookList) {
				LinearLayout bookLL = setUpBookLayout(ll, book);

				TextView tv = getTextViewTitle(book);
				bookLL.addView(tv);

				TextView tv2 = getTextViewAuthors(book);
				bookLL.addView(tv2);

				TextView tv3 = chooseLastView(position, book);
				if (tv3 != null) {
					bookLL.addView(tv3);
				}

				TextView buffer = new TextView(
						mActivity.getApplicationContext());
				buffer.setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, 30));
				bookLL.addView(buffer);

				ll.addView(bookLL);
				View line = new View(mActivity.getApplicationContext());
				line.setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT, 1));
				line.setBackgroundColor(Color.WHITE);
				ll.addView(line);
			}
		}

		private LinearLayout setUpBookLayout(LinearLayout ll, final Book book) {
			LinearLayout bookLL = new LinearLayout(ll.getContext());
			bookLL.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			bookLL.setPadding(10, 0, 10, 0);
			bookLL.setOrientation(LinearLayout.VERTICAL);
			bookLL.setClickable(true);
			bookLL.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mActivity.setContentView(R.layout.book_selected);
					TextView title = (TextView) mActivity
							.findViewById(R.id.book_title);
					title.setText(Html.fromHtml("<i>" + book.getTitle()
							+ "</i>"));
					TextView author = (TextView) mActivity
							.findViewById(R.id.book_author);
					author.setText("by " + book.getAuthors().get(0)[1] + " "
							+ book.getAuthors().get(0)[0]);
					TextView year = (TextView) mActivity
							.findViewById(R.id.book_publication);
					year.setText(Html.fromHtml("<b>" + year.getText() + "</b> "
							+ book.getYear()));
					TextView genre = (TextView) mActivity
							.findViewById(R.id.book_genre);
					genre.setText(Html.fromHtml("<b>" + genre.getText()
							+ "</b> " + book.getGenres().get(0)));
					TextView summary = (TextView) mActivity
							.findViewById(R.id.book_summary);
					summary.setText(Html.fromHtml("<b>" + summary.getText()
							+ "</b> " + book.getSummary()));
					TextView tag = (TextView) mActivity
							.findViewById(R.id.book_tags);
					tag.setText(Html.fromHtml("<b>" + tag.getText() + "</b> "
							+ book.getTags().get(0)));
					TextView isRead = (TextView) mActivity
							.findViewById(R.id.book_is_read);
					isRead.setText(Html.fromHtml("<b>" + isRead.getText()
							+ "</b> " + book.getIsRead()));
					TextView rating = (TextView) mActivity
							.findViewById(R.id.book_rating);
					rating.setText(Html.fromHtml("<b>" + rating.getText()
							+ "</b> " + book.getRating()));
					mActivity.getPageManager().setUpListeners();
				}
			});
			return bookLL;
		}

		private TextView getTextViewAuthors(final Book book) {
			TextView tv2 = new TextView(
					mActivity.getApplicationContext());
			tv2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			tv2.setText(book.getAuthors().get(0)[0] + ", "
					+ book.getAuthors().get(0)[1]);
			tv2.setTextSize(14);
			tv2.setPadding(50, 0, 0, 0);
			tv2.setTextColor(Color.LTGRAY);
			return tv2;
		}

		private TextView getTextViewTitle(final Book book) {
			TextView tv = new TextView(
					mActivity.getApplicationContext());
			tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			tv.setText(book.getTitle());
			tv.setTextSize(16);
			tv.setTextColor(Color.WHITE);
			return tv;
		}

		private TextView chooseLastView(String position, Book book) {
			TextView tv = new TextView(
					mActivity.getApplicationContext());
			tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			tv.setTextSize(14);
			tv.setPadding(50, 0, 0, 0);
			tv.setTextColor(Color.LTGRAY);
			if (position.equals("Genre")) {
				tv.setText(book.getGenres().get(0));
			} else if (position.equals("Publication Year")) {
				tv.setText(book.getYear());
			} else if (position.equals("Rating")) {
				tv.setText(book.getRating());
			} else if (position.equals("Tag")) {
				tv.setText(book.getTags().get(0));
			} else {
				tv = null;
			}
			return tv;
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// do nothing
		}
	}

}
