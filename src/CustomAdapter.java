package com.tlth.arkad13;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter {
	private final Activity activity;
	private final ArrayList<Post> list;

	public CustomAdapter(Activity activity, int resource, ArrayList<Post> list) {
		super(activity, resource, list);
		this.activity = activity;
		this.list = list;
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ViewHolder view;

		if(rowView == null)
		{
			// Get a new instance of the row layout view
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.posts_list_item, null);

			// Hold the view objects in an object, that way the don't need to be "re-  finded"
			view = new ViewHolder();
			view.headLine= (TextView) rowView.findViewById(R.id.product_name);
			view.date= (TextView) rowView.findViewById(R.id.date);


			rowView.setTag(view);
		} else {
			view = (ViewHolder) rowView.getTag();
		}

		/** Set data to your Views. */
		Post item = list.get(position);
		view.headLine.setText(item.getName());
		view.date.setText(item.getDate());


		return rowView;
	}

	protected static class ViewHolder{
		protected TextView headLine;
		protected TextView date;
	}
}
