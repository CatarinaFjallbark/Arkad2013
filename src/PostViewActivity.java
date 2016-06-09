package com.tlth.arkad13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PostViewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.post_activity);
		

		TextView head = (TextView) findViewById(R.id.head);
		TextView text = (TextView) findViewById(R.id.text);

		Intent i = getIntent();

		int position = i.getIntExtra("position", 0);

		ApplicationClass appClass = ((ApplicationClass) getApplicationContext());
		Posts posts = appClass.getArticles();
		Post post = posts.get(position);
		
		new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
        .execute(post.getPicture());


		head.setText(post.getName());
		text.setText(post.getContent());
	}
}