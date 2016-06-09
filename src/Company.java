package com.tlth.arkad13;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Company extends JSONItem {
	private static final String INFO = "custom_fields";
	private static final String TITLE = "title";
	private static final String WEBB = "studenthemsida";
	private static final String ORT = "taxonomy_verksamhetsorter";
	private static final String BRANCH = "taxonomy_bransch";
	private static final String FACEBOOK = "facebook";
	private static final String TWITTER = "twitter";
	private static final String LINKEDIN = "linkedin";
	private static final String YOUTUBE = "youtube";
	private static final String CUSTOM = "custom_fields";

	private static final String ATTACH = "attachments";
	private static final String IMAGES = "images";
	private static final String CAPTION = "caption";

	private static final String THUMBNAIL = "medium";
	private static final String URL = "url";

	private JSONObject info;
	private JSONArray brancher;
	private JSONArray orter;

	//private boolean isFavvo;

	public Company(JSONObject json) {
		super(json);


		try {
			info = json.getJSONObject(INFO);
			brancher = json.getJSONArray(BRANCH);
			orter = json.getJSONArray(ORT);


		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getMap() {
		try {
			JSONArray ja = json.getJSONArray(ATTACH);
			JSONObject attach = ja.getJSONObject(0);
			JSONObject images = attach.getJSONObject(IMAGES);
			JSONObject thumbnail = images.getJSONObject(THUMBNAIL);
			return thumbnail.getString(URL);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return "";
	}
	
	public String getImageTxt(){
		try {
			JSONArray ja = json.getJSONArray(ATTACH);
			JSONObject attach = ja.getJSONObject(0);
			return attach.getString(CAPTION);

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return "";

	}

	public String getWebb() {
		try {
			return info.getJSONArray(WEBB).getString(0).toString();
		} catch (JSONException e) {
			return "";

		}

	}

	public String getName() {
		try {
			return json.getString(TITLE);
		} catch (JSONException e) {
			return "";
		}
	}
	

	public void setFavvo(int value) {
		ApplicationClass appClass = (ApplicationClass) ApplicationClass.getApp();

		appClass.setFavvo(getId(), value);
	}

	public boolean isFavvo() {
		ApplicationClass appClass = (ApplicationClass) ApplicationClass.getApp();

		int isFavvoInt = appClass.isFavvo(getId());
		
		if(isFavvoInt==0){
			return false;
		}
		return true;
	}

	public ArrayList<String> getBranch() {
		ArrayList<String> branch = new ArrayList<String>();
		for (int i = 0; i < brancher.length(); i++) {
			try {
				branch.add(brancher.getJSONObject(i).getString(TITLE));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return branch;
	}

	public ArrayList<String> getLocation() {
		ArrayList<String> location = new ArrayList<String>();
		for (int i = 0; i < orter.length(); i++) {
			try {
				location.add(orter.getJSONObject(i).getString(TITLE));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return location;

	}

	public String getFb() {
		JSONObject obj;
		JSONArray ara;
		try {
			obj = json.getJSONObject(CUSTOM);
			ara = obj.getJSONArray(FACEBOOK);
			return ara.getString(0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public String getTw() {
		JSONObject obj;
		JSONArray ara;
		try {
			obj = json.getJSONObject(CUSTOM);
			ara = obj.getJSONArray(TWITTER);
			return ara.getString(0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public String getLi() {
		JSONObject obj;
		JSONArray ara;
		try {
			obj = json.getJSONObject(CUSTOM);
			ara = obj.getJSONArray(LINKEDIN);
			return ara.getString(0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public String getYo() {
		JSONObject obj;
		JSONArray ara;
		try {
			obj = json.getJSONObject(CUSTOM);
			ara = obj.getJSONArray(YOUTUBE);
			return ara.getString(0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
