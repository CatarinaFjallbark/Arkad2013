package com.tlth.arkad13;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class EventsActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.event_activity);

		TextView head = (TextView) findViewById(R.id.head);
		TextView text = (TextView) findViewById(R.id.text);
		TextView txtWebb = (TextView) findViewById(R.id.anmal);
		ImageView iv = (ImageView) findViewById(R.id.anmal1);


		Intent i = getIntent();
		int position = i.getIntExtra("position", 0);

		// Skapar listan med Artiklar
		ApplicationClass appClass = ((ApplicationClass) getApplicationContext());
		Events events = appClass.getEventList();
		Event event = events.get(position);

		new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
        .execute(event.getPicture());
		head.setText(event.getName());
		text.setText(event.getContent());
		
		int webbId = event.getWebbId();

		final String s = event.getPodio();

		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(s));
					startActivity(browserIntent);
			}

		});
		
		
		if (webbId == -2) {
			txtWebb.setText("Eventanmälan är tyvärr stängd");
		} else if (webbId == -1) {
			txtWebb.setText("Se hemsidan för anmälan");
		} else {
			iv.setImageResource(R.drawable.anmalan);
		}

	}
}