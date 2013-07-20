package com.example.library.engine;


import java.util.ArrayList;
import java.util.List;

public class Book {

	private String mTitle;
	private List<String[]> mAuthors;
	private String mYear;
	private List<String> mGenres;
	private List<String> mTags;
	private String mSummary;
	private String mRating;
	private String mIsRead;

	public Book(String title, String[] authorArray, String year, String genre,
			String tag, String summary, String rating, String read) {
		setTitle(title);
		List<String[]> authors = new ArrayList<String[]>();
		authors.add(authorArray);
		setAuthors(authors);
		setYear(year);
		List<String> genres = new ArrayList<String>();
		genres.add(genre);
		setGenres(genres);
		List<String> tags = new ArrayList<String>();
		tags.add(tag);
		setTags(tags);
		setSummary(summary);
		setRating(rating);
		setIsRead(read);
	}

	public Book(String title, List<String[]> authors, String year,
			List<String> genres, List<String> tags, String summary,
			String rating, String read) {
		setTitle(title);
		setAuthors(authors);
		setYear(year);
		setGenres(genres);
		setTags(tags);
		setSummary(summary);
		setRating(rating);
		setIsRead(read);
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
}
