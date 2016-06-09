package com.tlth.arkad13;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Event extends JSONItem{

	private static final String TITLE = "title_plain";
	private static final String DATE = "event_date";
	private static final String INFO = "custom_fields";
	private static final String PODIO =	"event_podio_id";
	private static final String PODIOURL =	"event_podio_url";
	private static final String ATTACH = "attachments";
	private static final String URL = "url";




	private JSONObject info;


	public Event(JSONObject json) {
		super(json);
		try {
			info = json.getJSONObject(INFO);
		} catch (JSONException e) {
			e.printStackTrace();
		}	}

	public String getName() {
		try {
			return json.getString(TITLE);
		} catch (JSONException e) {
			return "";
		}
	}

	public String getDate() {
		try {
			return info.getJSONArray(DATE).getString(0);
		} catch (JSONException e) {
			return "";
		}
	}


	public int getWebbId() {
		try {
			return info.getJSONArray(PODIO).getInt(0);
		} catch (JSONException e) {
			return -1;
		}
	}
	
	public String getPodio() {
		try {
			return info.getJSONArray(PODIOURL).getString(0);
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

