package com.tlth.arkad13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class EventsViewActivity extends Activity {

	// List view
	private ListView lv;

	// Listview Adapter
	ArrayAdapter<String> adapter;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events_view);


		lv = (ListView) findViewById(R.id.list_view);

		// Skapar listan med Artiklar
		ApplicationClass appClass = ((ApplicationClass) getApplicationContext());
		Events el = appClass.getEventList();


		// Adding items to listview
		// adapter = new ArrayAdapter<String>(this, R.layout.list_item,
		// R.id.product_name, article.stringList());
		adapter = new CustomAdapter2(this, R.layout.events_list_item, el);
		lv.setAdapter(adapter);

		// listening to single list item on click
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(),
						EventsActivity.class);
				i.putExtra("position", position);

				startActivity(i);

			}
		});
	}
}