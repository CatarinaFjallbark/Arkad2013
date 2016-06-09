package com.tlth.arkad13;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Posts extends ArrayList<Post> {
	private static final String URL = "http://arkad.tlth.se/api/get_posts/";
	private static final String POSTS = "posts";
	
	public Posts() {
		try	{
			JSONObject result = new JSONDownloader().execute(URL).get();
			JSONArray postsData = result.getJSONArray(POSTS);

			for (int i = 0; i < postsData.length(); i++) {
				add(new Post(postsData.getJSONObject(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> stringList() {
		ArrayList<String> stringList = new ArrayList<String>();
		for (Post n : this) {
			stringList.add(n.getName());
		}
		return stringList;
	}
}
