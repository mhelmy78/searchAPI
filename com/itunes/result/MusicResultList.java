package com.itunes.result;

import java.util.List;

public class MusicResultList {
	private int resultCount;
	private List<MusicSearchResult> results;
	
	public int getResultCount() {
		return resultCount;
	}
	
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
	
	public List<MusicSearchResult> getResults() {
		return results;
	}
	
	public void setResults(List<MusicSearchResult> results) {
		this.results = results;
	}
}
