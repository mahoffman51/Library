package com.example.library.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Library {

	private List<Book> mLibrary;

	public Library() {
		mLibrary = new ArrayList<Book>();
		makeTestLibrary();
	}

	private void makeTestLibrary() {
		makeTestBook("Title", "First", "Last", "2000", "genre", "tag",
				"summary", "5", "yes");
		makeTestBook("A Title", "Z First", "A Last", "3000", "A genre",
				"Z tag", "A summary", "9", "no");
		makeTestBook("Z Title", "A First", "Z Last", "1000", "Z genre",
				"A tag", "Z summary", "1", "yes");
	}

	private void makeTestBook(String string, String string2, String string3,
			String string4, String string5, String string6, String string7,
			String string8, String string9) {
		String[] author = { string3, string2 };
		mLibrary.add(new Book(string, author, string4, string5, string6,
				string7, string8, string9));
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
