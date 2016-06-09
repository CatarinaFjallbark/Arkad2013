package com.tlth.arkad13;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Companies extends ArrayList<Company> {
	private static final String URL = "http://arkad.tlth.se/api/get_posts/?postType=webbkatalog";
	private static final String COMPANYS = "posts";

	public void loadData() throws TimeoutException, Exception {
		JSONObject result = new JSONDownloader().execute(URL).get(20L,
				TimeUnit.SECONDS);

		JSONArray companys = result.getJSONArray(COMPANYS);

		for (int i = 0; i < companys.length(); i++) {
			Company c = new Company(companys.getJSONObject(i));
			c.setFavvo(-1);
			add(c);
			
		}

	}

	public ArrayList<String> stringList() {
		ArrayList<String> stringList = new ArrayList<String>();
		for (Company c : this) {
			stringList.add(c.getName());
		}
		return stringList;
	}

	public Company getCompanyByName(Object name) {
		for (Company c : this) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return this.get(0);
	}

	public int getPosByCompany(Object c) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).equals(c)) {
				return i;
			}
		}
		return -1;
	}

	public ArrayList<Company> getFavvoComp() {
		ArrayList<Company> favorites = new ArrayList<Company>();
		for (Company c : this) {
			if (c.isFavvo() && !favorites.contains(c)) {
				favorites.add(c);
			}
		}
		
		return favorites;
	}

	public ArrayList<String> getFavvoStrings() {
		ArrayList<String> favvoString = new ArrayList<String>();
		for (Company c : getFavvoComp()) {
			favvoString.add(c.getName());
		}
		return favvoString;
	}

	public ArrayList<String> getBranches() {
		ArrayList<String> branchString = new ArrayList<String>();
		branchString.add("Alla branscher");
		for (Company c : this) {
			for (int i = 0; i < c.getBranch().size(); i++) {
				String temp = c.getBranch().get(i);
				if (!branchString.contains(temp)) {
					branchString.add(temp);
				}
			}
		}
		return branchString;

	}

	public ArrayList<String> getLocations() {
		ArrayList<String> locationString = new ArrayList<String>();
		locationString.add("Alla");

		for (Company c : this) {
			for (int i = 0; i < c.getLocation().size(); i++) {
				String temp = c.getLocation().get(i);
				if (!locationString.contains(temp)) {
					locationString.add(temp);
				}
			}
		}
		return locationString;

	}

	public ArrayList<String> getStringFilterB(String s) {

		ArrayList<String> filtered = new ArrayList<String>();

		for (Company c : this) {
			for (int i = 0; i < c.getBranch().size(); i++) {
				String temp = c.getBranch().get(i);
				if (temp.equals(s) && !filtered.contains(c.getName())) {
					filtered.add(c.getName());
				}
			}
		}
		return filtered;

	}

	public ArrayList<String> getStringFilterO(String s) {

		ArrayList<String> filtered = new ArrayList<String>();

		for (Company c : this) {
			for (int i = 0; i < c.getLocation().size(); i++) {
				String temp = c.getLocation().get(i);
				if (temp.equals(s) && !filtered.contains(c.getName())) {
					filtered.add(c.getName());
				}
			}
		}
		return filtered;

	}

}