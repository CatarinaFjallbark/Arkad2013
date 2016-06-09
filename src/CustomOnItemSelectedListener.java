package com.tlth.arkad13;

import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {
	private Companies c;
	private ArrayList <String> filtered;
	private String bransch;
	private String last;


	public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
		

		bransch = parent.getItemAtPosition(pos).toString();
		last=bransch;

	}
	
	public String getStringB(){
		
		return bransch;
	}
	
	public String getLast(){
		return last;
	}

	
	public ArrayList <String> getFilteredB(){
		Log.d("CattaTag", bransch);

		filtered = c.getStringFilterB(bransch);

		return filtered;
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	public void setCompanies(Companies headLine) {
		// TODO Auto-generated method stub
		this.c=headLine;

	}

}