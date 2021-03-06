package com.example.library.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import android.content.Context;

import com.example.library.database.BooksDataSource;

public class Library {

	private List<Book> mLibrary;

	public Library(Context c) {
		mLibrary = new ArrayList<Book>();
		// TODO: Add ability to make library from database and add books to this
		// list as well as database
		BooksDataSource bds = new BooksDataSource(c);
		bds.open();
		makeLibrary(bds);
		bds.close();
		// TODO: Also add ability to delete from library/database from here
	}

	private void makeLibrary(BooksDataSource bds) {
		mLibrary = bds.getAllBooks();
	}

	public List<Book> getLibrary() {
		return mLibrary;
	}

	public void setLibrary(List<Book> l) {
		mLibrary = l;
	}

	public List<Book> alphabetizeBy(String position) {
		Collections.sort(mLibrary, new BookComparator(position));
		return mLibrary;
	}

	public class BookComparator implements Comparator<Book> {

		String mToCompare;

		public BookComparator(String position) {
			mToCompare = position;
		}

		@Override
		public int compare(Book first, Book second) {
			int toReturn = 0;
			if (mToCompare.equals("Author (Last Name)")) {
				toReturn = first.getAuthors().get(0)[0].toLowerCase(
						Locale.ENGLISH).compareTo(
						second.getAuthors().get(0)[0]
								.toLowerCase(Locale.ENGLISH));
				if (toReturn == 0) {
					toReturn = first.getAuthors().get(0)[1].toLowerCase(
							Locale.ENGLISH).compareTo(
							second.getAuthors().get(0)[1]
									.toLowerCase(Locale.ENGLISH));
				}
			} else if (mToCompare.equals("Genre")) {
				toReturn = first
						.getGenres()
						.get(0)
						.toLowerCase(Locale.ENGLISH)
						.compareTo(
								second.getGenres().get(0)
										.toLowerCase(Locale.ENGLISH));
			} else if (mToCompare.equals("Publication Year")) {
				toReturn = -first
						.getYear()
						.toLowerCase(Locale.ENGLISH)
						.compareTo(second.getYear().toLowerCase(Locale.ENGLISH));
			} else if (mToCompare.equals("Rating")) {
				toReturn = -first
						.getRating()
						.toLowerCase(Locale.ENGLISH)
						.compareTo(
								second.getRating().toLowerCase(Locale.ENGLISH));
			} else if (mToCompare.equals("Tag")) {
				toReturn = first
						.getTags()
						.get(0)
						.toLowerCase(Locale.ENGLISH)
						.compareTo(
								second.getTags().get(0)
										.toLowerCase(Locale.ENGLISH));
			} else if (mToCompare.equals("Title")) {
				toReturn = first
						.getTitle()
						.toLowerCase(Locale.ENGLISH)
						.compareTo(
								second.getTitle().toLowerCase(Locale.ENGLISH));
			}
			if (toReturn != 0) {
				return toReturn;
			}
			return first.getAuthors().get(0)[0].toLowerCase(Locale.ENGLISH)
					.compareTo(
							second.getAuthors().get(0)[0]
									.toLowerCase(Locale.ENGLISH));
		}

	}

}
