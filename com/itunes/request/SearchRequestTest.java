package com.itunes.request;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Test;

import com.itunes.parameter.ItunesMedia;
import com.itunes.parameter.ItunesSearchParams;
import com.itunes.result.MusicResultList;

public class SearchRequestTest {

	private static Logger logger = Logger.getGlobal();
	
	@Test
	public void searchByArtistName()  {	
		ItunesSearchParams searchParams = createTerms("jack", "johnson");
		searchParams.setMedia(ItunesMedia.MUSIC);
		searchParams.setLimit("200");
		
		MusicResultList results = SearchRequest.search(searchParams);
		
		String artistName = results.getResults().get(0).getArtistName();
		logger.info("Found " + results.getResultCount() + " results "  + "for " +artistName);
		
		assertEquals("Jack Johnson", results.getResults().get(0).getArtistName());
		assertEquals(200, results.getResultCount());
		
	}
	
	@Test
	public void searchByArtistNameWithDefs()  {
		ItunesSearchParams searchParams = createTerms("jack", "johnson");
		
		String media = null;
		searchParams.setMedia(media);
		
		searchParams.setLimit(null);
		searchParams.setCountry(null);
		
		MusicResultList results = SearchRequest.search(searchParams);
		
		String artistName = results.getResults().get(0).getArtistName();
		logger.info("Found " + results.getResultCount() + " results "  + "for " +artistName);
		
		assertEquals("Jack Johnson", results.getResults().get(0).getArtistName());
		assertEquals(50, results.getResultCount());
		
	}
	
	@Test
	public void searchByArtistInvalidCountry()  {
		ItunesSearchParams searchParams = createTerms("jack", "johnson");
		searchParams.setCountry("AUS");
		
		MusicResultList results = null;
		
		try {
			results = SearchRequest.search(searchParams);
		} catch (NullPointerException exception) {
		}	
		
		assertNull(results);	
	}
	
	@Test
	public void searchByArtistInvalidMedia()  {
		ItunesSearchParams searchParams = createTerms("jack", "johnson");
		
		searchParams.setMedia("CD");
		
		MusicResultList results = null;
		
		try {
			results = SearchRequest.search(searchParams);
		} catch (NullPointerException exception) {
		}	
		
		assertNull(results);	
	}
	
	@Test
	public void searchByInvalidArtist()  {	
		ItunesSearchParams searchParams = createTerms("jck", "johnson");
		
		MusicResultList results = SearchRequest.search(searchParams);
		
		assertEquals(0, results.getResultCount());
	}
	
	@Test
	public void searchByArtistAndAlbum()  {	
		ItunesSearchParams searchParams = createTerms("jack", "johnson", "brushfire", "fairytales");
		searchParams.setMedia(ItunesMedia.MUSIC);
		searchParams.setLimit("1");
		
		MusicResultList results = SearchRequest.search(searchParams);
		
		String artistName = results.getResults().get(0).getArtistName();
		logger.info("Found " + results.getResultCount() + " results "  + "for " 
		+artistName + " and brushfire fairytales");
		
		assertEquals("Jack Johnson", results.getResults().get(0).getArtistName());
		assertTrue("Album does not contain Brushfire Fairytales as expected", 
				results.getResults().get(0).getCollectionName().contains("Brushfire Fairytales"));
		assertEquals(1, results.getResultCount());
		
	}
	
	@Test
	public void searchByArtistAndTrack()  {
		ItunesSearchParams searchParams = createTerms("jack", "johnson", "banana", "pancakes");
		searchParams.setMedia(ItunesMedia.MUSIC);
		searchParams.setLimit("1");
		
		MusicResultList results = SearchRequest.search(searchParams);
		
		String artistName = results.getResults().get(0).getArtistName();
		String trackName =  results.getResults().get(0).getTrackName();
		logger.info("Found " + results.getResultCount() + " results "  + "for " 
		+artistName + " and " +trackName);
		
		assertEquals("Jack Johnson", results.getResults().get(0).getArtistName());
		assertEquals("Banana Pancakes", trackName);
		assertEquals(1, results.getResultCount());
		
	}
	
	@Test
	public void searchByArtistAndCountry()  {
		ItunesSearchParams searchParams = createTerms("the", "rolling", "stones");
		searchParams.setMedia(ItunesMedia.MUSIC);
		searchParams.setLimit("100");
		searchParams.setCountry("CA");
		
		MusicResultList results = SearchRequest.search(searchParams);
		
		String artistName = results.getResults().get(0).getArtistName();
		logger.info("Found " + results.getResultCount() + " results "  + "for " +artistName);
		
		assertEquals("The Rolling Stones", results.getResults().get(0).getArtistName());
		assertEquals(100, results.getResultCount());
		
	}
	
	private ItunesSearchParams createTerms(String... terms) {
		ItunesSearchParams searchParams = new ItunesSearchParams();
		for (String term : terms) {
			searchParams.insertTerm(term);
		}
		
		return searchParams;
		}

}
