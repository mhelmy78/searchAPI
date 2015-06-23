package com.itunes.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itunes.parameter.ItunesSearchParams;
import com.itunes.parameter.ItunesTerm;
import com.itunes.result.MusicResultList;
import com.google.gson.Gson;

public class SearchRequest {
	
	private static final String itunesUrl = "https://itunes.apple.com/search?";
	private static final Logger logger = Logger.getLogger(SearchRequest.class.getName());
	
	public static MusicResultList search(ItunesSearchParams params) {
		URL url;
		url = createUrl(itunesUrl, buildSearchParams(params));
		HttpURLConnection httpConn = openConnection(url);
		return parseResponseData(readResponse(httpConn));
	}
	
	private static HttpURLConnection openConnection(URL url) {
		HttpURLConnection httpConn = null;
		try {
			httpConn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			logger.log(Level.ALL, e.getMessage(), e);
			
		}
		return httpConn;
	}
	
	private static MusicResultList parseResponseData(String data) {
		Gson gson = new Gson();
		return gson.fromJson(data, MusicResultList.class);
	}
	
	private static String readResponse(HttpURLConnection httpConn) {
		return readDataFromResponseStream(createResponseReader(httpConn));
	}
	
	private static String readDataFromResponseStream(BufferedReader reader) {
		StringBuilder data = new StringBuilder();
		String line;
		
		try {
			while ((line = reader.readLine()) != null) {
				data.append(line);
			}
			reader.close();
		} catch (IOException e) {
			logger.log(Level.ALL, e.getMessage(), e);
	
		}
		
		return data.toString();
	}
	
	private static BufferedReader createResponseReader(HttpURLConnection httpConn) {
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream()));
		} catch (IOException e) {
			logger.log(Level.ALL, e.getMessage(), e);
		}
		return reader;
	}
	
	private static URL createUrl(String urlStr, String params) {
		URL url = null;
		
		try {
			url = new URL(urlStr + params);
		} catch (MalformedURLException e) {
			logger.log(Level.ALL, e.getMessage(), e);
		}
		
		return url;
	}
	
	public static String buildSearchParams(ItunesSearchParams params) {
		StringBuilder query = new StringBuilder();
		query.append(new ItunesTerm(params.getTerms()).makeSearchParam());
		
		if(params.getCountry() != null) {
			query.append("&country=" + params.getCountry());
		}
		
		if(params.getMedia() != null) {
			query.append("&media=" + params.getMedia().toString());
		}
		
		if(params.getLimit() != null) {
			query.append("&limit=" + params.getLimit());
		}
		
		return query.toString();
	}

}
