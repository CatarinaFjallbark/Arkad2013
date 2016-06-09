package com.tlth.arkad13;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<SponsorImage> sponsorList;


	public ImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return sponsorList.size();
	}

	public void setList(ArrayList<SponsorImage> si){
		sponsorList = si;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		//  if (convertView == null) {  // if it's not recycled, initialize some attributes
		imageView = new ImageView(mContext);
		imageView.setOnClickListener(null);
		Resources r = Resources.getSystem();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());


		imageView.setLayoutParams(new GridView.LayoutParams((int)px, (int)px));
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		// imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
		/*   } else {
            imageView = (ImageView) convertView;
        }*/

		//imageView.setImageResource(mThumbIds[position]);
		new DownloadImageTask(imageView).execute(Html.fromHtml(sponsorList.get(position).getImage()).toString());
		return imageView;
	}

}