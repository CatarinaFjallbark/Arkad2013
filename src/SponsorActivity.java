package com.tlth.arkad13;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class SponsorActivity extends Activity {
	private static final String URL = "http://arkad.tlth.se/api/get_posts/?postType=sponsor";
	private static final String SPONSORS = "posts";
	private ArrayList<SponsorImage> sponsorList;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sponsors);

		sponsorList = new ArrayList<SponsorImage>();

		GridView gridview = (GridView) findViewById(R.id.gridview);
		ImageAdapter ia = new ImageAdapter(this);
		ia.setList(sponsorList);
		
		gridview.setAdapter(ia);

			try {

				JSONObject result = new JSONDownloader().execute(URL).get();

				JSONArray sponsors = result.getJSONArray(SPONSORS);

				for (int i = 0; i < sponsors.length(); i++) {
					SponsorImage sp = new SponsorImage(sponsors.getJSONObject(i));
					sponsorList.add(sp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		

	}
	

}
