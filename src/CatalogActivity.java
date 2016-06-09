package com.tlth.arkad13;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Spinner;
import android.widget.Toast;

public class CatalogActivity extends Activity {

	// List view
	private ListView lv;
	private Companies headLine;
	private Button b;
	private Button filter;

	private CustomOnItemSelectedListener itemSelectedB;
	private CustomOnItemSelectedListenerOrt itemSelectedO;

	private ArrayList<String> filtered;

	// Listview Adapter
	ArrayAdapter<String> adapter;
	ArrayAdapter<String> adapter2;
	ArrayAdapter<String> adapter3;

	// Search EditText
	EditText inputSearch;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalog);
		// Skapar listan med företag och favouriter
		ApplicationClass appClass = ((ApplicationClass) getApplicationContext());
		headLine = appClass.getHead();


		if (headLine.size() == 0) {
			try {
				headLine.loadData();
			} catch (TimeoutException e) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Du verkar ej ha tillgång till Internet", Toast.LENGTH_LONG);
	
				toast.setGravity(Gravity.CENTER_VERTICAL
						| Gravity.CENTER_HORIZONTAL, 0, 0);
				toast.show();			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		filtered = new ArrayList<String>();

		final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

		ArrayList<String> spinnerB = new ArrayList<String>();
		spinnerB = headLine.getBranches();

		ArrayList<String> spinnerO = new ArrayList<String>();
		spinnerO = headLine.getLocations();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, spinnerB);
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, spinnerO);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dataAdapter2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner1.setAdapter(dataAdapter);
		spinner2.setAdapter(dataAdapter2);

		itemSelectedB = new CustomOnItemSelectedListener();
		itemSelectedB.setCompanies(headLine);

		itemSelectedO = new CustomOnItemSelectedListenerOrt();
		itemSelectedO.setCompanies(headLine);

		spinner1.setOnItemSelectedListener(itemSelectedB);
		spinner2.setOnItemSelectedListener(itemSelectedO);

		lv = (ListView) findViewById(R.id.list_view);
		inputSearch = (EditText) findViewById(R.id.inputSearch);

		b = (Button) findViewById(R.id.favvo);
		filter = (Button) findViewById(R.id.sok);

		// Adding items to listview
		adapter = new ArrayAdapter<String>(this, R.layout.catalog_list_item,
				R.id.product_name, headLine.stringList());
		adapter2 = new ArrayAdapter<String>(getApplication(),
				R.layout.catalog_list_item, R.id.product_name,
				headLine.getFavvoStrings());
		
		Log.d("AlexTag", String.valueOf(headLine.getFavvoStrings()));

	
		adapter3 = new ArrayAdapter<String>(this, R.layout.catalog_list_item,
				R.id.product_name, filtered);
		lv.setAdapter(adapter);


		/**
		 * Enabling Search Filter
		 * */
		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				CatalogActivity.this.adapter.getFilter().filter(cs);

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				CatalogActivity.this.adapter2.getFilter().filter(cs);

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				CatalogActivity.this.adapter3.getFilter().filter(cs);

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Skapar onClicklistner på knappen
		b.setOnClickListener(new OnClickListener() {
			Boolean isClicked = false;

			@Override
			public void onClick(View v) {

				if (isClicked) {
					lv.setAdapter(adapter);
					isClicked = false;
					b.setText("Favoriter");
					filter.setBackgroundColor(Color.parseColor("#004080"));
					spinner1.setSelection(0);
					spinner2.setSelection(0);



				} else {

					if (!headLine.getFavvoComp().isEmpty()) {
						lv.setAdapter(adapter2);
						isClicked = true;
						b.setText("Alla");
						filter.setBackgroundColor(Color.GRAY);
						//inputSearch.setText("");

					} else {

						Toast toast = Toast.makeText(v.getContext(),
								"Inga valda favoriter", Toast.LENGTH_SHORT);
			
						toast.setGravity(Gravity.CENTER_VERTICAL
								| Gravity.CENTER_HORIZONTAL, 0, 0);
						toast.show();
					}
				}
			}
		});

		// Skapar onClicklistner på knappen
		filter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String bransch = itemSelectedB.getStringB();
				String ort = itemSelectedO.getStringO();

				if (bransch == "Alla branscher" && ort == "Alla") {
					lv.setAdapter(adapter);



				} else if (bransch == "Alla branscher" || ort == "Alla") {
					setAdapter3();
					lv.setAdapter(adapter3);


				} else {

					setDubble3();
					lv.setAdapter(adapter3);

				}
			}
		});

		// listening to single list item on click
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// selected item
				String company = (String) (lv.getItemAtPosition(position));
				Company comp = headLine.getCompanyByName(company);

				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(),
						CompanyViewActivity.class);
				// sending data to new activity
				i.putExtra("companies", company);
				i.putExtra("favvo", comp.isFavvo());
				i.putExtra("position", position);

				startActivity(i);

			}
		});

	}

	public void setAdapter3() {
		ArrayList<String> filteredB = new ArrayList<String>();
		ArrayList<String> filteredO = new ArrayList<String>();
		filtered.clear();
		adapter3.clear();

		filteredB = itemSelectedB.getFilteredB();
		filteredO = itemSelectedO.getFilteredO();


		filtered = filteredB;
		Log.d("CattaTag", String.valueOf(adapter3.getCount()));

		filtered.addAll(filteredO);
		Log.d("CattaTag", String.valueOf(filtered.size()));


		for (String companyName : filtered) {
			adapter3.add(companyName);
		}

	}

	public void setDubble3() {
		ArrayList<String> filteredB = new ArrayList<String>();
		ArrayList<String> filteredO = new ArrayList<String>();
		filtered.clear();
		adapter3.clear();

		filteredB = itemSelectedB.getFilteredB();
		filteredO = itemSelectedO.getFilteredO();

		ArrayList<String> filteredZ = new ArrayList<String>();

		for (int i = 0; i < filteredB.size(); i++) {
			for (int j = 0; j < filteredO.size(); j++) {
				if (filteredB.get(i).equals(filteredO.get(j))) {
					filteredZ.add(filteredB.get(i));
				}
			}
		}
	filtered = new ArrayList<String>(filteredZ);
		Log.d("HejHopp", String.valueOf(filtered.size()));


		for (String companyName : filtered) {
			adapter3.add(companyName);
		}
	}
	
	protected void onResume() {
		super.onResume();

		adapter2.clear();

		
		ArrayList<String> companyStrings = headLine.getFavvoStrings();

		for (String companyName : companyStrings) {
			adapter2.add(companyName);
		}
	}
}