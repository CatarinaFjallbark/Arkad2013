package com.tlth.arkad13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PostsViewActivity extends Activity {

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.posts_view);

		ListView lv = (ListView) findViewById(R.id.list_view);

		// Skapar listan med Artiklar
		ApplicationClass appClass = ((ApplicationClass) getApplicationContext());
		Posts posts = appClass.getArticles();

		// Adding items to listview
		ArrayAdapter<String> adapter = new CustomAdapter(this,
				R.layout.posts_list_item, posts);
		lv.setAdapter(adapter);

		// listening to single list item on click
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(),
						PostViewActivity.class);
				i.putExtra("position", position);
				startActivity(i);
			}
		});
	}
}