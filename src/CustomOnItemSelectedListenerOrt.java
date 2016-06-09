package com.tlth.arkad13;

import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CustomOnItemSelectedListenerOrt implements OnItemSelectedListener {
	private Companies c;
	private ArrayList<String> filtered;
	private String news;
	private String last;

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		news = parent.getItemAtPosition(pos).toString();
		last = news;

	}

	public String getStringO() {
		return news;
	}

	public String getLast() {
		return last;
	}

	public ArrayList<String> getFilteredO() {
		Log.d("CattaTag", news);

		filtered = c.getStringFilterO(news);

		return filtered;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	public void setCompanies(Companies headLine) {
		// TODO Auto-generated method stub
		this.c = headLine;

	}

}