package com.itunes.parameter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItunesTerm {
	private final static String paramKey = "term=";
	private List<String> termList;
	private final static Logger logger = Logger.getAnonymousLogger();
	
	public ItunesTerm(List<String> termList) {
		this.termList = termList;
	}
	
	private String buildQueryString(List<String> termList) {
		final StringBuilder queryStr = new StringBuilder();
		for (String queryValue : termList) {
			queryStr.append(queryValue).append(" ");
		}
		return queryStr.toString();
	}
	
	
	public String makeSearchParam() {
		String query = buildQueryString(termList);
		
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
		
		return paramKey + query;
	}
}
