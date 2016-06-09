package com.tlth.arkad13;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SelectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		
	
		ImageButton news = (ImageButton) findViewById(R.id.Senaste);
		news.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SelectActivity.this, PostsViewActivity.class);
				ImageButton news = (ImageButton) findViewById(R.id.Senaste);
				news.setImageResource(R.drawable.senaste);
				startActivity(intent);			
			}

		});
		
		ImageButton catalog = (ImageButton) findViewById(R.id.Catalog);
		catalog.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SelectActivity.this, CatalogActivity.class);
				ImageButton news = (ImageButton) findViewById(R.id.Catalog);
				news.setImageResource(R.drawable.katalog);

				startActivity(intent);				
			}

		});

		ImageButton sponsors = (ImageButton) findViewById(R.id.Sponsorer);
		sponsors.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SelectActivity.this, SponsorActivity.class);
				ImageButton news = (ImageButton) findViewById(R.id.Sponsorer);
				news.setImageResource(R.drawable.sponsorer);

				startActivity(intent);				
			}

		});

		ImageButton contact = (ImageButton) findViewById(R.id.Kontakt);
		contact.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SelectActivity.this, ContactActivity.class);
				startActivity(intent);				
			}

		});

		ImageButton event = (ImageButton) findViewById(R.id.Event);
		event.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SelectActivity.this, EventsViewActivity.class);
				ImageButton news = (ImageButton) findViewById(R.id.Event);
				news.setImageResource(R.drawable.events);

				startActivity(intent);				
			}

		});
	}
	
	
	protected void onResume(){
		super.onResume();
		ImageButton news = (ImageButton) findViewById(R.id.Senaste);
		news.setImageResource(R.drawable.senastebtn);
		ImageButton event = (ImageButton) findViewById(R.id.Event);
		event.setImageResource(R.drawable.eventbtn);
		ImageButton catalog = (ImageButton) findViewById(R.id.Catalog);
		catalog.setImageResource(R.drawable.katalogbtn);
		ImageButton sponsorer = (ImageButton) findViewById(R.id.Sponsorer);
		sponsorer.setImageResource(R.drawable.sponsorbtn);
	}
}
