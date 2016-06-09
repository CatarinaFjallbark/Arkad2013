package com.tlth.arkad13;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;



@SuppressWarnings("serial")
public class Events extends ArrayList<Event> {
	private static final String URL = "http://arkad.tlth.se/api/get_posts/?postType=event";
	private static final String POSTS = "posts";
	
	public Events() {
		try	{
			JSONObject result = new JSONDownloader().execute(URL).get();
			JSONArray postsData = result.getJSONArray(POSTS);

			for (int i = 0; i < postsData.length(); i++) {
				add(new Event(postsData.getJSONObject(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> stringList() {
		ArrayList<String> stringList = new ArrayList<String>();
		for (Event n : this) {
			stringList.add(n.getName());
		}
		return stringList;
	}
}

