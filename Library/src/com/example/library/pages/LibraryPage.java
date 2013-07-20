package com.example.library.pages;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.library.LibraryActivity;
import com.example.library.R;

public class LibraryPage {

	private LibraryActivity mActivity;

	public LibraryPage(LibraryActivity activity) {
		mActivity = activity;
		setUpSpinner();
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

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int pos,
				long row) {
			String position = parent.getItemAtPosition(pos).toString();
			//TODO: use position to alphabetize entries
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// Do Nothing
		}

	}

}