package com.tlth.arkad13;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Post extends JSONItem{

	private static final String TITLE = "title_plain";
	private static final String DATE = "date";
	private static final String ATTACH = "attachments";
	private static final String URL = "url";

	
	public Post(JSONObject json) {
		super(json);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		try {
			return json.getString(TITLE);
		} catch (JSONException e) {
			return "";
		}
	}

	public String getDate() {
		try {
			return json.getString(DATE);
		} catch (JSONException e) {
			return "";
		}
	}
	
	public String getPicture(){
		try {
			JSONArray ja = json.getJSONArray(ATTACH);
			JSONObject attach = ja.getJSONObject(0);

			return attach.getString(URL);
		} catch (JSONException e) {
			return "";
		}
	}
}
