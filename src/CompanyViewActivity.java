package com.tlth.arkad13;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class CompanyViewActivity extends Activity {
	private CheckBox c;
	private Companies h;
	private ImageView fb;
	private ImageView tw;
	private ImageView st;
	private ImageView li;
	private ImageView you;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.company_view);
		
		ApplicationClass appClass = ((ApplicationClass) getApplicationContext());
		h = appClass.getHead();

		TextView txtProduct = (TextView) findViewById(R.id.product_label);
		TextView txtCont = (TextView) findViewById(R.id.content);
		TextView txtImage = (TextView) findViewById(R.id.imageText);

		
	
		fb = (ImageView) findViewById(R.id.facebook);
		tw = (ImageView) findViewById(R.id.twitter);
		st = (ImageView) findViewById(R.id.student);
		li = (ImageView) findViewById(R.id.linked);
		you = (ImageView) findViewById(R.id.youtube);

		Intent i = getIntent();
		// getting attached intent data
		final String product = i.getStringExtra("companies");
		
		
		Company c2 = h.getCompanyByName(product);
		



		c = (CheckBox) findViewById(R.id.checkBox1);
		boolean favvo = i.getBooleanExtra("favvo", false);

		//final int nbr = i.getIntExtra("position", 0);
		//final int nbr = h.indexOf(c2.getName());


		
		new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
        .execute(c2.getMap());

		if(c2.getFb().equals("")){
			changeFb();
		}
		if(c2.getTw().equals("")){
			changeTw();
		}
		if(c2.getLi().equals("")){
			changeLi();
		}
		if(c2.getYo().equals("")){
			changeYo();
		}
		if(c2.getWebb().equals("")){
			changeWebb();
		}
		
		

		// displaying selected product name
		txtProduct.setText(product);
		txtCont.setText(c2.getContent());
		txtImage.setText(c2.getImageTxt());

		fb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Company c1 = h.getCompanyByName(product);
				String link = c1.getFb();
				if (link.equals("")) {
				} else {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(Html.fromHtml(link).toString()));
					startActivity(browserIntent);
				}
			}

		});
		

		tw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Company c1 = h.getCompanyByName(product);
				String link = c1.getTw();
				if (link.equals("")) {
				} else {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(Html.fromHtml(link).toString()));
					startActivity(browserIntent);
				}
			}

		});
		

		you.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Company c1 = h.getCompanyByName(product);
				String link = c1.getYo();
				if (link.equals("")) {
				} else {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(Html.fromHtml(link).toString()));
					startActivity(browserIntent);
				}
			}

		});
		

		st.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Company c1 = h.getCompanyByName(product);
				String link = c1.getWebb();
				if (link.equals("")) {
				} else {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(Html.fromHtml(link).toString()));
					startActivity(browserIntent);
				}
			}

		});
		

		li.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Company c1 = h.getCompanyByName(product);
				String link = c1.getLi();
				if (link.equals("")) {
				} else {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(Html.fromHtml(link).toString()));
					startActivity(browserIntent);
				}
			}

		});





		c.setChecked(c2.isFavvo());
	      
		c.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// Tar ut company som ska göras till favourit och sätter in dem
				// på rätt plats som favouriter
				Company c1 = h.getCompanyByName(product);
				int nbr = h.indexOf(c1);
				

				// Gör det snyggare

				if (c.isChecked()) {
					if (!c1.isFavvo()) {
						h.remove(nbr);
						c1.setFavvo(1);
						h.add(nbr, c1);
					}
				}
				if (!c.isChecked()) {
					if (c1.isFavvo()) {
						h.remove(nbr);
						c1.setFavvo(0);
						h.add(nbr, c1);
					}
				}

			}
		});
		
	      

	}

	private void changeFb() {
		fb.setImageResource(R.drawable.facebook);

	}
	private void changeTw() {
		tw.setImageResource(R.drawable.twitter);

	}
	private void changeYo() {
		you.setImageResource(R.drawable.youtube);

	}
	private void changeWebb() {
		st.setImageResource(R.drawable.untitled2);

	}
	private void changeLi() {
		li.setImageResource(R.drawable.linkedin);

	}
}