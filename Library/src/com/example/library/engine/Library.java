package com.example.library.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Library {

	private List<Book> mLibrary;
	
	public Library() {
		mLibrary = new ArrayList<Book>();
		//TODO: Make Dummy Library for testing
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
				toReturn = first.getAuthors().get(0)[0].compareTo(second
						.getAuthors().get(0)[0]);
				if (toReturn == 0) {
					toReturn = first.getAuthors().get(0)[1].compareTo(second
							.getAuthors().get(0)[1]);
				}
			} else if (mToCompare.equals("Genre")) {
				toReturn = first.getGenres().get(0)
						.compareTo(second.getGenres().get(0));
			} else if (mToCompare.equals("Publication Year")) {
				toReturn = -first.getYear().compareTo(second.getYear());
			} else if (mToCompare.equals("Rating")) {
				toReturn = -first.getRating().compareTo(second.getRating());
			} else if (mToCompare.equals("Tag")) {
				toReturn = first.getTags().get(0)
						.compareTo(second.getTags().get(0));
			} else if (mToCompare.equals("Title")) {
				toReturn = first.getTitle().compareTo(second.getTitle());
			}
			if (toReturn != 0) {
				return toReturn;
			}
			return first.getAuthors().get(0)[0].compareTo(second.getAuthors()
					.get(0)[0]);
		}

	}

}
