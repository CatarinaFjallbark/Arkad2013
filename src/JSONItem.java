package com.tlth.arkad13;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

public abstract class JSONItem{
	private static final String ID = "id";
	private static final String CONTENT = "content";	
	protected JSONObject json;


	public JSONItem(JSONObject json){
		this.json = json;
	}

	
	public int getId() {
		try {
			return json.getInt(ID);
		} catch (JSONException e) {
			return -1;
		}
	}
	
	public String getContent() {
		try {
			String s = json.getString(CONTENT).toString();
			
			return removeImageSpanObjects(s).toString();
		} catch (JSONException e) {
			return "";
		}
	}
	
	public static Spanned removeImageSpanObjects(String inStr) {
	    SpannableStringBuilder spannedStr = (SpannableStringBuilder) Html
	            .fromHtml(inStr.trim());
	    Object[] spannedObjects = spannedStr.getSpans(0, spannedStr.length(),
	            Object.class);
	    for (int i = 0; i < spannedObjects.length; i++) {
	        if (spannedObjects[i] instanceof ImageSpan) {
	            ImageSpan imageSpan = (ImageSpan) spannedObjects[i];
	            spannedStr.replace(spannedStr.getSpanStart(imageSpan),
	                    spannedStr.getSpanEnd(imageSpan), "");
	        }
	    }
	    return spannedStr;
	}

}
