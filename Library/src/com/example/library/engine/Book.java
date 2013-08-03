package com.example.library.engine;

import java.util.ArrayList;
import java.util.List;

public class Book {

	public static final long USER_ID_FLAG = -1;

	private long mId;
	private String mTitle;
	private List<String[]> mAuthors;
	private String mYear;
	private List<String> mGenres;
	private List<String> mTags;
	private String mSummary;
	private String mRating;
	private String mIsRead;

	public Book(long id, String title, String author, String year,
			String genre, String tag, String summary, String rating, String read) {
		mId = id;
		mTitle = title;
		mAuthors = convertStringAuthor(author);
		mYear = year;
		mGenres = convertStringGenre(genre);
		mTags = convertStringTag(tag);
		mSummary = summary;
		mRating = rating;
		mIsRead = read;
	}

	private List<String> convertStringTag(String tag) {
		// TODO Actually make list
		List<String> tags = new ArrayList<String>();
		tags.add(tag);
		return tags;
	}

	private List<String> convertStringGenre(String genre) {
		// TODO Actually make list
		List<String> genres = new ArrayList<String>();
		genres.add(genre);
		return genres;
	}

	private List<String[]> convertStringAuthor(String author) {
		// TODO Actually make list
		List<String[]> authors = new ArrayList<String[]>();
		String[] authorArray = { author, "" };
		authors.add(authorArray);
		return authors;
	}

	public long getId() {
		return mId;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public List<String[]> getAuthors() {
		return mAuthors;
	}

	public void setAuthors(List<String[]> authors) {
		mAuthors = authors;
	}

	public String getYear() {
		return mYear;
	}

	public void setYear(String year) {
		if (year != null)
			mYear = year;
		else
			mYear = "";
	}

	public List<String> getGenres() {
		return mGenres;
	}

	public void setGenres(List<String> genres) {
		if (genres.isEmpty())
			genres.add("");
		mGenres = genres;
	}

	public List<String> getTags() {
		return mTags;
	}

	public void setTags(List<String> tags) {
		if (tags.isEmpty())
			tags.add("");
		mTags = tags;
	}

	public String getSummary() {
		return mSummary;
	}

	public void setSummary(String summary) {
		if (summary != null)
			mSummary = summary;
		else
			mSummary = "";
	}

	public String getRating() {
		return mRating;
	}

	public void setRating(String rating) {
		if (rating != null)
			mRating = rating;
		else
			mRating = "";
	}

	public String getIsRead() {
		return mIsRead;
	}

	public void setIsRead(String isRead) {
		if (isRead != null)
			mIsRead = isRead;
		else
			mIsRead = "";
	}

	public String getAuthorsString() {
		// TODO: Make this actually get all authors
		// Account situations of one-named author (i.e. Plato)
		return mAuthors.get(0)[0];
	}

	public String getGenresString() {
		// TODO: Make this really list all genres
		return mGenres.get(0);
	}

	public String getTagsString() {
		// TODO: Make this really list all tags
		return mTags.get(0);
	}

	public String displayAuthorsFirstThenLast() {
		// TODO: Make this display all the authors as desired in the selected
		// book view. i.e. "John Smith, Adam Wells"
		return getAuthorsString();
	}
}
