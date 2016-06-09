package com.tlth.arkad13;

import org.json.JSONArray;
import org.json.JSONObject;


public class SponsorImage {
	private static final String ATTACH = "attachments";
	private static final String IMAGES = "images";
	private static final String THUMBNAIL = "medium";
	private static final String URL = "url";


	private JSONObject json;
	
	public SponsorImage(JSONObject json){
		this.json = json;
		}
	
	public String getImage(){
		try{
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
	
	

}
