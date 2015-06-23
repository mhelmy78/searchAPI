package com.itunes.parameter;

import java.util.ArrayList;
import java.util.List;

public class ItunesSearchParams {
	private List<String> terms = new ArrayList<>();
	private String country = "US";
	private String media = ItunesMedia.ALL.toString();
	private String limit = "50";

	public void insertTerm(String queryTerm) {
		terms.add(queryTerm);
	}
	
	public List<String> getTerms() {
		return terms;
	}
	
	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getMedia() {
		return media;
	}

	public void setMedia(ItunesMedia media) {
		this.media = media.toString();
	}
	
	public void setMedia(String media) {
		this.media = media;
	}

}
